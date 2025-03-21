package com.tec.VeterinariaCliente.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Veteritaria {


	private Long id;
	
	private String nombre;

	private String raza;

	private int edad;

	private Long clienteId;

	private Long responsableId;

	private Long veteritariaId;
	
	
}
