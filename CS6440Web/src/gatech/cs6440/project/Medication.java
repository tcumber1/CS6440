package gatech.cs6440.project;

public class Medication {
	
	private String name;
	private String dosageForm;
	private String numPills;
	private String dosageQuantity;
	private String refills;
	private String prescriber;
	private String dateWritten;
	private String NDC;
	private String status;

	public Medication(){
		super();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the dosageInstructions
	 */
	public String getDosageForm() {
		return dosageForm;
	}

	/**
	 * @param dosageInstructions the dosageInstructions to set
	 */
	public void setDosageForm(String dosageForm) {
		this.dosageForm = dosageForm;
	}

	/**
	 * @return the numPills
	 */
	public String getNumPills() {
		return numPills;
	}

	/**
	 * @param numPills the numPills to set
	 */
	public void setNumPills(String numPills) {
		this.numPills = numPills;
	}

	/**
	 * @return the dosageQuantity
	 */
	public String getDosageQuantity() {
		return dosageQuantity;
	}

	/**
	 * @param dosageQuantity the dosageQuantity to set
	 */
	public void setDosageQuantity(String dosageQuantity) {
		this.dosageQuantity = dosageQuantity;
	}

	/**
	 * @return the refills
	 */
	public String getRefills() {
		return refills;
	}

	/**
	 * @param refills the refills to set
	 */
	public void setRefills(String refills) {
		this.refills = refills;
	}

	/**
	 * @return the prescriber
	 */
	public String getPrescriber() {
		return prescriber;
	}

	/**
	 * @param prescriber the prescriber to set
	 */
	public void setPrescriber(String prescriber) {
		this.prescriber = prescriber;
	}

	/**
	 * @return the dateWritten
	 */
	public String getDateWritten() {
		return dateWritten;
	}

	/**
	 * @param dateWritten the dateWritten to set
	 */
	public void setDateWritten(String dateWritten) {
		this.dateWritten = dateWritten;
	}

	/**
	 * @return the NDC
	 */
	public String getNDC() {
		return NDC;
	}

	/**
	 * @param nDC the NDC to set
	 */
	public void setNDC(String NDC) {
		this.NDC = NDC;
	}

	/**
	 * @return the status 
	 */
	public String getstatus() {
		return status;
	}

	/**
	 * @param name the status to set
	 */
	public void setstatus(String status) {
		this.status = status;
	}
	

}
