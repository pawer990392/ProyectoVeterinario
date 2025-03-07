package com.tec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tec.controller.dto.VeterinariaMapper;
import com.tec.controller.dto.VeteritariaDto;
import com.tec.model.Veteritaria;
import com.tec.repository.VeteritariaRepository;
import com.tec.service.generic.BaseResponse;

@Service
public class VeteritariaServiceImp implements IVeteritariaService {

	@Autowired
	private VeteritariaRepository veteritarioRepository;
	@Autowired
	private VeterinariaMapper vetMapper;

	@Override
	public BaseResponse<Boolean> crearVeteritario(VeteritariaDto veteritariaDto) {

		BaseResponse<Boolean> response = new BaseResponse<Boolean>();

		try {

			Veteritaria vet = vetMapper.toVeteritaria(veteritariaDto);
			vetMapper.toVeteritariaDto(veteritarioRepository.save(vet));

			response.isSuccess = true;
			response.data = true;
			response.message = "SE HA AGREGADO CORRECTAMENTE EL REGISTRO VETERINARIA";

		} catch (Exception e) {

			response.isSuccess = false;
			response.data = false;
			response.message = "PROBLEMA A AGREGAR EL REGISTRO VETERINARIA";

		}
		return response;
	}

	public BaseResponse<List<VeteritariaDto>> listarVeteritario() {

		BaseResponse<List<VeteritariaDto>> response = new BaseResponse<List<VeteritariaDto>>();

		try {
			List<VeteritariaDto> listarDto = vetMapper.veterinariaDto(veteritarioRepository.findAll());

			if (listarDto.size() == 0) {
				response.isSuccess = false;
				response.data = null;
				response.message = "NO HAY DATOS QUE MOSTRAR";
			} else {
				response.isSuccess = true;
				response.data = listarDto;
				response.message = "MOSTRANDO LOS DATOS CORRECTAMENTE";
			}

		} catch (Exception e) {
			response.isSuccess = false;
			response.data = null;
			response.message = "ERRROR AL MOSTRAR LOS DATOS";
		}
		return response;

	}

	public BaseResponse<VeteritariaDto> getVeterinariaById(String id) {

		String vaidate = "^[1-9][0-9]*$";
		BaseResponse<VeteritariaDto> response = new BaseResponse<VeteritariaDto>();
		// PARA VALIDAR SI ES NUMERO
		if (!(id.matches(vaidate)) || id == null) {
			response.isSuccess = false;
			response.data = null;
			response.message = "EL ID DEBE SER UN NUMERO ENTERO";

		} else {
			Long convIdVeterinaria = Long.parseLong(id);// testarlo
			// es traer el datos
			Optional<Veteritaria> VetEncontrado = veteritarioRepository.findById(convIdVeterinaria);

			if (!VetEncontrado.isPresent()) {
				response.isSuccess = false;
				response.data = null;
				response.message = "EL ID PROPORCIONARDO NO EXISTE EN LA BASE DE DATOS";

			} else {
				response.isSuccess = true;
				response.data = vetMapper.toVeteritariaDtoOptinal(VetEncontrado);
				response.message = "MOSTRANDO LOS DATOS DEL VETERIANARIA";
			}
		}

		return response;
	}

	public BaseResponse<Boolean> updateVeterinaria(Long id, VeteritariaDto veteritariaDto) {

		BaseResponse<Boolean> response = new BaseResponse<Boolean>();

		try {
			// estamos trayendo si es opcional
			Optional<Veteritaria> encotroVet = veteritarioRepository.findById(id);

			if (encotroVet.isPresent()) {
				// convertimos el objetco a la entidad
				Veteritaria vet = encotroVet.get();
				vetMapper.updateToVeterinaria(vet, veteritariaDto);
				// le iendicamos que guardamos los datos
				veteritarioRepository.save(vet);
				response.isSuccess = true;
				response.data = true;
				response.message = "SE HA ACTUALIZADO EL REGISTRO EXITOSAMENTE";

			} else {
				response.isSuccess = false;
				response.data = false;
				response.message = "NO EXISTE EL ID DEL REGISTRO PARA PODER ACTUALIZAR";
			}
		} catch (Exception e) {
			response.isSuccess = false;
			response.data = false;
			response.message = "ERROR AL PRESENTAR MODIFICACION MODIFICACION DURANTE EL REGISTRO";
		}

		return response;
	}

