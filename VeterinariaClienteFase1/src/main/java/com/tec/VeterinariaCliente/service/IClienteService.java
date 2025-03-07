package com.tec.VeterinariaCliente.service;

import java.util.List;

import com.tec.VeterinariaCliente.dtos.ClienteDto;
import com.tec.VeterinariaCliente.service.generic.ResponseGeneric;

public interface IClienteService {

	
	public ResponseGeneric<Boolean> crearCliente (ClienteDto clienteDto);
	
	public ResponseGeneric<List<ClienteDto>> listarCliente();
	
	public ResponseGeneric<ClienteDto> getClienteById(Long id);
	
	public ResponseGeneric<Boolean> updateCliente (Long id,ClienteDto clienteDto);
	
	public ResponseGeneric<Boolean> deleteCliente(Long id);
	
	
}
