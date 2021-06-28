package bachelorthesis.IOMDOProject.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.Ontology;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.util.FileManager;



/**
 * Kopie von OntologyEditor, werde noch bearbeiten welche klassen es braucht und welche nicht. 
 * Reading Ontology only.
 * @author romap1
 * 
 */
public class OntologyReader {


	// a new instance of an ontology model // romap1: habe static rausgenommen, weil ich vorteil nicht gesehen habe
	private  OntModel ontModel;

	private static OntologyReader editor;
	// a new counter to count the URIs
	


	public static OntologyReader getInstance()
	{

		if (editor == null)
			
			editor = new OntologyReader("myModel.owl");

		return editor;
	}
	
	@SuppressWarnings("exports")
	public static Model getReaderModel() {
		Model model = OntologyReader.getInstance().ontModel;
		return model;
	}

	/**
	 * Constructor to create a new Ontology Editor.
	 * @param filePath the local path to the Ontology File (.owl)
	 */
	public OntologyReader(String filePath) {

		ontModel = ModelFactory.createOntologyModel();
		try {
			File file = new File(filePath);
			FileReader reader = new FileReader(file);
			ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
			ontModel.read(reader, "myModel.owl");
			//ontModel.write(System.out, "RDF/XML-ABBREV");
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public RDFNode getCaseNumber(String indvURI) {
		Individual indv = ontModel.getIndividual(indvURI);
		DatatypeProperty property = ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000250");
		return indv.getPropertyValue(property);
	}

	/**
	 * Method to get the birthday of a specific individual as String
	 * @author romap1
	 * @param indvURI
	 * @return the birthday of the individual as String
	 */ 
	public String getBirthdayValue(String indvURI) {
		Individual indv = ontModel.getIndividual(indvURI);
		RDFNode node  = indv.getPropertyValue(ontModel.getDatatypeProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000255"));
		return node.toString().replace("^^" + XSDDatatype.XSDdate.getURI(), "").toString();
	}

	
}
