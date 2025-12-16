package tecnm.itch.reservaciones.clienteClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@FeignClient(name = "restaurante", url = "http://localhost:8080/api/cliente")
public interface ClienteClient {
	@GetMapping("/{id}")
	ClienteDto findById(@PathVariable("id") Integer id);

	@JsonIgnoreProperties(ignoreUnknown = true)
	class ClienteDto {
		@JsonAlias({ "id_cliente", "id_cliente" })
		private Integer id_cliente;
		private String nombreCliente;
		private String correoCliente;
		private String telefonoCliente;

		public Integer getIdCliente() {
			return id_cliente;
		}

		public void setIdCliente(Integer idCliente) {
			this.id_cliente = idCliente;
		}

		public String getNombreCliente() {
			return nombreCliente;
		}

		public void setNombreCliente(String nombreCliente) {
			this.nombreCliente = nombreCliente;
		}

		public String getCorreoCliente() {
			return correoCliente;
		}

		public void setCorreoCliente(String correoCliente) {
			this.correoCliente = correoCliente;
		}

		public String getTelefonoCliente() {
			return telefonoCliente;
		}

		public void setTelefonoCliente(String telefonoCliente) {
			this.telefonoCliente = telefonoCliente;
		}
	}
}
