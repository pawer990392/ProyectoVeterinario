package com.tec.Veterinaria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tec.Veterinaria.controller.dtos.VeterinariaDto;
import com.tec.Veterinaria.service.IVeterinariaService;
import com.tec.Veterinaria.service.generic.ResponseGeneric;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController

@RequestMapping(path="api/v1/veterinaria")
public class VeterinariaController {

	@Autowired
	private IVeterinariaService services;
	
	@PostMapping
	public ResponseEntity<ResponseGeneric<Boolean>> saveVet(@RequestBody VeterinariaDto veterinariaDto){
		
		ResponseGeneric<Boolean> response = services.saveVeterinaria(veterinariaDto);
		
		return ResponseEntity.ok(response);
	}
	@GetMapping()
	public ResponseEntity<ResponseGeneric<List<VeterinariaDto>>> getAllVet() {
		
		ResponseGeneric<List<VeterinariaDto>> response = services.getAllVeterinaria();

		return ResponseEntity.ok(response);
	}
	@GetMapping("/{idVeterinario}")
	public ResponseEntity<ResponseGeneric<Optional<VeterinariaDto>>> getByIdVet(@PathVariable Long idVeterinario) {
		
		ResponseGeneric<Optional<VeterinariaDto>> response = services.getVeterinariaById(idVeterinario);

		return ResponseEntity.ok(response);
	}
	@PutMapping("/{idVet}")
	public ResponseEntity<ResponseGeneric<Boolean>> updateSet(@PathVariable Long idVet, @RequestBody VeterinariaDto veterinariaDto){
		
		ResponseGeneric<Boolean> response = services.updateVeterinaria(idVet, veterinariaDto);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{idVet}")
	public ResponseEntity<ResponseGeneric<Boolean>> deleteVet(@PathVariable Long idVet){
		
		ResponseGeneric<Boolean> response = services.deleteVeterinaria(idVet);
		
		return ResponseEntity.ok(response);
	}
	

}
