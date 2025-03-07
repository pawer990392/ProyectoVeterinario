package com.tec.VeterinariaCliente.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {

	
	private long id;
	// --------------------------------------------------------------
	
	private String nombre;
	// --------------------------------------------------------------
	
	private String direccion;
	// --------------------------------------------------------------
	
	private long contacto;
	
	
	 public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public long getContacto() {
		return contacto;
	}
	public void setContacto(long contacto) {
		this.contacto = contacto;
	}
	// --------------------------------------------------------------
	
	

	
}
