/*Se crean archivos con excepciones personalizadas para sustituir a las excepciones generales que se habían usado en el nivel 2*/
package N3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class ExcepPersonal_Restaurante {

	public static void main(String[] args) {
		String[] dishes = new String[10];
		int[] prices = new int[10];

		// se crea un diccionario con la carta de precios para luego completar con él los arrays ya creados
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

		// se introducen los valores de los arrays platos y precios a partir de los valores que hay en la carta
		int i = 0;
		for (Map.Entry<String, Integer> entry : priceDish.entrySet()) {
			dishes[i] = entry.getKey();
			prices[i] = entry.getValue();
			i++;
		}
		// se muestra la carta de precios y se van añadiendo platos al pedido.
		ArrayList<String> orderList = new ArrayList<String>();
		String selectedDish;
		boolean end = false;
		do {
			selectedDish = showMenu(dishes, prices);
			if (selectedDish != null) {
				try {
					dishExists(selectedDish, dishes, orderList);
				} catch (NotExistingDishException e) {; 
					JOptionPane.showMessageDialog(null,	("El plato " + selectedDish + " no existe!"), "ERROR!", 0);
					System.err.println(e.getMessage());
					e.printStackTrace();					
				}
				end = askMore(orderList); // Tras cada plato añadido, se pregunta si quiere alguno más
				
			} else {
				orderList.clear(); // si cancela en lugar de pedir, se cancela(vacía) todo el pedido
				JOptionPane.showMessageDialog(null, "Pedido cancelado.");
				end = true;
			}
		} while (!end);

		// se calcula el precio total del pedido
		if (selectedDish != null) {
			int totalPrice = 0;
			try {
				totalPrice = totalPrice(orderList, priceDish);
			} catch (EmptyOrderException e) {
				JOptionPane.showMessageDialog(null, "No has añadido ningún plato válido al pedido", "PEDIDO CANCELADO",
						0);
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
			// se selecciona forma de pago
			if (totalPrice != 0) {
				boolean wayOfPayment = false;
				do {
					try {
						wayOfPayment = wayOfPayment();
					} catch (InvalidPaymentException e) {
						JOptionPane.showMessageDialog(null, "FORMA DE PAGO NO VÁLIDA", null, 0);
						System.err.println(e.getMessage());
						e.printStackTrace();
					}
				} while (!wayOfPayment);

				// se calcula pago en efectivo:
				JOptionPane.showMessageDialog(null, payment(totalPrice), "PAGO EN EFECTIVO", -1);
			}
		}
	}

	// para mostrar ventana para elegir pizza viendo la carta
	public static String showMenu(String[] dishes, int[] prices) {
		String message = "";
		for (int i = 0; i < dishes.length; i++) {
			message += dishes[i] + " - " + prices[i] + "€\n";
		}
		message += "\n\nElige una pizza:";
		String selectDish = JOptionPane.showInputDialog(null, message, "NUESTRAS PIZZAS", -1);
		return selectDish;
	}

	// para comprobar que el plato elegido está en la carta
	public static ArrayList<String> dishExists(String selectedDish, String[] dishes, ArrayList<String> orderList)
			throws NotExistingDishException {
		int counter = 0;
		for (String dish : dishes) {
			if (selectedDish.equalsIgnoreCase(dish)) {
				orderList.add(selectedDish);
				counter++;
			}
		}
		if (counter == 0) {
			throw new NotExistingDishException("El usuario ha seleccionado un plato inexistente: " + selectedDish
					+ ". No se añadirá al pedido.");
		}
		return orderList;
	}

	// para mostrar ventana para preguntar si quiere algo más
	public static boolean askMore(ArrayList<String> orderList) {
		String message = "";
		for (int i = 0; i < orderList.size(); i++) {
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

	// para calcular precio total
	public static int totalPrice(ArrayList<String> orderList, HashMap<String, Integer> priceDish) throws EmptyOrderException {
		int totalPrice = 0;
		for (String orderDish : orderList) {
			for (Map.Entry<String, Integer> entry : priceDish.entrySet()) {
				if (orderDish.equalsIgnoreCase(entry.getKey())) {
					totalPrice += entry.getValue();
				}
			}
		}
		if (totalPrice != 0) {
			JOptionPane.showMessageDialog(null, ("El coste total de tu pedido es de " + totalPrice + "€."),
					"PEDIDO CONFIRMADO", -1);
		} else {
			throw new EmptyOrderException("No hay ningún plato añadido");
		}
		return totalPrice;
	}

	// para seleccionar forma de pago
	public static boolean wayOfPayment() throws InvalidPaymentException {
		String[] buttons = { "Efectivo", "Tarjeta" };
		int window = JOptionPane.showOptionDialog(null, "Elige una forma de pago", "FORMA DE PAGO",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
		if (window == 0) {
			return true;
		} else {
			throw new InvalidPaymentException("No es posible pagar con tarjeta actualmente.");
		}
	}

	// para calcular pago en efectivo
	public static String payment(int totalPrice) {
		int euro1 = 1, euro2 = 2, euro5 = 5, euro10 = 10, euro20 = 20, euro50 = 50, euro100 = 100, euro200 = 200,
				euro500 = 500;
		String infoPayment = "";
		euro500 = totalPrice / 500;
		infoPayment = euro500 + " billetes de 500\n";
		totalPrice = totalPrice % 500;
		euro200 = totalPrice / 200;
		infoPayment += euro200 + " billetes de 200\n";
		totalPrice = totalPrice % 200;
		euro100 = totalPrice / 100;
		infoPayment += euro100 + " billetes de 100\n";
		totalPrice = totalPrice % 100;
		euro50 = totalPrice / 50;
		infoPayment = euro50 + " billetes de 50\n";
		totalPrice = totalPrice % 50;
		euro20 = totalPrice / 20;
		infoPayment += euro20 + " billetes de 20\n";
		totalPrice = totalPrice % 20;
		euro10 = totalPrice / 10;
		infoPayment += euro10 + " billetes de 10\n";
		totalPrice = totalPrice % 10;
		euro5 = totalPrice / 5;
		infoPayment += euro5 + " billetes de 5\n";
		totalPrice = totalPrice % 5;
		euro2 = totalPrice / 2;
		infoPayment += euro2 + " monedas de 2\n";
		totalPrice = totalPrice % 2;
		infoPayment += euro1 + " monedas de 1\n";

		return infoPayment;
	}

}

