package BusinessModel.Trading;

import java.time.LocalDateTime;

public class CFD {

	private int id;
	private Double takeProfit;
	private Double stopLoss;
	private Double priceAcquisition;
	private Double quantity;
	private LocalDateTime date;
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

	public void setId(int id) {
		this.id = id;
	}

	public Double getTP() {
		return takeProfit;
	}

	public void setTakeProfit(Double takeProfit) {
		this.takeProfit = takeProfit;
	}

	public Double getSL() {
		return stopLoss;
	}

	public void setStopLoss(Double stopLoss) {
		this.stopLoss = stopLoss;
	}

	public void setPriceAcquisition(Double priceAcquisition) {
		this.priceAcquisition = priceAcquisition;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setAssetID(int assetID) {
		this.assetID = assetID;
	}
}