package hu.ingatlankozvetites.views;

import hu.ingatlankozvetites.IngatlankozvetitesUI;
import hu.ingatlankozvetites.beans.Felh;

import java.sql.SQLException;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ProfileView extends VerticalLayout implements View, ClickListener{

	private static final long serialVersionUID = 1L;
	
	private VerticalLayout rootLayout = new VerticalLayout();
	private Button updateButton = new Button("Mentés");
	private TextField mailTextField = new TextField("E-mail cím");
	private TextField nameTextField = new TextField("Név");
	private TextField userNameTextField = new TextField("Felhasználó név");
	private TextField phoneTextField = new TextField("Telefonszám");
	private Felh userData;
	
	private void initView(){
		userData = (Felh)  VaadinService.getCurrentRequest().getWrappedSession().getAttribute("USERTOEDIT");
		if (userData == null){ 
			userData = (Felh)  VaadinService.getCurrentRequest().getWrappedSession().getAttribute("USER");
			if (userData == null){
			getUI().getNavigator().navigateTo(IngatlankozvetitesUI.MAINVIEW);
			return;
			}
		}
		
		
		VerticalLayout verLay = new VerticalLayout();
		verLay.setSizeFull();
		verLay.setMargin(true);
		
		verLay.addComponent(userNameTextField);
		userNameTextField.setEnabled(false);
		verLay.addComponent(nameTextField);
		verLay.addComponent(mailTextField);
		verLay.addComponent(phoneTextField);
		verLay.addComponent(updateButton);
		fillContent();
		rootLayout.addComponent(verLay);
	}
	
	
	private void fillContent() {
		mailTextField.setValue(userData.getEmail());
		nameTextField.setValue(userData.getNev());
		userNameTextField.setValue(userData.getFelh_nev());
		phoneTextField.setValue(userData.getTel_sz());
	}


	@Override
	public void enter(ViewChangeEvent event) {
		initView();
		updateButton.addClickListener(this);
		addComponent(rootLayout);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == updateButton){
			retrieveData();
			try {
				Felh.adatModosit(userData);
				Notification.show("Sikeres módosítás");
			} catch (SQLException e) {
				Notification.show("Sikertelen módosítás");
				e.printStackTrace();
			}
			
		}
	}


	private void retrieveData() {
		userData.setEmail(mailTextField.getValue());
		userData.setNev(nameTextField.getValue());
		userData.setTel_sz(phoneTextField.getValue());
	}
	
	
}
