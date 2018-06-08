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
public class ListaDeMusicas extends DefaultListModel<String> {
	
	public void adicionarItem(String item) {
		this.addElement(item);
	}
	
	public DefaultListModel<String> getModelo() {return this;}
	
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
