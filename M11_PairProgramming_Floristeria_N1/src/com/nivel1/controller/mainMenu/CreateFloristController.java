package com.nivel1.controller.mainMenu;

import com.nivel1.controller.Controller;
import com.nivel1.model.domain.Florist;
import com.nivel1.model.persistence.FloristRepository;
import com.nivel1.view.MessageView;
import com.nivel1.view.ReadInfoWindow;
import com.nivel1.view.ShowInfoWindow;
import com.nivel1.view.utils.Session;

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
