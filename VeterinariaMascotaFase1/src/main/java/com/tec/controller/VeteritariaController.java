package com.tec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tec.controller.dto.VeteritariaDto;
import com.tec.service.VeteritariaServiceImp;
import com.tec.service.generic.BaseResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

//
@RestController
@RequestMapping(path="api/v1/mascota")
public class VeteritariaController {

	@Autowired 
	private VeteritariaServiceImp veteritariaServices;
	
	
	@PostMapping()
	public ResponseEntity<BaseResponse<Boolean>> postDataVeteritaria(@RequestBody VeteritariaDto veteritariaDto) {
		//TODO: process POST request
		BaseResponse<Boolean> response = veteritariaServices.crearVeteritario(veteritariaDto);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping()
	public ResponseEntity<BaseResponse<List<VeteritariaDto>>> postDataVeteritaria() {
		BaseResponse<List<VeteritariaDto>> response = veteritariaServices.listarVeteritario();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{idMascota}")
	public ResponseEntity<BaseResponse<VeteritariaDto>> getByIdVeterinaria(@PathVariable String idMascota){
		
		System.out.println("desde contorlador es "+idMascota);
		BaseResponse<VeteritariaDto> response= veteritariaServices.getVeterinariaById(idMascota);
		
		return ResponseEntity.ok(response);

	}
	
	@PutMapping("/{idMascota}")
	public ResponseEntity<BaseResponse<Boolean>> putDataVeteritaria(@PathVariable Long idMascota, @RequestBody VeteritariaDto veteritariaDto){
		
		BaseResponse<Boolean> response= veteritariaServices.updateVeterinaria(idMascota, veteritariaDto);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{idMascota}")
	public ResponseEntity<BaseResponse<Boolean>> deleteVeterinaria(@PathVariable Long idMascota ){
		BaseResponse<Boolean> response= veteritariaServices.deleteVeterinaria(idMascota);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/veterinario/{idVeterinaria}")
	public ResponseEntity<BaseResponse<List<VeteritariaDto>>> getByIdVeterinaria(@PathVariable int idVeterinaria){		
		
		//System.out.println("desde contorlador es "+idMascota);
		BaseResponse<List<VeteritariaDto>> response= this.veteritariaServices.listByVeterinary(idVeterinaria);
		return ResponseEntity.ok(response);

	}
	
	@GetMapping("/cliente/{clienteId}")
	public ResponseEntity<BaseResponse<List<VeteritariaDto>>> getListClientId(@PathVariable int clienteId){	
		
		//System.out.println("desde contorlador es "+idMascota);
		BaseResponse<List<VeteritariaDto>> response= this.veteritariaServices.listByClienteId(clienteId);
		return ResponseEntity.ok(response);

	}
	
	@GetMapping("/responsable/{responsableId}")
	public ResponseEntity<BaseResponse<List<VeteritariaDto>>> getListResponsableId(@PathVariable int responsableId){	
		
		//System.out.println("desde contorlador es "+idMascota);
		BaseResponse<List<VeteritariaDto>> response= this.veteritariaServices.listByIdResponsableId(responsableId);
		return ResponseEntity.ok(response);

	}
	
}
