package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.dto.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO {
	
//	public ArrayList<Ville> findAllVilles() {
//		System.out.println("findAllVilles");
//		
//		ArrayList<Ville> listVille = new ArrayList<Ville>();
//		
//		Ville ville = new Ville();
//		ville.setCodePostal("44000");
//		ville.setLigne("ligne");
//		ville.setNomCommune("test");
//		
//		listVille.add(ville);
//		
//		return listVille;
//	}
	
	public ArrayList<Ville> findAllVilles() {
		System.out.println("findAllVilles");
		
		ArrayList<Ville> listVille = new ArrayList<Ville>();
		
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con = DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/twic","root","h2bzVR7L6");  

			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("select * from ville_france");  
			while(rs.next()) {
				Ville ville = new Ville(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				listVille.add(ville);
			}
			con.close();  
		} catch(Exception e){ 
			System.out.println(e);
		}
		return listVille;
	}  
	
	public ArrayList<Ville> findVilleByPostalCode(String codePostal) {
		System.out.println("findAllVilles");
		
		ArrayList<Ville> listVille = new ArrayList<Ville>();
		
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con = DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/twic","root","h2bzVR7L6");  

			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("select * from ville_france WHERE Code_postal = " + codePostal);  
			while(rs.next()) {
				Ville ville = new Ville(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				listVille.add(ville);
			}
			con.close();  
		} catch(Exception e){ 
			System.out.println(e);
		}
		return listVille;
	}  
	
}
