## Exemplo:

**Enter cliente data:**

Name: **Alex Green**
Email: **alex@gmail.com**

Birth date (DD/MM/YYYY): **15/03/1985**

**Enter order data:**
Status: **PROCESSING**

How many items to this order? **2**

**Enter #1 item data:**
Product name: **TV**
Product price: **1000.00**
Quantity: **1**

**Enter #2 item data:**
Product name: **Mouse**
Product price: **40.00**
Quantity: **2**

 

ORDER SUMMARY:
Order moment: 20/04/2018 11:25:09
Order status: PROCESSING
Client: Alex Green (15/03/1985) - alex@gmail.com

Order items:
TV, $1000.00, Quantity: 1, Subtotal: $1000.00
Mouse, $40.00, Quantity: 2, Subtotal: $80.00
Total price: $1080.00


## Diagrama de classe
<h1>
<img src="https://github.com/Maksuedson/assets/blob/master/OOP/DiagramaClassesOOP.PNG">
</h1>
## Objetos instanciados
<h1>
<img src="https://github.com/Maksuedson/assets/blob/master/OOP/ObjetosInstaciados.PNG">
</h1>

#Classe Client
```
package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private String name;
	private String email;
	private Date birthDate;
	
	public Client() {
	}

	public Client(String name, String email, Date birthDate) {
		super();
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	@Override
	public String toString() {
		return name + " (" + sdf.format(birthDate) + ") - " + email;
 	}
}
```

#Classe Product

```
package entities;

public class Product {

	private String name;
	private Double price;
	
	public Product() {
	}

	public Product(String name, Double price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}

```

#Classe OrderItem
```
package entities;

public class OrderItem {
	private Integer quantity;
	private Double price;

	private Product product;

	public OrderItem() {
	}

	public OrderItem(Integer quantity, Double price, Product product) {
		super();
		this.quantity = quantity;
		this.price = price;
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getSubTotal() {
		return price * quantity;
	}
	
	@Override
	public String toString() {
		return getProduct().getName()
				+ ", $" + String.format("%.2f", price)
				+ ", Quantity: "
				+ quantity
				+", Subtotal: $"
				+ String.format("%.2f", getSubTotal());
	}

}

```

#Classe Order

```
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

```

#Enum OrderStatus

```
package entities.enums;

public enum OrderStatus {
	PENDING_PAYMENT,
	PROCESSING,
	SHIPPED,
	DELIVERED

}

```

#Classe Program

```
package aplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;

public class Program {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter client data: ");
		System.out.print("Name: ");
		String name = sc.nextLine().toUpperCase();
		
		System.out.print("Email: ");
		String email = sc.next().toLowerCase();
		
		System.out.println("Birth date (dd/MM/yyyy: )");
		Date birthDate = sdf.parse(sc.next());
		
		
		
		Client client = new Client(name, email, birthDate);
		
		System.out.println("Enter order data: ");
		System.out.print("Status: ");
		OrderStatus status = OrderStatus.valueOf(sc.next().toUpperCase());
		Order order = new Order(new Date(), status, client);
		
		System.out.print("How many items to this order? ");
		int N = sc.nextInt();		
		for(int i = 0; i<N; i++) {
			System.out.println("Enter #" + (i+1) + " item data: ");
			System.out.println("Product name: ");
			sc.nextLine();
			String productName = sc.next().toUpperCase();
			
			System.out.println("Product price: ");
			double productPrice = sc.nextDouble();
			
			System.out.println("Quantity: ");
			int quantity = sc.nextInt();
			
			Product product = new Product(productName, productPrice);			
			OrderItem it = new OrderItem(quantity, productPrice, product);
			order.addItem(it);
		}
		
		System.out.println();
		System.out.println(order);
		
		sc.close();
	}
}

```