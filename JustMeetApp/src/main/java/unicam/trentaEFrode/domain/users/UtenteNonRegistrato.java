package unicam.trentaEFrode.domain.users;

import java.time.LocalDate;
import java.util.List;

import unicam.trentaEFrode.domain.mainElements.DocuDiRegis;
import unicam.trentaEFrode.domain.mainElements.GestoreRegistrazioni;

public class UtenteNonRegistrato implements Utente {

	@Override
	public void inviaFeedback() {
		// TODO Auto-generated method stub
	}

	public List<Integer> compilaDocuDiRegis(String nome, String cognome, String email, String nickname, String password,
			String ripetiPassword, LocalDate data, String cap, String citta, String provincia) {
		DocuDiRegis docu = new DocuDiRegis(nome, cognome, email, nickname, password, ripetiPassword, data, cap, citta, provincia);
		return GestoreRegistrazioni.getInstance().effettuaControlli(docu);
	}

}