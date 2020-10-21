package ex1;

public class Execult {

	public static void main(String[] args) {
		
		Pessoa p1 = new Pessoa("Rodrigo","M",29);
		Pessoa p2 = new Pessoa("Thiago","M",20);
		Pessoa p3 = new Pessoa("Fernanda","F",18);
		
		Livro l1 = new Livro ("A era do Gelo","Sr. Gelado",300,p1);
		Livro l2 = new Livro ("Terra Seca","Geraldo J.",100,p1);
		Livro l3 = new Livro ("Zero Graus","Sand.",900,p2);
				
		l2.abrir();
		l2.folhear(135);
		l2.avancarPag();
		
		System.out.println(l2.detalhes());
		
	}

}
