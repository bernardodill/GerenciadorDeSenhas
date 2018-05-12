package domain;

import java.util.List;

public interface TypePersistence {

	public List<App> getApps();
	
	public void atualizarDados(List<App> l);
	
}
