package com.nivel2.controller.mainMenu;

import java.util.stream.Collectors;

import com.nivel2.controller.Controller;
import com.nivel2.model.domain.ActiveFlorist;
import com.nivel2.model.persistence.FloristRepository;
import com.nivel2.view.MessageView;
import com.nivel2.view.ReadInfoWindow;
import com.nivel2.view.ShowInfoWindow;
import com.nivel2.view.utils.Session;

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
