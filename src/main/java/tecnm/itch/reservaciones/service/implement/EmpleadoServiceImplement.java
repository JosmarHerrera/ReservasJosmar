package tecnm.itch.reservaciones.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tecnm.itch.reservaciones.dto.EmpleadoDto;
import tecnm.itch.reservaciones.entity.Empleado;
import tecnm.itch.reservaciones.mapper.EmpleadoMapper;
import tecnm.itch.reservaciones.repository.EmpleadoRepository;
import tecnm.itch.reservaciones.service.EmpleadoService;

@Service
public class EmpleadoServiceImplement implements EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;

	@Override
	public EmpleadoDto crearEmpleado(EmpleadoDto empleadoDto) {
		Empleado empleado = EmpleadoMapper.toEntity(empleadoDto);
		Empleado savedEmpleado = empleadoRepository.save(empleado);
		return EmpleadoMapper.toDTO(savedEmpleado);
	}

	@Override
	public List<EmpleadoDto> listarEmpleados() {
		List<Empleado> empleadosActivos = empleadoRepository.findByEstatus(1);
		return empleadosActivos.stream().map(EmpleadoMapper::toDTO).collect(Collectors.toList());
	}

	@Override
	public EmpleadoDto obtenerPorId(Integer id) {
		Empleado empleado = empleadoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Empleado no encontrado con id: " + id));
		return EmpleadoMapper.toDTO(empleado);
	}

	@Override
	public EmpleadoDto actualizarEmpleado(EmpleadoDto empleadoDto) {
		Empleado existingEmpleado = empleadoRepository.findById(empleadoDto.getId_empleado()).orElseThrow(
				() -> new RuntimeException("Empleado no encontrado con id: " + empleadoDto.getId_empleado()));

		existingEmpleado.setNombre(empleadoDto.getNombre());
		existingEmpleado.setPuesto(empleadoDto.getPuesto());

		Empleado updatedEmpleado = empleadoRepository.save(existingEmpleado);
		return EmpleadoMapper.toDTO(updatedEmpleado);
	}

	@Override
	public void eliminarEmpleado(Integer id) {
		Empleado empleado = empleadoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Empleado no encontrado con id: " + id));

		empleado.setEstatus(0); // Marcar como inactivo
		empleadoRepository.save(empleado); // Guardar el cambio
	}

	@Override
	public List<EmpleadoDto> listarMeserosActivos() {
		return empleadoRepository.findByPuestoIgnoreCaseAndEstatus("Mesero", 1).stream().map(EmpleadoMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public void vincularUsuario(int idEmpleado, int idUsuario) {
		// 1. Busca el Empleado de la BD
		Empleado empleado = empleadoRepository.findById(idEmpleado)
				.orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + idEmpleado));

		// 2. Actualiza solo el campo id_usuario
		empleado.setId_usuario(idUsuario);

		// 3. Guarda la entidad actualizada
		empleadoRepository.save(empleado);
	}
	
	@Override
	public EmpleadoDto obtenerPorUsuario(Integer idUsuario) {
	    Empleado empleado = empleadoRepository.findByIdUsuario(idUsuario)
	        .orElseThrow(() -> new RuntimeException("Empleado no encontrado con idUsuario: " + idUsuario));
	    return EmpleadoMapper.toDTO(empleado);
	}



}
