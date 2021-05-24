package bachelorthesis.IOMDOProject.model;
/**
 * A simpleton class to transport data form RDPatDatController to IOMDocumentationController
 * @author romap1
 *
 */
public class PatientSurgeryData {

	private static PatientSurgeryData dataHolder;
	private String surname = "adadsad";
	private String familyname = "";
	private String birthday ="";
	private String diagnose = "";
	private String surgery= "";
	private String surgerydate= "";
	
	public static PatientSurgeryData getInstance()
	{
		if (dataHolder == null)
			dataHolder = new PatientSurgeryData();
		return dataHolder;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFamilyname() {
		return familyname;
	}

	public void setFamilyname(String familyname) {
		this.familyname = familyname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getDiagnose() {
		return diagnose;
	}

	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}

	public String getSurgery() {
		return surgery;
	}

	public void setSurgery(String surgery) {
		this.surgery = surgery;
	}

	public String getSurgerydate() {
		return surgerydate;
	}

	public void setSurgerydate(String surgerydate) {
		this.surgerydate = surgerydate;
	}
	
	public void clearData() {
	this.surname = "";
	this.familyname = "";
	this.birthday = "";
	this.diagnose = "";
	this.surgery ="";
	this.surgerydate = "";
	}
}
