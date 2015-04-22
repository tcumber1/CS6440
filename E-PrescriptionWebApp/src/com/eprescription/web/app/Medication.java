package com.eprescription.web.app;

public class Medication {
	
	private String name;
	private String dosageInstructions;
	private int numPills;
	private String doseQuantity;
	private int refills;
	private String prescriber;
	private String dateWritten;
	private String NDC;
	
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
	public String getDosageInstructions() {
		return dosageInstructions;
	}

	/**
	 * @param dosageInstructions the dosageInstructions to set
	 */
	public void setDosageInstructions(String dosageInstructions) {
		this.dosageInstructions = dosageInstructions;
	}

	/**
	 * @return the dosageSize
	 */
	public int getNumPills() {
		return numPills;
	}

	/**
	 * @param dosageSize the dosageSize to set
	 */
	public void setNumPills(int numPills) {
		this.numPills = numPills;
	}

	/**
	 * @return the dosageQuantity
	 */
	public String getDoseQuantity() {
		return doseQuantity;
	}

	/**
	 * @param dosageQuantity the dosageQuantity to set
	 */
	public void setDoseQuantity(String doseQuantity) {
		this.doseQuantity = doseQuantity;
	}

	/**
	 * @return the refills
	 */
	public int getRefills() {
		return refills;
	}

	/**
	 * @param refills the refills to set
	 */
	public void setRefills(int refills) {
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
	 * @return the nDC
	 */
	public String getNDC() {
		return NDC;
	}

	/**
	 * @param nDC the nDC to set
	 */
	public void setNDC(String nDC) {
		NDC = nDC;
	}

}
