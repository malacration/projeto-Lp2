package login;


import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.awt.TextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

import interfaces.Tela;

/*****************************************************************************/
/**
 * Tela de login
 * */
public class TelaLogin extends JFrame {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private Tela telaAposOLogin;
	
	private TextField txtLogin;
	private JPasswordField txtSenha;
	private JLabel lblUsuario;
	private JLabel lblSenha;
	private JButton btnLogin;
	
	/*****************************************************************************/
	/**
	 * Construtor da classe
	 * */
	public TelaLogin(Tela telaAposOLogin) {
		/*****************************************************************************/
		/*Constroi a tela*/
		this.telaAposOLogin = telaAposOLogin;
		this.setBounds(100, 100, 203, 235);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		txtLogin = new TextField();
		txtLogin.setBounds(10, 41, 167, 22);
		this.getContentPane().add(txtLogin);
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 21, 46, 14);
		this.getContentPane().add(lblUsuario);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(10, 69, 46, 14);
		this.getContentPane().add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(10, 94, 167, 22);
		this.getContentPane().add(txtSenha);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(10, 137, 167, 48);
		this.getContentPane().add(btnLogin);
		
		/*****************************************************************************/
		/*Funcao de click para fazer login*/
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("deprecation")
				Usuario teste = new Usuario(txtLogin.getText().toLowerCase(),txtSenha.getText().toLowerCase());
				if(logar(teste)) {
					telaAposOLogin.ativar();
					setVisible(false);
				}
			}
		});
		
		this.setVisible(true);
	}
	
	/*****************************************************************************/
	/**
	 * efetua o login do usuario
	 * @param user Usuario com os dados informados na tela de login
	 * @return true caso os dados estejam corretos
	 * @return false caso os dados estejam errados
	 * */
	@SuppressWarnings({ "resource", "unused" })
	public boolean logar(Usuario user) {
		try {
			
			FileReader arq = new FileReader("usuarios.txt");
			BufferedReader leitor = new BufferedReader(arq);
			String usuario = "";
			String senha = ""; 
			String tipo = "";
			
			/*****************************************************************************/
			/*Procura pelo usuario e senha no arquivo "usuarios.txt"*/
			while(usuario != null) {
				usuario = leitor.readLine();
				senha = leitor.readLine();
				tipo = leitor.readLine();
				if(user.getLogin().equals(usuario)) {
					if(user.getSenha().equals(senha)){
						arq.close();
						return true;
					}
				}
			}
			
			arq.close();
		
		/*****************************************************************************/
		/*Caso o arquivo nao exista, ele sera criado aqui*/
		}catch(IOException e1){
			e1.printStackTrace();
			System.out.println("Erro na leitura do arquivo:" + e1.getMessage() + ".");		
			FileWriter escritor;
			
			try {
				
				escritor = new FileWriter("usuarios.txt");
				PrintWriter cout = new PrintWriter(escritor);
				cout.println("admin");
				cout.print("123");
				cout.print("1");
				escritor.close();
				System.out.println("Um novo arquivo foi criado.");
				
			} catch (IOException e2) {
				
				e2.printStackTrace();
				
			}
			
		}
		System.out.println("ruim");
		return false;
	}
}