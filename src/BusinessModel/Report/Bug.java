package BusinessModel.Report;

import java.time.LocalDateTime;

public class Bug {

	private int id;
	private String error;
	private int idClient;
	private LocalDateTime date;

	public Bug(int id, String error, LocalDateTime date, int idClient) {
		this.id = id;
		this.error = error;
		this.idClient = idClient;
		this.date = date;
	}

	public Bug() {
	}

	public String getError() {
		return error;
	}
	public int getIdClient() {
		return idClient;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public int getId() {
		return id;
	}
}