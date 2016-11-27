package framework.match;
import java.util.List;

import db.mannager.Job;

public interface Matcher {
	List<Job> allMatchs();
	List<Job> matchByName(String nome);
	List<Job> matchByNames(List<String> nomes);
}
