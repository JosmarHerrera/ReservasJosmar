package tecnm.itch.reservaciones.service;

import java.util.List;

import tecnm.itch.reservaciones.dto.EmpleadoDto;

public interface EmpleadoService {
	EmpleadoDto crearEmpleado(EmpleadoDto empleadoDto);

	List<EmpleadoDto> listarEmpleados();

	EmpleadoDto obtenerPorId(Integer id);

	EmpleadoDto actualizarEmpleado(EmpleadoDto empleadoDto);

	void eliminarEmpleado(Integer id);

	public List<EmpleadoDto> listarMeserosActivos();

	void vincularUsuario(int idEmpleado, int idUsuario);
	
	EmpleadoDto obtenerPorUsuario(Integer idUsuario);

}
