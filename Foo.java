package com.tek.interview.question;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Please remove all bugs from the code below to get the following output:
 * 
 * <PRE>
 * *******Order 1*******
 * 1 book: 13.74
 * 1 music CD: 16.49
 * 1 chocolate bar: 0.94
 * Sales Tax: 2.84
 * Total: 28.33
 * 
 * *******Order 2*******
 * 1 imported box of chocolate: 11.5
 * 1 imported bottle of perfume: 54.62
 * Sales Tax: 8.62
 * Total: 57.5
 * 
 * *******Order 3*******
 * 1 Imported bottle of perfume: 32.19
 * 1 bottle of perfume: 20.89
 * 1 packet of headache pills: 10.73
 * 1 box of imported chocolates: 12.94
 * Sales Tax: 8.77
 * Total: 67.98
 * Sum of orders: 153.81
 * <PRE>
 * 
 *
 */


/**
 * represents an item, contains a price and a description.
 *
 */
class Item {

	private String description;
	private float price;

	public Item(String description, float price) {
		super();
		this.description = description;
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public float getPrice() {
		return price;
	}
}

/**
 * represents an order line which contains the @link Item and the quantity.
 * 
 */
class OrderLine {

	private int quantity;
	private Item item;

	/**
	 * @param item Item of the order
	 * @param quantity Quantity of the item
	 * @throws Exception 
	 *         If the {@code item} is null
	 */
	public OrderLine(Item item, int quantity) throws Exception {
		if (item == null) {
			System.err.println("ERROR - Item is NULL");
			throw new Exception("Item is NULL");
		}
		assert quantity > 0;
		
		//this keyword must be used  to reference the current object item
		
		this.item = item;
		
		//this keyword must be used  to reference the current object item
		
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}

	public int getQuantity() {
		return quantity;
	}
}

class Order {

	private List<OrderLine> orderLines;

	public void add(OrderLine o) throws Exception {
		if (o == null) {
			System.err.println("ERROR - Order is NULL");
			throw new IllegalArgumentException("Order is NULL");
		}
		orderLines.add(o);
	}
	
	//Initialized OrderLines
	
	public void setOrderLines(List<OrderLine> orderLines){
		this.orderLines = orderLines;
	}
	
	public List<OrderLine> getOrderLines(List<OrderLine> orderLines){
		return orderLines;
	}

	public int size() {
		return orderLines.size();
	}

	public OrderLine get(int i) {
		return orderLines.get(i);
	}

	public void clear() {
		this.orderLines.clear();
	}
}

//Class name must start with Capital 

class Calculator {

	//Declared as double value to exact values
	
		public static double rounding(double value) {
			
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			return Double.parseDouble(decimalFormat.format(value));
		}
	

	/**
	 * receives a collection of orders. For each order, iterates on the order lines and calculate the total price which
	 * is the item's price * quantity * taxes.
	 * 
	 * For each order, print the total Sales Tax paid and Total price without taxes for this order
	 */
	public void calculate(Map<String, Order> o) {


		double grandTotal = 0;

		// Iterate through the orders
		for (Map.Entry<String, Order> entry : o.entrySet()) {
			System.out.println("*******" + entry.getKey() + "*******");
			
			//removed duplicate initialization of grandTotal=0
			Order r = entry.getValue();

			float totalTax = 0;
			double total = 0;

			// Iterate through the items in the order
			//size should be less than r.size()
			
			for (int i = 0; i < r.size(); i++) {

				// Calculate the taxes
				double tax = 0;
				
				
				// refactored code 
				//Calculating the sales tax , total , unit price  for multiple quantities
				int quantity= r.get(i).getQuantity();
				double val = (r.get(i).getItem().getPrice())*(quantity);
				
				//must have a check for case sensitive having imported as description
				if (r.get(i).getItem().getDescription().toLowerCase().contains("imported")) {
					//tax = rounding(r.get(i).getItem().getPrice() * 0.15); // Extra 5% tax on imported items
					tax = rounding(val*0.15);
				} else {
					//tax = rounding(r.get(i).getItem().getPrice() * 0.10);
					tax = rounding(val*0.10);
				}

				// Calculate the total price
				//Floating values will be removed by Math.floor
				double totalPrice = rounding((val + rounding(tax)));
				// Print out the item's total price
				System.out.println(quantity + " " + r.get(i).getItem().getDescription() + ": " + totalPrice);
				
				// Keep a running total
				totalTax += tax;
				total += val;
			}

			// Print out the total taxes
			//getting the rounding values
			System.out.println("Sales Tax: " + rounding(totalTax));

			//Taxes are added for individual item so no need to add again for total
			//total = total + rounding(totalTax);

			// Print out the total amount
			System.out.println("Total: " + rounding(total));
			grandTotal += total;
		}

		System.out.println("Sum of orders: " + rounding(grandTotal));
	}
	
}

public class Foo {

	public static void main(String[] args) throws Exception {

		Map<String, Order> o = new LinkedHashMap<String, Order>();
		
		
		// first order
		Order c = new Order();
		//Initializing Array list
		
		c.setOrderLines(new ArrayList<OrderLine>());
		c.add(new OrderLine(new Item("book", (float) 12.49), 1));
		c.add(new OrderLine(new Item("music CD", (float) 14.99), 1));
		c.add(new OrderLine(new Item("chocolate bar", (float) 0.85), 1));
		o.put("Order 1", c);
		
		//clears order in the cart
		//c.clear();
		//second order
		//Creating a new Object for new order
		c = new Order();
		c.setOrderLines(new ArrayList<OrderLine>());
		c.add(new OrderLine(new Item("imported box of chocolate", 10), 1));
		c.add(new OrderLine(new Item("imported bottle of perfume", (float) 47.50), 1));
		o.put("Order 2", c); 

		//third order
		c = new Order();
		c.setOrderLines(new ArrayList<OrderLine>());
		c.add(new OrderLine(new Item("Imported bottle of perfume", (float) 27.99), 1));
		c.add(new OrderLine(new Item("bottle of perfume", (float) 18.99), 1));
		c.add(new OrderLine(new Item("packet of headache pills", (float) 9.75), 1));
		c.add(new OrderLine(new Item("box of imported chocolates", (float) 11.25), 1));
		o.put("Order 3", c);

		new Calculator().calculate(o);

	}
}
