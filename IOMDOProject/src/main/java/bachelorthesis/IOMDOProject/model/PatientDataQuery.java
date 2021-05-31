package bachelorthesis.IOMDOProject.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.util.FileManager;

import javafx.scene.control.cell.PropertyValueFactory;

public class PatientDataQuery {
	
	static OntologyEditor oe = OntologyEditor.getInstance();

	public static void main(String[] args) throws IOException {
		System.out.println(sparqlTest("http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000262"));
	}
	
	/**
	 * a method to query the patient, his diagnosis and his surgery
	 * @return an arraylist with all the patient, their diagnosis and surgeries (e.g. [Patient1, Schwannom, Aufrichtung, Patient2, ...]
	 * @throws IOException
	 */
	public static ArrayList<String>  sparqlTest(String uri) throws IOException {
		//FileManager.get().addLocatorClassLoader(QueryTester.class.getClassLoader());
		// ACHTUNG, hier habe ich noch keine andere Möglichkeit gefunden wie ich laden kann! Da es ein Model sein muss
		//Model model = FileManager.get().loadModel("/Users/stefanie/Desktop/SPARQLtest2.owl");
		Model model = oe.getModel();
		ArrayList<String> data;
				//"http://www.semanticweb.org/ontologies/2021/1/24/IOMO#Patient1";
		
		String queryString =
				"PREFIX  xsd:  <http://www.w3.org/2001/XMLSchema#> \n" +
						"PREFIX  OntoSPM: <http://medicis/spm.owl/OntoSPM#> \n" +
						"PREFIX  rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n" +
						"PREFIX  rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
						"PREFIX  owl:  <http://www.w3.org/2002/07/owl#> \n" +
						"SELECT DISTINCT  ?patient ?diagnosis ?surgery\n" +
						"WHERE\n" +
						"{?pat rdf:type OntoSPM:patient .\n" +
						"?pat rdfs:label ?patient .\n" +
						"?pat <http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000285> ?doc .\n" +
						"?diag rdf:type/(rdfs:subClassOf)* <http://purl.obolibrary.org/obo/OGMS_0000073> .\n" +
						"?surg rdf:type/(rdfs:subClassOf)* <http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000333> .\n" +
						"?doc <http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000282>  ?surg .\n" +
						"?doc<http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000282>  ?diag .\n" +
						"?diag rdf:type ?nameOfDiagnosis .\n" +
						"?surg rdf:type ?nameOfSurgery .\n" +
						"?doc rdfs:label ?iomdoc .\n" +
						"?nameOfDiagnosis rdfs:label ?diagnosis .\n" +
						"filter(langMatches(lang(?diagnosis),\"DE\"))\n" +
						"?nameOfSurgery rdfs:label ?surgery .\n" +
						"filter(langMatches(lang(?surgery),\"DE\")) .\n" +
						" FILTER (?pat = <"+uri+">) \n" +
						"  }";
		// create the query
		Query query = QueryFactory.create(queryString);
		// execute the query
		QueryExecution qexec = QueryExecutionFactory.create(query, model);;

		try {
			// create a new result set
			ResultSet results = qexec.execSelect();

			// write to a ByteArrayOutputStream
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			// as CSV (comma seperated)
			ResultSetFormatter.outputAsCSV(outputStream, results);

			// and turn that into a String
			String csv = new String(outputStream.toByteArray());
					
			// we want to cut the results in comma and new line
			String delimiters = ",\\s*|\\\n\\s*";
			
			// create a new array list with all the values of the result:
			// e.g. [Patient, Diagnosis, Surgery, Patient1, Schwannom, Aufrichtung, Patient2, ...]
			ArrayList<String> patientList = new ArrayList<>(Arrays.asList(csv.split(delimiters)));

			// create a new ArrayList
			data = new ArrayList<>();
			
			// remove the "Patient, Diagnosis, Surgery to only have the patient data in the ArrayList
			for(int i = 3; i < patientList.size(); i = i+1) {
				data.add(patientList.get(i));
			}

		} finally {
			// close the query execution
			qexec.close();

		}
		return data;



	}

	
}