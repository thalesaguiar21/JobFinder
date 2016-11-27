package framework.webcrawler;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Classe abstrata, que representa a entidade respons�vel por procurar
 * os dados requeridos para a aplica��o funcionar
 * .
 * @author Geovanni Casemiro, Rafael Lucena, Thales Aguiar
 *
 */
public abstract class Picareta {

	private String baseUrl;
	protected static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    protected List<String> links = new LinkedList<String>();
    protected Document htmlDocument;
    protected int cont = 0;


    /**
     * Respons�vel por fazer a requisi��o HTTP, checar resposta e reunir todos os links da p�gina.
     * Depois, faz a busca por palavras chave.
     * 
     * @param url
     *            - O url da p�gina que ser� visitada
     * @return Se a visita foi um sucesso ou n�o
     */
    public final boolean crawl(String url){
        try
        {
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            this.htmlDocument = htmlDocument;
            if(connection.response().statusCode() == 200)
            {
                System.out.println("\nVisitando... recebida a seguinte p�gina: " + url);
            }
            if(!connection.response().contentType().contains("text/html"))
            {
                System.out.println("**FALHA** algo que n�o � HTML foi retornado!");
                return false;
            }
    		Elements linksOnPage = htmlDocument.select("a[href]");
    		System.out.println("Found (" + linksOnPage.size() + ") links");
    		for(Element link : linksOnPage)
    		{
            	String nextUrl = link.absUrl("href");
            	if(comp(nextUrl) ){
            		continue;
            	}
            	else{
            		nextUrl = javascriptLinks(nextUrl);
            		this.links.add(nextUrl);
            	}
            }
            return true;
        }
        catch(IOException ioe)
        {
        	System.out.println(ioe);
        	System.out.println("Tentando novamente");
        	cont++;
        	if(cont >= 5){
        		cont = 0;
        		System.out.println("N�o foi poss�vel acessar :" + url);
        		return false;
        	}
        	else if(crawl(url)){
    			cont = 0;
    			return true;
        	}
        	else{
        		return false;
        	}
        }
    }


    /**
     * Faz uma busca por determinadas palavras no HTML e trata a string
     * retornando apenas o que � importante.
     * 
     * @return Se a palavra foi ou n�o encontrada
     */
    public abstract float searchForWord();
    
    protected abstract boolean comp(String url);
    
    protected String javascriptLinks(String url){
		if(url.matches(".*Pagina=\\d*#")){
			int pageIndex = url.lastIndexOf("=");
			String prefix = url.substring(0, pageIndex + 1);
			url = prefix + (Integer.parseInt(url.substring(pageIndex + 1, url.length() - 1)) + 1);
		}
		return url;
    }


    public List<String> getLinks()
    {
        return this.links;
    }
    
    public void esvaziarLinks(){
    	this.links.clear();
    }


	public String getBaseUrl() {
		return baseUrl;
	}


	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

}
