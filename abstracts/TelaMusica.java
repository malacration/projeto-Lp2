package abstracts;

import javax.swing.DefaultListModel;
import javax.swing.JList;


import interfaces.Tela;

/*****************************************************************************/
/**
 * Classe abstrata que representa qualquer Tela que faça uso de Jlist com musicas
 * */
public abstract class TelaMusica implements Tela{
	
	protected JList<String> listaMusica = new JList<String>();
	protected DefaultListModel<String> modelo;
	
	/*****************************************************************************/
	/**
	 * Construtor
	 * */
	protected TelaMusica(){
		listaMusica = new JList<String>();
		modelo = new DefaultListModel<String>();
		listaMusica.setModel(modelo);
	}
	
	/*****************************************************************************/
	/**
	 * Funcao que atualiza o Jlist baseado no arquivo "musicas.txt"
	 * */
	/*
	public void attLista() {
		
		FileReader leitor;
		BufferedReader cin;
		
		/*****************************************************************************/
		/*Faz a leitura do arquivo e salva tudo na pilha
		try {
			String texto;
        	leitor = new FileReader ("musicas.txt");
        	cin = new BufferedReader (leitor);
        	
        	modelo.removeAllElements();
            while((texto = cin.readLine()) != null) {
            	modelo.addElement(texto);
            }
            
            cin.close();
            
		}
		/*****************************************************************************/
		/*Caso o arquivo nao seja localizado, ele é gerado aqui
		catch(FileNotFoundException e) {
			
			System.out.println("Erro: Arquivo nao encontrado. " + e.getMessage());
			e.printStackTrace();
			
			FileWriter escritor;
			PrintWriter anotar;
			try {
				
				escritor = new FileWriter("musicas.txt");
				anotar = new PrintWriter(escritor);
				escritor.close();
				System.out.println("Um novo arquivo foi criado.");
				
			} catch (IOException e2) {				
				e2.printStackTrace();
			}
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}
	*/
	
}
