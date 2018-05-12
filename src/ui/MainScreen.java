package ui;



import java.util.List;
import controller.MainScreenController;
import domain.App;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import persistence.Data;


public class MainScreen extends Application {

	List<App> list;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\resources\\mainScreen.fxml"));
		
		AnchorPane pane = loader.load();
		
		MainScreenController controller = loader.getController();
		primaryStage.setTitle("Gerenciador de Senhas");
		primaryStage.setScene(new Scene(pane));
		primaryStage.setResizable(false);
		
		primaryStage.setOnCloseRequest(ev ->{
			new Data().atualizarDados(controller.getAllApps());
		});
		
		primaryStage.show();
		
	}

	
	
	
	

}
