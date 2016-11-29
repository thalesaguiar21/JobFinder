package framework.webcrawler;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import db.mannager.Job;
import db.mannager.JobDAO;
import framework.webcrawler.Picareta;

public class PicaretaDeEmpregos extends Picareta {

	JobDAO jdao;

	PicaretaDeEmpregos(){
		this.setBaseUrl("http://www.tribunadonorte.com.br/classificados/empregos");
	}
	
	@Override
	public float searchForWord(){
	    if(this.htmlDocument.body() == null)
	    {
	        System.out.println("**ERRO** Invoque crawl() antes de realizar a análise do documento!");
	        return -1f;
	    }
	    Elements jobs = this.htmlDocument.select(".job-content");
	    for(Element e : jobs){
	    	jdao.insertJob(
	    			new Job(e.select(".job-title").text(),
	    					"", 
	    					e.select(".job-description").text(), 
	    					e.select(".job-code").text(),
	    					0), 0);
	    }
		return 1f;
	}

	@Override
	protected boolean comp(String url) {
		return (url.equals("") || !(url.startsWith("http://www.tribunadonorte.com.br/classificados/empregos?page=")));
	}

}
