package fr.sodifrance.mastermind.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import fr.sodifrance.mastermind.beans.plateau.Combinaison;
import fr.sodifrance.mastermind.beans.plateau.Couleur;
import fr.sodifrance.mastermind.beans.rest.Reponse;

@RunWith(MockitoJUnitRunner.class)
public class CombinaisonGeneratorServiceTest {

	@Spy
	private CombinaisonGeneratorService combinaisonGeneratorService;
	
	@Test
	public void genererCombinaison_should_return_Combinaison() throws Exception {
		// given
		// when
		Combinaison combinaison = combinaisonGeneratorService.genererCombinaison();
		// then
		assertThat(combinaison).isNotNull();
		assertThat(combinaison.getCouleurs()).isNotEmpty();
		assertThat(combinaison.getCouleurs()).hasSize(4);
	}
	
	@Test
	public void genererCombinaison_should_return_CombinaisonOfAllowedCouleurs() throws Exception {
		// given
		List<String> couleurs = new ArrayList<String>();
		couleurs.add("bleu");
		couleurs.add("jaune");
		couleurs.add("rouge");
		couleurs.add("vert");
		couleurs.add("orange");
		couleurs.add("violet");
		couleurs.add("noir");
		couleurs.add("blanc");
		// when
		Combinaison combinaison = combinaisonGeneratorService.genererCombinaison();
		// then
		for(Couleur couleur: combinaison.getCouleurs()) {
			assertThat(couleur.getNom()).isIn(couleurs);
		}
	}
	
	@Test(expected = Exception.class)
	public void genererCombinaison_should_validate_Couleur() throws Exception {
		// given
		when(combinaisonGeneratorService.createRandomCouleur()).thenReturn(new Couleur("zzzz"));
		// when
		combinaisonGeneratorService.genererCombinaison();
		// then ...Exception
	}
}
