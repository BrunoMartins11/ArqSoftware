package BusinessModel.Trading;

import BusinessModel.Assets.*;

import java.time.LocalDateTime;

public class CFD {


	private int id;
	private Double takeProfit;
	private Double stopLoss;
	private Double priceAcquisition;
	private Double quantity;
	private java.time.LocalDateTime date;
	private Position position;
	private int assetID;




	public CFD(int id, Double takeProfit, Double stopLoss, Double priceAcquisition, Double quantity, LocalDateTime date, Position position, int assetID) {
		this.id = id;
		this.takeProfit = takeProfit;
		this.stopLoss = stopLoss;
		this.priceAcquisition = priceAcquisition;
		this.quantity = quantity;
		this.date = date;
		this.position = position;
		this.assetID = assetID;
	}

	public CFD() {
		// TODO - implement CFD.CFD
		throw new UnsupportedOperationException();
	}

	public int getId() {
		return id;
	}

	public Double getPriceAcquisition() {
		return priceAcquisition;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public int getAssetID() {
		return assetID;
	}

	public Asset getAsset() {
		// TODO - implement CFD.getAsset
		throw new UnsupportedOperationException();
	}

	public Double getQuantity() {
		// TODO - implement CFD.getQuantity
		throw new UnsupportedOperationException();
	}

	public Position getPosition() {
		// TODO - implement CFD.getPosition
		throw new UnsupportedOperationException();
	}

	public void setTP() {
		// TODO - implement CFD.setTP
		throw new UnsupportedOperationException();
	}

	public void setSL() {
		// TODO - implement CFD.setSL
		throw new UnsupportedOperationException();
	}

	public Double getTP() {
		// TODO - implement CFD.getTP
		throw new UnsupportedOperationException();
	}

	public Double getSL() {
		// TODO - implement CFD.getSL
		throw new UnsupportedOperationException();
	}

}