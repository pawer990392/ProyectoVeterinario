package com.tec.VeterinariaCliente.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="v_Cliente")
@Builder
public class Cliente {

	  // --------------------------------------------------------------
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		// --------------------------------------------------------------
		private String nombre;
		// --------------------------------------------------------------
		private String direccion;
		// --------------------------------------------------------------
		private long contacto;
	
	
	
}
