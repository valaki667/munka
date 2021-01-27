package hu.ingatlankozvetites.views;

//import java.util.Spliterator;
//import java.util.function.Consumer;

import hu.ingatlankozvetites.IngatlankozvetitesUI;
import hu.ingatlankozvetites.beans.Felh;

import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class UserHolder extends VerticalLayout implements ClickListener{
	private static final long serialVersionUID = 1L;
	String felh_nev;
	String nev;
	String email;
	String tel_sz;
	String jelszo;
	boolean admin;
	//fields
	private Label userNameLabel = new Label();
	private Label userFelhNameLabel = new Label();
	private Label isAdminLabel = new Label();
	private Label mailLabel = new Label();
	private Label phoneLabel = new Label();
	private Button editButton = new Button("Szerkesztés");
	private Felh data;
	
	public UserHolder(Felh data){
		super();
		if (data==null)
			return;
		this.data=data;
		userNameLabel.setValue("Név: "+data.getNev());
		userFelhNameLabel.setValue("Felhasználónév: "+data.getFelh_nev());
		isAdminLabel.setValue("Típus "+(data.isAdmin()?"Admin":"Nem admin"));
		mailLabel.setValue("Mail: "+data.getEmail());
		phoneLabel.setValue("Telefon: "+data.getTel_sz());
		
		this.addComponent(userNameLabel);
		this.addComponent(userFelhNameLabel);
		this.addComponent(isAdminLabel);
		this.addComponent(mailLabel);
		this.addComponent(phoneLabel);
		this.addComponent(editButton);
		editButton.addClickListener(this);
		
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == editButton){
			VaadinService.getCurrentRequest().getWrappedSession().setAttribute("USERTOEDIT",data);
			getUI().getNavigator().navigateTo(IngatlankozvetitesUI.USER_EDIT);
		}
	}
	
}
