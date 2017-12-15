package com.test.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;


import com.test.beans.Utilisateur;

import com.test.dao.DAOFactory;

import com.test.dao.UtilisateurDao;


public class ListeInscrits extends HttpServlet {

    public static final String CONF_DAO_FACTORY = "daofactory";

    public static final String ATT_LISTUSER         = "listeinscrits";

    public static final String VUE              = "/listeinscrits.jsp";


    private UtilisateurDao     utilisateurDao;


    public void init() throws ServletException {

        /* Récupération d'une instance de notre DAO Utilisateur */

        this.utilisateurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();

    }


    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

       
    	ArrayList<Utilisateur> listeutilisateurs = utilisateurDao.trouverTous();
    	request.setAttribute( ATT_LISTUSER, listeutilisateurs );

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );

    }


    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

       this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );

    }

}