package fr.sodifrance.mastermind.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import fr.sodifrance.mastermind.beans.rest.Reponse;
import fr.sodifrance.mastermind.services.CombinaisonGeneratorService;

@Controller
public class MainController {
	
	private final String COMBINAISON = "combinaison";
	
	@Value("${server.port}")
	private String port;
	
	@Autowired
	private CombinaisonGeneratorService combinaisonGeneratorService;

	@GetMapping("/debuter")
	public ResponseEntity<Reponse> debuter(HttpSession session) {
		try {
			session.setAttribute(COMBINAISON, combinaisonGeneratorService.genererCombinaison());
			return new ResponseEntity<Reponse>(new Reponse("en cours", 1), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Reponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/info")
	public ResponseEntity<Integer> info() {
		return new ResponseEntity<Integer>(Integer.valueOf(port), HttpStatus.OK);
	}
}
