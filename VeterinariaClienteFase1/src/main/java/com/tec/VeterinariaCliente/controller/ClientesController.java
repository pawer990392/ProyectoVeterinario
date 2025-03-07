package com.tec.VeterinariaCliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.tec.VeterinariaCliente.RestTemplate.ResponseGenericExterior;
import com.tec.VeterinariaCliente.dtos.ClienteDto;
import com.tec.VeterinariaCliente.entidades.Veteritaria;
import com.tec.VeterinariaCliente.service.ClienteServiceImp;
import com.tec.VeterinariaCliente.service.generic.ResponseGeneric;

@RestController
@RequestMapping(path="api/v1/veteritaria/cliente")
@CrossOrigin

public class ClientesController {

	@Autowired 
	private ClienteServiceImp myServiceCliente;
	
	@PostMapping()
	public ResponseEntity<ResponseGeneric<Boolean>> guardarCliente(@RequestBody ClienteDto clienteDto) {
		System.out.println("Entrando del controlador");
		ResponseGeneric<Boolean> response = myServiceCliente.crearCliente(clienteDto);
		
		return ResponseEntity.ok(response);
	}
	@GetMapping()
	public ResponseEntity<ResponseGeneric<List<ClienteDto>>>listarClientes(){
		ResponseGeneric<List<ClienteDto>> resp = myServiceCliente.listarCliente();
		return ResponseEntity.ok(resp);
	}
	
	@GetMapping("/{idCliente}")
	public ResponseEntity<ResponseGeneric<ClienteDto>>findByIdCliente(@PathVariable Long idCliente){
		ResponseGeneric<ClienteDto> resp = myServiceCliente.getClienteById(idCliente);
		return ResponseEntity.ok(resp);	
	}
	
	@PutMapping("/{idCliente}")
	public  ResponseEntity<ResponseGeneric<Boolean>> updateCliente (@PathVariable long idCliente, @RequestBody ClienteDto clienteDto ){	
		ResponseGeneric<Boolean> response = this.myServiceCliente.updateCliente(idCliente, clienteDto);
		return ResponseEntity.ok(response);
		
	}
	@DeleteMapping("/{idCliente}")
	public ResponseEntity<ResponseGeneric<Boolean>> renoveCliente (@PathVariable long idCliente ){	
		ResponseGeneric<Boolean> response = this.myServiceCliente.deleteCliente(idCliente);
		return ResponseEntity.ok(response);		
	}
	
	
	@GetMapping("/mascota/{clienteId}")
	public ResponseEntity<?> buscarMascotaPorClienteById (@PathVariable long clienteId ){
		
		try {
			ResponseGeneric<ClienteDto> resp = myServiceCliente.getClienteById(clienteId);
			
			
			if(!resp.isSuccess) {
				
				return ResponseEntity.badRequest().body("NO EXISTE Cliente REGISTRADO CON ESTE ID "+clienteId);
			}else {
				
				ResponseGenericExterior<List<Veteritaria>> responseEntity = this.myServiceCliente.obtenerVeterinarias(clienteId);
				
				if(!responseEntity.isSuccess()) {
					return ResponseEntity.ofNullable("NO HAY MASCOTAS SOBRE ESTE CLIENTE");
				}else {
					return ResponseEntity.ok(responseEntity);
				}
				
			}
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("OCURRIO UN ERRO AL PROCESAR LA INFORMACION "+ e.getMessage());

		}
		
	}	
	
}
