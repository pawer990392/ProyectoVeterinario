package com.tec.VeterinariarResponsable.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tec.VeterinariarResponsable.model.Responsable;


@Repository
public interface ResponsableRepository extends JpaRepository<Responsable, Long>{

	public List<Responsable> findByVeteritariaId(int veteritariaId);
	
}
