package core;

import login.TelaLogin;
import menuPrincipal.ListaDeMusicas;
import menuPrincipal.TelaCadastro;
import menuPrincipal.TelaManipMusic;
import menuPrincipal.TelaPrincipal;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		TelaCadastro telaCadastro;
		TelaManipMusic manipMusic;
		TelaPrincipal menuPrincipal;
		TelaLogin telaLogin;
		ListaDeMusicas lista;
		
		try {
			lista = new ListaDeMusicas();
			telaCadastro = new TelaCadastro();
			manipMusic = new TelaManipMusic(lista);
			menuPrincipal = new TelaPrincipal(manipMusic,telaCadastro,lista);
			
			//telaLogin = new TelaLogin(menuPrincipal);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
