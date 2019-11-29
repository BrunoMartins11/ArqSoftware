package BusinessModel.Assets;

import Data.AssetDAO;
import Services.Observer;

public class Asset implements Observer {


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

	@Override
	public void update(int id, Double value) {
		this.value = value;
		(new AssetDAO()).update(this);
	}
}