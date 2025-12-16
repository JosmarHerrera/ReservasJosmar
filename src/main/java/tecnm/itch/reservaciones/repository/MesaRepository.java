package tecnm.itch.reservaciones.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tecnm.itch.reservaciones.entity.Mesa;

public interface MesaRepository extends JpaRepository<Mesa, Integer> {
	Optional<Mesa> findByNumeroAndUbicacion(Integer numero, String ubicacion);

	@Query("SELECT m FROM Mesa m WHERE m.id_mesa NOT IN (" + "SELECT r.mesa.id_mesa FROM Reservar r "
			+ "WHERE r.fecha = :fecha AND r.estatus IN ('PENDIENTE', 'CONFIRMADA')" + ")")
	List<Mesa> findMesasDisponiblesPorFecha(@Param("fecha") LocalDate fecha);
}
