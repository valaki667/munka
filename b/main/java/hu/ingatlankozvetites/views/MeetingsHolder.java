package hu.ingatlankozvetites.views;

import hu.ingatlankozvetites.IngatlankozvetitesUI;
import hu.ingatlankozvetites.beans.Ajanlat;
import hu.ingatlankozvetites.beans.Idopont;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class MeetingsHolder extends VerticalLayout implements ClickListener{
	private static final long serialVersionUID = 1L;

	private Label kezd = new Label();
	private Label vege = new Label();
	private Label ugynok = new Label();
	private Label erdeklodo = new Label();
	private Label hirdetes = new Label();
	private Idopont data;
	private Button navigateToProp = new Button("Hírdetés megtekintése");
	private Button deleteButton = new Button("Törlés");
	
	public MeetingsHolder(Idopont data){
		super();
		if (data==null)
			return;
		this.data=data;
		SimpleDateFormat sfdt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		kezd.setValue("Kezdő időpont: "+sfdt.format(data.getIdop()));
		vege.setValue("Vége időpont: "+sfdt.format(data.getVege()));
		ugynok.setValue("Ügynök felhasználó név: "+data.getUgynok());
		erdeklodo.setValue("Érdeklődő felhasználó név: "+data.getErdeklodo());
		hirdetes.setValue("Hírdetés id: " + data.getAj_id());
		this.addComponent(kezd);
		this.addComponent(vege);
		this.addComponent(ugynok);
		this.addComponent(erdeklodo);
		HorizontalLayout horlay = new HorizontalLayout();
		horlay.addComponent(hirdetes);
		horlay.addComponent(navigateToProp);
		this.addComponent(horlay);
		navigateToProp.addClickListener(this);
		this.addComponent(deleteButton);
		deleteButton.addClickListener(this);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == navigateToProp){
			VaadinService.getCurrentRequest().getWrappedSession().setAttribute("AJ_ID", data);
			getUI().getNavigator().navigateTo(IngatlankozvetitesUI.MAINVIEW);
		}
		if(event.getButton() == deleteButton){
			try {
				Idopont.torol(data);
				Notification.show("sikeres törlés");
			} catch (SQLException e) {
				Notification.show("sikertelen törlés");
				e.printStackTrace();
			}
		}
	}
	
}
