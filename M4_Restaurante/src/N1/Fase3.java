package N1;

import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.ListIterator;

public class Fase3 {

	public static void main(String[] args) {
		String[] dishes = new String[10];
		int[] prices = new int[10];
		
		//se crea un diccionario con la carta de precios para luego completar con él los arrays ya creados
		HashMap<String, Integer> priceDish = new HashMap<String, Integer>();
		priceDish.put("Bufalina", 12);
		priceDish.put("4 Formaggi", 12);
		priceDish.put("Pino Daniele", 18);
		priceDish.put("Margherita", 9);
		priceDish.put("Prosciutto", 10);
		priceDish.put("Parmiggiana", 11);
		priceDish.put("Al Tonno", 13);
		priceDish.put("Carbonara", 11);
		priceDish.put("Massimo Troise", 17);
		priceDish.put("Ortolana", 12);
		
		//se introducen los valores de los arrays platos y precios a partir de los valores que hay en la carta
		int i = 0;
		for(Map.Entry<String, Integer> entry : priceDish.entrySet()) {
			dishes[i] = entry.getKey();
			prices[i] = entry.getValue();
			i++;			
		}
		//se muestra la carta de precios y se van añadiendo platos al pedido. 		
		ArrayList<String> orderList = new ArrayList<String>();
		boolean end = false;
		do {
			String selectedDish = showMenu(dishes, prices);
			if(selectedDish != null) {	
				orderList.add(selectedDish);
				end = askMore(orderList); 	//Tras cada plato añadido, se pregunta si quiere alguno más
			} else {
				orderList.clear();  	//si cancela en lugar de pedir, se cancela(vacía) todo el pedido
				JOptionPane.showMessageDialog(null, "Pedido cancelado.");
				end = true;
			}
		} while (!end);			

		//se eliminan los platos que no existan informando al cliente 
		dishExists(orderList, dishes);
		//se calcula el precio total del pedido y se calcula la forma de pago
		payment(totalPrice(orderList, priceDish));
		
		
	}	
	//para mostrar ventana para elegir pizza viendo la carta
	/**
	 * A mí me parecía más sencillo usar como parámetro directamente el HashMap priceDish, 
	 * pero por el enunciado daba la sensación de que pedía que se hiciera con los dos arrays por separado.... 
	 * @param dishes
	 * @param prices
	 * @return
	 */
	public static String showMenu(String[] dishes, int[] prices) {
		String message = "";
		for(int i = 0; i < dishes.length; i++) {
			message += dishes[i] + " - " + prices[i] + "€\n";
		}
		message += "\n\nElige una pizza:";
		String selectDish = JOptionPane.showInputDialog(null, message, "NUESTRAS PIZZAS", -1);
		return selectDish;
	}	
	//para mostrar ventana para preguntar si quiere algo más
	public static boolean askMore(ArrayList<String> orderList) {
		String message = "";
		for(int i = 0; i < orderList.size(); i++) {
			if (message == "") {
				message += orderList.get(i);
			} else {
			message += ", " + orderList.get(i);
			}
		}
		message = message + "\n\n¿Quieres pedir algo más?";
		int windowAskMore = JOptionPane.showConfirmDialog(null, message, "TU SELECCIÓN:", JOptionPane.YES_NO_OPTION, -1);
		if (windowAskMore == JOptionPane.YES_OPTION) {
			return false;
		} else {
			return true;
		}
	}	
	//para comprobar que el plato elegido está en la carta
	public static void dishExists(ArrayList<String> orderList, String[] dishes) {
		String noDishes = "", message = "";
		int counter = 0;
		ListIterator<String> lt = orderList.listIterator();
		while(lt.hasNext()) {
			String orderDish = lt.next();
			for(int j = 0; j < dishes.length; j++) {
				if(orderDish.equalsIgnoreCase(dishes[j])) {
					counter++;
				}
			}
			if (counter == 0) {
				noDishes += "   ==>   " + orderDish + "\n";
				lt.remove();
			} else {
				counter = 0;
			}
		}			
		if (noDishes != "") {
			message = "No disponemos de:\n" + noDishes;
			JOptionPane.showMessageDialog(null, message, "ATENCIÓN!", 2);;
		}
		
	}	
	//para calcular precio total
	public static int totalPrice(ArrayList<String> orderList, HashMap<String, Integer> priceDish) {
		int totalPrice = 0;
		for(String orderDish : orderList) {
			for(Map.Entry<String, Integer> entry : priceDish.entrySet()) {
				if(orderDish.equalsIgnoreCase(entry.getKey())) {
					 totalPrice += entry.getValue(); 
				}
			}
		}
		JOptionPane.showMessageDialog(null, ("El coste total de tu pedido es de "+totalPrice+"€."), "PEDIDO CONFIRMADO", -1);
		return totalPrice;
	}
	//para calcular la forma de pago
	public static void payment(int totalPrice) {
		int euro1 = 1, euro2 = 2, euro5 = 5, euro10 = 10, euro20 = 20, euro50 = 50, euro100 = 100, euro200 = 200, euro500 = 500;
		String infoPayment = "";
		euro500 = totalPrice/500;
		infoPayment = euro500 + " billetes de 500\n";
		totalPrice = totalPrice%500;
		euro200 = totalPrice/200;
		infoPayment += euro200 + " billetes de 200\n";
		totalPrice = totalPrice%200;
		euro100 = totalPrice/100;
		infoPayment += euro100 + " billetes de 100\n";
		totalPrice = totalPrice%100;
		euro50 = totalPrice/50;
		infoPayment = euro50 + " billetes de 50\n";
		totalPrice = totalPrice%50;
		euro20 = totalPrice/20;
		infoPayment += euro20 + " billetes de 20\n";
		totalPrice = totalPrice%20;
		euro10 = totalPrice/10;
		infoPayment += euro10 + " billetes de 10\n";
		totalPrice = totalPrice%10;
		euro5 = totalPrice/5;
		infoPayment += euro5 + " billetes de 5\n";
		totalPrice = totalPrice%5;
		euro2 = totalPrice/2;
		infoPayment += euro2 + " monedas de 2\n";		
		totalPrice = totalPrice%2;
		infoPayment += euro1 + " monedas de 1\n";
		
		String[] buttons = {"Efectivo", "Tarjeta"};
		int window = JOptionPane.showOptionDialog(null, "Elige una forma de pago", "FORMA DE PAGO", 
											JOptionPane.DEFAULT_OPTION, 
											JOptionPane.QUESTION_MESSAGE, null, 
											buttons, buttons[0]);
		if(window == 0) {
			JOptionPane.showMessageDialog(null, infoPayment, "PAGO EN EFECTIVO", -1);;
		}
	}
}
