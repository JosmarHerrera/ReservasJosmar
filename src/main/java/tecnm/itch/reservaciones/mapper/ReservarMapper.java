package tecnm.itch.reservaciones.mapper;

import tecnm.itch.reservaciones.dto.ReservarDto;
import tecnm.itch.reservaciones.entity.Mesa;
import tecnm.itch.reservaciones.entity.Reservar;

public class ReservarMapper {

	// De DTO a Entidad
	public static Reservar toEntity(ReservarDto dto, Mesa mesa) {
		if (dto == null)
			return null;
		Reservar r = new Reservar();
		r.setId_reserva(dto.getId_reserva());
		r.setFecha(dto.getFecha());
		r.setHora(dto.getHora());
		r.setId_cliente(dto.getId_cliente());
		r.setMesa(mesa);
		r.setEstatus(dto.getEstatus() != null ? dto.getEstatus() : "PENDIENTE");
		return r;
	}

	// De Entidad a DTO
	public static ReservarDto toDto(Reservar r) {
		if (r == null)
			return null;

		ReservarDto dto = new ReservarDto();
		dto.setId_reserva(r.getId_reserva());
		dto.setFecha(r.getFecha());
		dto.setHora(r.getHora());
		dto.setId_cliente(r.getId_cliente());
		dto.setEstatus(r.getEstatus());

		if (r.getMesa() != null) {
			dto.setMesa(MesaMapper.toDTO(r.getMesa()));
		}
		return dto;
	}
}
