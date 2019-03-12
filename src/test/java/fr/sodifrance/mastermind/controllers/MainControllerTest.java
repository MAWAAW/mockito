package fr.sodifrance.mastermind.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ReflectionUtils;

import fr.sodifrance.mastermind.beans.plateau.Combinaison;
import fr.sodifrance.mastermind.beans.rest.Reponse;
import fr.sodifrance.mastermind.services.CombinaisonGeneratorService;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {

	@InjectMocks
	private MainController mainController;

	@Mock
	private HttpSession session;

	@Mock
	private CombinaisonGeneratorService combinaisonGeneratorService;

	@Test
	public void debuter_should_return_ResponseEntity() {
		// given
		// when
		ResponseEntity<Reponse> result = mainController.debuter(session);
		// then
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result.getBody().getEtat()).isEqualTo("en cours");
		assertThat(result.getBody().getNumProposition()).isEqualTo(1);
	}

	@Test
	public void debuter_should_generate_combinaison() throws Exception{
		// given
		// when
		mainController.debuter(session);
		// then
		verify(combinaisonGeneratorService).genererCombinaison();
	}

	@Test
	public void debuter_should_save_combinaison() throws Exception{
		// given
		ArgumentCaptor<String> param1 = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Combinaison> param2 = ArgumentCaptor.forClass(Combinaison.class);
		Combinaison combinaison = new Combinaison();
		when(combinaisonGeneratorService.genererCombinaison()).thenReturn(combinaison);
		// when
		mainController.debuter(session);
		// then
		verify(session).setAttribute(param1.capture(), param2.capture());
		assertThat(param1.getValue()).isEqualTo("combinaison");
		assertThat(param2.getValue()).isEqualTo(combinaison);
	}
	
	@Test
	public void debuter_should_return_code500() throws Exception {
		// given
		when(combinaisonGeneratorService.genererCombinaison()).thenThrow(new UnsupportedOperationException());
		// when
		ResponseEntity<Reponse> result = mainController.debuter(session);
		// then
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Test
	public void info_should_return_listenningPort() {
		// given
		Integer port = 123;
		ReflectionTestUtils.setField(mainController, "port", Integer.toString(port));
		// when
		ResponseEntity<Integer> result = mainController.info();
		// then
		assertThat(result).isNotNull();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result.getBody().intValue()).isEqualTo(port);
	}

}
