package Tests;

import java.sql.*;

public class TestLogInSignUpDAO {

	public static void main(String[] args) throws SQLException {
		LogInSignUpDAO logIn = new LogInSignUpDAO();
		
		//Test vérification compte existe
		if (logIn.compteExiste("admin", "admin")) {
		    System.out.println("Le compte existe");
		} else {
		    System.out.println("Le compte n'existe pas");
		}
		
		//Test vérification compte existe pas
		if (logIn.compteExiste("ad", "admin")) {
		    System.out.println("Le compte existe");
		} else {
		    System.out.println("Le compte n'existe pas");
		}
		
		// TEST POUR AJOUTER UN COMPTE (IL MARCHE DONC NE PAS EXECUTER OU ALORS SUPPRIMER ENSUITE)
		//System.out.println("Nombre de lignes ajoutées : " + logIn.addCompte("admin", "admin"));
		
		
		//test mdp correct avec une mdp correct
		if (logIn.mdpCorrect("admin", "admin")) {
			System.out.println("MDP CORRECT");
		} else {
			System.out.println("MDP INCORRECT");
		}
		
		//test mdp correct avec un mdp incorrect
		if (logIn.mdpCorrect("admin", "admni")) {
			System.out.println("MDP CORRECT");
		} else {
			System.out.println("MDP INCORRECT");
		}
	}

}
