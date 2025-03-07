package com.tec.VeterinariarResponsable.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.tec.VeterinariarResponsable.FeingCliente.IMascotaFeingCliente;
import com.tec.VeterinariarResponsable.controller.dto.ResponsableDto;
import com.tec.VeterinariarResponsable.entidades.Veterinaria;
import com.tec.VeterinariarResponsable.mapper.ResponsableMapper;
import com.tec.VeterinariarResponsable.model.Responsable;
import com.tec.VeterinariarResponsable.repository.ResponsableRepository;
import com.tec.VeterinariarResponsable.service.generic.*;



@Service
public class ResponsableServiceImp implements IResponsableService {

	// inyectamos
	@Autowired
	private ResponsableRepository responsableRepository;
	@Autowired
	private IMascotaFeingCliente mascotaFeingCliente;
	
	//@Autowired
    //private ResponsableMapper responsableMapper;
	
	
	public ResponseGenericExterior<List<Veterinaria>> getListMascotaPorResponsable(int responsableId){
		
		ResponseGenericExterior<List<Veterinaria>> resp = this.mascotaFeingCliente.buscarMascotaPorResponsable(responsableId);	
		return resp;
	}
	
	
	
	

	@Override
	public ResponseGeneric<Boolean> saveEntity(ResponsableDto responsableDto) {

		ResponseGeneric<Boolean> response = new ResponseGeneric<Boolean>();

		try {

			Responsable responsable = ResponsableMapper.MAPPER.toEntity(responsableDto);
			this.responsableRepository.save(responsable);
			response.isSuccess = true;
			response.data = true;
			response.message = "SE HA GUARDADO CORRECTAMENTE EL REGISTRO";

		} catch (Exception e) {
			response.isSuccess = false;
			response.data = false;
			response.message = "ERROR AL GUARDAR EL REGISTRO";
		}

		return response;

	}

	public ResponseGeneric<List<ResponsableDto>> getAllResponsable() {

		ResponseGeneric<List<ResponsableDto>> response = new ResponseGeneric<List<ResponsableDto>>();

		List<Responsable> listEntity = this.responsableRepository.findAll();

		try {
			if (listEntity.isEmpty()) {
				response.isSuccess = true;
				response.data = null;
				response.message = "NO HAY DATOS QUE MOSTRAR";
			} else {
				response.isSuccess = true;
				response.data = listEntity.stream().map((Responsable) -> ResponsableMapper.MAPPER.toDto(Responsable))
						.collect(Collectors.toList());
				response.message = "MOSTRANDO LOS DATOS";
			}

		} catch (Exception e) {
			response.isSuccess = false;
			response.data = null;
			response.message = "ERROR AL MOSTRAR LOS DATOS";
		}

		return response;

	}

	public ResponseGeneric<Boolean> updateEntity(Long idResponsable, ResponsableDto responsableDto) {

		ResponseGeneric<Boolean> response = new ResponseGeneric<Boolean>();
		try {
			Optional<Responsable> dataEncontrado = this.responsableRepository.findById(idResponsable);

			if (dataEncontrado.isEmpty()) {
				response.isSuccess = true;
				response.data = true;
				response.message = "EL REGISTRO NO EXISTE";
			} else {

				Responsable dataEnvio = dataEncontrado.get();
				System.out.println("DESPUES DE ENCONTRAR " + responsableDto);

				dataEnvio.setId(responsableDto.getId());
				dataEnvio.setNombre(responsableDto.getNombre());
				dataEnvio.setContacto(responsableDto.getTelefono());
				dataEnvio.setVeteritariaId(responsableDto.getVeteritariaId());

				this.responsableRepository.save(dataEnvio);

				response.isSuccess = true;
				response.data = true;
				response.message = "SE HA MODIFICADO CORRECTAMENTE EL REGISTRO";

			}

		} catch (Exception e) {
			response.isSuccess = false;
			response.data = null;
			response.message = "ERROR AL MODIFICAR LOS DATOS";
		}

		return response;
	}

	public ResponseGeneric<ResponsableDto> getByIdResponsable(Long id) {
		ResponseGeneric<ResponsableDto> response = new ResponseGeneric<ResponsableDto>();

		// encontrar
		Optional<Responsable> responsable = this.responsableRepository.findById(id);
	
		try {

			if (responsable.isEmpty()) {
				response.isSuccess = false;
				response.data = null;
				response.message = "NO SE ENCONTRO EL DATOS QUE DESEA BUSCAR CON ESTE ID";
			} else {
				Responsable envio = responsable.get();
				response.isSuccess = true;
				response.data = ResponsableMapper.MAPPER.toDto(envio);
				
				response.message = "MOSTRANDO EL REGISTRO";

			}

		} catch (Exception e) {
			response.isSuccess = false;
			response.data = null;
			response.message = "ERROR A GENERAR EL REGISTRO";
		}
		return response;
	}
	//buscar por medio de veterinaria
	public ResponseGeneric<List<ResponsableDto>> getListVeterianaria(int veterinariaId){
		 
		ResponseGeneric<List<ResponsableDto>> response = new  ResponseGeneric<List<ResponsableDto>>();
		
		List<Responsable> findIsvet= this.responsableRepository.findByVeteritariaId(veterinariaId);
		
		try {

			if(findIsvet.isEmpty()) {
				
				response.isSuccess=false;
				response.data=null;
				response.message="NO HAY REGISTRO SOBRE LA ENTIDAD";
				
			}else {
				
				response.isSuccess=true;
				response.data=response.data = findIsvet.stream().map((Responsable) -> ResponsableMapper.MAPPER.toDto(Responsable))
						.collect(Collectors.toList());
				response.message="REGISTRO MOSTRADO CORRECTAMENTE";
			}
			
		}catch (Exception e) {
			
			response.isSuccess=false;
			response.data=null;
			response.message="ERRROR AL MOSTRAR LOS DATOS";
			
		}
		
		return response;
		
	}

		 
		 
		 
}
