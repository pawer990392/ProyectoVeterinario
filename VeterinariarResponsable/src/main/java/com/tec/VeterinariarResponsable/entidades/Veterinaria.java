package com.tec.VeterinariarResponsable.entidades;

import lombok.Data;

@Data
public class Veterinaria {

	
	private Long id;
	
	private String nombre;

	private String raza;

	private int edad;

	private Long clienteId;

	private Long responsableId;

	private Long veteritariaId;
	
	
}
