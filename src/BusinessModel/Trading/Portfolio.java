package BusinessModel.Trading;

import Data.PortfolioDAO;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {

	private int id;
	private List<Integer> CFDs;
	private List<Integer> watchList;


	public Portfolio() {
		id = (new PortfolioDAO()).getAll().size()+1;
		CFDs = new ArrayList<>();
		watchList = new ArrayList<>();
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
				CFDs.remove((Integer) id);
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

	public void removeItemWatchList(int idAsset){
		watchList.remove((Integer) idAsset);
		(new PortfolioDAO()).deletePortfolioWLItem(id, idAsset);
	}

	public void addItemToWatchList(int idAsset){
		watchList.add(idAsset);
		(new PortfolioDAO()).saveToPortfolioWL(id, idAsset);
	}
}