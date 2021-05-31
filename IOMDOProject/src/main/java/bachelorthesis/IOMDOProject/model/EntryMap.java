package bachelorthesis.IOMDOProject.model;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
 * Provides an HashMap with Labels as Key. Contains All "data items"for the Documentation during an Intraoperative Neuromonitoring.
 * @author romap1
 *
 */
public class EntryMap {

	private OntologyEditor ontEdit = OntologyEditor.getInstance();

	private HashMap<String, String> entryMap; 
	
	private static String NS = "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/";
	
	public EntryMap() {
		fillMap();
	}
	
	public void fillMap() {
		
		entryMap = new HashMap<String, String>();
		//VEP Beobachtung = 403
		HashMap<String, String> vepFindingMap = (HashMap<String, String>) ontEdit.getSubclasses( NS + "IOMO_0000403");
		//Mapping MEssung = 373
		HashMap<String, String> mappingMeasurementMap = (HashMap<String, String>) ontEdit.getSubclasses(NS +"IOMO_0000373");
		//Technische Probleme= 154
		HashMap<String, String> technicalIssuesMap = (HashMap<String, String>) ontEdit.getSubclasses(NS + "IOMO_0000154");
		
		//HashMap<String, String> dwaveMeasurementMap = (HashMap<String, String>) ontEdit.getSubclasses(NS + "IOMO_0000369");
		//AEP Messung = 240
		HashMap<String, String> aepMeasurementMap = (HashMap<String, String>) ontEdit.getSubclasses(NS + "IOMO_0000240");
		//CBT Messung = 371, Nötig?
		HashMap<String, String> cbtMeasurementMap = (HashMap<String, String>)ontEdit.getSubclasses(NS + "IOMO_0000371");
		//Operationsprozess = 58
		HashMap<String, String> surgeryProcessMap = (HashMap<String, String>)ontEdit.getSubclasses(NS + "IOMO_0000058");
		//es fehlen noch alle Unterkategorien!!!
		
		//Reflex Beobachtungen = 234
		HashMap<String, String> reflexFindingMap = (HashMap<String, String>)ontEdit.getSubclasses(NS + "IOMO_0000234");
		//EEG Beobachtung = 231
		HashMap<String, String> eegFindingMap = (HashMap<String, String>)ontEdit.getSubclasses(NS + "IOMO_0000231");
		//Anästhesie Prozess = 159
		HashMap<String, String> anesthesyProcessMap = (HashMap<String, String>)ontEdit.getSubclasses(NS + "IOMO_0000159");
		//Mapping Beobachtung = 376
		HashMap<String, String> mappingFindingMap = (HashMap<String, String>)ontEdit.getSubclasses(NS + "IOMO_0000376");
		//IOM Prozess = 57, nötig? Nein
		//HashMap<String, String> iomProcessMap = (HashMap<String, String>)ontEdit.getSubclasses(NS + "IOMO_0000057");
		//SEP Beobachtung= 401
		HashMap<String, String> sepFindingMap = (HashMap<String, String>)ontEdit.getSubclasses(NS + "IOMO_0000401");
		//SEP Veränderungen = 417
		HashMap<String, String> changeOfSEPMAP = (HashMap<String, String>)ontEdit.getSubclasses(NS + "IOMO_0000417");
		//AEP Beobachtung = 402
		HashMap<String, String> aepFindingMap = (HashMap<String, String>)ontEdit.getSubclasses(NS + "IOMO_0000402");
		//AEP Veränderung= 429
		HashMap<String, String> changeOfAEPMap = (HashMap<String, String>) ontEdit.getSubclasses(NS + "IOMO_0000429");
		//D-Wave finding =387, no subclasses!!
		HashMap<String, String> dwaveFindingMap = (HashMap<String, String>) ontEdit.getSubclasses(NS + "IOMO_0000387");
		//EMG finding = 370, no subclasses!!
		HashMap<String, String> emgFindingMap = (HashMap<String, String>) ontEdit.getSubclasses(NS + "IOMO_0000370");
		//MEP Beobachtung = 400
		HashMap<String, String> mepFindingMap = (HashMap<String, String>) ontEdit.getSubclasses(NS + "IOMO_0000400");
		//MEP Veränderungen =405
		HashMap<String, String> changeOfMEPMap = (HashMap<String, String>) ontEdit.getSubclasses(NS + "IOMO_0000405");
		//manipulating_action_by_human, hat noch unterkategorien, noch nicht gelöst
		HashMap<String, String> actionMap = (HashMap<String, String>) ontEdit.getSubclasses( "http://medicis/spm.owl/OntoSPM#manipulating_action_by_human");
		//get all Entities that are shown as Category in the GUI
		HashMap<String, String> categoryMap  = (HashMap<String, String>) ontEdit.getAllEntitiesToBeShown(); 
		HashMap<String, String> measurementsWithValueMap  = (HashMap<String, String>) ontEdit.getAllMeasurementsWithValues(); 
		
		String gridPositioning ="IOMO_0000064";
		HashMap<String, String> gridPositioningMap  = (HashMap<String, String>) ontEdit.getSubclasses(NS + gridPositioning); 
		
		//Puts all maps into the entryMap
		entryMap.putAll(vepFindingMap);entryMap.putAll(mappingMeasurementMap);entryMap.putAll(technicalIssuesMap);//entryMap.putAll(dwaveMeasurementMap);
		entryMap.putAll(aepMeasurementMap);entryMap.putAll(cbtMeasurementMap);entryMap.putAll(surgeryProcessMap);entryMap.putAll(reflexFindingMap);
		entryMap.putAll(eegFindingMap);entryMap.putAll(anesthesyProcessMap);entryMap.putAll(mappingFindingMap);//entryMap.putAll(iomProcessMap);
		entryMap.putAll(sepFindingMap);entryMap.putAll(aepFindingMap);entryMap.putAll(dwaveFindingMap);entryMap.putAll(emgFindingMap);entryMap.putAll(mepFindingMap);
		entryMap.putAll(actionMap); entryMap.putAll(changeOfSEPMAP);entryMap.putAll(changeOfAEPMap);entryMap.putAll(changeOfMEPMap);
		entryMap.putAll(categoryMap);entryMap.putAll(measurementsWithValueMap);entryMap.putAll(gridPositioningMap);
		
		
		//put indivituadl Entities into the entryMAP
		String iOMStart = "IOMO_0000461";
		entryMap.put(ontEdit.getOntClassName(NS + iOMStart), NS + iOMStart);
		String iOMEnd = "IOMO_0000462";
		entryMap.put(ontEdit.getOntClassName(NS + iOMEnd), NS + iOMEnd);
		
		
	
	}

		
	
		
		/*
		//Test for Prefixes
		Map<String, String> hirarchylist = ontEdit.test();
		Iterator<Map.Entry<String, String>> it = hirarchylist.entrySet().iterator();
		Set<String> keys = hirarchylist.keySet();
		Iterator<String> key = keys.iterator();
		
		while (it.hasNext()) {
			Map.Entry<String, String> eqwe = it.next();
			String value = eqwe.getValue();
			System.out.println("iter test: " +value);
			System.out.println("key test:  "  + key.next());
			
		}
		
		
	}
	
	/**
	 * 
	 * @param Label
	 * @return the uri of the given String
	 */
	public String getUri(String Label) {
		return entryMap.get(Label);
		
	}
	
}
