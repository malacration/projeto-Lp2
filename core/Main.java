package core;

import login.TelaLogin;
import menuPrincipal.TelaCadastro;
import menuPrincipal.TelaManipMusic;
import menuPrincipal.TelaPrincipal;
import observadores.ObservadorListaMusicas;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		TelaCadastro telaCadastro;
		TelaManipMusic manipMusic;
		TelaPrincipal menuPrincipal;
		TelaLogin telaLogin;
		
		ObservadorListaMusicas observador;
		
		try {
			
			telaCadastro = new TelaCadastro();
			manipMusic = new TelaManipMusic();
			menuPrincipal = new TelaPrincipal(manipMusic,telaCadastro);
			
			observador = new ObservadorListaMusicas(menuPrincipal,manipMusic);
			
			//telaLogin = new TelaLogin(menuPrincipal);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
