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

import tecnm.itch.reservaciones.dto.ReservarDto;
import tecnm.itch.reservaciones.service.ReservarService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/reserva")
public class ReservarController {
	@Autowired
	private ReservarService reservarService;

	// Crear reserva
	@PostMapping
	public ResponseEntity<ReservarDto> crearReserva(@RequestBody ReservarDto dto) {
		ReservarDto nuevaReserva = reservarService.crearReserva(dto);
		return ResponseEntity.ok(nuevaReserva);
	}

	// Listar todas las reservas
	@GetMapping
	public ResponseEntity<List<ReservarDto>> listarReservas(
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
		List<ReservarDto> reservas = reservarService.listarReservas(fecha);
		return ResponseEntity.ok(reservas);
	}

	// Obtener reserva por ID
	@GetMapping("/{id}")
	public ResponseEntity<ReservarDto> obtenerReserva(@PathVariable Integer id) {
		ReservarDto reserva = reservarService.obtenerPorId(id);
		return ResponseEntity.ok(reserva);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ReservarDto> actualizarReserva(@PathVariable Integer id, @RequestBody ReservarDto dto) {
		ReservarDto reservaActualizada = reservarService.actualizarReserva(id, dto);
		return ResponseEntity.ok(reservaActualizada);
	}

	// Eliminar reserva por ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarReserva(@PathVariable Integer id) {
		reservarService.eliminarReserva(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}/confirmar")
	public ResponseEntity<ReservarDto> confirmarReserva(@PathVariable Integer id) {
		ReservarDto reservaConfirmada = reservarService.confirmarReserva(id);
		return ResponseEntity.ok(reservaConfirmada);
	}

	@PutMapping("/{id}/cancelar")
	public ResponseEntity<ReservarDto> cancelarReserva(@PathVariable Integer id) {
		ReservarDto reservaCancelada = reservarService.cancelarReserva(id);
		return ResponseEntity.ok(reservaCancelada);
	}
}
