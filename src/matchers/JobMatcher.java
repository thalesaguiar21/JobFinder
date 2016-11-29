package matchers;
import java.util.ArrayList;
import java.util.List;

import db.mannager.*;
import framework.DadosGlobais;
import framework.match.Matcher;

public class JobMatcher implements Matcher{
	
	public JobMatcher() {
		
	}
	
	public List<Job> allMatchs() {
		/*List<Map<String, Object>> servIncoerentes = new ArrayList<Map<String, Object>>();
		servIncoerentes = DadosGlobais.getDados().getMyDb().select("SELECT se.nome, se.cpf FROM BolsaFamilia bf, Servidor se\n" +
																	 "WHERE substring(se.cpf from 4 for 9) = substring(bf.cpf from 4 for 9)\n" +
																	 "ORDER BY se.nome, se.cpf;");*/
		List<Job> jobOffers = new ArrayList<Job>();
		
		/*for(int i = 0; i < servIncoerentes.size(); i++){
			Job newSe = new Job((String)servIncoerentes.get(i).get("nome"),
														(String)servIncoerentes.get(i).get("cpf"));
			servidores.add(newSe);
		}*/
		
		return jobOffers;
	}

	public List<Job> matchByName(String nome) {
		List<Job> jobOffers = new ArrayList<Job>();
		for(Job j : DadosGlobais.getDados().getServidoresPublicos()){
			String jobTitle = j.getTitle().trim();
			if(jobTitle.equalsIgnoreCase(nome)) jobOffers.add(j);
		}
		return jobOffers;
	}

	public List<Job> matchByNames(List<String> nomes) {
		return null;
	}

}
