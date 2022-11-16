package com.blo;

import java.util.List;

import com.dto.Ville;

public interface VilleBLO {
	public List<Ville> getInfoVilles(String codePostal);

	public List<Ville> getVilleByPostalCode(String codePostal);

	public Ville getVilleByCodeCommune(String codeCommune);

	public String addVille(String codeCommune, String nomCommune, String codePostal, String libelleAcheminement, String ligne, String latitude, String longitude);

	public String modifyVille(String codeCommune, String nomCommune, String codePostal, String libelleAcheminement,
			String ligne, String latitude, String longitude);
	
	public String deleteVille(String codeCommune);
}