	public BaseResponse<Boolean> deleteVeterinaria(Long id) {

		BaseResponse<Boolean> response = new BaseResponse<Boolean>();

		try {
			Optional<Veteritaria> encotroVet = veteritarioRepository.findById(id);

			if (encotroVet.isEmpty()) {
				response.isSuccess = false;
				response.data = false;
				response.message = "NO EXISTE EL ID PARA ELIMINAR EL REGISTRO";
			} else {

				Veteritaria vet = new Veteritaria();
				vet = encotroVet.get();
				veteritarioRepository.delete(vet);
				response.isSuccess = true;
				response.data = true;
				response.message = "SE HA ELIMINADO CORRECTAMENTE EL REGISTRO";
			}

		} catch (Exception e) {
			// TODO: handle exception
			response.isSuccess = false;
			response.data = false;
			response.message = "ERROR AL PRESENTAR MODIFICACION MODIFICACION DURANTE EL REGISTRO";
		}

		return response;
	}

	// metodo para buscar por veterinario por su ID
	public BaseResponse<List<VeteritariaDto>> listByVeterinary(int veterinaryId) {

		BaseResponse<List<VeteritariaDto>> response = new BaseResponse<List<VeteritariaDto>>();

		List<Veteritaria> findIsvet = this.veteritarioRepository.findByVeteritariaId(veterinaryId);

		try {

			if (findIsvet.isEmpty()) {

				response.isSuccess = false;
				response.data = null;
				response.message = "NO HAY REGISTRO SOBRE ESTE VETERINARIO";

			} else {

				response.isSuccess = true;
				response.data = this.vetMapper.veterinariaDto(findIsvet);
				response.message = "REGISTRO MOSTRADO CORRECTAMENTE";
			}

		} catch (Exception e) {

			response.isSuccess = false;
			response.data = null;
			response.message = "ERRROR AL MOSTRAR LOS DATOS";

		}

		return response;

	}

	// metodo para buscar por clienteId por su ID
	public BaseResponse<List<VeteritariaDto>> listByClienteId(int clienteId) {

		BaseResponse<List<VeteritariaDto>> response = new BaseResponse<List<VeteritariaDto>>();

		List<Veteritaria> findIsvet = this.veteritarioRepository.findByClienteId(clienteId);

		try {

			if (findIsvet.isEmpty()) {

				response.isSuccess = false;
				response.data = null;
				response.message = "NO HAY REGISTRO SOBRE LA ENTIDAD";

			} else {

				response.isSuccess = true;
				response.data = this.vetMapper.veterinariaDto(findIsvet);
				response.message = "REGISTRO MOSTRADO CORRECTAMENTE";
			}

		} catch (Exception e) {

			response.isSuccess = false;
			response.data = null;
			response.message = "ERRROR AL MOSTRAR LOS DATOS";

		}

		return response;

	}

	public BaseResponse<List<VeteritariaDto>> listByIdResponsableId(int responsableId) {

		BaseResponse<List<VeteritariaDto>> response = new BaseResponse<List<VeteritariaDto>>();

		try {
			List<Veteritaria> listResponsable = this.veteritarioRepository.findByResponsableId(responsableId);

			if (listResponsable.isEmpty()) {
				response.isSuccess = false;
				response.data = null;
				response.message = "NO SE ENCONTRO REGISTRO SOBRE ESTA VALOR";
			} else {
				response.isSuccess = true;
				response.data = this.vetMapper.veterinariaDto(listResponsable);
				response.message = "REGISTRO MOSTRADO CORRECTAMENTE";
			}

		} catch (Exception e) {

			response.isSuccess = false;
			response.data = null;
			response.message = "ERRROR AL MOSTRAR LOS DATOS";
		}
		return response;
	}

}
