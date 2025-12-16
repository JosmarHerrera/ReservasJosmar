package tecnm.itch.reservaciones.service;

import java.util.List;

import tecnm.itch.reservaciones.dto.AtenderDto;
import tecnm.itch.reservaciones.dto.EmpleadoDto;

public interface AtenderService {
	AtenderDto crearAtender(AtenderDto dto);

	List<AtenderDto> listarAtenciones();

	AtenderDto obtenerPorId(Integer id);

	void eliminarAtender(Integer id);

	EmpleadoDto obtenerEmpleadoPorVenta(Integer idVenta);
}
