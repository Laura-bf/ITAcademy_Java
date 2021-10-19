package com.nivel2.controller.mainMenu;

import com.nivel2.controller.Controller;
import com.nivel2.model.domain.Florist;
import com.nivel2.model.persistence.FloristRepository;
import com.nivel2.view.MessageView;
import com.nivel2.view.ReadInfoWindow;
import com.nivel2.view.ShowInfoWindow;
import com.nivel2.view.utils.Session;

public class CreateFloristController extends Controller {
	
	private FloristRepository floristRep;
	
	public CreateFloristController(Session session) {
		super(session);
		this.floristRep = FloristRepository.instance();
	}
	
	public void control() {
		Florist florist = this.createFlorist();
		if(florist!=null) {
			this.floristRep.add(florist);
			ShowInfoWindow.showInfo("FLORISTERIA CREADA");
		}
	}
	
	private Florist createFlorist() {
		Florist florist = null;
		String name = ReadInfoWindow.readString(MessageView.CREATE_FLORIST);
		if(name != null)
			florist = new Florist(name);
		return florist;

	}
	
}
