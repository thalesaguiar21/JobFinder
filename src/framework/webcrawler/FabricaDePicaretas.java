package framework.webcrawler;

public class FabricaDePicaretas {
	
	public static Picareta criarPicareta(EnumPicaretas type){
		switch (type) {
		case JOB:
			return new PicaretaDeEmpregos();
		case COMPANY:
			return null;
		default:
			System.out.println("Não existe uma Picareta para o numero " + type);
			return null;
		}
	}
}
