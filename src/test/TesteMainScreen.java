package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberInputStream;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import controller.MainScreenController;
import domain.App;
import persistence.Data;

public class TesteMainScreen {

	
	Data d;
	
	@Before
	public void setUp() throws Exception {
		d = new Data();
	}

	@Test
	public void carregaArquivo() {
		try {
			Object object = new JSONParser().parse(new FileReader(new File("src\\resources\\data.json").getCanonicalFile()));
			JSONObject jsonObject = (JSONObject) object;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() {
				
	}

}
