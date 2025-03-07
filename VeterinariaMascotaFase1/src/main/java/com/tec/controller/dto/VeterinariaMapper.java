package com.tec.controller.dto;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tec.model.Veteritaria;



@Service
public class VeterinariaMapper {

	@Autowired
	private ModelMapper modelMap;
	
	
	public Veteritaria toVeteritaria(VeteritariaDto veteritariaDto) {
		
		return modelMap.map(veteritariaDto,Veteritaria.class);
		
	}
	
	public VeteritariaDto toVeteritariaDto (Veteritaria veterinaria) {
		return modelMap.map(veterinaria,VeteritariaDto.class);
	}
	
	//aprender usar Stream y ModelMapper
	public List<VeteritariaDto> veterinariaDto(List<Veteritaria> veterinarias){
		return veterinarias.stream().map(vet->modelMap.map(vet, VeteritariaDto.class))
				.collect(Collectors.toList());
	}
	
	public void updateToVeterinaria(Veteritaria veterinaria,VeteritariaDto veterinariaDto) {
	
		   //porque pone el idveterianaria en nulo?
		//System.out.println("LA CLASE DESDE MAPPER ES ID "+veterinaria.getId());
		veterinariaDto.setId(veterinaria.getId());//porque sucede eso, luego 		
			modelMap.map(veterinariaDto, veterinaria);
		}
		
	
	
	public VeteritariaDto toVeteritariaDtoOptinal (Optional<Veteritaria> veterinaria) {
		return modelMap.map(veterinaria,VeteritariaDto.class);
	}
}
