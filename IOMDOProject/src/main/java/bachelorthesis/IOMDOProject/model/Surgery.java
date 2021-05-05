package bachelorthesis.IOMDOProject.model;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * 
 * @author romap1
 *
 */
public class Surgery {

	//Property --> automatische updates möglich
		private SimpleStringProperty diagnosis;
		private SimpleStringProperty surgery;
		private SimpleStringProperty surgeon;
		private SimpleStringProperty assistant;
		private SimpleStringProperty surgerydate;
		
		/**
		 * 
		 * @param diagnosis
		 * @param surgery
		 * @param surgerydate
		 * @param surgeron
		 * @param assistant
		 */
		public Surgery(String diagnosis, String surgery, String surgerydate, String surgeron, String assistant) {
			this.diagnosis = new SimpleStringProperty(diagnosis);
			this.surgery = new SimpleStringProperty(surgery);
			this.surgeon = new SimpleStringProperty(surgeron);
			this.assistant = new SimpleStringProperty(assistant);
			this.surgerydate = new SimpleStringProperty(surgerydate);
		}
		
		public String getDiagnosis() {
			return diagnosis.get();
		}
		public String getSurgery() {
			return surgery.get();
		}
		public String getSurgeryDate() {
			return surgerydate.get();
		}
		public String getSurgeon() {
			return surgeon.get();
		}
		public String getAssistant() {
			return assistant.get();
		}
		
	
	
}
