package com.tec.VeterinariaCliente.mapper.clientesDtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tec.VeterinariaCliente.dtos.ClienteDto;
import com.tec.VeterinariaCliente.model.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class ClientesMapperImp implements ClienteDtos {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public Boolean crearCliente(ClienteDto clientDtos) {

		try {
			Cliente cliente = new Cliente();
			// cliente.setId(clientDtos.getId());
			cliente.setNombre(clientDtos.getNombre());
			cliente.setDireccion(clientDtos.getDireccion());
			cliente.setContacto(clientDtos.getContacto());

			this.em.persist(cliente);
			this.em.flush(); // SINCRINIZA LOS CAMBIOS PEDIENTES EN LAS ENTIDADES CON LA BASE DE DATOS
			return true; // retinamos un true en caso de inserccion

		} catch (Exception e) {

			return false;
		}
	}

	@Override
	@Transactional
	public Boolean updateCliente(ClienteDto clientDtos) {
		try {
			Cliente cliente = this.em.find(Cliente.class, clientDtos.getId());
			// cliente.setId(clientDtos.getId());
			cliente.setNombre(clientDtos.getNombre());
			cliente.setDireccion(clientDtos.getDireccion());
			cliente.setContacto(clientDtos.getContacto());

			this.em.merge(cliente);
			return true; // retornamos un true en caso de inserccion
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * El método createQuery se utiliza para crear consultas dinámicas, que son
	 * consultas definidas directamente dentro de la lógica empresarial de una
	 * aplicación. ejemplo public List findWithName(String name) { return
	 * em.createQuery( "SELECT c FROM Customer c WHERE c.name LIKE :custName")
	 * .setParameter("custName", name) .setMaxResults(10) .getResultList(); }
	 * 
	 */
	@Override
	@Transactional
	public Long exisNumberPhone(Long numberCLiente) {

		System.out.print("Entro al meotod");
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Long> query = cb.createQuery(Long.class);// inicamos que devolvera
		Root<Cliente> root = query.from(Cliente.class);// accesa a que objecto de nuestro JPA
		query.select(cb.count(root)) // SELECT EL CAMPO
				.where(cb.equal(root.get("contacto"), numberCLiente));/// LA CONDICION QUE SE VA AL FILTRAR
		// https://refactorizando.com/criteria-queries-spring-data/
		if (em.createQuery(query).getSingleResult() > 0) {
			return 1L;// si exiete registro mayor al conteo retorna 1
		} else {
			return 0L; // caso contrario retorna 0
		}

	}

	// tomar la informacion del la entidad cliente
	@Override
	@Transactional
	public ClienteDto toClientes(Optional<Cliente> cliente) {

		if (cliente == null) {
			return null;
		}
		ClienteDto clienteDto = new ClienteDto();

		clienteDto.setId(cliente.get().getId());
		clienteDto.setNombre(cliente.get().getNombre());
		clienteDto.setDireccion(cliente.get().getDireccion());
		clienteDto.setContacto(cliente.get().getContacto());

		return clienteDto; // retornamos un dto

	}

	@Transactional
	public ClienteDto toCliente(Cliente cliente) {

		if (cliente == null) {
			return null;
		}

		ClienteDto clienteDto = new ClienteDto();

		clienteDto.setId(cliente.getId());
		clienteDto.setNombre(cliente.getNombre());
		clienteDto.setDireccion(cliente.getDireccion());
		clienteDto.setContacto(cliente.getContacto());

		return clienteDto; // retornamos un dto

	}

	public Cliente toClienteEntity(ClienteDto clienteDto) {

		if (clienteDto == null) {
			return null;
		}

		Cliente cliente = new Cliente();

		cliente.setId(clienteDto.getId());
		cliente.setNombre(clienteDto.getNombre());
		cliente.setDireccion(clienteDto.getDireccion());
		cliente.setContacto(clienteDto.getContacto());

		return cliente; // retornamos una entidad

	}

	@Override
	public List<ClienteDto> listarClientesDto(List<Cliente> clientes) {
		// TODO Auto-generated method stub
		if (clientes == null) {
			return null;
		} else {
			List<ClienteDto> myClienteListDto = new ArrayList<ClienteDto>(clientes.size());
			System.out.println("datos triados son " + clientes.size());
			for (Cliente cliente : clientes) {
				myClienteListDto.add(this.toCliente(cliente));
			}
			return myClienteListDto;

		}
	}

	@Transactional(readOnly = true)
	public List<Cliente> listarCliente() {

		return this.em.createQuery("SELECT u FROM Cliente u", Cliente.class).getResultList();
	}

	@Override
	public Optional<Cliente> findbyIdCliente(long idCliente) {
		return Optional.ofNullable(this.em.find(Cliente.class, idCliente));
	}

	@Override
	@Transactional
	// https://www.udb.edu.sv/udb_files/recursos_guias/informatica-ingenieria/java-avanzado/2020/i/guia-13.pdf
	public Boolean deleteClienteDao(Optional<Cliente> encontrado) {

		if (!encontrado.isPresent()) {
			return false;
		}
		try {

			Cliente cliente = this.em.find(Cliente.class, encontrado.get().getId());

			if (cliente != null) {
				cliente = this.em.merge(cliente);// confirmamaos que este la data
				this.em.remove(cliente);// lo eliminamos
				return true;// trurn true
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

	}

}
