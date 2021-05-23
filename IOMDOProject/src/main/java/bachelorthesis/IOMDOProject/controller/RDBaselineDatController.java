package bachelorthesis.IOMDOProject.controller;

import bachelorthesis.IOMDOProject.model.Counter;
import bachelorthesis.IOMDOProject.model.OntologyEditor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class RDBaselineDatController {

	//OntologyEditor ontEdit = new OntologyEditor("/Users/stefanie/Desktop/IOMO_27.owl");
	//OntologyEditor ontEdit = new OntologyEditor("src\\main\\resources\\bachelorthesis\\IOMDOProject\\IOMO_28.owl");
	OntologyEditor ontEdit = OntologyEditor.getInstance();
	// Muscle
	ObservableList<String> muscleList = FXCollections.observableArrayList(ontEdit.getAllMuscles().keySet());

	@FXML
	private Accordion accordion;

	@FXML
	private GridPane tesMepGridpane;

	@FXML
	private ComboBox<String> cbTes1, cbTes2, cbTes3, cbTes4, cbTes5, cbTes6, cbTes7, cbTes8, cbTes9, cbTes10, cbTes11, cbTes12, cbTes13, cbTes14;

	@FXML
	private ComboBox<String> cbDcs1, cbDcs2, cbDcs3, cbDcs4, cbDcs5, cbDcs6, cbDcs7, cbDcs8, cbDcs9, cbDcs10;

	@FXML
	private TextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10, tf11, tf12, tf13, tf14;
	
	@FXML
    private TextField tfDcs1, tfDcs2, tfDcs3, tfDcs4, tfDcs5, tfDcs6, tfDcs7, tfDcs8, tfDcs9, tfDcs10;


	@FXML
	private TextField tfMedLN, tfMedRN, tfTibLN, tfTibRN, tfMedLP, tfMedRP, tfTibLP, tfTibRP;

	@FXML
	private Button btnSaveTesMep, btnSaveSsep, btnSaveDcsMep;

	Counter muscNumber = new Counter(1);
	Counter mANumber = new Counter(1);

	@FXML
	public void initialize() {
		cbTes1.setItems(muscleList);
		cbTes2.setItems(muscleList);
		cbTes3.setItems(muscleList);
		cbTes4.setItems(muscleList);
		cbTes5.setItems(muscleList);
		cbTes6.setItems(muscleList);
		cbTes7.setItems(muscleList);
		cbTes8.setItems(muscleList);
		cbTes9.setItems(muscleList);
		cbTes10.setItems(muscleList);
		cbTes11.setItems(muscleList);
		cbTes12.setItems(muscleList);
		cbTes13.setItems(muscleList);
		cbTes14.setItems(muscleList);
		
		cbDcs1.setItems(muscleList);
		cbDcs2.setItems(muscleList);
		cbDcs3.setItems(muscleList);
		cbDcs4.setItems(muscleList);
		cbDcs5.setItems(muscleList);
		cbDcs6.setItems(muscleList);
		cbDcs7.setItems(muscleList);
		cbDcs8.setItems(muscleList);
		cbDcs9.setItems(muscleList);
		cbDcs10.setItems(muscleList);
	}

	public void saveSsep(ActionEvent event) {
		// Medianus LN
		String nerve1 = ontEdit.createNewIndividual("http://purl.obolibrary.org/obo/FMA_65328", "nerve1");
		String ms1 = ontEdit.createNewMilisecond("ms1");
		ontEdit.addPropertiesToMilisecond(ms1, tfMedLN.getText());
		ontEdit.addStatement(ms1,"http://purl.obolibrary.org/obo/IAO_0000136", nerve1);
		// Medianus RN
		String nerve2 = ontEdit.createNewIndividual("http://purl.obolibrary.org/obo/FMA_65327", "nerve2");
		String ms2 = ontEdit.createNewMilisecond("ms2");
		ontEdit.addPropertiesToMilisecond(ms2, tfMedRN.getText());
		ontEdit.addStatement(ms2,"http://purl.obolibrary.org/obo/IAO_0000136", nerve2);
		// Tibialis LN
		String nerve3 = ontEdit.createNewIndividual("http://purl.obolibrary.org/obo/FMA_80064", "nerve3");
		String ms3 = ontEdit.createNewMilisecond("ms3");
		ontEdit.addPropertiesToMilisecond(ms3, tfTibLN.getText());
		ontEdit.addStatement(ms3,"http://purl.obolibrary.org/obo/IAO_0000136", nerve3);
		// Tibialis RN
		String nerve4 = ontEdit.createNewIndividual("http://purl.obolibrary.org/obo/FMA_80063", "nerve4");
		String ms4 = ontEdit.createNewMilisecond("ms4");
		ontEdit.addPropertiesToMilisecond(ms4, tfTibRN.getText());
		ontEdit.addStatement(ms4,"http://purl.obolibrary.org/obo/IAO_0000136", nerve4);

		// Medianus LP
		String nerve5 = ontEdit.createNewIndividual("http://purl.obolibrary.org/obo/FMA_65328", "nerve5");
		String ms5 = ontEdit.createNewMilisecond("ms5");
		ontEdit.addPropertiesToMilisecond(ms5, tfMedLP.getText());
		ontEdit.addStatement(ms5,"http://purl.obolibrary.org/obo/IAO_0000136", nerve5);
		// Medianus RP
		String nerve6 = ontEdit.createNewIndividual("http://purl.obolibrary.org/obo/FMA_65327", "nerve6");
		String ms6 = ontEdit.createNewMilisecond("ms6");
		ontEdit.addPropertiesToMilisecond(ms6, tfMedRP.getText());
		ontEdit.addStatement(ms6,"http://purl.obolibrary.org/obo/IAO_0000136", nerve6);
		// Tibialis LP
		String nerve7 = ontEdit.createNewIndividual("http://purl.obolibrary.org/obo/FMA_80064", "nerve7");
		String ms7 = ontEdit.createNewMilisecond("ms7");
		ontEdit.addPropertiesToMilisecond(ms7, tfTibLP.getText());
		ontEdit.addStatement(ms7,"http://purl.obolibrary.org/obo/IAO_0000136", nerve7);
		// Tibialis RP
		String nerve8 = ontEdit.createNewIndividual("http://purl.obolibrary.org/obo/FMA_80063", "nerve8");
		String ms8 = ontEdit.createNewMilisecond("ms8");
		ontEdit.addPropertiesToMilisecond(ms8, tfTibRP.getText());
		ontEdit.addStatement(ms8,"http://purl.obolibrary.org/obo/IAO_0000136", nerve8);

		ontEdit.saveNewOWLFile();

	}

	public void saveTesMep(ActionEvent event) {

		String tesMepMeasurement = ontEdit.createNewMeasurement(ontEdit.getBaselines().get("TES MEP Baseline Messung"), "Measurement");
		//String mA = ontEdit.createNewMiliampere("mA");
		//ontEdit.addPropertiesToMiliampere(mA, "4");
		//ontEdit.addStatement(tesMepMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
		//ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle1);
		//ontEdit.saveNewOWLFile();

		if (cbTes1.getSelectionModel().getSelectedItem() != null) {
			Integer mn = muscNumber.getValue();
			String muscle1 = ontEdit.createMuscle(ontEdit.getAllMuscles().get(cbTes1.getSelectionModel().getSelectedItem()), "Muscle".concat(mn.toString()));
			String mA = ontEdit.createNewMiliampere("mA");
			ontEdit.addPropertiesToMiliampere(mA, tf1.getText());
			ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle1);
			ontEdit.addStatement(tesMepMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);	
			muscNumber.increment();
		}
		else {

		}
		if (cbTes2.getSelectionModel().getSelectedItem() != null) {
			Integer mn = muscNumber.getValue();
			String muscle2 = ontEdit.createMuscle(ontEdit.getAllMuscles().get(cbTes2.getSelectionModel().getSelectedItem()), "Muscle".concat(mn.toString()));
			String mA = ontEdit.createNewMiliampere("mA");
			ontEdit.addPropertiesToMiliampere(mA, tf2.getText());
			ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle2);
			ontEdit.addStatement(tesMepMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
			muscNumber.increment();
		}
		else {

		}
		if (cbTes3.getSelectionModel().getSelectedItem() != null) {
			Integer mn = muscNumber.getValue();
			String muscle3 = ontEdit.createMuscle(ontEdit.getAllMuscles().get(cbTes3.getSelectionModel().getSelectedItem()), "Muscle".concat(mn.toString()));
			String mA = ontEdit.createNewMiliampere("mA");
			ontEdit.addPropertiesToMiliampere(mA, tf3.getText());
			ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle3);
			ontEdit.addStatement(tesMepMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
			muscNumber.increment();
		}
		else {

		}
		if (cbTes4.getSelectionModel().getSelectedItem() != null) {
			Integer mn = muscNumber.getValue();
			String muscle4 = ontEdit.createMuscle(ontEdit.getAllMuscles().get(cbTes4.getSelectionModel().getSelectedItem()),"Muscle".concat(mn.toString()));
			String mA = ontEdit.createNewMiliampere("mA");
			ontEdit.addPropertiesToMiliampere(mA, tf4.getText());
			ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle4);
			ontEdit.addStatement(tesMepMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
		}
		else {

		}
		if (cbTes5.getSelectionModel().getSelectedItem() != null) {
			String muscle5 = ontEdit.createMuscle(ontEdit.getAllMuscles().get(cbTes5.getSelectionModel().getSelectedItem()), "Muscle");
			String mA = ontEdit.createNewMiliampere("mA");
			ontEdit.addPropertiesToMiliampere(mA, tf5.getText());
			ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle5);
			ontEdit.addStatement(tesMepMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
		}
		else {

		}
		if (cbTes6.getSelectionModel().getSelectedItem() != null) {
			String muscle6 = ontEdit.createMuscle(ontEdit.getAllMuscles().get(cbTes6.getSelectionModel().getSelectedItem()), "Muscle");
			String mA = ontEdit.createNewMiliampere("mA");
			ontEdit.addPropertiesToMiliampere(mA, tf6.getText());
			ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle6);
			ontEdit.addStatement(tesMepMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
		}
		else {

		}
		if (cbTes7.getSelectionModel().getSelectedItem() != null) {
			String muscle7 = ontEdit.createMuscle(ontEdit.getAllMuscles().get(cbTes7.getSelectionModel().getSelectedItem()), "Muscle");
			String mA = ontEdit.createNewMiliampere("mA");
			ontEdit.addPropertiesToMiliampere(mA, tf7.getText());
			ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle7);
			ontEdit.addStatement(tesMepMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
		}
		else {

		}
		if (cbTes8.getSelectionModel().getSelectedItem() != null) {
			String muscle8 = ontEdit.createMuscle(ontEdit.getAllMuscles().get(cbTes8.getSelectionModel().getSelectedItem()), "Muscle");
			String mA = ontEdit.createNewMiliampere("mA");
			ontEdit.addPropertiesToMiliampere(mA, tf8.getText());
			ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle8);
			ontEdit.addStatement(tesMepMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
		}
		else {

		}
		if (cbTes9.getSelectionModel().getSelectedItem() != null) {
			String muscle9 = ontEdit.createMuscle(ontEdit.getAllMuscles().get(cbTes9.getSelectionModel().getSelectedItem()), "Muscle");
			String mA = ontEdit.createNewMiliampere("mA");
			ontEdit.addPropertiesToMiliampere(mA, tf9.getText());
			ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle9);
			ontEdit.addStatement(tesMepMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
		}
		else {

		}
		if (cbTes10.getSelectionModel().getSelectedItem() != null) {
			String muscle10 = ontEdit.createMuscle(ontEdit.getAllMuscles().get(cbTes10.getSelectionModel().getSelectedItem()), "Muscle");
			String mA = ontEdit.createNewMiliampere("mA");
			ontEdit.addPropertiesToMiliampere(mA, tf10.getText());
			ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle10);
			ontEdit.addStatement(tesMepMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
		}
		else {

		}
		if (cbTes11.getSelectionModel().getSelectedItem() != null) {
			String muscle11 = ontEdit.createMuscle(ontEdit.getAllMuscles().get(cbTes11.getSelectionModel().getSelectedItem()), "Muscle");
			String mA = ontEdit.createNewMiliampere("mA");
			ontEdit.addPropertiesToMiliampere(mA, tf11.getText());
			ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle11);
			ontEdit.addStatement(tesMepMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
		}
		else {

		}
		if (cbTes12.getSelectionModel().getSelectedItem() != null) {
			String muscle12 = ontEdit.createMuscle(ontEdit.getAllMuscles().get(cbTes12.getSelectionModel().getSelectedItem()), "Muscle");
			String mA = ontEdit.createNewMiliampere("mA");
			ontEdit.addPropertiesToMiliampere(mA, tf12.getText());
			ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle12);
			ontEdit.addStatement(tesMepMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
		}
		else {

		}
		if (cbTes13.getSelectionModel().getSelectedItem() != null) {
			String muscle13 = ontEdit.createMuscle(ontEdit.getAllMuscles().get(cbTes13.getSelectionModel().getSelectedItem()), "Muscle");
			String mA = ontEdit.createNewMiliampere("mA");
			ontEdit.addPropertiesToMiliampere(mA, tf13.getText());
			ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle13);
			ontEdit.addStatement(tesMepMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
		}
		else {

		}
		if (cbTes14.getSelectionModel().getSelectedItem() != null) {
			String muscle14 = ontEdit.createMuscle(ontEdit.getAllMuscles().get(cbTes14.getSelectionModel().getSelectedItem()), "Muscle");
			String mA = ontEdit.createNewMiliampere("mA");
			ontEdit.addPropertiesToMiliampere(mA, tf14.getText());
			ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle14);
			ontEdit.addStatement(tesMepMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
		}
		else {

		}

		ontEdit.saveNewOWLFile();
	}

	public void saveDcsMep(ActionEvent event) {
		
		String dcsMepMeasurement = ontEdit.createNewMeasurement(ontEdit.getBaselines().get("DCS MEP Baseline Messung"), "Measurement");
		
		
		if (cbDcs1.getSelectionModel().getSelectedItem() != null) {
			Integer mn = muscNumber.getValue();
			String muscle1 = ontEdit.createMuscle(ontEdit.getAllMuscles().get(cbDcs1.getSelectionModel().getSelectedItem()), "Muscle");
			String mA = ontEdit.createNewMiliampere("mA");
			ontEdit.addPropertiesToMiliampere(mA, tfDcs1.getText());
			ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle1);
			ontEdit.addStatement(dcsMepMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);	
			muscNumber.increment();
		}
		else {

		}
		if (cbDcs2.getSelectionModel().getSelectedItem() != null) {
			Integer mn = muscNumber.getValue();
			String muscle2 = ontEdit.createMuscle(ontEdit.getAllMuscles().get(cbDcs2.getSelectionModel().getSelectedItem()), "Muscle");
			String mA = ontEdit.createNewMiliampere("mA");
			ontEdit.addPropertiesToMiliampere(mA, tfDcs2.getText());
			ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle2);
			ontEdit.addStatement(dcsMepMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
			muscNumber.increment();
		}
		else {

		}
		if (cbDcs3.getSelectionModel().getSelectedItem() != null) {
			Integer mn = muscNumber.getValue();
			String muscle3 = ontEdit.createMuscle(ontEdit.getAllMuscles().get(cbDcs3.getSelectionModel().getSelectedItem()), "Muscle");
			String mA = ontEdit.createNewMiliampere("mA");
			ontEdit.addPropertiesToMiliampere(mA, tfDcs3.getText());
			ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle3);
			ontEdit.addStatement(dcsMepMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
			muscNumber.increment();
		}
		else {

		}
		if (cbDcs4.getSelectionModel().getSelectedItem() != null) {
			Integer mn = muscNumber.getValue();
			String muscle4 = ontEdit.createMuscle(ontEdit.getAllMuscles().get(cbDcs4.getSelectionModel().getSelectedItem()),"Muscle");
			String mA = ontEdit.createNewMiliampere("mA");
			ontEdit.addPropertiesToMiliampere(mA, tfDcs4.getText());
			ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle4);
			ontEdit.addStatement(dcsMepMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
		}
		else {

		}
		if (cbDcs5.getSelectionModel().getSelectedItem() != null) {
			String muscle5 = ontEdit.createMuscle(ontEdit.getAllMuscles().get(cbDcs5.getSelectionModel().getSelectedItem()), "Muscle");
			String mA = ontEdit.createNewMiliampere("mA");
			ontEdit.addPropertiesToMiliampere(mA, tfDcs5.getText());
			ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle5);
			ontEdit.addStatement(dcsMepMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
		}
		else {

		}
		if (cbDcs6.getSelectionModel().getSelectedItem() != null) {
			String muscle6 = ontEdit.createMuscle(ontEdit.getAllMuscles().get(cbDcs6.getSelectionModel().getSelectedItem()), "Muscle");
			String mA = ontEdit.createNewMiliampere("mA");
			ontEdit.addPropertiesToMiliampere(mA, tfDcs6.getText());
			ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle6);
			ontEdit.addStatement(dcsMepMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
		}
		else {

		}
		if (cbDcs7.getSelectionModel().getSelectedItem() != null) {
			String muscle7 = ontEdit.createMuscle(ontEdit.getAllMuscles().get(cbDcs7.getSelectionModel().getSelectedItem()), "Muscle");
			String mA = ontEdit.createNewMiliampere("mA");
			ontEdit.addPropertiesToMiliampere(mA, tfDcs7.getText());
			ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle7);
			ontEdit.addStatement(dcsMepMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
		}
		else {

		}
		if (cbTes8.getSelectionModel().getSelectedItem() != null) {
			String muscle8 = ontEdit.createMuscle(ontEdit.getAllMuscles().get(cbTes8.getSelectionModel().getSelectedItem()), "Muscle");
			String mA = ontEdit.createNewMiliampere("mA");
			ontEdit.addPropertiesToMiliampere(mA, tfDcs8.getText());
			ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle8);
			ontEdit.addStatement(dcsMepMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
		}
		else {

		}
		if (cbTes9.getSelectionModel().getSelectedItem() != null) {
			String muscle9 = ontEdit.createMuscle(ontEdit.getAllMuscles().get(cbTes9.getSelectionModel().getSelectedItem()), "Muscle");
			String mA = ontEdit.createNewMiliampere("mA");
			ontEdit.addPropertiesToMiliampere(mA, tfDcs9.getText());
			ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle9);
			ontEdit.addStatement(dcsMepMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
		}
		else {

		}
		if (cbTes10.getSelectionModel().getSelectedItem() != null) {
			String muscle10 = ontEdit.createMuscle(ontEdit.getAllMuscles().get(cbTes10.getSelectionModel().getSelectedItem()), "Muscle");
			String mA = ontEdit.createNewMiliampere("mA");
			ontEdit.addPropertiesToMiliampere(mA, tfDcs10.getText());
			ontEdit.addStatement(mA, "http://purl.obolibrary.org/obo/IAO_0000136", muscle10);
			ontEdit.addStatement(dcsMepMeasurement, "http://www.semanticweb.org/ontologies/2021/1/24/IOMO/IOMO_0000275", mA);
		}

		ontEdit.saveNewOWLFile();
	}




}
