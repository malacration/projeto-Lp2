package login;

public class Usuario {
	private String login;
	private String senha;
	private boolean vip;
	/*******************************************************************/
	/*Construtor vazio*/
	public Usuario() {}
	
	/*******************************************************************/
	/**Construtor da classe
	 * @param login variavel que guarda o login do usuario
	 * @param senha variavel que guarda a senha do usuario
	 * */
	public Usuario(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	/*******************************************************************/
	/**Construtor da classe
	 * @param login variavel que guarda o login do usuario
	 * @param senha variavel que guarda a senha do usuario
	 * @param vip   variavel que informa o tipo de usuario
	 * */
	public Usuario(String login, String senha, boolean vip) {
		this.login = login;
		this.senha = senha;
		this.setVip(vip);
	}
	
	/*******************************************************************/
	/*Getters e setters*/
	public boolean isVip() {return vip;}
	public String getLogin() {return login;}
	public String getSenha() {return senha;}	
	public void setVip(boolean vip) {this.vip = vip;}
	public void setLogin(String login) {this.login = login;}
	public void setSenha(String senha) {this.senha = senha;}
	
	
	

	
}
