package com.tec.VeterinariaCliente.mapper.clientesDtos;


import java.util.List;
import java.util.Optional;

import com.tec.VeterinariaCliente.dtos.ClienteDto;
import com.tec.VeterinariaCliente.model.Cliente;




public interface ClienteDtos {

	
	public Boolean crearCliente(ClienteDto clientDtos);
	public Long exisNumberPhone(Long numberCLiente);
	public List<Cliente> listarCliente();
	public List<ClienteDto> listarClientesDto(List<Cliente> clientes);
    public Optional<Cliente> findbyIdCliente(long idCliente);
	public Boolean updateCliente(ClienteDto clientDtos);
	public ClienteDto toClientes(Optional<Cliente> encontrado);
	public Boolean deleteClienteDao(Optional<Cliente> encontrado);

	
	
	//public ClienteDto toClientes(Cliente cliente);
	
	
	//public void updateCliente(Cliente cliente, ClienteDto clienteDtos);
	
	
}
