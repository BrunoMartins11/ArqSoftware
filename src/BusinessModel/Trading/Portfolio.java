package BusinessModel.Trading;

import Data.PortfolioDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Portfolio {

	private int id;
	private List<Integer> idCFD;

	public Portfolio() {
		id = (new PortfolioDAO()).getAll().size()+1;
		idCFD = new ArrayList<>();
		(new PortfolioDAO()).save(this);
	}

	public Portfolio(int id, List<Integer> cfds) {
		this.id = id;
		this.idCFD = cfds;
	}

	public void addCFD(int id){
		throw new UnsupportedOperationException();
	}

	public void removeCFD(int id){
		throw new UnsupportedOperationException();
	}

	public List<Integer> getAllCFD(){
		throw new UnsupportedOperationException();
	}

	public int getId() {
		return id;
	}
}