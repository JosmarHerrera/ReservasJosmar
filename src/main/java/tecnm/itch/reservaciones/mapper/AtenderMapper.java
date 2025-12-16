package tecnm.itch.reservaciones.mapper;

import tecnm.itch.reservaciones.dto.AtenderDto;
import tecnm.itch.reservaciones.entity.Atender;
import tecnm.itch.reservaciones.entity.Empleado;

public class AtenderMapper {
	// De DTO a Entidad
	public static Atender toEntity(AtenderDto dto, Empleado empleado) {
		if (dto == null)
			return null;

		Atender atender = new Atender();
		atender.setEmpleado(empleado);
		atender.setIdVenta(dto.getIdVenta());

		return atender;
	}

	// De Entidad a DTO
	public static AtenderDto toDto(Atender atender) {
		if (atender == null)
			return null;

		return new AtenderDto(atender.getIdAtender(), atender.getEmpleado().getId_empleado(), atender.getIdVenta());
	}
}