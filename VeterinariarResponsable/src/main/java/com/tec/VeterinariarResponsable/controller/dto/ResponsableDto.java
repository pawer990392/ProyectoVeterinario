package com.tec.VeterinariarResponsable.controller.dto;


public class ResponsableDto {

	@Override
	public String toString() {
		return "ResponsableDto [id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", veteritariaId="
				+ veteritariaId + "]";
	}

	private Long id;
	private String nombre;
	private Long telefono;
	private Long veteritariaId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	public Long getTelefono() {
		return telefono;
	}
	

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public Long getVeteritariaId() {
		return veteritariaId;
	}
	

	public void setVeteritariaId(Long veteritariaId) {
		this.veteritariaId = veteritariaId;
	}
	
	public ResponsableDto(Long id, String nombre, Long telefono, Long veteritariaId) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
		this.veteritariaId = veteritariaId;
	}
	
	
}
