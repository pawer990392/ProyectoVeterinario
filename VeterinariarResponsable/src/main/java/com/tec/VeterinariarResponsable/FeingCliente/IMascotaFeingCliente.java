package com.tec.VeterinariarResponsable.FeingCliente;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.tec.VeterinariarResponsable.entidades.Veterinaria;
import com.tec.VeterinariarResponsable.service.generic.ResponseGenericExterior;

@FeignClient(name="VeterinariaMascotaFase1",url="http://localhost:8092",path="/api/v1/mascota")
public interface IMascotaFeingCliente {

	
	@GetMapping(path="/responsable/{responsableId}")
	public ResponseGenericExterior<List<Veterinaria>> buscarMascotaPorResponsable(@PathVariable int responsableId);
	
	
	
}
