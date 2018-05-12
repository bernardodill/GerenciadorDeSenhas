package controller;


import domain.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditScreenController {

	@FXML
	TextField nameApp;
	@FXML
	TextField user;
	@FXML
	TextField password;
	
	@FXML
	Button sendButton;
	
	
	String nome,usuario,senha;
	
	int opcao;
	
	public EditScreenController(String nome, String usuario, String senha, int opcao){
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.opcao = opcao;
	}
	
	@FXML
	public void initialize(){
		nameApp.setText(nome);
		user.setText(usuario);
		password.setText(senha);
	}
	
	
	public App getItem(){
		return new App(nameApp.getText(), user.getText(), password.getText());
	}
	
	
	public boolean verificaCampos(){
		return !nameApp.getText().equals("") || !user.getText().equals("") || !password.getText().equals("");
		
	}
	
	
	
	
}
