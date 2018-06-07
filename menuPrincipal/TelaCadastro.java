package menuPrincipal;

import javax.swing.JFrame;
import javax.swing.JTextField;

import interfaces.Tela;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
import java.awt.event.ActionEvent;

public class TelaCadastro implements Tela{

	private JFrame frame;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;
	private JButton btnCriar;
	private JLabel lblTipoDeUsuario;
	private JLabel lblSenha;
	private JRadioButton rdbtnVip;
	private JRadioButton rdbtnNormal;
	private JLabel lblUsuario;
	private ButtonGroup rdbGroup = new ButtonGroup();
	
	
	/**
	 * Create the application.
	 */
	public TelaCadastro() {
		initialize();
		rdbGroup.add(rdbtnNormal);
		rdbGroup.add(rdbtnVip);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 330, 210);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		lblUsuario.setBounds(10, 35, 46, 14);
		frame.getContentPane().add(lblUsuario);
		
		lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		lblSenha.setBounds(10, 60, 46, 14);
		frame.getContentPane().add(lblSenha);
		
		lblTipoDeUsuario = new JLabel("Tipo de usuario:");
		lblTipoDeUsuario.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		lblTipoDeUsuario.setBounds(10, 85, 92, 14);
		frame.getContentPane().add(lblTipoDeUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		txtUsuario.setBounds(88, 33, 224, 20);
		frame.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		rdbtnNormal = new JRadioButton("Normal");
		rdbtnNormal.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		rdbtnNormal.setBounds(108, 81, 65, 23);
		frame.getContentPane().add(rdbtnNormal);
		
		rdbtnVip = new JRadioButton("Vip");
		rdbtnVip.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		rdbtnVip.setBounds(175, 81, 46, 23);
		frame.getContentPane().add(rdbtnVip);
		
		txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		txtSenha.setBounds(88, 58, 224, 20);
		frame.getContentPane().add(txtSenha);
		
		btnCriar = new JButton("Criar usuario");
		btnCriar.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 24));
		btnCriar.setBounds(10, 123, 302, 52);
		frame.getContentPane().add(btnCriar);
		
		
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!txtUsuario.getText().trim().equals("")){
					if(txtSenha.getPassword().length != 0)
						if(rdbtnVip.isSelected() || rdbtnNormal.isSelected()) {
							criarUsuario();
					}
				}
			}
		});
	}
	
	@SuppressWarnings("deprecation")
	public void criarUsuario() {		
		FileReader leitor;
		BufferedReader cin;
		FileWriter escritor;
		BufferedWriter anotar;
		
		try {
			String texto;
			Stack<String> pilha = new Stack<String>();
        	leitor = new FileReader ("usuarios.txt");
        	cin = new BufferedReader (leitor);

        	
            while((texto = cin.readLine()) != null) {
            	pilha.add(texto);
            }
            
        	escritor = new FileWriter("usuarios.txt");
			anotar = new BufferedWriter(escritor);
			
            pilha.forEach(text -> {
            	try {
            		anotar.write(text);
					anotar.newLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            });
            	
            
            
            anotar.write(txtUsuario.getText().toLowerCase());
            anotar.newLine();
            anotar.write(txtSenha.getText().toLowerCase());
            anotar.newLine();
            if(rdbtnVip.isSelected())
            	anotar.write("1");
            else
            	anotar.write("0");
            anotar.newLine();
            
            anotar.close();
            cin.close();
            
		}
		/*****************************************************************************/
		/*Caso o arquivo nao seja localizado, ele é gerado aqui*/
		catch(FileNotFoundException e) {
			System.out.println("Erro: Arquivo nao encontrado. " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		} 
		
	}
	
	@Override
	public void ativar() {
		frame.setVisible(true);
		
	}

	@Override
	public void desativar() {
		frame.setVisible(false);
		
	}
}
