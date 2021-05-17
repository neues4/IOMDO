package bachelorthesis.IOMDOProject.model;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Patient {

	
	//Property --> automatische updates möglich
		private SimpleStringProperty surname;
		private SimpleStringProperty firstname;
		private SimpleStringProperty PID;
		private SimpleStringProperty FID;
		private SimpleStringProperty birthday;
		private SimpleIntegerProperty caseNr;
		//private ObjectProperty <LocalDate>birthday;
		
		/**
		 * 
		 * @param surname
		 * @param firstname
		 * @param birthday
		 * @param PID
		 * @param FID
		 */
		public Patient(String surname, String firstname, String birthday, String PID, String FID, int caseNr) {
			this.surname = new SimpleStringProperty(surname);
			this.firstname = new SimpleStringProperty(firstname);
			//this.birthday = new SimpleObjectProperty<>(birthday);
			this.birthday = new SimpleStringProperty(birthday);
			this.PID = new SimpleStringProperty(PID);
			this.FID = new SimpleStringProperty(FID);
			this.caseNr = new SimpleIntegerProperty(caseNr);
		}
		
		public String getSurname() {
			return surname.get();
		}
		public String getFirstname() {
			return firstname.get();
		}
		//public LocalDate getbirthday() {
			//return birthday.get();
		//}
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
		
		//public ObjectProperty<LocalDate> birthdayProperty() {
		  //  return birthday;
		//}
	
}
