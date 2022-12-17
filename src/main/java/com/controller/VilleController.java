package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blo.VilleBLO;
import com.dto.Ville;

@RestController
public class VilleController {

	@Autowired
	VilleBLO villeBLOService;

	@GetMapping("/ville")
	@ResponseBody
	public List<Ville> getVilles(@RequestParam(required = false, value = "codePostal") String codePostal) {
		return villeBLOService.getInfoVilles(codePostal);
	}

	@GetMapping("/villes/{codePostal}")
	@ResponseBody
	public List<Ville> getVilleByCodePostal(@PathVariable("codePostal") String codePostal) {
		return villeBLOService.getVilleByPostalCode(codePostal);
	}

	@GetMapping("/ville/{codeCommune}")
	@ResponseBody
	public Ville getVilleByCodeCommune(@PathVariable("codeCommune") String codeCommune) {
		return villeBLOService.getVilleByCodeCommune(codeCommune);
	}

	@PostMapping("/ville/add")
	@ResponseBody
	public String addVille(@RequestParam(required = true, value="codeCommune") String codeCommune,
			@RequestParam(required = true, value="nomCommune") String nomCommune,
			@RequestParam(required = true, value="codePostal") String codePostal,
			@RequestParam(required = false, value="libelleAcheminement") String libelleAcheminement,
			@RequestParam(required = false, value="ligne") String ligne,
			@RequestParam(required = false, value="latitude") String latitude,
			@RequestParam(required = false, value="longitude") String longitude
			){
		return villeBLOService.addVille(codeCommune, nomCommune, codePostal, libelleAcheminement, ligne, latitude, longitude);
	}

	@PutMapping("/ville/put")
	@ResponseBody
	public String putVille(@RequestParam(required = true, value="codeCommune") String codeCommune,
			@RequestParam(required = true, value="nomCommune") String nomCommune,
			@RequestParam(required = true, value="codePostal") String codePostal,
			@RequestParam(required = false, value="libelleAcheminement") String libelleAcheminement,
			@RequestParam(required = false, value="ligne") String ligne,
			@RequestParam(required = false, value="latitude") String latitude,
			@RequestParam(required = false, value="longitude") String longitude) {
		try {
			return villeBLOService.modifyVille(codeCommune, nomCommune.replace("_", " "), codePostal, libelleAcheminement.replace("_", " "), ligne.replace("_", " "), latitude, longitude);
		}
		catch(Exception e) {
			return villeBLOService.addVille(codeCommune, nomCommune, codePostal, libelleAcheminement, ligne, latitude, longitude);
		}
	}

	@DeleteMapping("/ville/delete")
	@ResponseBody
	public String deleteVille(@RequestParam(required = true, value="codeCommune") String codeCommune) {
		return villeBLOService.deleteVille(codeCommune);
	}
}
