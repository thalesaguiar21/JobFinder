package matchers;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import db.mannager.*;
import framework.DadosGlobais;
import framework.match.Matcher;

public class JobMatcher implements Matcher{
	
	public JobMatcher() {
		
	}
	
	public List<Job> allMatchs() {
		List<Map<String, Object>> trabalhos = new ArrayList<Map<String, Object>>();
		trabalhos = DadosGlobais.getDados().getMyDb().select("SELECT se.title, se.role FROM job se");

		List<Job> jobOffers = new ArrayList<Job>();
		
		for(int i = 0; i < trabalhos.size(); i++){
			Job newSe = new Job((String)trabalhos.get(i).get("title"),"",(String)trabalhos.get(i).get("role"),"",0);
			jobOffers.add(newSe);
		}
		
		return jobOffers;
	}

	public List<Job> matchByName(String profissao) {
		List<Map<String, Object>> trabalhos = new ArrayList<Map<String, Object>>();
		trabalhos = DadosGlobais.getDados().getMyDb().select("SELECT se.title, se.role FROM job se\n" +
																	 "WHERE se.role ILIKE '%"+ profissao +"%';");
		List<Job> jobOffers = new ArrayList<Job>();
		
		for(int i = 0; i < trabalhos.size(); i++){
			Job newSe = new Job((String)trabalhos.get(i).get("title"),"",(String)trabalhos.get(i).get("role"),"",0);
			jobOffers.add(newSe);
		}

		return jobOffers;
	}

	public List<Job> matchByNames(List<String> nomes) {
		return null;
	}

}
