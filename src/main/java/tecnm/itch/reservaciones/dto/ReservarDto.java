package tecnm.itch.reservaciones.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservarDto {
	private Integer id_reserva;
	private LocalDate fecha;
	private LocalTime hora;
	private Integer id_cliente;
	private MesaDto mesa;
	private String nombre_cliente;
	private Integer numero_mesa;
	private String estatus;
}
