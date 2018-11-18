package org.app.service.ejb;
import java.util.List;
import javax.ejb.Remote;
import org.app.service.entities.Echipa;

@Remote
public interface EchipaTipEchipaAngajatService {
	List<Echipa> createEchipe(Integer numarEchipe);
	Echipa getEchipa(Integer idEchipa);
	String getMessage();
}
