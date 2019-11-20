package BusinessModel.Assets;

public class Asset {


	private int id;
	private Double value;
	private String company;
	private AssetType type;

	public Asset(int id, Double value, String company, AssetType type) {
		this.id = id;
		this.value = value;
		this.company = company;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public Double getValue() {
		return value;
	}

	public String getCompany() {
		return this.company;
	}

	public AssetType getType() {
		return type;
	}

}