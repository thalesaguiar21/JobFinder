package framework.match;

import matchers.JobMatcher;

public class FabricaDeMatchers {
	
	public static Matcher criarMatcher(EnumMatchers type){
		switch (type) {
		case V_B_FAMILIA:
			return new JobMatcher();
		default:
			System.out.println("Não existe um verificador para o numero " + type);
			return null;
		}
	}

}
