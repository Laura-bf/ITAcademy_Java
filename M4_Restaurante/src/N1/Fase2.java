package N1;

import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Fase2 {

	public static void main(String[] args) {
		int euro1 = 1, euro2 = 2, euro5 = 5, euro10 = 10, euro20 = 20, euro50 = 50, euro100 = 100, euro200 = 200, euro500 = 500;
		int priceTotal;
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
		//se muestra la carta de precios y se van añadiendo platos al pedido y preguntando si quiere algo más
		ArrayList<String> orderList = new ArrayList<String>();
		boolean end = false;
		do {
			String selectedDish = showMenu(dishes, prices);
			if(selectedDish != null) {			
				orderList.add(selectedDish);
				end = askMore(orderList);
			} else {
				orderList.clear();  	//si cancela en lugar de pedir, se cancela(vacía) todo el pedido
				JOptionPane.showMessageDialog(null, "Pedido cancelado.");
				end = true;
			}
		} while (!end); 
		
		//Muestra por pantalla la lista con los platos que se han pedido
		System.out.println(orderList.toString());
		
	}	
	//para mostrar ventana para elegir pizza viendo la carta
	public static String showMenu(String[] dishes, int[] prices) {
		String message = "";
		for(int i = 0; i < dishes.length; i++) {
			message += dishes[i] + " - " + prices[i] + "€\n";
		}
		message += "\n\nElige una pizza:";
		String selectDish = JOptionPane.showInputDialog(null, message, "NUESTRAS PIZZAS", -1);
		return selectDish;
	}
	
	//ventana para ver si quiere algo más
	public static boolean askMore(ArrayList<String> order) {
		String message = "";
		for(int i = 0; i < order.size(); i++) {
			if (message == "") {
				message += order.get(i);
			} else {
			message += ", " + order.get(i);
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
}
