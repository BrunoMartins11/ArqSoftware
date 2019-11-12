package BusinessModel.Trading;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Portfolio {

	private int id;
	private List<Integer> idCFD;

	public Portfolio() {
		idCFD = new ArrayList<>();
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
}