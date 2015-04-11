package com.eprescription.web.app;


public class Problem {
	
	private String diagnosis;
	private String status;
	private String onSetDate;
	
	public Problem(Object obj){
		Problem newProblem = (Problem) obj;
		
		this.diagnosis = newProblem.getDiagnosis();
		this.status = newProblem.getStatus();
		this.onSetDate = newProblem.getOnSetDate();
	}
	
	public Problem(){
		super();
	}
	
	
	/**
	 * @return the diagnosis
	 */
	public String getDiagnosis() {
		return diagnosis;
	}
	/**
	 * @param diagnosis the diagnosis to set
	 */
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the onSetDate
	 */
	public String getOnSetDate() {
		return onSetDate;
	}
	/**
	 * @param onSetDate the onSetDate to set
	 */
	public void setOnSetDate(String onSetDate) {
		this.onSetDate = onSetDate;
	}
	
	

}
