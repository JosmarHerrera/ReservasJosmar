package tecnm.itch.reservaciones.service.implement;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tecnm.itch.reservaciones.dto.MesaDto;
import tecnm.itch.reservaciones.entity.Mesa;
import tecnm.itch.reservaciones.mapper.MesaMapper;
import tecnm.itch.reservaciones.repository.MesaRepository;
import tecnm.itch.reservaciones.service.MesaService;

@Service
public class MesaServiceImplement implements MesaService {

	@Autowired
	private MesaRepository mesaRepository;

	@Override
	public MesaDto crearMesa(MesaDto mesaDto) {
		mesaRepository.findByNumeroAndUbicacion(mesaDto.getNumero(), mesaDto.getUbicacion()).ifPresent(m -> {
			throw new IllegalArgumentException("Ya existe una mesa con el número " + m.getNumero()
					+ " en la ubicación '" + m.getUbicacion() + "'.");
		});

		Mesa mesa = MesaMapper.toEntity(mesaDto);
		Mesa nuevaMesa = mesaRepository.save(mesa);
		return MesaMapper.toDTO(nuevaMesa);
	}

	@Override
	public List<MesaDto> listarMesas() {
		List<Mesa> mesas = mesaRepository.findAll();
		return mesas.stream().map(MesaMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public MesaDto obtenerPorId(Integer id) {
		Mesa mesa = mesaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Mesa no encontrada con id: " + id));
		return MesaMapper.toDTO(mesa);
	}

	@Override
	public MesaDto actualizarMesa(MesaDto mesaDto) {
		Optional<Mesa> mesaExistente = mesaRepository.findByNumeroAndUbicacion(mesaDto.getNumero(),
				mesaDto.getUbicacion());

		if (mesaExistente.isPresent() && !mesaExistente.get().getId_mesa().equals(mesaDto.getId_mesa())) {
			throw new IllegalArgumentException("Ya existe otra mesa con el número " + mesaDto.getNumero()
					+ " en la ubicación '" + mesaDto.getUbicacion() + "'.");
		}

		Mesa mesa = MesaMapper.toEntity(mesaDto);
		Mesa updatedMesa = mesaRepository.save(mesa);
		return MesaMapper.toDTO(updatedMesa);
	}

	@Override
	public void eliminarMesa(Integer id) {
		Mesa mesa = mesaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Mesa no encontrada con id: " + id));
		mesaRepository.delete(mesa);
	}

	@Override
	public List<MesaDto> listarMesasDisponibles(LocalDate fecha) {
		if (fecha == null) {
			return List.of();
		}

		List<Mesa> mesasDisponibles = mesaRepository.findMesasDisponiblesPorFecha(fecha);
		return mesasDisponibles.stream().map(MesaMapper::toDTO).collect(Collectors.toList());
	}
}
