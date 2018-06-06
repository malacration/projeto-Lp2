package menuPrincipal;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;

import abstracts.TelaMusica;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;


public class TelaManipMusic extends TelaMusica{

	/*******************************************************************/
	/*Declara as variaveis*/
	private JFrame frame;
	//private JList<String> listMusica;
	private JLabel lblAdd;
	private JLabel lblRemoverTodos;
	private JLabel lblAddTxt;
	private JLabel lblRemoverTxt;
	private JLabel lblRemoverTodosTxt;
	private JLabel lblRemover;
	private DefaultListModel<String> modelo;
	
	
	/**
	 * Create the application.
	 */
	public TelaManipMusic() {
		super();
		initialize();
		attLista();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 400, 460);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		listaMusica.setBounds(10, 11, 374, 330);
		frame.getContentPane().add(listaMusica);
		
		setModelo(new DefaultListModel<String>());
		listaMusica.setModel(getModelo());
		
		lblAdd = new JLabel("");
		lblAdd.setVerticalAlignment(SwingConstants.TOP);
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setIcon(new ImageIcon("icon\\telaManipMusic\\icons8-mais-filled-50.png"));
		lblAdd.setBounds(30, 352, 50, 50);
		frame.getContentPane().add(lblAdd);
		
		lblRemover = new JLabel("");
		lblRemover.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				remover();
				attLista();
			}
		});
		lblRemover.setVerticalAlignment(SwingConstants.TOP);
		lblRemover.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemover.setIcon(new ImageIcon("icon\\telaManipMusic\\icons8-excluir-filled-50.png"));
		lblRemover.setBounds(169, 352, 50, 50);
		frame.getContentPane().add(lblRemover);
		
		lblRemoverTodos = new JLabel("New label");
		lblRemoverTodos.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				removerTudo();
				attLista();
			}
		});
		lblRemoverTodos.setHorizontalAlignment(SwingConstants.LEFT);
		lblRemoverTodos.setIcon(new ImageIcon("icon\\telaManipMusic\\icons8-lixo-filled-50.png"));
		lblRemoverTodos.setBounds(310, 352, 50, 50);
		frame.getContentPane().add(lblRemoverTodos);
		
		
		lblAddTxt = new JLabel("Adicionar");
		lblAddTxt.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 14));
		lblAddTxt.setBounds(20, 406, 70, 14);
		frame.getContentPane().add(lblAddTxt);
		
		
		lblRemoverTxt = new JLabel("Remover");
		lblRemoverTxt.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 14));
		lblRemoverTxt.setBounds(159, 406, 70, 14);
		frame.getContentPane().add(lblRemoverTxt);
		
		
		lblRemoverTodosTxt = new JLabel("Remover Todos");
		lblRemoverTodosTxt.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 14));
		lblRemoverTodosTxt.setBounds(281, 406, 103, 14);
		frame.getContentPane().add(lblRemoverTodosTxt);
		
		
		lblAdd.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				add();
				attLista();
			}
		});
	}

	/*******************************************************************/
	/**
	 * Funcao resposavel por adicionar uma musica ao Jlist
	 * 
	 * */
	public void add(){
		JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {chooser.getSelectedFile().getAbsolutePath();
    		getModelo().addElement(chooser.getSelectedFile().getAbsolutePath());
    		attTxt();
        }
	}
	
	/*******************************************************************/
	/**
	 * Funcao responsavel por atualizar o arquivo "musicas.txt"
	 * 
	 * */
	public void attTxt() {
		FileWriter escritor;
		BufferedWriter cout;
		
        Stack<String> pilha = new Stack<String>();
		
		for(int i = 0; i < getModelo().getSize(); i++){            	
			try{
				pilha.add(getModelo().getElementAt(i).toString());
		    }
		    catch (ArrayIndexOutOfBoundsException e) {
		        System.out.println("Erro ao ler musica: " + e.getMessage());
		        e.printStackTrace();
		    }
		}
		
		try {
			escritor = new FileWriter ("musicas.txt");
		    cout = new BufferedWriter (escritor);

			pilha.forEach(x -> {try {
				cout.write(x);
				cout.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}});
			
			cout.close();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/*******************************************************************/
	/**
	 * Funcao responsavel por remover uma musica do JList
	 * 
	 * */
	public void remover(){
		try{
			getModelo().remove(listaMusica.getSelectedIndex());
			attTxt();
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Erro ao remover musica: " + e.getMessage());
            e.printStackTrace();
        }
	}
	
	/*******************************************************************/
	/**
	 * Fucao responsavel por remover todas as musicas do Jlist
	 * 
	 * */
	public void removerTudo(){
		while(getModelo().getSize()>0) {
			try{
				getModelo().remove(getModelo().getSize()-1);
				attTxt();
	        }
	        catch (ArrayIndexOutOfBoundsException e) {
	            System.out.println("Erro ao remover musica: " + e.getMessage());
	            e.printStackTrace();
	        }
		}
	}

	/*******************************************************************/
	/*getters e setters*/
	public DefaultListModel<String> getModelo() {return modelo;}
	public void setModelo(DefaultListModel<String> modelo) {this.modelo = modelo;}
	
		
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
        	
        	modelo.removeAllElements();
            while((texto = cin.readLine()) != null) {
            	modelo.addElement(texto);
            }
            
            cin.close();
            
		}
		/*****************************************************************************/
		/*Caso o arquivo nao seja localizado, ele é gerado aqui*/
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
	
	public void ativar() {frame.setVisible(true);}
	public void desativar() {frame.setVisible(false);}
	
}
