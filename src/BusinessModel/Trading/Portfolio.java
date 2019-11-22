package BusinessModel.Trading;

import Data.PortfolioDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Portfolio {

	private int id;
	private List<Integer> CFDs;
	private List<Integer> watchList;


	public Portfolio() {
		id = (new PortfolioDAO()).getAll().size()+1;
		CFDs = new ArrayList<>();
		(new PortfolioDAO()).save(this);
	}

	public Portfolio(int id, List<Integer> cfds, List<Integer> watchList) {
		this.id = id;
		this.CFDs = cfds;
		this.watchList = watchList;
	}

	public void addCFD(int id){
		CFDs.add(id);
	}

	public void removeCFD(int id){
		for (Integer i: CFDs) {
			if(i == id){
				CFDs.remove(id);
				break;
			}
		}
	}

	public List<Integer> getCFDs() {
		return CFDs;
	}

	public List<Integer> getWatchList() {
		return watchList;
	}

	public int getId() {
		return id;
	}
}