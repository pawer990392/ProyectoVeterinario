package com.tec.Veterinaria.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tec.Veterinaria.controller.dtos.VeterinariaDto;
import com.tec.Veterinaria.model.Veterinaria;
import com.tec.Veterinaria.repository.VeterinariaRepository;
import com.tec.Veterinaria.service.generic.ResponseGeneric;

@Service
public class VeterinariaServiceImp implements IVeterinariaService {

	private final VeterinariaRepository veterinariaRepository;

	public VeterinariaServiceImp(VeterinariaRepository veterinariaRepository) {
		this.veterinariaRepository = veterinariaRepository;
	}

	@Override
	public ResponseGeneric<List<VeterinariaDto>> getAllVeterinaria() {

		ResponseGeneric<List<VeterinariaDto>> response = new ResponseGeneric<List<VeterinariaDto>>();

		try {

			List<VeterinariaDto> listData = this.veterinariaRepository.findAll().stream().map(this::toEntityDto)
					.collect(Collectors.toList());

			if (listData.size() == 0) {
				response.isSuccess = false;
				response.data = null;
				response.message = "NO HAY REGISTRADO EN LA ENTIDAD";

			} else {
				response.isSuccess = true;
				response.data = listData;
				response.message = "MOSTRANDO LOS DATOS VETERINARIA";

			}

		} catch (Exception e) {

			response.isSuccess = false;
			response.data = null;
			response.message = "PROBLEMA AL MOSTRAR LOS DATOS";

		}
		return response;
	}

	@Override
	public ResponseGeneric<Optional<VeterinariaDto>> getVeterinariaById(Long id) {

		ResponseGeneric<Optional<VeterinariaDto>> response = new ResponseGeneric<Optional<VeterinariaDto>>();

		Optional<VeterinariaDto> entityOptional = Optional
				.ofNullable(this.veterinariaRepository.findById(id).map(this::toEntityDto).orElse(null));

		if (entityOptional.isPresent()) {

			response.isSuccess = true;
			response.data = Optional.ofNullable(entityOptional.get());
			response.message = "MOSTRANDO SUS DATOS CON EL ID CORRESPODIENTE";

		} else {

			response.isSuccess = false;
			response.data = null;
			response.message = "NO HAY DATOS CON ESTE ID DEL REGISTRO";

		}

		return response;
	}

	@Override
	public ResponseGeneric<Boolean> saveVeterinaria(VeterinariaDto myEntityDto) {

		ResponseGeneric<Boolean> response = new ResponseGeneric<Boolean>();

		try {

			// aqui hacemos una preuba simple de validaciones pero se puede aplicar de
			// diferentes manera
			String cadenaNombre = myEntityDto.nombre();

			if (cadenaNombre.isBlank() || cadenaNombre == null) {
				response.isSuccess = false;
				response.data = false;
				response.message = "FAVOR DE INGRESA DATOS CORRECTO DE NOMBRE DE VETERINARIO";
			} else {
				// si encotro concidencia primero
				ResponseGeneric<Boolean> encontrado = this.verificarNombre(cadenaNombre);
				if (encontrado.data) {
					response.isSuccess = false;
					response.data = false;
					response.message = "FAVOR DE INGRESA OTRO NOMBRE DE VETERINARIO, YA EXISTE EN EL REGISTRO";
				} else {
					// aqui viene toda la validacion y logica ajaja
					Veterinaria veterinaria = this.toEntity(myEntityDto);
					// guardamos el registro
					// this.veterinariaRepository.save(veterinaria);
					this.veterinariaRepository.saveAndFlush(veterinaria);

					response.isSuccess = true;
					response.data = true;
					response.message = "SE HA GUARDADO REGISTRO EXITOSAMENTE";
				}
			}
		} catch (Exception e) {

			response.isSuccess = false;
			response.data = false;
			response.message = "ERRROR AL INSERTAR LOS DATOS";

		}
		return response;

	}

