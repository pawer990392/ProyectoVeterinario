package com.tec.Veterinaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tec.Veterinaria.model.Veterinaria;



@Repository
public interface VeterinariaRepository extends JpaRepository<Veterinaria, Long> {

    public List<Veterinaria> findByNombreIgnoreCase(String nombre);
	
}
