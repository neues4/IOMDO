package bachelorthesis.IOMDOProject.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Patient {

	
	//Property --> automatische updates möglich
		private SimpleStringProperty surname;
		private SimpleStringProperty firstname;
		private SimpleStringProperty PID;
		private SimpleStringProperty FID;
		private SimpleStringProperty birthday;
		
		/**
		 * 
		 * @param surname
		 * @param firstname
		 * @param birthday
		 * @param PID
		 * @param FID
		 */
		public Patient(String surname, String firstname, String birthday, String PID, String FID) {
			this.surname = new SimpleStringProperty(surname);
			this.firstname = new SimpleStringProperty(firstname);
			this.birthday = new SimpleStringProperty(birthday);
			this.PID = new SimpleStringProperty(PID);
			this.FID = new SimpleStringProperty(FID);
		}
		
		
		public String getSurname() {
			return surname.get();
		}
		public String getFirstname() {
			return firstname.get();
		}
		public String getbirthday() {
			return birthday.get();
		}
		public String getPID() {
			return PID.get();
		}
		public String getFID() {
			return FID.get();
		}
		
	
}