	@Override
	public ResponseGeneric<Boolean> updateVeterinaria(Long id, VeterinariaDto myEntityDto) {

		ResponseGeneric<Boolean> response = new ResponseGeneric<Boolean>();
		ResponseGeneric<Optional<VeterinariaDto>> encontrado = this.getVeterinariaById(id);

		try {

			if (!encontrado.isSuccess) {

				response.isSuccess = false;
				response.data = null;
				response.message = "NO EXISTE ESTE REGISTRO PARA PODER HACER MODIFICACIONES";

			} else {
				// trasnfoamcion a la dto a la data
				Veterinaria vet = toEntity(encontrado.data.get());

				vet.setId(myEntityDto.id());
				vet.setNombre(myEntityDto.nombre());
				vet.setDireccion(myEntityDto.direccion());
				vet.setTelefono(myEntityDto.telefono());

				this.veterinariaRepository.save(vet);

				response.isSuccess = true;
				response.data = true;
				response.message = "GUARDADO CORRECTAMENTE LOS DATOS";

			}

		} catch (Exception e) {
			response.isSuccess = false;
			response.data = null;
			response.message = "ERROR AL GUARDAR LOS DATOS ";
		}

		return response;

	}

	@Override
	public ResponseGeneric<Boolean> deleteVeterinaria(Long id) {

		ResponseGeneric<Boolean> response = new ResponseGeneric<Boolean>();
		
		ResponseGeneric<Optional<VeterinariaDto>> encontrado = this.getVeterinariaById(id);
		System.out.println("CASO DE ENCONTRADO ES "+encontrado);
		try {
			if (id < 0) {
				response.isSuccess = false;
				response.data = false;
				response.message = "EL VALOR DEL ID NO CORRESPONDE AL FORMATO CORRECTO";
			} else {
				
				// ENCONTRAR
				if (!encontrado.isSuccess) {
					response.isSuccess = false;
					response.data = false;
					response.message = "NO SE PUEDE ELIMINAR REGISTRO NO EXISTE ESTA INFORMACION";
				} else {
					// creamos la entuidad;

					Veterinaria vet = this.toEntity(encontrado.data.get());
					vet.setId(encontrado.data.get().id());
					this.veterinariaRepository.delete(vet);

					response.isSuccess = true;
					response.data = true;
					response.message = "REGISTRO ELMINADO EXITOSAMENTE";
				}

			}

		} catch (Exception e) {
			response.isSuccess = false;
			response.data = false;
			response.message = "SE HA GENERADO UN ERROR EN EL SERVIDOR";

		}

		return response;
	}

	// otra manera de hacerlo mapeando datos
	private Veterinaria toEntity(VeterinariaDto myEntityDto) {
		Veterinaria myEntity = new Veterinaria();

		myEntity.setNombre(myEntityDto.nombre());
		myEntity.setDireccion(myEntityDto.direccion());
		myEntity.setTelefono(myEntityDto.telefono());
		return myEntity;

	}

	private VeterinariaDto toEntityDto(Veterinaria myEntity) {
		return new VeterinariaDto(myEntity.getId(), myEntity.getNombre(), myEntity.getDireccion(),
				myEntity.getTelefono());

	}

	@Override
	public Long countData() {

		return this.veterinariaRepository.count();

	}

	public ResponseGeneric<Boolean> verificarNombre(String nombre) {

		ResponseGeneric<Boolean> response = new ResponseGeneric<Boolean>();

		List<Veterinaria> listaEntity;

		listaEntity = this.veterinariaRepository.findByNombreIgnoreCase(nombre);

		if (listaEntity.size() == 0) {

			response.isSuccess = false;
			response.data = false;
			response.message = "NO SE ENCONTRO CONICIDENCIA DE DATOS";

		} else {
			response.isSuccess = true;
			response.data = true;
			response.message = "HAY CONCIDENCIA DE DATOS";
		}

		return response;
	}
}
