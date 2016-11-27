package framework.webcrawler;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Minerador {
	
	  private static final int MAX_PAGES_TO_SEARCH = 10000;
	  private Set<String> pagVisitadas;
	  private List<String> pagParaVisitar;
	  
	  
	  public Minerador(){
		  pagVisitadas = new HashSet<String>();
		  pagParaVisitar = new LinkedList<String>();
	  }


	  /**
	   * Ponto principal das funcionalidades do Spider(Minerador). Cria as legs(Picaretas) para fazer
	   * as requisições HTTP e analisar a página web.
	   * 
	   * @param picareta
	   *            - O tipo de picareta que será usado na busca
	   */
	  public boolean minerar(EnumPicaretas picType)
	  {
		  Picareta picareta =  FabricaDePicaretas.criarPicareta(picType);
		  if(picareta != null && picareta.getBaseUrl() != ""){
			  String currentUrl = picareta.getBaseUrl();
			  pagVisitadas.add(picareta.getBaseUrl());
		      while(this.pagVisitadas.size() < MAX_PAGES_TO_SEARCH){
	              picareta.crawl(currentUrl);
		          float success = picareta.searchForWord();
		          if(success != -1f){
		              System.out.println(String.format("**Sucesso**. Palavra encontrada em %s", currentUrl));
		          }
		          this.pagParaVisitar.addAll(picareta.getLinks());
		          picareta.esvaziarLinks();
		          do{
			          currentUrl = this.nextUrl();
		          }while(currentUrl.equals("") && !pagParaVisitar.isEmpty());
		          if(currentUrl.equals("")){
		        	  break;
		          }
		      }
		      System.out.println("\n**Fim**  " + this.pagVisitadas.size() + " página(s) visitada(s)");
		  } else {return false;}
		return true;
	  }


	  /**
	   * Retorna a próxima URL a ser visitada, na ordem em que foi adicionada.
	   * 
	   * @return
	   */
	  private String nextUrl()
	  {
	      String nextUrl;
	      do
	      {
	    	  if(pagParaVisitar.size() == 0){
	    		  return "";
	    	  }
	          nextUrl = this.pagParaVisitar.remove(0);
	      } while(this.pagVisitadas.contains(nextUrl));
	      this.pagVisitadas.add(nextUrl);
	      return nextUrl;
	  }
}
