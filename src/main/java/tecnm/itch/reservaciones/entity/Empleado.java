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
@Table(name = "empleado")
public class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empleado")
	private Integer id_empleado;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "puesto")
	private String puesto; // cocinero, mesero, cajero
	@Column(name = "estatus")
	private int estatus = 1;

	@Column(name = "id_usuario")
	private Integer id_usuario;
}
