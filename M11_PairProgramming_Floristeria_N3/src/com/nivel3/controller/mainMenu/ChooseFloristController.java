package com.nivel3.controller.mainMenu;

import java.util.stream.Collectors;

import com.nivel3.controller.Controller;
import com.nivel3.model.domain.ActiveFlorist;
import com.nivel3.model.persistence.FloristRepository;
import com.nivel3.view.MessageView;
import com.nivel3.view.ReadInfoWindow;
import com.nivel3.view.ShowInfoWindow;
import com.nivel3.view.utils.Session;

public class ChooseFloristController extends Controller {
	
	private FloristRepository floristRepository;
	
	public ChooseFloristController(Session session){
		super(session);
		this.floristRepository = FloristRepository.instance();
	}
	
	public void control() {	
		this.showFlorists();
		int id = this.chooseId();
		if (id != 0){
			ActiveFlorist.setActiveFlorist(this.floristRepository.getFloristById(id));
			ShowInfoWindow.showInfo("FLORISTERIA "+ ActiveFlorist.instance().getName() +" SELECCIONADA");
			this.session.floristMenu();
		}
	}
	
	public boolean isFloristListEmpty() {
		return this.floristRepository.getSizeFlorist()==0;
	}
	
	private void showFlorists() {
		ShowInfoWindow.showInfo(floristRepository.getFlorisList()
				.stream().map(l -> l.toString()).collect(Collectors.toList()));
		
	}
	
	private int chooseId() {
		int id = ReadInfoWindow.readId(MessageView.CHOOSE_FLORIST, this.floristRepository.getSizeFlorist());
		return id;
	}
	
	
}
