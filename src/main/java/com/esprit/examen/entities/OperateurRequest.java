package com.esprit.examen.entities;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperateurRequest {

	
	private Long idOperateur;
	private String nom;
	private String prenom;
	
	private String password;
}
