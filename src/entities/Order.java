package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.enums.OrderStatus;

public class Order {
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	private Date moment;
	private OrderStatus status;	
	private Client client;	
	private List<OrderItem> items = new ArrayList<OrderItem>();
	
	public Order() {		
	}

	public Order(Date moment, OrderStatus status, Client client) {
		super();
		this.moment = moment;
		this.status = status;
		this.client = client;
	}

	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<OrderItem> getItems() {
		return items;
	}
	
	public void addItem(OrderItem item) {
		items.add(item);
	}
	
	public void removeItem(OrderItem item) {
		items.remove(item);
	}
	
	public double getTotal() {
		double sum = 0;
		
		for(OrderItem it: items) {
			sum += it.getSubTotal();			
		}

		return sum;		
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Order moment: ");
		sb.append(sdf.format(moment) +"\n");
		sb.append("Order status: ");
		sb.append(status + "\n");
		sb.append("Client: " + client + "\n");
		sb.append("Order items:\n\n");
		sb.append("-------------------------------------------------------\n");
		
		for(OrderItem item: items) {
			sb.append(item + "\n");
		}
		sb.append("-------------------------------------------------------\n");				
		sb.append("Total price: $");
		sb.append(String.format("%.2f", getTotal()));
		
		return sb.toString();
	}
}