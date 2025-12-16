package tecnm.itch.reservaciones.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tecnm.itch.reservaciones.dto.MesaDto;
import tecnm.itch.reservaciones.service.MesaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/mesa")
public class MesaController {
	@Autowired
	private MesaService mesaService;

	// Crear una nueva mesa
	@PostMapping
	public ResponseEntity<MesaDto> crearMesa(@RequestBody MesaDto mesaDto) {
		MesaDto nuevaMesa = mesaService.crearMesa(mesaDto);
		return ResponseEntity.ok(nuevaMesa);
	}

	// Listar todas las mesas
	@GetMapping
	public ResponseEntity<List<MesaDto>> listarMesas() {
		List<MesaDto> mesas = mesaService.listarMesas();
		return ResponseEntity.ok(mesas);
	}

	// Obtener una mesa por ID
	@GetMapping("/{id}")
	public ResponseEntity<MesaDto> obtenerMesa(@PathVariable Integer id) {
		MesaDto mesa = mesaService.obtenerPorId(id);
		return ResponseEntity.ok(mesa);
	}

	// Actualizar una mesa existente
	@PutMapping("/{id}")
	public ResponseEntity<MesaDto> actualizarMesa(@PathVariable Integer id, @RequestBody MesaDto mesaDto) {
		mesaDto.setId_mesa(id);
		MesaDto updatedMesa = mesaService.actualizarMesa(mesaDto);
		return ResponseEntity.ok(updatedMesa);
	}

	// Eliminar una mesa
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarMesa(@PathVariable Integer id) {
		mesaService.eliminarMesa(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/disponibles")
	public ResponseEntity<List<MesaDto>> getMesasDisponibles(
			@RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
		List<MesaDto> mesas = mesaService.listarMesasDisponibles(fecha);
		return ResponseEntity.ok(mesas);
	}
}
