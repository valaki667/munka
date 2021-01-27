package hu.ingatlankozvetites;


import hu.ingatlankozvetites.beans.Ajanlat;
import hu.ingatlankozvetites.beans.Felh;
import hu.ingatlankozvetites.beans.Regisztr;
import hu.ingatlankozvetites.dao.DBControl;
import hu.ingatlankozvetites.views.ActAdsLayoutWithFilter;
import hu.ingatlankozvetites.views.LoginOrRegisterView;
import hu.ingatlankozvetites.views.MeetingsView;
import hu.ingatlankozvetites.views.MyUploadsView;
import hu.ingatlankozvetites.views.ProfileView;
import hu.ingatlankozvetites.views.UploadView;
import hu.ingatlankozvetites.views.UsersView;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;




import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("ingatlankozvetites")
public class IngatlankozvetitesUI extends UI {

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = IngatlankozvetitesUI.class, productionMode = false)
	
	public static class Servlet extends VaadinServlet {
	}
	public static final String MAINVIEW = "main";
	public static final String LOGIN_OR_REG = "loginreg";
	public static final String UPLOAD = "upload";
	public static final String USERS = "users";
	public static final String MYUPLOADS ="myuploads";
	public static final String MEETINGS ="meetings";
	public static final String USER_EDIT ="useredit";
	private Navigator navigator;
	private Button actualAdsButton = new Button("Aktuális ajánlatok");
	private Button loginVsRegButton = new Button("Belépés / Regisztráció");
	private Button myPropertiesButton = new Button("Hírdetéseim");
	private Button usersButton = new Button("Felhasználók");
	private Button uploadButton = new Button("Feltöltés");
	private Button meetingButton = new Button("Időpontok");
	private Button profileButton = new Button("Profil");
	private Button logOut = new Button("Kilépés");
	private WrappedSession session;
	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout rootLayout = new VerticalLayout();
		rootLayout.setMargin(true);
		setContent(rootLayout);
		
		session = VaadinService.getCurrentRequest().getWrappedSession();
		//init menu bar
		HorizontalLayout menuBar = new HorizontalLayout();
		menuBar.addComponent(actualAdsButton);
		Felh loggedInUser=(Felh)session.getAttribute("USER");
		if (loggedInUser!=null){
			menuBar.addComponent(myPropertiesButton);
			menuBar.addComponent(meetingButton);
			menuBar.addComponent(uploadButton);
			if (loggedInUser.isAdmin())
				menuBar.addComponent(usersButton);
			menuBar.addComponent(profileButton);
			menuBar.addComponent(logOut);
			
		}else{
			menuBar.addComponent(loginVsRegButton);
		}
		menuBar.setSpacing(true);
		menuBar.setMargin(true);
		
		//init click listener, and add it to buttons
		MainClickListener clickListener = new MainClickListener();
		actualAdsButton.addClickListener(clickListener);
		loginVsRegButton.addClickListener(clickListener);
		myPropertiesButton.addClickListener(clickListener);
		uploadButton.addClickListener(clickListener);
		usersButton.addClickListener(clickListener);
		profileButton.addClickListener(clickListener);
		meetingButton.addClickListener(clickListener);
		logOut.addClickListener(clickListener);
		//add menubar to layout
		rootLayout.addComponent(menuBar);
		
		//define content layout and add it to view
		Panel contentLayout = new Panel();
		contentLayout.setSizeFull();
		rootLayout.addComponent(contentLayout);
		rootLayout.setSizeFull();
		rootLayout.setExpandRatio(contentLayout, 1);
		
		//init navigator for content 
		navigator = new Navigator(this, contentLayout);
		
		navigator.addView("", new ActAdsLayoutWithFilter());
        navigator.addView(MAINVIEW, new ActAdsLayoutWithFilter());
        navigator.addView(LOGIN_OR_REG, new LoginOrRegisterView());
        navigator.addView(UPLOAD, new UploadView());
        navigator.addView(USERS, new UsersView());
        navigator.addView(MYUPLOADS, new MyUploadsView());
        navigator.addView(USER_EDIT, new ProfileView());
        navigator.addView(MEETINGS, new MeetingsView());
