package framework;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.mannager.PostgreSQLJDBC;
import db.mannager.Job;
import framework.db.DBManager;
import framework.match.EnumMatchers;
import framework.match.FabricaDeMatchers;
import framework.match.Matcher;
import framework.webcrawler.Minerador;

/**
 * Classe Singleton para armazenar informações necessárias a todo o sistema,
 * garantindo assim que os dados acessados por uma classe A serão os mesmos
 * acessados pela classe B. 
 * @author Geovanni, Rafael, Thales 
 *
 */
public final class DadosGlobais {
	
	private static DadosGlobais dadosSis;
	private Map<Integer, ArrayList<Float>> remPorNivel;
	private List<Job> ServidoresPublicos;
	private Minerador miner;
	private Matcher matcher;
	private DBManager myDb;
	

	private DadosGlobais(){
		this.remPorNivel        = new HashMap<Integer, ArrayList<Float>>();
		this.ServidoresPublicos = new ArrayList<Job>();
		this.miner              = new Minerador();
		this.matcher            = null;
		this.myDb               = new PostgreSQLJDBC();
	}
	
	public static DadosGlobais getDados(){
		if(dadosSis == null){
			dadosSis = new DadosGlobais();
		}
		return dadosSis;
	}
	
	public Map<Integer, ArrayList<Float>> getRemPorNivel(){
		return this.remPorNivel;
	}
	
	public void setRemPorNivel(Map<Integer, ArrayList<Float>> remPorNivel){
		this.remPorNivel = remPorNivel;
	}
	
	public List<Job> getServidoresPublicos(){
		return this.ServidoresPublicos;
	}
	
	public void setServidoresPublicos(List<Job> ServidoresPublicos){
		this.ServidoresPublicos = ServidoresPublicos;
	}
	
	public Minerador getMiner(){
		return this.miner;
	}
	
	public Matcher getMatcher(){
		return this.matcher;
	}
	
	public void setMatcher(EnumMatchers type){
		this.matcher = FabricaDeMatchers.criarMatcher(type);
	}
	
	public DBManager getMyDb() {
		return myDb;
	}
	
	public void setMyDb(DBManager myDb) {
		this.myDb = myDb;
	}
}
