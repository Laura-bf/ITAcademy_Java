/*En aquest exercici es proposa un exercici complex de comprovacions i bucles per crear un cron�metre  digital que vagi mostrant el temps transcorregut en format rellotge hh:mm:ss amb un segon d'espera.
 *Nom�s necessites 3 variables int (hour, minutes, seconds)  
 *El cron�metre ha de tenir 6 d�gits en tot moment i ha d'iniciar a 00:00:00  
 *L�aplicaci� no ha de finalitzar mai. 
 *Per que el rellotge trigui un segon has d�implementar: Thread.sleep(1000);
 */

package N3;

import java.text.DecimalFormat;

public class Cronometro {

	public static void main(String[] args) {
		int hours=0, minutes=0, seconds=0;
		
		do {
			for(hours = 0; hours <= 23; hours++) {
				for(minutes = 0; minutes <= 59; minutes++) {
					for(seconds = 0; seconds <= 59; seconds++) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						DecimalFormat formatter = new DecimalFormat("00");
						String hour = formatter.format(hours);
						String minute = formatter.format(minutes);
						String second = formatter.format(seconds);
						System.out.println(hour+":"+minute+":"+second);
//						}
					}
				}
			}	
			if(hours == 24) {
				hours = 0;
				minutes = 0;
				seconds = 0;
			}
		}while(hours == 0);

		}

}
