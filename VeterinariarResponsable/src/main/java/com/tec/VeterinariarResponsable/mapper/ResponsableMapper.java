package com.tec.VeterinariarResponsable.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.tec.VeterinariarResponsable.controller.dto.ResponsableDto;
import com.tec.VeterinariarResponsable.model.Responsable;


//@Mapper marca la interfaz como una interfaz de mapeo 
//y permite que el procesador MapStruct entre en acción durante la compilación.
@Mapper(componentModel = "spring")
public interface ResponsableMapper {

	ResponsableMapper MAPPER = Mappers.getMapper(ResponsableMapper.class);

	@Mapping(source="telefono",target = "contacto")
    Responsable toEntity(ResponsableDto responsableDto);//mapToUser
    @Mapping(source="contacto",target = "telefono")
    ResponsableDto toDto(Responsable entity);//mapToUserDto
	
}
