package bachelorthesis.IOMDOProject.model;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntTools;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.iterator.ExtendedIterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * Contains all OntClasses with a German Label. With the right Label or the URI the given match will be returned.
 * @author romap1
 *
 */
public class OntClassMap {

	private OntologyEditor ontEdit = OntologyEditor.getInstance();
	private List<OntClass>  OntClassList= new ArrayList<OntClass>();
	private HashMap<String, String> ontClassUriMap = new HashMap<String, String>(); 
	private HashMap<String, String> ontClassLabelMap = new HashMap<String, String>(); 
	
	public OntClassMap() {
		OntClassList = ontEdit.getAllOntologyClasses();
		fillUriMap();
		fillLabelMap();
	}
	
	private void fillUriMap() {
		Iterator<OntClass> iter = OntClassList.iterator();
		while (iter.hasNext()) {
			OntClass ontClass =  (OntClass) iter.next();
			if(ontClass.getLabel("DE") != null) {
				ontClassUriMap.put(ontClass.getLabel("DE"), ontClass.getURI());
			}
		}
	}

	/**
	 * 
	 * @return Hashmap with Label as Key and Uri as value
	 */
	public HashMap<String, String>getUriMap(){
		return ontClassUriMap;
		
	}
	
	private void fillLabelMap() {
		OntClassList = ontEdit.getAllOntologyClasses();
		Iterator<OntClass> iter = OntClassList.iterator();
		while (iter.hasNext()) {
			OntClass ontClass =  (OntClass) iter.next();
			if(ontClass.getLabel("DE") != null) {
				ontClassLabelMap.put(ontClass.getURI(), ontClass.getLabel("DE"));
			}
		}
	}
	
	/**
	 * 
	 * @return Hashmap with URI as Key and Label as value
	 */
	public HashMap<String, String>getLabelMap(){
		return ontClassLabelMap;
		
	}
	
	/**
	 * 
	 * @param label
	 * @return Label of given Uri or null
	 */
	public String getUriFromLabel(String label) {
		return ontClassUriMap.get(label);
	}
	/**
	 * 
	 * @param uri
	 * @return URI of given Label or null
	 */
	public String getLabelFromURI(String uri) {
		return ontClassLabelMap.get(uri);
	}
	
	public List<OntClass> getOntClassList() {
		return OntClassList;
	}
	
	//not finished!!!
	public OntClass getOntClass(String label) {
		for(int i = 0; i < OntClassList.size(); i++)
		if (OntClassList.get(i).toString() ==(label));
		
		
		return null;
	}
		
}



