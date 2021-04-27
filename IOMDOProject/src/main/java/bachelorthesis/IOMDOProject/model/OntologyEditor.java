package bachelorthesis.IOMDOProject.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;

import bachelorthesis.IOMDOProject.Main;


/**
 * 
 * @author neues4
 * 
 */
public class OntologyEditor {

	private static OntModel ontModel;

	/**
	 * Constructor to create a new Ontology Editor.
	 * @param filePath the local path to the Ontology File (.owl)
	 */
	public OntologyEditor(String filePath) {

		ontModel = ModelFactory.createOntologyModel();
		try {
			File file = new File(filePath);
			FileReader reader = new FileReader(file);
			ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
			ontModel.read(reader, null);
			//ontModel.write(System.out, "RDF/XML-ABBREV");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Method to return all Classes present in the Ontology
	 * (only to the console to check if the ontology was loaded)
	 * @return 
	 */
	public String getAllOntologyClasses() {
		Iterator classIter = ontModel.listClasses();
		while (classIter.hasNext()) {
			OntClass ontClass = (OntClass) classIter.next();
			String uri = ontClass.getURI();
			if (uri != null)
				System.out.println(uri);
		}
		return null;
	}


	/**
	 * Method to return the label of a specific class
	 * @param classURI the class uri
	 * @return the label of the class in english
	 */
	public String getOntologyClass(String classURI) {
		OntClass ontClass = ontModel.getOntClass(classURI);
		return ontClass.getLabel("EN");
	}


	/**
	 * Method to return the URI of the loaded ontology
	 * @return the URI of the loaded ontology
	 */
	public String getOntologyURL() {
		String ontologyURI = null;
		Iterator iter = ontModel.listOntologies();

		if(iter.hasNext()) {
			Ontology onto = (Ontology) iter.next();
			ontologyURI = onto.getURI();
			return "Ontology URI = "+ ontologyURI;
		}
		return null; 

	}

	/**
	 * Method to get all the properties of a specific class
	 * (to test and see what we have)
	 * @param classURI
	 * @return an array list of all the properties of a specific class
	 */
	public ArrayList<String> getPropertiesOfClass(String classURI) {
		OntClass student = ontModel.getOntClass(classURI);
		ArrayList<String> propAL = new ArrayList();
		Iterator propIter = student.listDeclaredProperties();
		while (propIter.hasNext()) {
			OntProperty property = (OntProperty) propIter.next();
			propAL.add(property.getLocalName());
		}
		return propAL;
	}

	/**
	 * A method to print all the datatype properties present in the ontology to the console
	 * Format: datatype property -> domain -> range
	 */
	public void getAllDatatypeProperties() {
		Iterator iter = ontModel.listDatatypeProperties();
		while (iter.hasNext()) {
			DatatypeProperty prop = (DatatypeProperty) iter.next();
			String propName = prop.getLocalName();
			String dom = "";
			String rng = "";
			if (prop.getDomain() != null)
				dom = prop.getDomain().getLocalName();
			if (prop.getRange() != null) 
				rng = prop.getRange().getLocalName();
			System.out.println(propName + ": \t (" + dom +") \t -> (" + rng+") ");
		}
	}

	/**
	 * Method to get all the Individuals of the ontology
	 * @return ArrayList of the individuals (label)
	 */
	public ArrayList<String> getAllIndividuals() {
		ArrayList<String> indAL = new ArrayList();
		Iterator indIter = ontModel.listIndividuals();
		while (indIter.hasNext()) {
			Individual ind = (Individual) indIter.next();
			indAL.add(ind.getLabel("EN"));
		}
		return indAL;
	}


	/**
	 * Method to create a new individual
	 * @param classURI the class URI of the class where a new individual should be added
	 * @param newIndvURI the URI of the new individual (has to be edited with counter!)
	 * @param indvLabel the label of the new individual
	 */
	public void createNewOntIndividual(String classURI, String newIndvURI, String indvLabel) {
		OntClass ontClass = ontModel.getOntClass(classURI);
		Individual indv = ontClass.createIndividual(newIndvURI);
		indv.addLabel(indvLabel, "EN");
	}


	/**
	 * Method to add property to individual
	 * @param indvURI the URI of the individual
	 * @param propURI the URI of the property
	 * @param name the name/age/number we want to add
	 */
	public void addPropertyToIndividual(String indvURI, String propURI, String name) {
		Individual indv = ontModel.getIndividual(indvURI);
		DatatypeProperty firstName = ontModel.getDatatypeProperty(propURI);
		indv.addProperty(firstName, ontModel.createTypedLiteral(name));
	}


	/**
	 * Method to save the edited ontology into an owl file
	 */
	public void saveNewOWLFile() {
		StringWriter sw = new StringWriter();
		ontModel.write(sw, "RDF/XML-ABBREV");
		String owlCode = sw.toString();
		File file = new File("myModel.owl");
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(owlCode);
			fw.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}
	
	
	/**
	 * Method to return a specific value of a property of a specific individual
	 * @param indvURI the URI of the individual
	 * @param propURI the URI of the property (for example the URI of "has first name")
	 * @return 
	 */
	public RDFNode getPropertyOfIndividual(String indvURI, String propURI) {
		Individual indv = ontModel.getIndividual(indvURI);
		DatatypeProperty property = ontModel.getDatatypeProperty(propURI);
		return indv.getPropertyValue(property);
	}
}
