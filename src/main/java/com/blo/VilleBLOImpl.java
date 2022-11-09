package com.blo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VilleDAO;
import com.dto.Ville;

@Service
public class VilleBLOImpl implements VilleBLO {
	
	@Autowired
	private VilleDAO villeDAO;
	
	public ArrayList<Ville> getInfoVilles(String codePostal) {
		ArrayList<Ville> listVille = new ArrayList<Ville>();
		
		listVille = villeDAO.findAllVilles();
		return listVille;
	}

	public ArrayList<Ville> getVilleByPostalCode(String codePostal) {
		ArrayList<Ville> listVille = new ArrayList<Ville>();
		listVille = villeDAO.findVilleByPostalCode(codePostal);
		return listVille;
	}
}
