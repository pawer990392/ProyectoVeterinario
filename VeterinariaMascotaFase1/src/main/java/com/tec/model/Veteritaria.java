package com.tec.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="v_Mascota")
@Builder
public class Veteritaria {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_Mascota")
	private Long id;
	@Column(name="nombre")
	private String nombre;
	@Column(name="raza")
	private String raza;
	@Column(name="edad")
	private int edad;
	@Column(name="cliente_id")
	private Long clienteId;
	@Column(name="responsable_id")
	private Long responsableId;
	@Column(name="Veteritaria_id")
	private Long veteritariaId;
	
	
	
	
	
	
	
}
