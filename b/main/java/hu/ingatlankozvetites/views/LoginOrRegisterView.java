package hu.ingatlankozvetites.views;

import hu.ingatlankozvetites.IngatlankozvetitesUI;
import hu.ingatlankozvetites.beans.Felh;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class LoginOrRegisterView extends HorizontalLayout implements View, ClickListener{

	private static final long serialVersionUID = 1L;
	private HorizontalLayout rootLayout=new HorizontalLayout();
	private VerticalLayout loginLayout ;
	private GridLayout registerLayout ;
	private TextField nameField = new TextField("Felhasználónév");
	private PasswordField passwordField = new PasswordField("Jelszó");
	private TextField regNameField = new TextField("Felhasználónév");
	private PasswordField regPasswordField = new PasswordField("Jelszó");
	private PasswordField regPasswordField2 = new PasswordField("Jelszó megerősítés");
	private TextField regFullNameField = new TextField("Teljes név");
	private TextField emailField = new TextField("Email-cím");
	private TextField phoneNumberField = new TextField("Telefonszám(36001234567)");
	
	private Button loginButton = new Button("Belépés");
	private Button registerButton = new Button("Regisztráció");
	
	@Override
	public void enter(ViewChangeEvent event) {
		rootLayout.removeAllComponents();
		initLoginArea();
		initRegistrationArea();
		setSizeFull();
		rootLayout.setSizeFull();
		addComponent(rootLayout);
	}

	private void initLoginArea(){
		loginLayout =new VerticalLayout();
		Label loginLabel= new Label("Belépés");
		loginLayout.addComponent(loginLabel);
		
		loginLayout.addComponent(nameField);
		loginLayout.addComponent(passwordField);
	
		loginLayout.addComponent(loginButton);
		loginButton.addClickListener(this);
		loginLayout.setSizeFull();
		loginLayout.setSpacing(true);
		loginLayout.setMargin(true);
		rootLayout.addComponent(loginLayout);
	}
	private void initRegistrationArea(){
		registerLayout = new GridLayout(2,5);
		Label regLabel= new Label("Regisztráció");
		registerLayout.addComponent(regLabel,0,0);
		
		registerLayout.addComponent(regNameField,0,1);
		registerLayout.addComponent(regPasswordField,0,2);
		registerLayout.addComponent(regPasswordField2,0,3);
		
		registerLayout.addComponent(regFullNameField,1,1);
		registerLayout.addComponent(emailField,1,2);
		registerLayout.addComponent(phoneNumberField,1,3);
		registerLayout.addComponent(registerButton,0,4);
		registerButton.addClickListener(this);
		registerLayout.setSizeFull();
		registerLayout.setSpacing(true);
		registerLayout.setMargin(true);
		rootLayout.addComponent(registerLayout);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == registerButton){
			Felh user;
			try {
				user =retrieveUserData();
			} catch (Exception e) {
				Notification.show("Az összes mezőt ki kell tölteni, és a jelszavaknak egyezniük kell!");
				return;
			}
			Notification.show(IngatlankozvetitesUI.reg(user));
		}
		if (event.getButton() == loginButton){
			Felh user=IngatlankozvetitesUI.login(nameField.getValue(), passwordField.getValue());
			if (user!=null){
				//set session 

				VaadinService.getCurrentRequest().getWrappedSession().setAttribute("USER", user);
		
				getUI().getNavigator().navigateTo(IngatlankozvetitesUI.MAINVIEW);
				Page.getCurrent().reload();
			}else {
				Notification.show("Hibás felhasználó név vagy jelszó");
			}
		}
		
	}
	private Felh retrieveUserData() throws Exception{
		Felh user= new Felh();
		
		//all fields filled validation
		if (emailField.getValue()==null || emailField.getValue().equals(""))
			throw new Exception();
		if (regNameField.getValue()==null || regNameField.getValue().equals(""))
			throw new Exception();
		if (regPasswordField.getValue()==null || regPasswordField.getValue().equals(""))
			throw new Exception();
		if (regFullNameField.getValue()==null || regFullNameField.getValue().equals(""))
			throw new Exception();
		if (phoneNumberField.getValue()==null || phoneNumberField.getValue().equals(""))
			throw new Exception();
		if (regPasswordField2.getValue()==null || regPasswordField2.getValue().equals(""))
			throw new Exception();
		
		//password match validation
		if (!regPasswordField.getValue().equals(regPasswordField2.getValue()))
			throw new Exception();
					
		user.setEmail(emailField.getValue());
		user.setFelh_nev(regNameField.getValue());
		user.setJelszo(regPasswordField.getValue());
		user.setNev(regFullNameField.getValue());
		user.setTel_sz(phoneNumberField.getValue());
		return user;
	}
}
