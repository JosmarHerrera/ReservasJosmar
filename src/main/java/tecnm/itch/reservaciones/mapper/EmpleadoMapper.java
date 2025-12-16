package tecnm.itch.reservaciones.mapper;

import tecnm.itch.reservaciones.dto.EmpleadoDto;
import tecnm.itch.reservaciones.entity.Empleado;

public class EmpleadoMapper {

	public static EmpleadoDto toDTO(Empleado empleado) {
		if (empleado == null)
			return null;

		// --- AÑADIR id_usuario AL FINAL ---
		return new EmpleadoDto(empleado.getId_empleado(), empleado.getNombre(), empleado.getPuesto(),
				empleado.getEstatus(), empleado.getId_usuario() // <-- CAMBIO
		);
	}

	public static Empleado toEntity(EmpleadoDto dto) {
		if (dto == null)
			return null;

		// --- AÑADIR id_usuario AL FINAL ---
		return new Empleado(dto.getId_empleado(), dto.getNombre(), dto.getPuesto(), dto.getEstatus(),
				dto.getId_usuario() // <-- CAMBIO
		);
	}
}