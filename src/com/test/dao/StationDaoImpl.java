package com.test.dao;

import static com.test.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.test.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.test.beans.Station;

public class StationDaoImpl implements StationDao {

    private static final String SQL_SELECT_PAR_NOM = "SELECT * FROM Station WHERE nom = ?";
    private static final String SQL_SELECT = "SELECT * FROM Station";
    private static final String SQL_INSERT           = "INSERT INTO Station (nom, adresse, ville, codePostal, nbPlaces) VALUES (?, ?, ?, ?, ?)";

    private DAOFactory          daoFactory;

    StationDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    /* Implémentation de la méthode définie dans l'interface StationDao */
    @Override
    public Station trouver( String nom ) throws DAOException {
        return trouver( SQL_SELECT_PAR_NOM, nom );
    }
    /* Implémentation de la méthode définie dans l'interface StationDao */
    @Override
    public ArrayList<Station> trouverTous() throws DAOException {
    	  	return trouverTous( SQL_SELECT);
    }

    /* Implémentation de la méthode définie dans l'interface StationDao */
    @Override
    public void creer( Station station ) throws DAOException {
    	Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, station.getNom(), station.getAdresse(), station.getVille(), station.getCodePostal(), Integer.parseInt(station.getNbPlaces()) );
            
           /* System.out.println(SQL_INSERT);
            System.out.println(station.getNom());
            System.out.println(station.getAdresse());
            System.out.println(station.getVille());
            System.out.println(station.getCodePostal());
            System.out.println(station.getNbPlaces());
            */
            
            int statut = preparedStatement.executeUpdate();
            
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de la station, aucune ligne ajoutée dans la table." );
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                station.setId( valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new DAOException( "Échec de la création de la station en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }

    /*
     * Méthode générique utilisée pour retourner un station depuis la base
     * de données, correspondant à la requête SQL donnée prenant en paramètres
     * les objets passés en argument.
     */
    private Station trouver( String sql, Object... objets ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Station station = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            /*
             * Préparation de la requête avec les objets passés en arguments
             * (ici, uniquement une adresse email) et exécution.
             */
            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données retournée dans le ResultSet */
            if ( resultSet.next() ) {
                station = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return station;
    }

    /*
     * Méthode générique utilisée pour retourner une liste de stations depuis la base
     * de données, correspondant à la requête SQL donnée prenant en paramètres
     * les objets passés en argument.
     */
    private ArrayList<Station> trouverTous( String sql, Object... objets ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Station station = null;
        ArrayList<Station> listestations=new ArrayList<Station>();

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            /*
             * Préparation de la requête avec les objets passés en arguments
             * (ici, uniquement une adresse email) et exécution.
             */
            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données retournée dans le ResultSet */
            while ( resultSet.next() ) {
            	
                station = map( resultSet );
                System.out.println("nom:"+ station.getNom());
                listestations.add(station);
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return listestations;
    }

    
    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des stations (un
     * ResultSet) et un bean Station.
     */
    private static Station map( ResultSet resultSet ) throws SQLException {
        Station station = new Station();
        station.setId( resultSet.getLong( "id" ) );
        station.setNom( resultSet.getString( "nom" ) );
        station.setAdresse( resultSet.getString( "adresse" ) );
        station.setVille( resultSet.getString( "ville" ) );
        station.setCodePostal( resultSet.getString( "codePostal" ) );
        station.setNbPlaces( resultSet.getString( "nbPlaces" ) );
        return station;
    }

    
   
}



