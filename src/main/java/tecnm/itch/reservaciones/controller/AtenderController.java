package tecnm.itch.reservaciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tecnm.itch.reservaciones.dto.AtenderDto;
import tecnm.itch.reservaciones.dto.EmpleadoDto;
import tecnm.itch.reservaciones.service.AtenderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/atender")
public class AtenderController {
	@Autowired
	private AtenderService atenderService;

	// Crear atención
	@PostMapping
	public ResponseEntity<AtenderDto> crearAtender(@RequestBody AtenderDto dto) {
		AtenderDto nuevaAtencion = atenderService.crearAtender(dto);
		return ResponseEntity.ok(nuevaAtencion);
	}

	// Listar todas las atenciones
	@GetMapping
	public ResponseEntity<List<AtenderDto>> listarAtenciones() {
		List<AtenderDto> atenciones = atenderService.listarAtenciones();
		return ResponseEntity.ok(atenciones);
	}

	// Obtener atención por ID
	@GetMapping("/{id}")
	public ResponseEntity<AtenderDto> obtenerAtencion(@PathVariable Integer id) {
		AtenderDto atender = atenderService.obtenerPorId(id);
		return ResponseEntity.ok(atender);
	}

	// Eliminar atención por ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarAtencion(@PathVariable Integer id) {
		atenderService.eliminarAtender(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/venta/{idVenta}")
	public ResponseEntity<EmpleadoDto> obtenerEmpleadoPorVenta(@PathVariable Integer idVenta) {
		EmpleadoDto empleado = atenderService.obtenerEmpleadoPorVenta(idVenta);
		return ResponseEntity.ok(empleado);
	}
}
