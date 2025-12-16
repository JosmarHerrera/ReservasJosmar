package tecnm.itch.reservaciones.service.implement;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import feign.FeignException;
import tecnm.itch.reservaciones.ResourceNotFoundException;
import tecnm.itch.reservaciones.clienteClient.ClienteClient;
import tecnm.itch.reservaciones.clienteClient.ClienteClient.ClienteDto;
import tecnm.itch.reservaciones.dto.ReservarDto;
import tecnm.itch.reservaciones.entity.Mesa;
import tecnm.itch.reservaciones.entity.Reservar;
import tecnm.itch.reservaciones.mapper.ReservarMapper;
import tecnm.itch.reservaciones.repository.MesaRepository;
import tecnm.itch.reservaciones.repository.ReservarRepository;
import tecnm.itch.reservaciones.service.ReservarService;

@Service
public class ReservarServiceImplement implements ReservarService {

	@Autowired
	private ReservarRepository reservarRepository;

	@Autowired
	private MesaRepository mesaRepository;

	@Autowired
	private ClienteClient cliente;

	@Override
	public ReservarDto crearReserva(ReservarDto dto) {
		if (dto.getId_cliente() == null) {
			throw new IllegalArgumentException("El id_cliente es obligatorio");
		}
		if (dto.getMesa() == null || dto.getMesa().getId_mesa() == null) {
			throw new IllegalArgumentException("La mesa y su ID son obligatorios");
		}

		try {
			cliente.findById(dto.getId_cliente());
		} catch (FeignException.NotFound nf) {
			throw new ResourceNotFoundException("Cliente no existe: " + dto.getId_cliente());
		} catch (FeignException fe) {
			throw new IllegalStateException("Error al consultar el servicio de Restaurante: " + fe.getMessage());
		}

		List<Reservar> existentes = reservarRepository.findByMesa_Id_mesaAndFechaAndEstatusIn(
				dto.getMesa().getId_mesa(), dto.getFecha(), List.of("PENDIENTE", "CONFIRMADA") // Checa reservas activas
		);

		if (!existentes.isEmpty()) {
			// Si la lista NO está vacía, la mesa ya está reservada.
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					"La mesa seleccionada ya no está disponible en esta fecha.");
		}

		Mesa mesa = mesaRepository.findById(dto.getMesa().getId_mesa()).orElseThrow(
				() -> new ResourceNotFoundException("Mesa no encontrada con id: " + dto.getMesa().getId_mesa()));

		Reservar reserva = ReservarMapper.toEntity(dto, mesa);
		Reservar savedReserva = reservarRepository.save(reserva);

		return ReservarMapper.toDto(savedReserva);
	}

	@Override
	public List<ReservarDto> listarReservas(LocalDate fecha) {
		List<Reservar> reservas;

		if (fecha != null) {
			reservas = reservarRepository.findByFecha(fecha);
		} else {
			reservas = reservarRepository.findAll();
		}

		return reservas.stream().map(reserva -> {
			ReservarDto dto = ReservarMapper.toDto(reserva);
			try {
				ClienteDto clienteDto = cliente.findById(reserva.getId_cliente());
				if (clienteDto != null) {
					dto.setNombre_cliente(clienteDto.getNombreCliente());
				} else {
					dto.setNombre_cliente("Desconocido");
				}
			} catch (FeignException fe) {
				dto.setNombre_cliente("Error al obtener cliente");
			}
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public ReservarDto obtenerPorId(Integer id) {
		Reservar reserva = reservarRepository.findById(id)
				// MEJORA: Usamos la excepción para un error 404.
				.orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con id: " + id));

		ReservarDto dto = ReservarMapper.toDto(reserva);
		// Lógica para llenar el nombre del cliente... (igual que en listar)
		return dto;
	}

	@Override
	public void eliminarReserva(Integer id) {
		Reservar reserva = reservarRepository.findById(id)
				// MEJORA: Usamos la excepción para un error 404.
				.orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con id: " + id));
		reservarRepository.delete(reserva);
	}

	@Override
	public ReservarDto confirmarReserva(Integer id) {
		Reservar reserva = reservarRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con id: " + id));

		// Asignación directa de String
		reserva.setEstatus("CONFIRMADA");
		Reservar reservaActualizada = reservarRepository.save(reserva);
		return ReservarMapper.toDto(reservaActualizada);
	}

	@Override
	public ReservarDto cancelarReserva(Integer id) {
		Reservar reserva = reservarRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con id: " + id));

		// Asignación directa de String
		reserva.setEstatus("CANCELADA");
		Reservar reservaActualizada = reservarRepository.save(reserva);
		return ReservarMapper.toDto(reservaActualizada);
	}

	@Override
	public ReservarDto actualizarReserva(Integer id, ReservarDto dto) {
		Reservar reservaExistente = reservarRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con id: " + id));

		try {
			cliente.findById(dto.getId_cliente());
		} catch (FeignException.NotFound nf) {
			throw new ResourceNotFoundException("Cliente no existe: " + dto.getId_cliente());
		}

		// ✨ CORRECCIÓN: Obtener el ID de la mesa desde el objeto anidado
		Mesa mesa = mesaRepository.findById(dto.getMesa().getId_mesa()).orElseThrow(
				() -> new ResourceNotFoundException("Mesa no encontrada con id: " + dto.getMesa().getId_mesa()));

		reservaExistente.setFecha(dto.getFecha());
		reservaExistente.setHora(dto.getHora());
		reservaExistente.setId_cliente(dto.getId_cliente());
		reservaExistente.setMesa(mesa);

		Reservar reservaActualizada = reservarRepository.save(reservaExistente);

		return ReservarMapper.toDto(reservaActualizada);
	}
}
