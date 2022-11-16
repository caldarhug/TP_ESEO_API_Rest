package com.dao;

import java.util.List;

import com.dto.Ville;

public interface VilleDAO {
	
	public List<Ville> findAllVilles();
	
	public List<Ville> findVilleByPostalCode(String codePostal);
	
	public Ville findVilleByCodeCommune(String codeCommune);

	public String addVille(String codeCommune, String nomCommune, String codePostal,
			String libelleAcheminement, String ligne, String latitude, String longitude);

	public String modifyVille(String codeCommune, String nomCommune, String codePostal,
			String libelleAcheminement, String ligne, String latitude, String longitude);
	
	public String deleteVille(String codeCommune);
}
