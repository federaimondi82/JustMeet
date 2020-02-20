package trentaEFrode;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;

import unicam.trentaEFrode.domain.mainElements.DocuDiRegis;
import unicam.trentaEFrode.domain.mainElements.Registratore;

class TestRegistratore {

	Registratore reg = Registratore.getInstance();
	
	@Test
	public final void test_registraUtente() {
		DocuDiRegis docu = new DocuDiRegis("Mario", "Rossi", String.valueOf((int)(Math.random()*10000))+"@email.it", String.valueOf((int)(Math.random()*10000)), "abc", "abc", LocalDate.of(2011, Month.MARCH, 3), "06", "Roma", "RM");
		docu.setInteressi("1_2_");
		assertTrue(reg.registra(docu));
	}
	
	@Test
	public final void test_registraUtenteGiaPresente() {
		DocuDiRegis docu = new DocuDiRegis("Mario", "Rossi", "mariorossi@email.it", "marione", "abc", "abc", LocalDate.of(2011, Month.MARCH, 3), "06", "Roma", "");
		assertFalse(reg.registra(docu));
	}
}
