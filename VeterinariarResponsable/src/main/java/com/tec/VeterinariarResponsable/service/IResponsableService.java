package com.tec.VeterinariarResponsable.service;

import java.util.List;

import com.tec.VeterinariarResponsable.controller.dto.ResponsableDto;
import com.tec.VeterinariarResponsable.service.generic.ResponseGeneric;

public interface IResponsableService {

	public ResponseGeneric<Boolean> saveEntity(ResponsableDto responsableDto);
	public ResponseGeneric<List<ResponsableDto>> getAllResponsable();
	public ResponseGeneric<Boolean> updateEntity(Long idResponsable, ResponsableDto responsableDto);
	public ResponseGeneric<ResponsableDto> getByIdResponsable(Long id);
}
