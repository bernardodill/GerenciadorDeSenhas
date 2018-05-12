package domain;

public class App {

	private String nome;
	private String usuario;
	private String senha;
	
	
	public App(String nome,String usuario, String senha){
		setNome(nome);
		setUsuario(usuario);
		setSenha(senha);
		
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
