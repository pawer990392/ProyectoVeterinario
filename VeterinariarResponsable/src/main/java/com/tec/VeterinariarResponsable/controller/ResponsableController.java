package com.tec.VeterinariarResponsable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tec.VeterinariarResponsable.controller.dto.ResponsableDto;
import com.tec.VeterinariarResponsable.entidades.Veterinaria;
import com.tec.VeterinariarResponsable.service.ResponsableServiceImp;
import com.tec.VeterinariarResponsable.service.generic.ResponseGeneric;
import com.tec.VeterinariarResponsable.service.generic.ResponseGenericExterior;


@RestController
@RequestMapping(path="api/v1/Responsable")
public class ResponsableController {
	
	
	@Autowired
	private ResponsableServiceImp services;
	
	@PostMapping
	public ResponseEntity<ResponseGeneric<Boolean>> saveResponsable(@RequestBody ResponsableDto responsableDto){
		
		ResponseGeneric<Boolean> response = this.services.saveEntity(responsableDto);
		return ResponseEntity.ok(response);	
	}
	
	@GetMapping
	public ResponseEntity<ResponseGeneric<List<ResponsableDto>>> getAllDtoResponsable(){
		
		ResponseGeneric<List<ResponsableDto>> response = this.services.getAllResponsable();
		return ResponseEntity.ok(response);		
	}
	@PutMapping("/{idReponsable}")
	public ResponseEntity<ResponseGeneric<Boolean>> UpdateResponsable(@PathVariable Long idReponsable,@RequestBody ResponsableDto responsableDto){
		
		ResponseGeneric<Boolean> response = this.services.updateEntity(idReponsable, responsableDto);
		return ResponseEntity.ok(response);		
	}
	
	@GetMapping("/{idResponsable}")
	public ResponseEntity<ResponseGeneric<ResponsableDto>> getByIdEntity(@PathVariable Long idResponsable){
		
		ResponseGeneric<ResponsableDto> response = this.services.getByIdResponsable(idResponsable);
		return ResponseEntity.ok(response);
		
	}
	@GetMapping("/veterinario/{idVeterinaria}")
	public ResponseEntity<ResponseGeneric<List<ResponsableDto>>> getListVeterinaria(@PathVariable int idVeterinaria){		
		//System.out.println("desde contorlador es "+idMascota);
		ResponseGeneric<List<ResponsableDto>> response= this.services.getListVeterianaria(idVeterinaria);
		return ResponseEntity.ok(response);

	}
	@GetMapping("/veterinario/mascota/{responsableId}")
	public ResponseEntity<?> getListMascotaResponsable(@PathVariable long responsableId){
		
		ResponseGeneric<Boolean> responseUnico = new  ResponseGeneric<Boolean>();

		
		    				
		try {
			
			ResponseGeneric<ResponsableDto> response = this.services.getByIdResponsable(responsableId);		

			if(!response.isSuccess) {//si es ! de verdadero
				
				responseUnico.isSuccess=false;
				responseUnico.data=false;
				responseUnico.message="NO EXISTE RESPONSABLE CON EL REGISTRO DEL ID "+responsableId;
				
				return ResponseEntity.ok(responseUnico);
			}else {
				ResponseGenericExterior<List<Veterinaria>> listaMascota = this.services.getListMascotaPorResponsable((int)responsableId);
				
				if(!listaMascota.isSuccess()) {
					
					responseUnico.isSuccess=false;
					responseUnico.data=false;
					responseUnico.message="NO HAY MASCOTAS SOBRE EL RESPONSABLE";
					
					return ResponseEntity.ok(responseUnico);					
					
				}else {
					return ResponseEntity.ok(listaMascota);
				}
			}			
		}catch (Exception e) {
			
			responseUnico.isSuccess=false;
			responseUnico.data=false;
			responseUnico.message="OCURRIO UN ERROR AL PROCESAR LA INFORMACION "+ e.getMessage();
			
			return ResponseEntity.ok(responseUnico);
		}
	}
	
	
	
	
	
	

}
