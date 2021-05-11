package bachelorthesis.IOMDOProject.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

import bachelorthesis.IOMDOProject.Main;



/**
 * 
 * @author neues4
 * 
 */
public class OntologyEditor {

	// a new instance of an ontology model
	private static OntModel ontModel;

	// a new counter to count the URIs
	Counter uriCounter = new Counter(400);

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
	 * Method to put all the Subclasses of the Class "Diagnosis" into a Hash Map
	 * Label as key and URL as value
	 * @return the HashMap with all the Labels and URLs of the Subclasses of Diagnosis
	 */
	public Map<String, String> getAllDiagnosis() {
		OntClass diagnosis = ontModel.getOntClass("http://purl.obolibrary.org/obo/OGMS_0000073");
		HashMap<String, String> diagMap = new HashMap<>();
		Iterator diagnosisIter = diagnosis.listSubClasses();
		while (diagnosisIter.hasNext()) {
			OntClass sub = (OntClass) diagnosisIter.next();
			diagMap.put(sub.getLabel("DE"), sub.getURI());
		}
		return diagMap;
	}

	/**
	 * Method to put all the Subclasses of the Class "Surgery" into a Hash Map
	 * Label as key and URL as value
	 * @return the HashMap with all the Labels and URLs of the Subclasses of Surgery
	 */
	public Map<String, String> getAllSurgeries() {
		OntClass surgery = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000333");
		HashMap<String, String> surgMap = new HashMap<>();
		Iterator surgeryIter = surgery.listSubClasses();
		while (surgeryIter.hasNext()) {
			OntClass sub = (OntClass) surgeryIter.next();
			surgMap.put(sub.getLabel("DE"), sub.getURI());
		}
		return surgMap;
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

	public ArrayList<String> getAllPatients() {
		ArrayList<String> patAl = new ArrayList();
		Iterator indIter = ontModel.listIndividuals(ontModel.getOntClass("http://medicis/spm.owl/OntoSPM#patient"));
		while (indIter.hasNext()) {
			Individual ind = (Individual) indIter.next();
			patAl.add(ind.getLabel("EN"));
		}
		return patAl;
	}

	public ArrayList<String> getAllPatientURIs() {
		ArrayList<String> patAl = new ArrayList();
		Iterator indIter = ontModel.listIndividuals(ontModel.getOntClass("http://medicis/spm.owl/OntoSPM#patient"));
		while (indIter.hasNext()) {
			Individual ind = (Individual) indIter.next();
			patAl.add(ind.getURI());
		}
		return patAl;
	}
	
	public ArrayList<String> getAllSurgeryURIs() {
		ArrayList<String> patAl = new ArrayList();
		Iterator indIter = ontModel.listIndividuals(ontModel.getOntClass("http://medicis/spm.owl/OntoSPM#patient"));
		while (indIter.hasNext()) {
			Individual ind = (Individual) indIter.next();
			patAl.add(ind.getURI());
		}
		return patAl;
	}


	/**
	 * DELETE! DO NOT USE!
	 * Method to create a new individual
	 * @param classURI the class URI of the class where a new individual should be added
	 * @param newIndvURI the URI of the new individual (has to be edited with counter!)
	 * @param indvLabel the label of the new individual
	 */
	/*
	public void createNewOntIndividual(String classURI, String indvLabel) {
		OntClass ontClass = ontModel.getOntClass(classURI);
		String iomoURI = "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000";
		//String newIndvURI = getOntologyURL().concat(iomo).concat("124");
		Integer count = uriCounter.getValue();
		String newIndvURI = iomoURI.concat(count.toString());
		Individual indv = ontClass.createIndividual(newIndvURI);
		indv.addLabel(indvLabel, "EN");
		uriCounter.increment();	
	}
	 */

	public String createNewPatient(String indvLabel) {
		OntClass ontClass = ontModel.getOntClass("http://medicis/spm.owl/OntoSPM#patient");
		Individual indv = ontClass.createIndividual(createNewURI());
		indv.addLabel(indvLabel, "EN");
		return indv.getURI();
	}

	
	public String createNewURI() {
		String iomoURI = "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000";
		Integer count = uriCounter.getValue();
		String newURI = iomoURI.concat(count.toString());
		uriCounter.increment();	
		return newURI;
	}
	
	/*
	public String createURI(String label) {
		String iomoURI = "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO#";
		String count = label;
		String newURI = iomoURI.concat(label);	
		return newURI;
	}
	*/

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

	public String getIndividualURI() {
		String indiURI = null;
		Iterator iter = ontModel.listIndividuals();
		if(iter.hasNext()) {
			Individual indi = (Individual) iter.next();
			indiURI = indi.getURI();
			return indiURI;
		}
		return null; 
	}
	
	
	
	public void addPropertiesToPatient(String nr, String PID, String FID, String firstname, String surname, String birthday) {
		Individual indv = ontModel.getIndividual(getAllPatientURIs().get(getAllPatientURIs().size()-1));
		DatatypeProperty datPropNr = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000250");
		DatatypeProperty datPropPid = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000251");
		DatatypeProperty datPropFid = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000252");
		DatatypeProperty datPropFirstName = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000253");
		DatatypeProperty datPropSurname = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000254");
		DatatypeProperty datPropBirthday = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000255");
		indv.addProperty(datPropNr, nr);
		indv.addProperty(datPropPid, PID);
		indv.addProperty(datPropFid, FID);
		indv.addProperty(datPropFirstName, firstname);
		indv.addProperty(datPropSurname, surname);
		indv.addProperty(datPropBirthday, birthday);
	}

	

	public String createDiagnosis(String classURI, String indvLabel) {
		OntClass ontClass = ontModel.getOntClass(classURI);
		Individual indv = ontClass.createIndividual(createNewURI());
		indv.addLabel(indvLabel, "EN");
		//ObjectProperty obProp = ontModel.getObjectProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000282");
		//indv.addProperty(obProp, "");
		return indv.getURI();

	}
	
	public String createSurgery(String classURI, String indvLabel) {
		OntClass ontClass = ontModel.getOntClass(classURI);
		Individual indv = ontClass.createIndividual(createNewURI());
		indv.addLabel(indvLabel, "EN");
		//ObjectProperty obProp = ontModel.getObjectProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000282");
		//indv.addProperty(obProp, "");
		return indv.getURI();

	}

	public void addPropertiesToSurgery(String surgeryDate, String surgeon, String assistant, String isisDevice) {
		Individual indv = ontModel.getIndividual(getIndividualURI());
		DatatypeProperty datPropSurgeryDate = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000365");
		DatatypeProperty datPropSurgeon = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000363");
		DatatypeProperty datPropAssistant = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000364");
		DatatypeProperty datPropIsisDevice = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000366");
		indv.addProperty(datPropSurgeryDate, surgeryDate);
		indv.addProperty(datPropSurgeon, surgeon);
		indv.addProperty(datPropAssistant, assistant);
		indv.addProperty(datPropIsisDevice, isisDevice);
	}



	public void addStatement(String subject, String property, String object ) {

		Statement stmt = ontModel.createStatement
				(     
						ontModel.createResource( subject ), 
						ontModel.createProperty( property ), 
						ontModel.createResource( object ) 
						);

		ontModel.add( stmt );

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

	/**
	 * Method to get the first name of a specific individual
	 * @param indvURI the URI of the individual
	 * @return the first name of the individual
	 */
	public RDFNode getFirstName(String indvURI) {
		Individual indv = ontModel.getIndividual(indvURI);
		DatatypeProperty property = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000253");
		return indv.getPropertyValue(property);
	}

	/**
	 * Method to get the surname of a specific individual
	 * @param indvURI the URI of the individual
	 * @return the surname of the individual
	 */
	public RDFNode getSurname(String indvURI) {
		Individual indv = ontModel.getIndividual(indvURI);
		DatatypeProperty property = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000254");
		return indv.getPropertyValue(property);
	}

	/**
	 * Method to get the PID of a specific individual
	 * @param indvURI the URI of the individual
	 * @return the PID of the individual
	 */
	public RDFNode getPID(String indvURI) {
		Individual indv = ontModel.getIndividual(indvURI);
		DatatypeProperty property = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000251");
		return indv.getPropertyValue(property);
	}

	/**
	 * Method to get the FID of a specific individual
	 * @param indvURI the URI of the individual
	 * @return the FID of the individual
	 */
	public RDFNode getFID(String indvURI) {
		Individual indv = ontModel.getIndividual(indvURI);
		DatatypeProperty property = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000252");
		return indv.getPropertyValue(property);
	}                              

	/**
	 * Method to get the birthday of a specific individual
	 * @param indvURI the URI of the individual
	 * @return the birthday of the individual
	 */
	public RDFNode getBirthday(String indvURI) {
		Individual indv = ontModel.getIndividual(indvURI);
		DatatypeProperty property = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000255");
		return indv.getPropertyValue(property);
	}

	/**
	 * Method to get the birthday of a specific individual as String
	 * @param indvURI
	 * @return the birthday of the individual as String
	 */ 
	public String getBirthdayValue(String indvURI) {
		Individual indv = ontModel.getIndividual(indvURI);
		RDFNode node  = indv.getPropertyValue(ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000255"));
		return node.toString().replace("^^" + XSDDatatype.XSDdate.getURI(), "").toString();
	}
}
