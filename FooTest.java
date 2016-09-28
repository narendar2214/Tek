package com.tek.interview.question;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
public class FooTest {
	
	//Test case scenario for calculating sales tax, total and unit price for multiple quantities
	@Test 
	public void testQuantityCal() throws Exception {
		Map<String, Order> o = new LinkedHashMap<String, Order>();
		Item item= new Item("impored",(float)12.49);
		Item item1= new Item("music CD", (float) 14.99);
		Item item2=new Item("chocolate bar", (float) 0.85);
		Order order= new Order();
		order.setOrderLines(new ArrayList<OrderLine>());
		order.add(new OrderLine(item, 4));
		order.add(new OrderLine(item1, 2));
		order.add(new OrderLine(item2, 1));
		o.put("order 2 Quantity", order);
		new Calculator().calculate(o);
		
	}

	//Test case scenario for sales tax(0.15/0.10) calculation for Case sensitive value (imported) 
	@Test
	public void testImportCal() throws Exception {
		Map<String, Order> o = new LinkedHashMap<String, Order>();
		Item item= new Item("Imported",(float)12.49);
		Item item1= new Item("imported", (float) 14.99);
		Item item2=new Item("chocolate bar", (float) 1.55);
		Order order= new Order();
		order.setOrderLines(new ArrayList<OrderLine>());
		order.add(new OrderLine(item, 4));
		order.add(new OrderLine(item1, 2));
		order.add(new OrderLine(item2, 5));
		o.put("order 1 Import", order);
		new Calculator().calculate(o);
	}
	
	
	//Test case scenario for IllegalArgumentException when order is null
	@Test(expected=IllegalArgumentException.class)
	public void testNullOrderCal() throws Exception{
		Map<String, Order> o = new LinkedHashMap<String, Order>();
		Order order= new Order();
		order.setOrderLines(new ArrayList<OrderLine>());
		order.add(null);
		new Calculator().calculate(o);
	}
	
	//Test case scenario for Exception when order is null
	@Test(expected=Exception.class)
	public void testNullItemCal() throws Exception{
		Map<String, Order> o = new LinkedHashMap<String, Order>();
		Order order= new Order();
		order.setOrderLines(new ArrayList<OrderLine>());
		order.add(new OrderLine(null, 4));
		new Calculator().calculate(o);
	}
	
	 
}
