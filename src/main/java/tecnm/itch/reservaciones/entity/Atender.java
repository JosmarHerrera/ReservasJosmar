package tecnm.itch.reservaciones.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
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
@Table(name = "atender")
public class Atender {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_atender")
	private Integer idAtender;

	// Relación con Empleado (sí está en este microservicio)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_empleado", nullable = false, foreignKey = @ForeignKey(name = "fk_atender_empleado"))
	private Empleado empleado;

	// Relación con Venta -> solo ID, porque la entidad Venta está en otro
	// microservicio
	@Column(name = "id_venta", nullable = false)
	private Integer idVenta;
}
