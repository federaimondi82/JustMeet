package unicam.trentaEFrode.domain.parsers;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import unicam.trentaEFrode.domain.mainElements.Categoria;
import unicam.trentaEFrode.domain.mainElements.Evento;
import unicam.trentaEFrode.domain.mainElements.Luogo;

public class ParserEventi {

	private static ParserEventi instance = null;

	public static ParserEventi getInstance() {
		if (instance == null)
			instance = new ParserEventi();
		return instance;
	}

//	eventi = {13-862-2020/1/1-11:11-10-100-festa-5-8-Ascoli-viaTuring-5672-63100-AP-bar-2};{20-5359-2020/1/1-11:11-10-100-festa-5-{17-Ascoli-viaTuring-3183-63100-AP-bar}2};{11-capodanno-2020/1/1-11:11-10-100-festa-5-{1-Ascoli-viaTuring-10-63100-AP-bar}2};{27-9333-2020/1/1-11:11-10-100-festa-5-{27-Ascoli-viaTuring-6685-63100-AP-bar}2};{18-238-2020/1/1-11:11-10-100-festa-5-{15-Ascoli-viaTuring-9175-63100-AP-bar}2};{25-404-2020/1/1-11:11-10-100-festa-5-{24-Ascoli-viaTuring-1313-63100-AP-bar}2};{16-8742-2020/1/1-11:11-10-100-festa-5-{11-Ascoli-viaTuring-8172-63100-AP-bar}2};{23-3285-2020/1/1-11:11-10-100-festa-5-{20-Ascoli-viaTuring-3526-63100-AP-bar}2};{14-8076-2020/1/1-11:11-10-100-festa-5-{9-Ascoli-viaTuring-8179-63100-AP-bar}2};{21-1644-2020/1/1-11:11-10-100-festa-5-{18-Ascoli-viaTuring-939-63100-AP-bar}2};{12-8645-2020/1/1-11:11-10-100-festa-5-{7-Ascoli-viaTuring-2226-63100-AP-bar}2};{19-1047-2020/1/1-11:11-10-100-festa-5-{16-Ascoli-viaTuring-1471-63100-AP-bar}2};{26-1172-2020/1/1-11:11-10-100-festa-5-{26-Ascoli-viaTuring-8428-63100-AP-bar}2};{17-5346-2020/1/1-11:11-10-100-festa-5-{14-Ascoli-viaTuring-6220-63100-AP-bar}2};{24-1462-2020/1/1-11:11-10-100-festa-5-{21-Ascoli-viaTuring-6301-63100-AP-bar}2};{15-8846-2020/1/1-11:11-10-100-festa-5-{10-Ascoli-viaTuring-2048-63100-AP-bar}2};{22-9341-2020/1/1-11:11-10-100-festa-5-{19-Ascoli-viaTuring-1258-63100-AP-bar}2};{28-Pranzo_di_beneficenza-2020/3/12-14:36-3-100-I_soldi_ricavati_andranno_in_beneficenza-3-{29-Ascoli_Piceno-via_della_cucina-7060-63100-AP-Ristorante_Casa_Mia}4};{29-Pranzo_di_beneficenza-2020/3/12-14:37-3-100-I_soldi_ricavati_andranno_in_beneficenza-3-{30-Ascoli_Piceno-via_della_cucina-2194-63100-AP-Ristorante_Casa_Mia}4};{30-Pranzo_di_beneficenza-2020/3/12-16:1-3-100-I_soldi_ricavati_andranno_in_beneficenza-3-{31-Ascoli_Piceno-via_della_cucina-8904-63100-AP-Ristorante_Casa_Mia}4};{31-Pranzo_di_beneficenza-2020/3/12-16:39-3-100-I_soldi_ricavati_andranno_in_beneficenza-3-{32-Ascoli_Piceno-via_della_cucina-1773-63100-AP-Ristorante_Casa_Mia}4};{10-capodanno2-2021/1/1-11:11-10-100-festa-5-{3-Roma-viaTuring-11-63100-RM-bar1}2};
	public List<Evento> parseEventi(String newJson) {

		if (newJson == "") return null;

		System.out.println("json 23 parsertest = " + newJson);

		String[] agenda = newJson.split(";");
		String[] evento;
		List<Evento> eventi = new ArrayList<Evento>();
		for (int i = 0; i < agenda.length; i++) {
			agenda[i] = agenda[i].substring(1, agenda[i].length() - 1);
			evento = agenda[i].split("-");
			eventi.add(new Evento(evento[1], // nome
					new GregorianCalendar(Integer.parseInt(evento[2].split("/")[0]),
							Integer.parseInt(evento[2].split("/")[1]), Integer.parseInt(evento[2].split("/")[2]),
							Integer.parseInt(evento[3].split(":")[0]), Integer.parseInt(evento[3].split(":")[0])), // dataora
					Integer.parseInt(evento[4]), Integer.parseInt(evento[5]), // min max
					evento[6], // descr
					Integer.parseInt(evento[7]), // durata
					new Luogo(evento[9], evento[10], evento[11], evento[12], evento[13], evento[14]),
					new Categoria(Integer.parseInt(evento[15]), evento[16], evento[17]))
							.setId(Integer.parseInt(evento[0])).setOrganizzatore(Integer.parseInt(evento[8])));
		}
		return eventi;
	}
}
