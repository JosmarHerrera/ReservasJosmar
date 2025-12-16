package tecnm.itch.reservaciones.mapper;

import tecnm.itch.reservaciones.dto.MesaDto;
import tecnm.itch.reservaciones.entity.Mesa;

public class MesaMapper {
	public static MesaDto toDTO(Mesa mesa) {
		if (mesa == null)
			return null;
		return new MesaDto(mesa.getId_mesa(), mesa.getNumero(), mesa.getCapacidad(), mesa.getUbicacion());
	}

	public static Mesa toEntity(MesaDto dto) {
		if (dto == null)
			return null;
		return new Mesa(dto.getId_mesa(), dto.getNumero(), dto.getCapacidad(), dto.getUbicacion());
	}
}
