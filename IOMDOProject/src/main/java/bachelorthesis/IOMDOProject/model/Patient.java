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
		private ObjectProperty <LocalDate>birthday;
		
		/**
		 * 
		 * @param surname
		 * @param firstname
		 * @param birthday
		 * @param PID
		 * @param FID
		 */
		public Patient(String surname, String firstname, LocalDate birthday, String PID, String FID) {
			this.surname = new SimpleStringProperty(surname);
			this.firstname = new SimpleStringProperty(firstname);
			this.birthday = new SimpleObjectProperty<>(birthday);
			this.PID = new SimpleStringProperty(PID);
			this.FID = new SimpleStringProperty(FID);
		}
		
		public String getSurname() {
			return surname.get();
		}
		public String getFirstname() {
			return firstname.get();
		}
		public LocalDate getbirthday() {
			return birthday.get();
		}
		public String getPID() {
			return PID.get();
		}
		public String getFID() {
			return FID.get();
		}
		public ObjectProperty<LocalDate> birthdayProperty() {
		    return birthday;
		}
	
}
