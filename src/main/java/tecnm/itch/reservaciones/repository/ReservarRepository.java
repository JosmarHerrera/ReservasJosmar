package tecnm.itch.reservaciones.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tecnm.itch.reservaciones.entity.Reservar;

public interface ReservarRepository extends JpaRepository<Reservar, Integer> {
	@Query("SELECT r FROM Reservar r WHERE r.mesa.id_mesa = :idMesa AND r.fecha = :fecha AND r.estatus IN :estatus")
	List<Reservar> findByMesa_Id_mesaAndFechaAndEstatusIn(@Param("idMesa") Integer idMesa,
			@Param("fecha") LocalDate fecha, @Param("estatus") List<String> estatus);

	List<Reservar> findByFecha(LocalDate fecha);
}
