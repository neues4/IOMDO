package bachelorthesis.IOMDOProject.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import bachelorthesis.IOMDOProject.model.OntologyReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class QueryController {

	@FXML
	private TextArea queryResult, inputQuery, ownQueryResult;

	@FXML
	private Button query1, query2, ownQuery;

	Integer y;

	// create a new instance of the ontology reader
	OntologyReader oe = OntologyReader.getInstance();

	/**
	 * Method to execute a query that looks for all the patient with postop outcome and their corresponding
	 * DCS MEP measurements
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void executeQuery(ActionEvent event) throws IOException {
		Model model = oe.getReaderModel();
		// the SPARQL query
		String queryString =
				"PREFIX  xsd:  <http://www.w3.org/2001/XMLSchema#>    \n"+
						"PREFIX  OntoSPM: <http://medicis/spm.owl/OntoSPM#>   \n" +
						"PREFIX  rdfs: <http://www.w3.org/2000/01/rdf-schema#>   \n" +
						"PREFIX  rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +   
						"PREFIX  owl:  <http://www.w3.org/2002/07/owl#>    \n" +
						"PREFIX IOMO: <http://www.semanticweb.org/ontologies/2021/1/24/IOMO/> \n" +
						"PREFIX obo: <http://purl.obolibrary.org/obo/> \n" +
						"SELECT  DISTINCT ?Nachname ?Vorname ?Geburtsdatum ?Diagnose ?PostopOutcome ?ArtDerMessung ?Muskel ?mA \n"+
						"WHERE    \n"+
						"{ ?pat rdf:type OntoSPM:patient .    \n"+
						"?pat rdfs:label ?patient .    \n"+
						"?pat IOMO:IOMO_0000254 ?Nachname .   \n"+
						"?pat IOMO:IOMO_0000253 ?Vorname .   \n"+
						"?pat IOMO:IOMO_0000255 ?Geburtsdatum .   \n"+
						"?pat IOMO:IOMO_0000285 ?doc .    \n"+
						"?diag rdf:type/(rdfs:subClassOf)* obo:OGMS_0000073 .  \n"  +
						"?surg rdf:type/(rdfs:subClassOf)* IOMO:IOMO_0000333 .   \n" +
						"?clinDataItem rdf:type obo:OGMS_0000123 . \n"+
						"?disp rdf:type/(rdfs:subClassOf)* IOMO:IOMO_0000381 .  \n"+
						"?mes rdf:type/(rdfs:subClassOf)* IOMO:IOMO_0000238 .   \n"+
						"?doc IOMO:IOMO_0000282  ?surg .   \n" +
						"?doc IOMO:IOMO_0000282  ?diag .  \n"  +
						"?doc IOMO:IOMO_0000282  ?mes .   \n" +
						"?doc IOMO:IOMO_0000282  ?clinDataItem .    \n"+
						"?clinDataItem obo:BFO_0000058 ?disp . \n"+
						"?diag rdf:type ?nameOfDiagnosis .    \n"+
						"?surg rdf:type ?nameOfSurgery .   \n"+
						"?mes rdf:type ?nameOfMes .   \n"+
						"?disp rdf:type ?nameOfDisposition . \n"+
						"?doc rdfs:label ?iomdoc .    \n"+
						"?nameOfDiagnosis rdfs:label ?Diagnose .   \n" +
						"filter(langMatches(lang(?Diagnose), \"DE\"))    \n"+
						"?nameOfSurgery rdfs:label ?surgery .    \n"+
						"filter(langMatches(lang(?surgery),\"DE\")) .  \n"  +
						"?nameOfDisposition rdfs:label ?PostopOutcome . \n"+
						"filter(langMatches(lang(?PostopOutcome),\"DE\"))  \n"+
						"?measurement rdf:type IOMO:IOMO_0000268 .   \n"+
						"?mes IOMO:IOMO_0000275 ?measurement .   \n"+
						"?nameOfMes rdfs:label ?ArtDerMessung .   \n"+
						"filter(langMatches(lang(?ArtDerMessung),\"DE\"))  \n"  +
						"?measurement obo:IAO_0000004 ?mA .   \n"+
						"?musc rdf:type/(rdfs:subClassOf)* IOMO:IOMO_0000227 .    \n"+
						"?measurement IOMO:IOMO_0000468 ?musc .   \n"+
						"?musc rdf:type ?nameOfMusc .   \n"+
						"?nameOfMusc rdfs:label ?Muskel . \n"  +
						"filter(langMatches(lang(?Muskel),\"DE\"))    \n"+
						" } ";
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

			// save a new csv file to desktop
			String userHomeFolder = System.getProperty("user.home");
			File textFile = new File(userHomeFolder, "queryResultMEPValues.csv");
			FileWriter csvWriter = new FileWriter(textFile);

			csvWriter.append(csv);

			csvWriter.flush();
			csvWriter.close();

			queryResult.setText(csv);

			showAlert();

		} finally {
			qexec.close();

		}

	}

	/**
	 * Method to execute a query that looks for all the patients with postop outcome and their
	 * corresponding lowest mapping threshold
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void executeQuery2(ActionEvent event) throws IOException {
		Model model = oe.getReaderModel();
		// the SPARQL query
		String queryString = 
				"PREFIX  xsd:  <http://www.w3.org/2001/XMLSchema#>    \n "+
						"PREFIX  rdfs: <http://www.w3.org/2000/01/rdf-schema#>   \n "+
						"PREFIX  rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>    \n" +
						"PREFIX  owl:  <http://www.w3.org/2002/07/owl#>    \n" +
						"PREFIX  OntoSPM: <http://medicis/spm.owl/OntoSPM#>    \n" +
						"PREFIX IOMO: <http://www.semanticweb.org/ontologies/2021/1/24/IOMO/> \n" +
						"PREFIX obo: <http://purl.obolibrary.org/obo/> \n" +
						"SELECT DISTINCT ?Name ?Vorname ?Geburtsdatum ?Diagnose ?PostopOutcome ?kleinsterMappingwert \n" +
						"WHERE    \n" +
						"{ ?pat rdf:type OntoSPM:patient .    \n" +
						"?pat rdfs:label ?patient .    \n" +
						"?pat IOMO:IOMO_0000254 ?Name .  \n" +
						"?pat IOMO:IOMO_0000253 ?Vorname .  \n" +
						"?pat IOMO:IOMO_0000255 ?Geburtsdatum .  \n" +
						"?pat IOMO:IOMO_0000285 ?doc .    \n" +
						"?diag rdf:type/(rdfs:subClassOf)* obo:OGMS_0000073 .    \n" +
						"?surg rdf:type/(rdfs:subClassOf)* IOMO:IOMO_0000333 .    \n" +
						"?clinDataItem rdf:type obo:OGMS_0000123 . \n" +
						"?disp rdf:type/(rdfs:subClassOf)* IOMO:IOMO_0000381 .   \n" +
						"?mapping rdf:type/(rdfs:subClassOf)* IOMO:IOMO_0000373 .   \n" +
						"?doc IOMO:IOMO_0000282  ?surg .    \n" +
						"?doc IOMO:IOMO_0000282  ?diag .    \n" +
						"?doc IOMO:IOMO_0000282  ?mapping .    \n" +
						"?doc IOMO:IOMO_0000282  ?clinDataItem .     \n" +
						"?clinDataItem obo:BFO_0000058 ?disp . \n" +
						"?diag rdf:type ?nameOfDiagnosis .    \n" +
						"?surg rdf:type ?nameOfSurgery .    \n"+ 
						"?mapping rdf:type ?nameOfMapping .  \n" +
						"?nameOfMapping rdfs:label ?map .  \n" +
						"filter(langMatches(lang(?map),\"DE\"))  \n" +
						"?doc rdfs:label ?iomdoc .    \n" +
						"?nameOfDiagnosis rdfs:label ?Diagnose .  \n  " +
						"filter(langMatches(lang(?Diagnose),\"DE\"))    \n" +
						"?nameOfSurgery rdfs:label ?surgery .    \n" +
						"filter(langMatches(lang(?surgery),\"DE\")) .   \n " +
						"?disp rdf:type ?nameOfDisposition . \n" +
						"?nameOfDisposition rdfs:label ?PostopOutcome .  \n" +
						"filter(langMatches(lang(?PostopOutcome),\"DE\"))    \n" +
						"?measurement rdf:type IOMO:IOMO_0000268 .   \n" +
						"?mapping IOMO:IOMO_0000275 ?measurement . \n" +
						"?measurement obo:IAO_0000004 ?value .   \n" +
						"{ \n" +
						"SELECT  ?pat (MIN(?value) as ?kleinsterMappingwert) \n" +
						"WHERE { \n" +
						"?pat rdf:type OntoSPM:patient . \n" +
						"?pat IOMO:IOMO_0000285 ?doc .    \n" +
						"?doc IOMO:IOMO_0000282 ?mapping .     \n" +
						"?mapping IOMO:IOMO_0000275 ?measurement .  \n" +
						"?measurement obo:IAO_0000004 ?value .   \n"  +
						"} GROUP BY ?pat \n" +
						"} \n"  +
						"}";
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

			// save a new csv file to desktop
			String userHomeFolder = System.getProperty("user.home");
			File textFile = new File(userHomeFolder, "queryResultLowestMappingThreshold.csv");
			FileWriter csvWriter = new FileWriter(textFile);


			csvWriter.append(csv);

			csvWriter.flush();
			csvWriter.close();

			queryResult.setText(csv);

			showAlert();

		} finally {
			// close the query execution
			qexec.close();

		}
	}

	/**
	 * Method that allows the user to create his own query
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void executeOwnQuery(ActionEvent event) throws IOException {
		Model model = oe.getReaderModel();

		// create the query
		Query query = QueryFactory.create(inputQuery.getText());
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

			// save a new csv file to desktop
			String userHomeFolder = System.getProperty("user.home");
			File textFile = new File(userHomeFolder, "queryResultOwnQuery.txt");
			FileWriter csvWriter = new FileWriter(textFile);

			csvWriter.append(csv);

			csvWriter.flush();
			csvWriter.close();

			ownQueryResult.setText(csv);
			showAlert();

		} finally {
			// close the query execution
			qexec.close();

		}
	}

	/**
	 * Alert to show the user that the csv was exported successfully
	 */
	public void showAlert() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("CSV Export");
		alert.setHeaderText("Das Ergebnis der Abfrage wurde erfolgreich als CSV-Datei exportiert!");
		alert.show();
	}



}