package tecnm.itch.reservaciones.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "mesa")
public class Mesa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_mesa")
	private Integer id_mesa;
	@Column(name = "numero")
	private Integer numero;
	@Column(name = "capacidad")
	private Integer capacidad;
	@Column(name = "ubicacion")
	private String ubicacion;
}
