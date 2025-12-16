package tecnm.itch.reservaciones.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import tecnm.itch.reservaciones.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    List<Empleado> findByEstatus(int estatus);
    List<Empleado> findByPuestoIgnoreCaseAndEstatus(String puesto, int estatus);

    @Query("SELECT e FROM Empleado e WHERE e.id_usuario = :idUsuario")
    Optional<Empleado> findByIdUsuario(@Param("idUsuario") Integer idUsuario);
}
