package bachelorthesis.IOMDOProject.model;

/**
import java.io.IOException;
import java.util.ArrayList;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;
*/

/**
 * A Class to show how Queries with SPARQL work
 * With this code we can extract the patients with their iom documents and the data items "surgery" and "diagnosis"
 * @author stefanie
 *
 */
public class QueryTester {
/*
	public static void main(String[] args) throws IOException {
		sparqlTest();
	}

	static void sparqlTest() throws IOException {
		// load the file (choose a file with instances of patient, iomdocument, diagnosis and surgery)
		FileManager.get().addLocatorClassLoader(QueryTester.class.getClassLoader());
		Model model = FileManager.get().loadModel("/Users/stefanie/Desktop/BADokumente/IOMO_SPARQLtest.owl");
		// the SPARQL query
		String queryString =
				"PREFIX  xsd:  <http://www.w3.org/2001/XMLSchema#> \n" +
						"PREFIX  OntoSPM: <http://medicis/spm.owl/OntoSPM#> \n" +
						"PREFIX  rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n" +
						"PREFIX  rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
						"PREFIX  owl:  <http://www.w3.org/2002/07/owl#> \n" +
						"SELECT DISTINCT  ?patient ?iomdoc ?diagnosis ?surgery\n" +
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
						"  }";
		// create the query
		Query query = QueryFactory.create(queryString);
		// execute the query
		QueryExecution qexec = QueryExecutionFactory.create(query, model);

		try {
			// create a new result set
			ResultSet results = qexec.execSelect();

			// print the query to the console
			//System.out.println(qexec.getQuery());
			// print the result to the console
			//ResultSetFormatter.out(System.out, results);
			
			// tested to create arrays for the tableview
			ArrayList<String> patient = new ArrayList<String>();
			ArrayList<String> iomdoc = new ArrayList<String>();
			ArrayList<String> diagnosis = new ArrayList<String>();
			ArrayList<String> surgery = new ArrayList<String>();
			
			// loop over the results
			while(results.hasNext()) {
				QuerySolution qs = results.next();
				
				String resultString = qs.toString();
				patient.add(qs.get("patient").toString());
				iomdoc.add(qs.get("iomdoc").toString());
				surgery.add(qs.get("diagnosis").toString());
				diagnosis.add(qs.get("surgery").toString());
				System.out.println(resultString);
				//test.add(resultString);

			}
	
			System.out.println(patient);
			System.out.println(iomdoc.toString());
			System.out.println(diagnosis.toString());
			System.out.println(surgery.toString());
			
		} finally {
			// close the query execution
			qexec.close();

		}



	}
*/
}
