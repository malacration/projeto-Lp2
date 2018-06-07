package menuPrincipal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/*****************************************************************************/
/**
 * Classe abstrata que representa qualquer Tela que faça uso de Jlist com musicas
 * */
public class ListaDeMusicas{
	
	private JList<String> listaMusica;
	private DefaultListModel<String> modelo;
	
	/*****************************************************************************/
	/**
	 * Construtor
	 * */
	public ListaDeMusicas(){
		listaMusica = new JList<String>();
		modelo = new DefaultListModel<String>();
		listaMusica.setModel(modelo);
	}
	
	
	public JList<String> getListaMusica() {return listaMusica;}
	public DefaultListModel<String> getModelo() {return modelo;}
	public void setListaMusica(JList<String> listaMusica) {this.listaMusica = listaMusica;}
	public void setModelo(DefaultListModel<String> modelo) {this.modelo = modelo;}
	
	/*****************************************************************************/
	/**
	 * Funcao que atualiza o Jlist baseado no arquivo "musicas.txt"
	 * */
	
	@SuppressWarnings("unused")
	public void attLista() {
		
		FileReader leitor;
		BufferedReader cin;
		
		/*****************************************************************************/
		/*Faz a leitura do arquivo e salva tudo na pilha*/
		
		try {
			String texto;
        	leitor = new FileReader ("musicas.txt");
        	cin = new BufferedReader (leitor);
        	
        	getModelo().removeAllElements();
            while((texto = cin.readLine()) != null) {
            	getModelo().addElement(texto);
            }
            
            cin.close();
         

		/*****************************************************************************/
		/*
		 * Caso o arquivo nao seja localizado, ele é gerado aqui
		 */
		}catch(FileNotFoundException e) {
			
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

	
}
