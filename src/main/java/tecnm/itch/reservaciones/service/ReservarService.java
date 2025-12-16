package tecnm.itch.reservaciones.service;

import java.time.LocalDate;
import java.util.List;

import tecnm.itch.reservaciones.dto.ReservarDto;

public interface ReservarService {
	ReservarDto crearReserva(ReservarDto dto);

	List<ReservarDto> listarReservas(LocalDate fecha);

	ReservarDto obtenerPorId(Integer id);

	void eliminarReserva(Integer id);

	ReservarDto actualizarReserva(Integer id, ReservarDto dto);

	ReservarDto confirmarReserva(Integer id);

	ReservarDto cancelarReserva(Integer id);
}
