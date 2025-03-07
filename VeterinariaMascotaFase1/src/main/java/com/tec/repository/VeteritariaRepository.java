package com.tec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tec.model.Veteritaria;

@Repository
public interface VeteritariaRepository extends JpaRepository<Veteritaria, Long> {

	
	public List<Veteritaria> findByVeteritariaId(int veteritariaId);
	public List<Veteritaria> findByClienteId(int clienteId);
	public List<Veteritaria> findByResponsableId(int responsableId);
	
	
}
