package gatech.cs6440.project;

public class Drug {

	private String name;
	private String dosage;
	private String dosageForm;
	private String productNDC;
	
	public Drug(){
		super();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String value) {
		this.name = value;
	}
	
	public String getDosage() {
		return dosage;
	}

	public void setDosage(String value) {
		this.dosage = value;
	}
	
	public String getDosageForm() {
		return dosageForm;
	}

	public void setDosageForm(String value) {
		this.dosageForm = value;
	}
	
	public String getProductNDC() {
		return productNDC;
	}

	public void setProductNDC(String value) {
		this.productNDC = value;
	}
	
	
}
