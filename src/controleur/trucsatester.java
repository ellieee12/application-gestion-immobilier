package controleur;

import java.sql.SQLException;

import classes.Compteur.typeCompteur;
import modeleDAO.CompteurDAO;

public class trucsatester {

	public static void main(String[] args) throws SQLException {
		
		CompteurDAO dao = new CompteurDAO();
		System.out.println(dao.getCompteurFromOneBienSelonType("toto", typeCompteur.EAU));
		
	}

}
