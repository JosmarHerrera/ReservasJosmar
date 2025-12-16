package tecnm.itch.reservaciones.service;

import java.time.LocalDate;
import java.util.List;

import tecnm.itch.reservaciones.dto.MesaDto;

public interface MesaService {
	MesaDto crearMesa(MesaDto mesaDto);

	List<MesaDto> listarMesas();

	MesaDto obtenerPorId(Integer id);

	MesaDto actualizarMesa(MesaDto mesaDto);

	void eliminarMesa(Integer id);

	List<MesaDto> listarMesasDisponibles(LocalDate fecha);
}
