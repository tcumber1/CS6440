package gatech.cs6440.project;

public class Allergy {
	
	private String allergyName;
	private String severity;
	private String reaction;
	private String type;
	
	public Allergy(){
		super();
	}

	/**
	 * @return the allergyName
	 */
	public String getAllergyName() {
		return allergyName;
	}

	/**
	 * @param allergyName the allergyName to set
	 */
	public void setAllergyName(String allergyName) {
		this.allergyName = allergyName;
	}

	/**
	 * @return the severity
	 */
	public String getSeverity() {
		return severity;
	}

	/**
	 * @param severity the severity to set
	 */
	public void setSeverity(String severity) {
		this.severity = severity;
	}

	/**
	 * @return the reaction
	 */
	public String getReaction() {
		return reaction;
	}

	/**
	 * @param reaction the reaction to set
	 */
	public void setReaction(String reaction) {
		this.reaction = reaction;
	}
	/**
	 * @return the reaction
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param reaction the reaction to set
	 */
	public void setType(String value) {
		this.type = value;
	}
	
	

}
