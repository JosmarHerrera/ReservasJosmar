package tecnm.itch.reservaciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tecnm.itch.reservaciones.dto.EmpleadoDto;
import tecnm.itch.reservaciones.service.EmpleadoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    // Crear un empleado
    @PostMapping
    public ResponseEntity<EmpleadoDto> crearEmpleado(@RequestBody EmpleadoDto empleadoDto) {
        EmpleadoDto nuevoEmpleado = empleadoService.crearEmpleado(empleadoDto);
        return ResponseEntity.ok(nuevoEmpleado);
    }

    // Listar todos los empleados
    @GetMapping
    public ResponseEntity<List<EmpleadoDto>> listarEmpleados() {
        List<EmpleadoDto> empleados = empleadoService.listarEmpleados();
        return ResponseEntity.ok(empleados);
    }

    // 🔹 Listar solo meseros activos
    @GetMapping("/meseros")
    public ResponseEntity<List<EmpleadoDto>> listarMeseros() {
        List<EmpleadoDto> meseros = empleadoService.listarMeserosActivos();
        return ResponseEntity.ok(meseros);
    }

    // Obtener un empleado por ID
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDto> obtenerEmpleado(@PathVariable Integer id) {
        EmpleadoDto empleado = empleadoService.obtenerPorId(id);
        return ResponseEntity.ok(empleado);
    }

    // 🔹 Nuevo: obtener empleado por idUsuario (para tickets / ventas)
    @GetMapping("/por-usuario/{idUsuario}")
    public ResponseEntity<EmpleadoDto> obtenerEmpleadoPorUsuario(@PathVariable Integer idUsuario) {
        EmpleadoDto empleado = empleadoService.obtenerPorUsuario(idUsuario);
        return ResponseEntity.ok(empleado);
    }

    // Actualizar un empleado
    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDto> actualizarEmpleado(@PathVariable Integer id,
                                                          @RequestBody EmpleadoDto empleadoDto) {
        empleadoDto.setId_empleado(id);
        EmpleadoDto updatedEmpleado = empleadoService.actualizarEmpleado(empleadoDto);
        return ResponseEntity.ok(updatedEmpleado);
    }

    // Eliminar un empleado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Integer id) {
        empleadoService.eliminarEmpleado(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idEmpleado}/vincular-usuario/{idUsuario}")
    public ResponseEntity<?> vincularUsuario(@PathVariable("idEmpleado") int idEmpleado,
                                             @PathVariable("idUsuario") int idUsuario) {
        empleadoService.vincularUsuario(idEmpleado, idUsuario);
        return ResponseEntity.ok("Empleado vinculado exitosamente.");
    }
}
