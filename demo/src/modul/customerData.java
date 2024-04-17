package modul;

import java.util.Date;

public class customerData {
	private int id;
	private double total;
	private String cashier;
	private Date date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getCashier() {
		return cashier; 	
	}
	public void setCashier(String cashier) {
		this.cashier = cashier;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public customerData(int id, double total, String cashier, Date date) {
		this.id = id;
		this.total = total;
		this.cashier = cashier;
		this.date = date;
	}

}
