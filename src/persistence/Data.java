package persistence;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import domain.App;
import domain.TypePersistence;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.Alert.AlertType;
import security.Criptografia;

public class Data implements TypePersistence {

	JSONObject jsonObject;

	public Data() {
		carregaArquivo();
	}


	private void carregaArquivo() {
		try {
			Object object = new JSONParser().parse(new FileReader(new File("src\\resources\\data.json").getCanonicalFile()));
			jsonObject = (JSONObject) object;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public List<App> getApps() {

		JSONArray arrayApps = (JSONArray) jsonObject.get("servicos");
		List<App> services = new ArrayList<>();
				
		for (Object obj : arrayApps) {
			JSONObject app = (JSONObject) obj;
			
			String nome = (String) app.get("nome");
			String usuario = (String) app.get("usuario");
			String senha = (String) app.get("senha");
			
			services.add(new App(nome, usuario, Criptografia.decriptar(senha.length(), senha)));
		}
		return services;
	}

	
	
	@Override
	public void atualizarDados(List<App> l) {
		
		jsonObject.remove("servicos");
		
		JSONArray array = new JSONArray();
		
		for(App app : l){
			JSONObject obj = new JSONObject();
			obj.put("nome", app.getNome());
			obj.put("usuario", app.getUsuario());
			obj.put("senha", Criptografia.encriptar(app.getSenha().length(), app.getSenha().toString()));
			array.add(obj);
		}
		
	
		jsonObject.put("servicos", array);
		
		
		try{
			FileWriter writer = new FileWriter(new File("src\\resources\\data.json"));
			writer.write(jsonObject.toJSONString());
			writer.flush();
		} catch(IOException e){
			e.printStackTrace();
		}
	
	}

	

}