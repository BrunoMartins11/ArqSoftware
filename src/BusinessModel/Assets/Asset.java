package BusinessModel.Assets;

public class Asset {


	int id;
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
		// TODO - implement Asset.getValue
		throw new UnsupportedOperationException();
	}

	public String getCompany() {
		return this.company;
	}

	public AssetType getType() {
		// TODO - implement Asset.getType
		throw new UnsupportedOperationException();
	}

}