package com.test.beans;

import java.sql.Timestamp;

public class Station {

	private Long      id;

    private String    nom;

    private String    adresse;

    private String    ville;

    private String    codePostal;
    
    private String    nbPlaces;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;   
    }
    
    public void setNom(String nom) {
    	this.nom = nom;
    }
    
    public String getNom() {
    	return nom;
    }

    public void setAdresse(String adresse) {
    	this.adresse = adresse;
    }
    
    public String getAdresse() {
    	return adresse;
    }

    public void setVille(String ville) {
    	this.ville = ville;
    }
    
    public String getVille() {
    	return ville;
    }
    
    public void setCodePostal(String codePostal) {
    	this.codePostal = codePostal;
    }
    
    public String getCodePostal() {
    	return codePostal;
    }
    
    public void setNbPlaces(String nbPlaces) {
    	this.nbPlaces = nbPlaces;
    }
    
    public String getNbPlaces() {
    	return nbPlaces;
    }

	
}