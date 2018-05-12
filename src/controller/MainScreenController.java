package controller;



import java.io.IOException;
import java.util.List;
import java.util.Optional;
import domain.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import persistence.Data;


import ui.Service;

public class MainScreenController implements Service {

	Stage appStage;
	AnchorPane pane;
	
	
	App app;
	
	Data data = new Data();
	
	EditScreenController controller;
	
	FXMLLoader loader;
	
	@FXML
	public ListView<App> listaApps;
	
	@FXML
	public Button editAppButton;
	
	@FXML
	public Button deleteAppButton; 


	
	
	@FXML
	public void initialize() {
		atualizaListView();
		populaListView();
		onChangeListView();
	}

	public void onChangeListView() {
		listaApps.getSelectionModel().selectedItemProperty().addListener((changed, oldVal, newVal) ->{
			deleteAppButton.setDisable(false);
			editAppButton.setDisable(false);
		});
	}

	public void populaListView() {
		listaApps.getItems().addAll(data.getApps());
	}
	
	public List<App> getAllApps(){
		return (List<App>) listaApps.getItems();
	}
	
	
	public void refreshList(){
		listaApps.refresh();
	}
	

	@Override
	public void newApp() {
		controller = new EditScreenController(null,null,null,1);
		novaJanela("Novo Aplicativo", "Adicionar");
		
		if(verificaApp()){
			listaApps.getItems().add(app);
			listaApps.refresh();
		}
	}

	@Override
	public void editApp() {
		controller = new EditScreenController(getSelectedItem().getNome(),getSelectedItem().getUsuario(), getSelectedItem().getSenha(),2);
		novaJanela("Editar Aplicativo", "Confirmar");
		editaAppSelecionado();
	}

	private void editaAppSelecionado() {
		if(verificaApp()){
			listaApps.getSelectionModel().getSelectedItem().setNome(app.getNome());
			listaApps.getSelectionModel().getSelectedItem().setUsuario(app.getUsuario());
			listaApps.getSelectionModel().getSelectedItem().setSenha(app.getSenha());
			listaApps.refresh();
		}
	}

	private boolean verificaApp() {
		return app != null && controller.verificaCampos();
	}
	
	
	public void novaJanela(String title, String nameButton){
		try{
			loader = new FXMLLoader(getClass().getResource("..\\resources\\appScreen.fxml"));
			loader.setController(controller);	
			controller = loader.getController();
			pane = loader.load();
			Button send = criarBotaoDeConfirmacaoAppStage(nameButton);
			pane.getChildren().add(send);
			criarAppStage(title);
		
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	private void criarAppStage(String title) {
		appStage = new Stage();
		appStage.setOnCloseRequest(ev ->{
			app = null;
		});
		appStage.setTitle(title);
		appStage.initModality(Modality.APPLICATION_MODAL);
		appStage.setResizable(false);
		appStage.setScene(new Scene(pane));
		appStage.showAndWait();
	}

	private Button criarBotaoDeConfirmacaoAppStage(String nameButton) {
		Button send = new Button(nameButton);
		send.setLayoutX(80);
		send.setLayoutY(210);
		send.setId("sendButton");
		
		send.setOnAction(e->{
			app = controller.getItem();
			appStage.close();
		});
		return send;
	}
	
	@Override
	public void deleteApp() {
		Optional<ButtonType> result = criaJanelaAlert();
		if (result.get() == ButtonType.OK){ 
			listaApps.getItems().remove(listaApps.getSelectionModel().getSelectedIndex());
			listaApps.refresh();
			
			
		}
	}

	private Optional<ButtonType> criaJanelaAlert() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmação");
		alert.setHeaderText("Realmente deseja deletar este item?");
		alert.setContentText(getSelectedItem().getNome()+"\n"+getSelectedItem().getUsuario()+"\n"+getSelectedItem().getSenha());
		
		Optional<ButtonType> result = alert.showAndWait();
		return result;
	}

	public App getSelectedItem() {
		return listaApps.getSelectionModel().getSelectedItem();
	}

	
	public void atualizaListView() {
		listaApps.setCellFactory(e -> new ListCell<App>() {
			@Override
			public void updateItem(App item, boolean empty) {

				

				if (!empty) {
					String text = "Nome: " + item.getNome() + "\nUsuario: " + item.getUsuario() + "\nSenha: "
							+ item.getSenha();
					setText(text);
				}
				
				super.updateItem(item, empty);
			}
		});

	}
}
