package gatech.cs6440.project;

public class Observation {
	
	private String observationName;
	private String date;
	private String value;
	
	public Observation(){
		super();
	}

	/**
	 * @return the observationName
	 */
	public String getObservationName() {
		return observationName;
	}

	/**
	 * @param observationName the observationName to set
	 */
	public void setObservationName(String observationName) {
		this.observationName = observationName;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
