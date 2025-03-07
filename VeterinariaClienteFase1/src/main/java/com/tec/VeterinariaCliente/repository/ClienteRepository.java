package com.tec.VeterinariaCliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tec.VeterinariaCliente.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	
	//public Cliente findByIdCliente(String nombre);
	
	
}
