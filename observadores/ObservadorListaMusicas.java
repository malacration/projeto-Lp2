package observadores;

import menuPrincipal.TelaManipMusic;
import menuPrincipal.TelaPrincipal;

public class ObservadorListaMusicas{
	
	private TelaPrincipal tp;
	private TelaManipMusic tmm;
	
	public ObservadorListaMusicas(TelaPrincipal tp, TelaManipMusic tmm){
		this.tp = tp;
		this.tmm = tmm;
	}
	
	public void att() {
		tp.attLista();
		tmm.attLista();
	}
}
