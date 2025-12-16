package tecnm.itch.reservaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MesaDto {
	private Integer id_mesa;
	private Integer numero;
	private Integer capacidad;
	private String ubicacion;
}