//		reg.addClickListener(new Button.ClickListener() {
//			public void buttonClick(ClickEvent event) {
//				layout.addComponent(new Label(reg(rfelh_nev.getValue(),rjelszo.getValue(),rjelszo2.getValue(),nev.getValue(),email.getValue(),tel_sz.getValue())));
//				//regTeszt
//			}
//		});
//		layout.addComponent(reg);
//		
//			
//		//feltöltés
		
//
//		//listázás
//		//List<Ajanlat> ajanlatok = Ajanlat.osszesListaz();
//		List<Ajanlat> ajanlatok = Ajanlat.szur(50000,"szeged",150,true,true,true,true,true,true,true,true,Date.valueOf("2011-01-01"),"admin");
//		List<Ajanlat> ajanlatok = Ajanlat.szur(50000,"szeged",150,2,2,2,2,2,2,2,2,Date.valueOf("2011-01-01"),"");
//		BeanItemContainer<Ajanlat> dataSource = new BeanItemContainer<Ajanlat>(Ajanlat.class);
//		Table table = new Table("Ajanlatok listazasa");
//		table.setContainerDataSource(dataSource);
//		dataSource.addAll(ajanlatok);
//		layout.addComponent(table);
//		
//		List<Felh> userek = Felh.listazas();
//		BeanItemContainer<Felh> dataSource2 = new BeanItemContainer<Felh>(Felh.class);
//		Table table2 = new Table("Userek listazasa");
//		table2.setContainerDataSource(dataSource2);
//		dataSource2.addAll(userek);
//		layout.addComponent(table2);
//		
//		// törlések
//		final TextField userTorol = new TextField("Törlendő felh_nev");
//		Button userTor = new Button("Törlés");
//		layout.addComponent(userTorol);
//		userTor.addClickListener(new Button.ClickListener() {
//			public void buttonClick(ClickEvent event) {
//				Felh.regTorles(userTorol.getValue());
//			}
//		});
//		layout.addComponent(userTor);
//		
//		final TextField ajTorol = new TextField("Törlendő ajánlat id");
//		Button ajTor = new Button("Törlés");
//		layout.addComponent(ajTorol);
//		ajTor.addClickListener(new Button.ClickListener() {
//			public void buttonClick(ClickEvent event) {
//				Ajanlat.torol(Integer.parseInt(ajTorol.getValue()));
//			}
//		});
//		layout.addComponent(ajTor);
	}

	public static Felh login(String nev,String jelszo) {
		Felh user = null;
		String sql = "select * FROM Felh WHERE felh_nev='"+nev+"' and jelszo='"+jelszo+"'";
		//String result = "kezdet";
		DBControl control;
		try {
			control = new DBControl();
			//result = Boolean.toString(control.select(sql));
			boolean success = control.select(sql);
			if (success) user = control.getUser("select * from Felh WHERE felh_nev='"+nev+"'").get(0);
			//return result;		
		} catch (SQLException e) {			
			e.printStackTrace();
			//result = "sqlexception";
		}
		return user;		
	}
	public static String reg(Felh user) {
		String result = "regkezdet";
		result = Regisztr.reg(user.getFelh_nev(), user.getJelszo(), user.getJelszo(), 
				user.getNev(), user.getEmail(), user.getTel_sz());	
		return result;
	}
	
	private class MainClickListener implements ClickListener{

		@Override
		public void buttonClick(ClickEvent event) {
			if (event.getButton() == actualAdsButton){
				navigator.navigateTo("");
			}
			if (event.getButton() == loginVsRegButton){
				navigator.navigateTo(LOGIN_OR_REG);
			}
			if (event.getButton() == logOut){
				session.setAttribute("MODE", false);
				session.setAttribute("USER", null);
				Page.getCurrent().reload();
			}
			if (event.getButton() == uploadButton){
				navigator.navigateTo(UPLOAD);
			}
			if (event.getButton() == usersButton){
				navigator.navigateTo(USERS);
			}
			if (event.getButton() == myPropertiesButton){
				navigator.navigateTo(MYUPLOADS);
			}
			if (event.getButton() == profileButton){
				navigator.navigateTo(USER_EDIT);
			}
			if (event.getButton() == meetingButton){
				navigator.navigateTo(MEETINGS);
			}
		}
		
	}

	
}