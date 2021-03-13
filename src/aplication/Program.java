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
