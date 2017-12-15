package com.test.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.test.beans.Station;
import com.test.dao.DAOException;
import com.test.dao.StationDao;

public final class AjoutStationForm {
    private static final String CHAMP_NOM      = "nom";
    private static final String CHAMP_ADRESSE       = "adresse";
    private static final String CHAMP_VILLE       = "ville";
    private static final String CHAMP_CODEPOSTAL       = "codePostal";
    private static final String CHAMP_NBPLACES       = "nbPlaces";


    private static final String ALGO_CHIFFREMENT = "SHA-256";

    private String              resultat;
    private Map<String, String> erreurs          = new HashMap<String, String>();
    private StationDao      stationDao;

    public AjoutStationForm( StationDao stationDao ) {
        this.stationDao = stationDao;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Station ajouterStation( HttpServletRequest request ) {
        String nom = getValeurChamp( request, CHAMP_NOM );
        String adresse = getValeurChamp( request, CHAMP_ADRESSE );
        String ville = getValeurChamp( request, CHAMP_VILLE );
        String codePostal = getValeurChamp( request, CHAMP_CODEPOSTAL );
        String nbPlaces = getValeurChamp( request, CHAMP_NBPLACES );
        
        Station station = new Station();
        try {
            traiterNom( nom, station );
            traiterAdresse( adresse, station );
            traiterVille( ville, station );
            traiterCodePostal( codePostal, station );
            traiterNbPlaces( nbPlaces, station );

            if ( erreurs.isEmpty() ) {
                stationDao.creer( station );
                resultat = "Succès de l'ajout.";
            } else {
                resultat = "Échec de l'ajout.";
            }
        } catch ( DAOException e ) {
            resultat = "Échec de l'ajout : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }

        return station;
    }

    /*
     * Appel à la validation du nom reçue et initialisation de la
     * propriété nom du bean
     */
    private void traiterNom( String nom, Station station ) {
        try {
            validationNom( nom );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }
        station.setNom( nom );
    }

    /*
     * Appel à la validation de l'adresse reçue et initialisation de la
     * propriété adresse du bean
     */
    private void traiterAdresse( String adresse, Station station ) {
        try {
            validationAdresse( adresse );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_ADRESSE, e.getMessage() );
        }
        station.setAdresse( adresse );
    }
    
    
    /*
     * Appel à la validation de la ville reçue et initialisation de la
     * propriété ville du bean
     */
    private void traiterVille( String ville, Station station ) {
        try {
            validationVille( ville );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_VILLE, e.getMessage() );
        }
        station.setVille( ville );
    }

    
    /*
     * Appel à la validation du code postal reçue et initialisation de la
     * propriété codePostal du bean
     */
    private void traiterCodePostal( String codePostal, Station station ) {
        try {
            validationCodePostal( codePostal );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_CODEPOSTAL, e.getMessage() );
        }
        station.setCodePostal( codePostal );
    }
   
    
    /*
     * Appel à la validation du nombre de places reçue et initialisation de la
     * propriété nbPlaces du bean
     */
    private void traiterNbPlaces( String nbPlaces, Station station ) {
        try {
            validationNbPlaces( nbPlaces );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_NBPLACES, e.getMessage() );
        }
        station.setNbPlaces( nbPlaces );
    }

    /* Validation du nom */
    private void validationNom( String nom ) throws FormValidationException {
        if ( nom != null && nom.length() < 4 ) {
            throw new FormValidationException( "Le nom de la station doit contenir au moins 4 caractères." );
        }
    }

    
    /* Validation de l'adresse */
    private void validationAdresse( String adresse ) throws FormValidationException {
        if ( adresse != null && adresse.length() < 10 ) {
            throw new FormValidationException( "L'adresse doit contenir au moins 10 caractères." );
        }
    }

    
    /* Validation de la ville */
    private void validationVille( String ville ) throws FormValidationException {
        if ( ville != null && ville.length() < 5 ) {
            throw new FormValidationException( "Le nom de la ville doit contenir au moins 5 caractères." );
        }
    }
    
    
    /* Validation du code postal */
    private void validationCodePostal( String codePostal ) throws FormValidationException {
        if ( codePostal != null && codePostal.length() < 3 && codePostal.length() > 7 ) {
            throw new FormValidationException( "Le code postal doit contenir entre 4 et 6 caractères." );
        }
    }
    
    
    /* Validation du nombre de places */
    private void validationNbPlaces( String nbPlaces ) throws FormValidationException {
        if ( nbPlaces != null && nbPlaces.length() < 0 && nbPlaces.length() > 3 ) {
            throw new FormValidationException( "Le nombre de places doit être de 2 caractères." );
        }
    }
    
    
    
    
    
    
    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}