package BusinessModel.User;

import BusinessModel.Report.Bug;
import BusinessModel.Trading.*;
import java.util.*;
import BusinessModel.Assets.*;

public class Investor extends User {

	private Double credit;
	private Portfolio portfolio;
	private List<Integer> watchList;

	public Investor(int id, String username, String email, String password, Portfolio p, Double credit) {
		super(id, username, email, password);
		portfolio = p;
		this.credit = credit;
	}

	public Investor() {
		super();
	}

	public Double getCredit(){
		return credit;
	}

	public List<Asset> getWatchList() {
		throw new UnsupportedOperationException();
	}

	public List<CFD> getAllCFD(){
		throw new UnsupportedOperationException();
	}

	public List<Asset> getMarket(){
		throw new UnsupportedOperationException();
	}

	public Double allInvested(){
		throw new UnsupportedOperationException();
	}

	public boolean closeCFD(int idCFD){
		throw new UnsupportedOperationException();
	}

	public boolean openCFD(int idCFD, Double qnt){
		throw new UnsupportedOperationException();
	}

	public void addWatchList(int idAsset){
		throw new UnsupportedOperationException();
	}

	public void removeWatchList(int idAsset){
		throw new UnsupportedOperationException();
	}

	public void insertCredit(Double value){
		throw new UnsupportedOperationException();
	}

	public void takeCredit(Double value){
		throw new UnsupportedOperationException();
	}

	public Bug reportBug(String text){
		throw new UnsupportedOperationException();
	}

	public int getPortfolioId(){
		return this.portfolio.getId();
	}
}