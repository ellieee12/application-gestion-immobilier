import java.util.HashMap;
import java.util.Map;

public class ModeleLoginSignUp {
	private Map<String,String> listeMDP;
	public ModeleLoginSignUp() {
		this.listeMDP=new HashMap<>();
		this.listeMDP.put("admin", "admin");
	}
	
	public boolean compteExiste(String username) {
		return this.listeMDP.containsKey(username);
	}
	
	public void addCompte(String username, String mdp) {
		this.listeMDP.put(username, mdp);
	}
	
	public boolean mdpCorrecte(String username,String mdp) {
		return this.compteExiste(username) && this.listeMDP.get(username).equals(String.valueOf(mdp));
	}
	
}
