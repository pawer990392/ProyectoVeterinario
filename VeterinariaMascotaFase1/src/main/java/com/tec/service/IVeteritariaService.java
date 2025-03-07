package com.tec.service;


import java.util.List;

import com.tec.controller.dto.VeteritariaDto;
import com.tec.service.generic.BaseResponse;



public interface IVeteritariaService {

	
	public BaseResponse<Boolean> crearVeteritario (VeteritariaDto veteritariaDto);
	
	public BaseResponse<List<VeteritariaDto>> listarVeteritario();
	
	public BaseResponse<VeteritariaDto> getVeterinariaById(String id);
	
	public BaseResponse<Boolean> updateVeterinaria (Long id,VeteritariaDto veteritariaDto);
	
	public BaseResponse<Boolean> deleteVeterinaria(Long id);

	
	
	
}
