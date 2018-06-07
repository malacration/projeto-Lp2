package menuPrincipal;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import interfaces.Tela;
import javazoom.jl.player.Player;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.awt.Toolkit;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class TelaPrincipal implements Tela {

	/*****************************************************************************/
	/*Telas da classe*/
	private TelaManipMusic telaMusicas;
	private TelaCadastro telaCadastro;
	
	
	/*****************************************************************************/
	/*Variaveis da classe*/
	private Player player;
	private JFrame frmTocadorDeMusica;
	private JLabel lblPlay;
	private JLabel lblAddMusic;
	private JLabel lblAddMusicImg;
	private JLabel lblUserProfile;
	private JLabel lblListaDeMusicas;
	private JMenuItem mntmCadastrarUsuario;
	private JMenu mnOpcoes;
	
	private ListaDeMusicas lista = new ListaDeMusicas();
	
	/**
	 * Create the application.
	 */
	public TelaPrincipal(TelaManipMusic manipMusic, TelaCadastro telaCadastro) {
		initialize();
		this.telaMusicas = manipMusic;
		this.telaCadastro = telaCadastro;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/*****************************************************************************/
		/*Constroi a tela*/
		frmTocadorDeMusica = new JFrame();
		frmTocadorDeMusica.setResizable(false);
		frmTocadorDeMusica.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 16));
		frmTocadorDeMusica.setTitle("Tocador de musica em alta qualidade mas com nome duvidoso");
		frmTocadorDeMusica.setIconImage(Toolkit.getDefaultToolkit().getImage("icon\\icons8-notas-musicais-filled-50.png"));
		frmTocadorDeMusica.setBounds(100, 100, 852, 503);
		frmTocadorDeMusica.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTocadorDeMusica.getContentPane().setLayout(null);
		lblAddMusicImg = new JLabel("");
		lblAddMusic = new JLabel("Add Musica");
		lblAddMusicImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddMusicImg.setIcon(new ImageIcon("icon\\icons8-musical-80.png"));
		lblAddMusicImg.setBounds(21, 49, 64, 64);
		frmTocadorDeMusica.getContentPane().add(lblAddMusicImg);
		
		
		//listaMusica.setBounds(116, 49, 450, 404);
		//frmTocadorDeMusica.getContentPane().add(listaMusica);
		
		
		lblAddMusic.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 16));
		lblAddMusic.setBounds(10, 112, 96, 23);
		frmTocadorDeMusica.getContentPane().add(lblAddMusic);
		
		
		lblPlay = new JLabel("");
		lblPlay.setIcon(new ImageIcon("icon\\look-kuya-kim-meets-his-kalokalike.png"));
		lblPlay.setBounds(10, 389, 64, 64);
		frmTocadorDeMusica.getContentPane().add(lblPlay);
		
		lblListaDeMusicas = new JLabel("Lista De Musicas");
		lblListaDeMusicas.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeMusicas.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 18));
		lblListaDeMusicas.setBounds(116, 24, 450, 14);
		frmTocadorDeMusica.getContentPane().add(lblListaDeMusicas);
		
		lblUserProfile = new JLabel("");
		lblUserProfile.setIcon(new ImageIcon("icon\\icons8-usu\u00E1rio-80.png"));
		lblUserProfile.setBounds(746, 11, 80, 80);
		frmTocadorDeMusica.getContentPane().add(lblUserProfile);
		
		
		mnOpcoes = new JMenu("Opcoes");
		mnOpcoes.setBounds(-1, 0, 107, 22);
		frmTocadorDeMusica.getContentPane().add(mnOpcoes);
		
		mntmCadastrarUsuario = new JMenuItem("Cadastrar usuario");
		mnOpcoes.add(mntmCadastrarUsuario);
		
		/*****************************************************************************/
		/*fazendo ainda*/
		mntmCadastrarUsuario.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				telaCadastro.ativar();
			}
		});
		
		/*******************************************************************/
		/*Funcao de click para adicionar musica*/
		lblAddMusicImg.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				telaMusicas.ativar();
			}
		});	
		/*******************************************************************/
		/*Funcao de click para adicionar musica*/
		lblAddMusic.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				telaMusicas.ativar();
			}
		});
		/*****************************************************************************/
		/*Funcao de click para tocar uma musica*/
		lblPlay.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//tocarMusica(listaMusica.getSelectedValue().toString());
			}
		});
		
		
		/********************************************************************************/
		/*botao temporario para cadastrar usuario*/
		JButton btnCriar = new JButton("Criar usuario");
		btnCriar.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 24));
		btnCriar.setBounds(10, 170, 100, 50);
		frmTocadorDeMusica.getContentPane().add(btnCriar);	
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					telaCadastro.ativar();
			}
		});
		
		/********************************************************************************/
		/*botao temporario para att a lista*/
		JButton btnAtt = new JButton("Att");
		btnAtt.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 24));
		btnAtt.setBounds(10, 222, 100, 50);
		frmTocadorDeMusica.getContentPane().add(btnAtt);	
		btnAtt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					attLista();
					
			}
		});
		
		attLista();
		ativar();
	}
	

	/*****************************************************************************/
	/**
	 * Toca uma playlist
	 * @param playlist array de strings com nome das musicas
	 * */	
	public void tocarMusica(ArrayList<String> playlist){
		for(int i = 0; i < playlist.size(); i++) {
			try{
				tocarMusica(playlist.get(i));
	        }
	        catch (Exception e) {
	            System.out.println("Erro ao tocar a playlist: " + e.getMessage());
	            e.printStackTrace();
	        }
		}
	}
	/*****************************************************************************/
	/**
	 * Toca uma musica
	 * @param musica string com o nome de uma musica
	 * */
	public void tocarMusica(String musica){
		try{
            FileInputStream stream = new FileInputStream(musica);
            BufferedInputStream buffer = new BufferedInputStream(stream);
            player = new Player (buffer);
            System.out.println("Executando...");
            player.play();
            System.out.println("Terminado");
        }
        catch (Exception e) {
            System.out.println("Erro ao tocar a musica: " + e.getMessage());
            e.printStackTrace();
        }
	}

	@Override
	public void ativar() {
		frmTocadorDeMusica.setVisible(true);
	}

	@Override
	public void desativar() {
		frmTocadorDeMusica.setVisible(false);
	}
	
	
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
        	
        //	modelo.removeAllElements();
            while((texto = cin.readLine()) != null) {
            	//modelo.addElement(texto);
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
}

