package com.tec.Veterinaria.service;

import java.util.List;
import java.util.Optional;

import com.tec.Veterinaria.controller.dtos.VeterinariaDto;
import com.tec.Veterinaria.service.generic.ResponseGeneric;

public interface IVeterinariaService {

	
	
	 	public ResponseGeneric<List<VeterinariaDto>> getAllVeterinaria();
	 	public ResponseGeneric<Optional<VeterinariaDto>> getVeterinariaById(Long id);
	 	public ResponseGeneric<Boolean> saveVeterinaria(VeterinariaDto myEntityDto);
	 	public ResponseGeneric<Boolean> updateVeterinaria(Long id, VeterinariaDto myEntityDto);
	 	public ResponseGeneric<Boolean> deleteVeterinaria(Long id);
	 	public Long countData();

	
}
