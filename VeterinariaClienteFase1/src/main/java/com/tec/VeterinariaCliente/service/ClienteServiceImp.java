package com.tec.VeterinariaCliente.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tec.VeterinariaCliente.RestTemplate.ResponseGenericExterior;
import com.tec.VeterinariaCliente.dtos.ClienteDto;
import com.tec.VeterinariaCliente.entidades.Veteritaria;
import com.tec.VeterinariaCliente.mapper.clientesDtos.ClienteDtos;
import com.tec.VeterinariaCliente.model.Cliente;
import com.tec.VeterinariaCliente.service.generic.ResponseGeneric;


@Service
public class ClienteServiceImp implements IClienteService {

	@Autowired
	private ClienteDtos clienteDAO;
	

	@Override
	public ResponseGeneric<Boolean> crearCliente(ClienteDto clienteDto) {

		ResponseGeneric<Boolean> response = new ResponseGeneric<Boolean>();
		// System.out.println("Estamos desdel servicio");

		try {
			// verificaciones
			// System.out.println("se obtiene el numero verdad "+clienteDto.getContacto());

			long respuestas = this.clienteDAO.exisNumberPhone(clienteDto.getContacto());
			// System.out.println("LA DATA ES "+respuestas);

			if (respuestas > 0) {
				response.isSuccess = false;
				response.data = false;
				response.message = "EL REGISTRO YA TIENE UN NUMERO DE CONTACTO EN LA TABLA";
			} else {

				boolean resp = this.clienteDAO.crearCliente(clienteDto);

				if (resp) {
					response.isSuccess = true;
					response.data = true;
					response.message = "REGISTRO GUARDADO CORRECTAMENTE";
				} else {
					response.isSuccess = false;
					response.data = false;
					response.message = "SE HA PRESENTADO UN ERROR AL GUARDAR EL REGISTRO";
				}
			}
		} catch (Exception e) {
			response.isSuccess = false;
			response.data = false;
			response.message = "SE HA PRESENTADO UN ERROR AL GUARDAR EL REGISTRO";

		}
		return response;
	}

	@Override
	public ResponseGeneric<List<ClienteDto>> listarCliente() {
		// TODO Auto-generated method stub
		ResponseGeneric<List<ClienteDto>> resp = new ResponseGeneric<List<ClienteDto>>();
		List<Cliente> mylistaEntity = this.clienteDAO.listarCliente();
		System.out.println("La data llega con " + mylistaEntity);
		List<ClienteDto> mylistaCliente = new ArrayList<>();
		mylistaCliente = this.clienteDAO.listarClientesDto(mylistaEntity);

		if (mylistaEntity.isEmpty()) {
			resp.isSuccess = true;
			resp.data = null;
			resp.message = "NO HAY REGISTRO EN LA TABLA CLIENTE";
		} else {
			resp.isSuccess = true;
			resp.data = mylistaCliente;
			resp.message = "MOSTRANDO LOS DATOS DE LA TABLA";
		}
		return resp;
	}

	@Override
	public ResponseGeneric<Boolean> updateCliente(Long id, ClienteDto clienteDto) {

		// verificar si existe la entidad
		Optional<Cliente> encontrado = this.clienteDAO.findbyIdCliente(id);
		ResponseGeneric<Boolean> response = new ResponseGeneric<Boolean>();
		try {
			if (encontrado.isPresent()) {

				boolean updateRegister = this.clienteDAO.updateCliente(clienteDto);

				if (updateRegister) {
					response.isSuccess = true;
					response.data = true;
					response.message = "REGISTRO EDITADO CORRECTAMENTE";
				} else {
					response.isSuccess = false;
					response.data = false;
					response.message = "SE HA PRESENTADO UN ERROR AL EDITAR EL REGISTRO";
				}
			} else {
				response.isSuccess = false;
				response.data = false;
				response.message = "NO EXIETE EL REGISTRO QUE SE DESEA EDITAR";
			}

		} catch (Exception e) {

			response.isSuccess = false;
			response.data = false;
			response.message = "SE HA PRESENTADO UN ERROR AL EDITAR EL REGISTRO";
		}
		return response;

	}

	@Override
	public ResponseGeneric<Boolean> deleteCliente(Long id) {
		ResponseGeneric<Boolean> resp = new ResponseGeneric<>();

		try {

			Optional<Cliente> encontrado = this.clienteDAO.findbyIdCliente(id);

			if (!encontrado.isPresent()) {
				resp.isSuccess = false;
				resp.data = false;
				resp.message = "EL CLIENTE NO EXISTE";
				return resp;
			}

			boolean eliminado = this.clienteDAO.deleteClienteDao(encontrado);
			if (eliminado) {
				resp.isSuccess = true;
				resp.data = true;
				resp.message = "EL CLIENTE SE HA ELIMINADO CORRECTAMENTE";
			} else {
				resp.isSuccess = false;
				resp.data = false;
				resp.message = "NO SE PUEDE ELIMINAR CLIENTE";
			}
		} catch (Exception e) {
			resp.isSuccess = false;
			resp.data = false;
			resp.message = "SE HA PRESENTADO UN ERROR AL ELIMINAR AL CLIENTE";
		}

		return resp;
	}

	@Override
	public ResponseGeneric<ClienteDto> getClienteById(Long id) {

		ResponseGeneric<ClienteDto> resp = new ResponseGeneric<ClienteDto>();
		Optional<Cliente> encontrado = this.clienteDAO.findbyIdCliente(id);

		try {

			if (encontrado.isPresent()) {
				ClienteDto currentCliente = this.clienteDAO.toClientes(encontrado);
				resp.isSuccess = true;
				resp.data = currentCliente;
				resp.message = "MOSTRANDO EL REGISTRO DEL CLIENTE";

			} else {
				resp.isSuccess = false;
				resp.data = null;
				resp.message = "NO SE PUEDE OBTENER EL REGISTRO DEL CLIENTE";
			}

		} catch (Exception e) {
			resp.isSuccess = false;
			resp.data = null;
			resp.message = "SE PRESENTO UN PROBLEMA AL OBTENER EL REGISTRO DEL CLIENTO SOLICITADO";
		}
		return resp;
	}
	
	//esta pagina nos ayudo https://stackoverflow-com.translate.goog/questions/78316606/get-request-with-body-using-resttemplate-in-spring-boot-3?_x_tr_sl=en&_x_tr_tl=es&_x_tr_hl=es&_x_tr_pto=tc
	//metodo para listar los pacientes desde dctor con restemplste
	//http://localhost:8091/api/v1/veteritaria/cliente/mascota/21
    @Autowired
    private RestTemplate restTemplate;

    public ResponseGenericExterior<List<Veteritaria>> obtenerVeterinarias(long clienteId) {
        String url = "http://localhost:8092/api/v1/mascota/cliente/" + clienteId;

        ResponseEntity<ResponseGenericExterior<List<Veteritaria>>> responseEntity = restTemplate.exchange(
            url,org.springframework.http.HttpMethod.GET,
            null,
            new ParameterizedTypeReference<ResponseGenericExterior<List<Veteritaria>>>() {}          
        );

        return responseEntity.getBody();
    }
	
	
	
}
