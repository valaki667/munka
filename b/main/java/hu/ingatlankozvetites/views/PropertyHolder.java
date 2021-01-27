package hu.ingatlankozvetites.views;

import java.sql.SQLException;
import java.util.Date;
/*import java.util.Spliterator;
import java.util.function.Consumer;*/


import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.CloseListener;

import hu.ingatlankozvetites.IngatlankozvetitesUI;
import hu.ingatlankozvetites.beans.Ajanlat;
import hu.ingatlankozvetites.beans.Felh;
import hu.ingatlankozvetites.beans.Idopont;

public class PropertyHolder extends VerticalLayout implements ClickListener {
	private static final long serialVersionUID = 1L;
	// constants
	public static final boolean MODE_ADMIN = true;
	public static final boolean MODE_USER = false;
	// fields
	private Label propertyNameTextField = new Label();
	private Label propertyDescriptionField = new Label();

	private Button reserveButton = new Button("Időpont foglalása");
	private Button editButton = new Button("Szerkesztés");
	Date date = new Date();
	Ajanlat mdata;
	Felh user;
	public PropertyHolder(Ajanlat data) {
		super();
		if (data == null)
			return;
		mdata=data;
		if (data.getCim() != null) {
			propertyNameTextField.setValue(data.getCim());
		} else {
			propertyNameTextField.setValue("nincs megadva");
		}
		propertyDescriptionField.setValue(getPropertyDescriptionString(data));

		this.addComponent(propertyNameTextField);
		this.addComponent(propertyDescriptionField);
		Felh user = (Felh) VaadinService.getCurrentRequest().getWrappedSession().getAttribute("USER");
		reserveButton.addClickListener(this);
		if (user != null){
			if (data.getHirdeto_nev().equals(user.getFelh_nev()) || user.isAdmin()){
				this.addComponent(editButton);
				editButton.addClickListener(this);
			}else{
				this.addComponent(reserveButton);
			}
			this.user=user;
		}else{
			
		}
	}

	private String getPropertyDescriptionString(Ajanlat data) {
		String desc = data.getLeiras() + " ";
		desc += "ár: " + data.getAr() + " Ft; ";
		desc += "cím: " + data.getCim() + "; ";
		desc += "terület: " + data.getTerulet() + " m2; ";
		desc += "eladó: "
				+ booleanToHunString(Ajanlat.toBoolean(data.getElado())) + "; ";
		desc += "ház: " + booleanToHunString(Ajanlat.toBoolean(data.getHaz()))
				+ "; ";
		desc += "udvar: "
				+ booleanToHunString(Ajanlat.toBoolean(data.getUdvar())) + "; ";
		desc += "kert: "
				+ booleanToHunString(Ajanlat.toBoolean(data.getKert())) + "; ";
		desc += "terasz: "
				+ booleanToHunString(Ajanlat.toBoolean(data.getTerasz()))
				+ "; ";
		desc += "medence: "
				+ booleanToHunString(Ajanlat.toBoolean(data.getMedence()))
				+ "; ";
		desc += "erkély: "
				+ booleanToHunString(Ajanlat.toBoolean(data.getErkely()))
				+ "; ";
		desc += "garázs: "
				+ booleanToHunString(Ajanlat.toBoolean(data.getGarazs()))
				+ "; ";
		return desc;
	}

	private String booleanToHunString(boolean data) {
		return data ? "Igen" : "Nem";
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == reserveButton) {
			TimePickerDialog sub = new TimePickerDialog(this);
			UI.getCurrent().addWindow(sub);
			sub.addCloseListener(new CloseListener() {
				
				@Override
				public void windowClose(CloseEvent e) {
					try {
						if(Idopont.ellenorzes(mdata.getAj_id(), date)){
//							System.out.println(date);
							Idopont.foglal(user.getFelh_nev(), mdata.getAj_id(), date);
							Notification.show("Sikeres foglalás!");
						}else{
							Notification.show("Időpont foglalt, kérlek válassz másik időpontot!");
						}
					} catch (SQLException e1) {
						Notification.show("Hiba a feladat végrehajtása során.");
						e1.printStackTrace();
					}		
				}
			});
		
		}
		if (event.getButton() == editButton){
			VaadinService.getCurrentRequest().getWrappedSession().setAttribute("PROPERTYTOEDIT", mdata);
			getUI().getNavigator().navigateTo(IngatlankozvetitesUI.UPLOAD);
		}
	}
}
