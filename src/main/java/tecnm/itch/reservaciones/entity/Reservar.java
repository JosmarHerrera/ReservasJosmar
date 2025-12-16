package tecnm.itch.reservaciones.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservar")
public class Reservar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_reserva")
	private Integer id_reserva;

	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;

	@Column(name = "hora", nullable = false)
	private LocalTime hora;

	@Column(name = "id_cliente", nullable = false)
	private Integer id_cliente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_mesa", nullable = false)
	private Mesa mesa;

	@Column(name = "estatus", nullable = false)
	private String estatus = "PENDIENTE";
}
