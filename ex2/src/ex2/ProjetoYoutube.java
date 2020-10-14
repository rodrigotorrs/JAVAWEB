package ex2;

public class ProjetoYoutube {

	public static void main(String[] args) {

		Video v[] = new Video[3];
		
		v[0] = new Video("Primeiro video do Canal");
		v[1] = new Video("Quem sou eu");
		v[2] = new Video("Vlog final de semana.");
		
		Gafanhoto g[] = new Gafanhoto[2];
		g[0] = new Gafanhoto("Rodrigo", 29, "M","Digo");
		g[1] = new Gafanhoto("Julia", 40, "F", "JU"); 
		
		Visualizacao vis[] = new Visualizacao [5];
		vis[0] = new Visualizacao(g[0], v[2]);
		vis[0].avaliar();
		System.out.println(vis[0].toString());
	
		
		vis[1] = new Visualizacao(g[0], v[1]);
		vis[0].avaliar(87.0f);
		System.out.println(vis[0].toString());
	}

}
