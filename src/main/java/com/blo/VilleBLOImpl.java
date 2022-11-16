package com.blo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VilleDAO;
import com.dto.Ville;

@Service
public class VilleBLOImpl implements VilleBLO {
	
	@Autowired
	private VilleDAO villeDAO;
	
	public List<Ville> getInfoVilles(String codePostal) {
		List<Ville> listVille;
		if (codePostal == null) {
			listVille = villeDAO.findAllVilles();
		}
		else {
			listVille = villeDAO.findVilleByPostalCode(codePostal);
		}
		return listVille;
	}

	public List<Ville> getVilleByPostalCode(String codePostal) {
		return villeDAO.findVilleByPostalCode(codePostal);
	}
	
	public Ville getVilleByCodeCommune(String codeCommune) {
		Ville ville = villeDAO.findVilleByCodeCommune(codeCommune);
		if (ville.getCodeCommune() == null) {
			return null;
		}
		else {
			return ville;
		}
	}
	
	public String addVille(String codeCommune, String nomCommune, String codePostal, String libelleAcheminement,
			String ligne, String latitude, String longitude) {
		return villeDAO.addVille(codeCommune, nomCommune, codePostal, libelleAcheminement, ligne, latitude, longitude);
	}
	
	public String modifyVille(String codeCommune, String nomCommune, String codePostal, String libelleAcheminement,
			String ligne, String latitude, String longitude) {
		return villeDAO.modifyVille(codeCommune, nomCommune, codePostal, libelleAcheminement, ligne, latitude, longitude);
	}

	public String deleteVille(String codeCommune) {
		return villeDAO.deleteVille(codeCommune);
	}
}
