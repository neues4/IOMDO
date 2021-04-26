package bachelorthesis.IOMDOProject.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
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

public class OntologyEditor {
	
	public static void main(String[] args) {
		
		OntModel model = ModelFactory.createOntologyModel();
		
		
		// read a File into Ontology Model
		String fileName = "/Users/stefanie/Desktop/IOMO_18.owl";
		try {
			File file = new File(fileName);
			FileReader reader = new FileReader(file);
			model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
			
			model.read(reader, null);
			
			//model.write(System.out, "RDF/XML-ABBREV");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// retrive ontology classes
		/*
		Iterator classIter = model.listClasses();
		while (classIter.hasNext()) {
			OntClass ontClass = (OntClass) classIter.next();
			String uri = ontClass.getURI();
			if (uri != null)
				System.out.println(uri);
		}
		*/
		
		// retrive a specified class
		/*
		String classURI = "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000176";
		OntClass test = model.getOntClass(classURI);
		System.out.println(test);
		*/
		
		// get the ontology URI
		
		/*
		String ontologyURI = null;
		Iterator iter = model.listOntologies();
		
		if(iter.hasNext()) {
			Ontology onto = (Ontology) iter.next();
			ontologyURI = onto.getURI();
			System.out.println("Ontology URI = "+ ontologyURI);
		} 
		*/
		
		// retrive the properties of a specified class
		/*
		OntClass student = model.getOntClass("http://medicis/spm.owl/OntoSPM#patient");
		
		Iterator propIter = student.listDeclaredProperties();
		while (propIter.hasNext()) {
			OntProperty property = (OntProperty) propIter.next();
			System.out.println(property.getLocalName());
		}
		*/
		
		// list all datatype properties in the model
		/*
		Iterator iter = model.listDatatypeProperties();
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
		*/
		
		// List all individuals in the model
		/*
		Iterator individuals = model.listIndividuals();
		while (individuals.hasNext()) {
			Individual individual = (Individual) individuals.next();
			System.out.println(individual.getLocalName());
		}
		*/
		
		
		
		// create an individual		
		OntClass test = model.getOntClass("http://medicis/spm.owl/OntoSPM#patient");
		Individual indv = test.createIndividual("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000263");
		indv.addLabel("Patient 1", "EN");
		
		Property hasFirstName = model.getProperty("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_000025");
		
		indv.addProperty(hasFirstName, "Derek");
	
		// List all individuals in the model
				
				Iterator individuals = model.listIndividuals();
				while (individuals.hasNext()) {
					Individual individual = (Individual) individuals.next();
					System.out.println(individual.getLocalName());
				}
				
				
				
				//save the model to a file
				StringWriter sw = new StringWriter();
				model.write(sw, "RDF/XML-ABBREV");
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
	

}

