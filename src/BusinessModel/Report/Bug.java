package BusinessModel.Report;

import java.time.LocalDateTime;

public class Bug {

	private int id;
	private String error;
	private int idClient;
	private LocalDateTime date;

	public int getIdClient() {
		return idClient;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public int getId() {
		return id;
	}

	public Bug(int id, String error, LocalDateTime date, int idClient) {
		this.id = id;
		this.error = error;
		this.idClient = idClient;
		this.date = date;
	}

	public Bug() {
		// TODO - implement Bug.Bug
		throw new UnsupportedOperationException();
	}

	public String getError() {
		// TODO - implement Bug.get
		throw new UnsupportedOperationException();
	}

}