package com.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blo.VilleBLO;
import com.dto.Ville;

@RestController
public class VilleController {

	@Autowired
	VilleBLO villeBLOService;
	
	// fonction pour récupérer le contenu de la BDD
	@RequestMapping(value = "/ville", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Ville> get(@RequestParam(required = false, value = "codePostal") String codePostal) {
		System.out.println("get");
		// TODO : mon code vers la BDD
		
		ArrayList<Ville> listeVille = villeBLOService.getInfoVilles(codePostal);
		
		return listeVille;
	}
	
	@GetMapping(value = "/ville/{codePostal}")
	@ResponseBody
	public ArrayList<Ville> getVilleByCodePostal(@PathVariable("codePostal") String codePostal) {
		System.out.println("getVilleByPostalCode");
		
		ArrayList<Ville> listeVille = villeBLOService.getVilleByPostalCode(codePostal);
		
		return listeVille;
	}
	
	// TODO :
	// fonction pour enregistrer un élément dans la BDD
//	@PostMapping(value = "/ville")
//	public ResponseEntity<Ville> addVille(@RequestBody Ville ville) {
//		Ville ville = villeBLOService.save(ville);
//	    if (ville == null) {
//	        throw new ServerException();
//	    } else {
//	        return new ResponseEntity<>(user, HttpStatus.CREATED);
//	    }
//	}
}
