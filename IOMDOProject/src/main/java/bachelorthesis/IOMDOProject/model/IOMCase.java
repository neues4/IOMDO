package bachelorthesis.IOMDOProject.model;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Class that Contains all the Properties for one IOM Case that are to be shown on the Protocol Table in the the ProtocolOverview
 * @author romap1
 *
 */
public class IOMCase {

	//with Property the content will be automatically updated
		private SimpleStringProperty surname;
		private SimpleStringProperty firstname;
		private SimpleStringProperty PID;
		private SimpleStringProperty FID;
		private SimpleStringProperty birthday;
		private SimpleIntegerProperty caseNr;
		private SimpleStringProperty surgery;
		private SimpleStringProperty diagnosis;
				
		/**
		 * Constructor
		 * @param surname: the surname of the patient of the IOM case
		 * @param firstname: the firstname of the patient of the IOM case
		 * @param birthday: the birthday of the patient of the IOM case
		 * @param PID: the Patient ID of the patient of the IOM case
		 * @param FID: the case ID of the patient of the IOM case
		 * @param caseNr: the caseNr of the patient of the IOM case
		 * @param diagnosis: the diagnosis of the patient of the IOM case
		 * @param surgery: the surgery of the patient of the IOM case
		 */
		public IOMCase(String surname, String firstname, String birthday, String PID, String FID, int caseNr, String diagnosis, String surgery) {
			this.surname = new SimpleStringProperty(surname);
			this.firstname = new SimpleStringProperty(firstname);
			this.birthday = new SimpleStringProperty(birthday);
			this.PID = new SimpleStringProperty(PID);
			this.FID = new SimpleStringProperty(FID);
			this.caseNr = new SimpleIntegerProperty(caseNr);
			this.surgery = new SimpleStringProperty(surgery);
			this.diagnosis = new SimpleStringProperty(diagnosis);
		}
			
		//getter Methodes
		public String getSurname() {
			return surname.get();
		}
		public String getFirstname() {
			return firstname.get();
		}
		public String getPID() {
			return PID.get();
		}
		public String getFID() {
			return FID.get();
		}
		public String getBirthday() {
			return birthday.get();
		}
		public int getCaseNr() {
			return caseNr.get();
		}
		public String getSurgery() {
			return surgery.get();
		}
		public String getDiagnosis() {
			return diagnosis.get();
		}
		
}
