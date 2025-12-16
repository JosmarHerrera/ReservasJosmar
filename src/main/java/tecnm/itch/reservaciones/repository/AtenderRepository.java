package tecnm.itch.reservaciones.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tecnm.itch.reservaciones.entity.Atender;

public interface AtenderRepository extends JpaRepository<Atender, Integer> {
	Optional<Atender> findByIdVenta(Integer idVenta);
}
