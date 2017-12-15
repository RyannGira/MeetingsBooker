package com.test.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;


import com.test.beans.Station;

import com.test.dao.DAOFactory;

import com.test.dao.StationDao;


public class ListeStations extends HttpServlet {

    public static final String CONF_DAO_FACTORY = "daofactory";

    public static final String ATT_LISTSTATION         = "listestations";

    public static final String VUE              = "/listestations.jsp";


    private StationDao     stationDao;


    public void init() throws ServletException {

        /* Récupération d'une instance de notre DAO Utilisateur */

        this.stationDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getStationDao();

    }


    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

       
    	ArrayList<Station> listestations = stationDao.trouverTous();
    	request.setAttribute( ATT_LISTSTATION, listestations );

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );

    }


    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

       this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );

    }

}