package sae3a01;

import classes.Immeuble;
import java.util.ArrayList;
import java.util.List;

public class ModeleMesImmeubles {
	
	private List<Immeuble> list;

	public ModeleMesImmeubles() {
		this.list = new ArrayList<Immeuble>();
	}

	public List<Immeuble> getList() {
		return list;
	}
	
	public boolean immeubleExiste(String adresse,String Ville ,String CP) {
		for (Immeuble i : this.list) {
			if (i.getAdresse().equals(adresse) && i.getCp().equals(CP) 
					&& i.getVille().equals(Ville)) {
				return true;
			}
		}
		return false;
	}
	
	public Immeuble getDernierImmeuble() {
		return this.list.get(this.list.size()-1);
	}
	
}
