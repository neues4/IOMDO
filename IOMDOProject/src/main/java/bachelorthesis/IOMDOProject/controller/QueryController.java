package bachelorthesis.IOMDOProject.controller;

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.plaf.DesktopIconUI;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.resultset.rw.ResultSetWriterCSV;
import org.apache.jena.riot.resultset.rw.ResultSetWriterText;
import org.apache.jena.util.FileManager;

import bachelorthesis.IOMDOProject.model.OntologyReader;
import bachelorthesis.IOMDOProject.model.QueryException;
import bachelorthesis.IOMDOProject.model.QueryTester;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class QueryController {

	@FXML
	private TextArea test;

	@FXML
	private Button query1, query2, query3;



	//OntologyReader oe = OntologyReader.getInstance();

	@SuppressWarnings({ "unused", "deprecation" })
	@FXML
	void executeQuery(ActionEvent event) throws QueryException, IOException {
		FileManager.get().addLocatorClassLoader(QueryTester.class.getClassLoader());
		Model model = FileManager.get().loadModel("/Users/stefanie/Desktop/SPARQLtest2.owl");
		//Model model = OntologyReader.getReaderModel();
		// the SPARQL query
		String queryString =
				"PREFIX  xsd:  <http://www.w3.org/2001/XMLSchema#> \n" +
						"PREFIX  OntoSPM: <http://medicis/spm.owl/OntoSPM#> \n"+
						"PREFIX  rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n"+
						"PREFIX  rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"+
						"PREFIX  owl:  <http://www.w3.org/2002/07/owl#> "+
						"SELECT  ?patient ?diagnosis ?disposition ?typeOfMeasurement ?value ?muscle \n"+
						"	WHERE \n"+
						"  { ?pat rdf:type OntoSPM:patient . \n"+
						"   ?pat rdfs:label ?patient . \n"+
						"  ?pat <http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000285> ?doc . \n"+
						"  ?diag rdf:type/(rdfs:subClassOf)* <http://purl.obolibrary.org/obo/OGMS_0000073> . \n"+
						"  ?surg rdf:type/(rdfs:subClassOf)* <http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000333> . \n"+
						"  ?disp rdf:type/(rdfs:subClassOf)* <http://purl.obolibrary.org/obo/HP_0012638> . \n"+
						"    ?mes rdf:type/(rdfs:subClassOf)* <http://purl.obolibrary.org/obo/IAO_0000109> . \n"+
						"   ?doc <http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000282>  ?surg . \n"+
						"    ?doc<http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000282>  ?diag . \n "+
						"   ?doc<http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000282>  ?disp . \n"+
						"   ?doc<http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000282>  ?mes . \n"+
						"   ?diag rdf:type ?nameOfDiagnosis . \n"+
						"   ?surg rdf:type ?nameOfSurgery . \n"+
						"   ?disp rdf:type ?nameOfDisposition . \n"+
						"    ?mes rdf:type ?nameOfMes . \n"+
						"	?nameOfMes rdfs:label ?typeOfMeasurement . \n"+
						"filter(langMatches(lang(?typeOfMeasurement), \"DE\")) \n"+
						"   ?doc rdfs:label ?iomdoc . \n"+
						"   ?nameOfDiagnosis rdfs:label ?diagnosis . \n"+
						"   filter(langMatches(lang(?diagnosis), \"DE\")) \n"+
						" ?nameOfSurgery rdfs:label ?surgery . \n"+
						"  filter(langMatches(lang(?surgery),\"DE\")) . \n"+
						"?nameOfDisposition rdfs:label ?disposition . \n" +
						"filter(langMatches(lang(?disposition), \"DE\")) \n" +
						"	?measurement rdf:type <http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000268> . \n"+
						"	?mes <http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275> ?measurement . \n"+
						"	?measurement <http://purl.obolibrary.org/obo/IAO_0000004> ?value . \n"+
						"	?musc rdf:type/(rdfs:subClassOf)* <http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000227> . \n"+
						"	?measurement <http://purl.obolibrary.org/obo/IAO_0000136> ?musc . \n"+
						"	?musc rdf:type ?nameOfMusc . \n"+
						"?nameOfMusc rdfs:label ?muscle . \n"+
						"	filter(langMatches(lang(?muscle),\"DE\")) \n"+
						"  }";
		// create the query
		Query query = QueryFactory.create(queryString);
		// execute the query
		QueryExecution qexec = QueryExecutionFactory.create(query, model);

		try {
			// create a new result set
			ResultSet results = qexec.execSelect();

			// write to a ByteArrayOutputStream
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			ResultSetFormatter.outputAsCSV(outputStream, results);


			// and turn that into a String
			String csv = new String(outputStream.toByteArray());

			FileWriter csvWriter = new FileWriter("changeInMiliamperDCS.csv");

			csvWriter.append(csv);

			csvWriter.flush();
			csvWriter.close();

			test.setText(csv);

		} finally {
			qexec.close();

		}

	}

	@FXML
	void executeQuery2(ActionEvent event) throws IOException {

	}

	@FXML
	public void executeQuery3(ActionEvent event) throws IOException {
		// load the file (choose a file with instances of patient, iomdocument, diagnosis and surgery)
		FileManager.get().addLocatorClassLoader(QueryTester.class.getClassLoader());
		Model model = FileManager.get().loadModel("/Users/stefanie/Desktop/SPARQLtest2.owl");
		// the SPARQL query
		// the SPARQL query
		String queryString = 
				"PREFIX  xsd:  <http://www.w3.org/2001/XMLSchema#> \n"  +
						"PREFIX  OntoSPM: <http://medicis/spm.owl/OntoSPM#> \n"   +
						"PREFIX  rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n" +
						"PREFIX  rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"+
						"PREFIX  owl:  <http://www.w3.org/2002/07/owl#> \n"   +
						"SELECT DISTINCT ?patient  ?diagnosis ?disposition ?value \n" +
						"WHERE \n"  +
						"{ ?pat rdf:type OntoSPM:patient . \n"+
						"?pat rdfs:label ?patient . \n"+
						"?pat <http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000285> ?doc . \n "+
						"?diag rdf:type/(rdfs:subClassOf)* <http://purl.obolibrary.org/obo/OGMS_0000073> . \n"+
						" ?surg rdf:type/(rdfs:subClassOf)* <http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000333> . \n" +
						"  ?disp rdf:type/(rdfs:subClassOf)* <http://purl.obolibrary.org/obo/HP_0012638> . \n"+
						"?mapping rdf:type/(rdfs:subClassOf)* <http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000373> . \n"+
						"  ?doc <http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000282>  ?surg . \n"+
						" ?doc<http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000282>  ?diag . \n"+
						" ?doc<http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000282>  ?disp . \n"+
						"  ?doc<http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000282>  ?mapping . \n"+
						" ?diag rdf:type ?nameOfDiagnosis . \n"+
						" ?surg rdf:type ?nameOfSurgery . \n"+
						"?mapping rdf:type ?nameOfMapping . \n"+
						"?nameOfMapping rdfs:label ?map . \n"+
						"filter(langMatches(lang(?map),\"DE\")) \n"+
						"?disp rdf:type ?nameOfDisposition . \n"+
						"  ?doc rdfs:label ?iomdoc . \n"+
						" ?nameOfDiagnosis rdfs:label ?diagnosis . \n"+
						"  filter(langMatches(lang(?diagnosis),\"DE\")) \n"+
						"  ?nameOfSurgery rdfs:label ?surgery . \n"+
						" filter(langMatches(lang(?surgery),\"DE\")) . \n"+
						"?nameOfDisposition rdfs:label ?disposition . \n"+
						"filter(langMatches(lang(?disposition),\"DE\")) \n"+
						"?measurement rdf:type <http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000268> . \n"+
						"?measurement <http://purl.obolibrary.org/obo/IAO_0000004> ?value . \n"+
						"} ORDER BY ?value \n"+
						" LIMIT 1";
		// create the query
		Query query = QueryFactory.create(queryString);
		// execute the query
		QueryExecution qexec = QueryExecutionFactory.create(query, model);

		try {
			// create a new result set
			ResultSet results = qexec.execSelect();

			// write to a ByteArrayOutputStream
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			ResultSetFormatter.outputAsCSV(outputStream, results);


			// and turn that into a String
			String csv = new String(outputStream.toByteArray());

			test.setText(csv);

			FileWriter csvWriter = new FileWriter("mappingThreshold.csv");

			csvWriter.append(csv);

			csvWriter.flush();
			csvWriter.close();

		} finally {
			// close the query execution
			qexec.close();

		}

	}
}