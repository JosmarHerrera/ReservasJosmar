package tecnm.itch.reservaciones.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tecnm.itch.reservaciones.ResourceNotFoundException;
import tecnm.itch.reservaciones.dto.AtenderDto;
import tecnm.itch.reservaciones.dto.EmpleadoDto;
import tecnm.itch.reservaciones.entity.Atender;
import tecnm.itch.reservaciones.entity.Empleado;
import tecnm.itch.reservaciones.mapper.AtenderMapper;
import tecnm.itch.reservaciones.repository.AtenderRepository;
import tecnm.itch.reservaciones.repository.EmpleadoRepository;
import tecnm.itch.reservaciones.service.AtenderService;
import tecnm.itch.reservaciones.service.EmpleadoService;

@Service
public class AtenderServiceImplement implements AtenderService {

	@Autowired
	private AtenderRepository atenderRepository;

	@Autowired
	private EmpleadoRepository empleadoRepository;

	@Autowired
	private EmpleadoService empleadoService;

	private static final Logger log = LoggerFactory.getLogger(AtenderServiceImplement.class);

	@Override
	@Transactional
	public AtenderDto crearAtender(AtenderDto dto) {

		log.info("Recibido AtenderDto: id_empleado={}, id_venta={}", dto.getIdEmpleado(), dto.getIdVenta());

		if (dto.getIdEmpleado() == null) {
			throw new IllegalArgumentException("El id_empleado es obligatorio en AtenderDto");
		}
		if (dto.getIdVenta() == null) {
			throw new IllegalArgumentException("El id_venta es obligatorio en AtenderDto");
		}

		Empleado empleado = empleadoRepository.findById(dto.getIdEmpleado()).orElseThrow(
				() -> new ResourceNotFoundException("Empleado no encontrado con id: " + dto.getIdEmpleado()));

		Atender atender = AtenderMapper.toEntity(dto, empleado);

		Atender savedAtender = atenderRepository.save(atender);

		return AtenderMapper.toDto(savedAtender);
	}

	@Override
	@Transactional(readOnly = true) // Marcar como solo lectura
	public List<AtenderDto> listarAtenciones() {
		List<Atender> atenciones = atenderRepository.findAll();
		// ✨ CORRECCIÓN: Simplifica el mapeo, ya no busca Reserva
		return atenciones.stream().map(AtenderMapper::toDto) // Llama al mapper simplificado
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public AtenderDto obtenerPorId(Integer id) {
		Atender atender = atenderRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Atención no encontrada con id: " + id));
		// ✨ CORRECCIÓN: Simplifica el mapeo
		return AtenderMapper.toDto(atender);
	}

	@Override
	@Transactional
	public void eliminarAtender(Integer id) {
		if (!atenderRepository.existsById(id)) {
			throw new ResourceNotFoundException("Atención no encontrada con id: " + id);
		}
		atenderRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public EmpleadoDto obtenerEmpleadoPorVenta(Integer idVenta) {
		Atender atencion = atenderRepository.findByIdVenta(idVenta).orElseThrow(
				() -> new ResourceNotFoundException("No se encontró registro de atención para la venta: " + idVenta));
		Integer idEmpleado = atencion.getEmpleado().getId_empleado();
		return empleadoService.obtenerPorId(idEmpleado);
	}

}
