package hu.ingatlankozvetites.views;

import hu.ingatlankozvetites.beans.Felh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class UsersView extends VerticalLayout implements View, ClickListener{

	private static final long serialVersionUID = 1L;
	private static final boolean EXTENDED= true;
	private static final boolean SIMPLE= false;
	
	private VerticalLayout rootLayout = new VerticalLayout();
	private TextField searchField= new TextField();
	private Button searchButton = new Button("Keresés");
	private VerticalLayout userListLayout = new VerticalLayout();
	private List<Felh> userList=new ArrayList<Felh>();
	private HorizontalLayout searchFieldHolder;
	
	private void initView(){
		rootLayout.removeAllComponents();
		searchFieldHolder = new HorizontalLayout();
		searchFieldHolder.addComponent(searchField);
		searchField.setInputPrompt("Kereső szöveg");
		searchFieldHolder.addComponent(searchButton);
		searchButton.addClickListener(this);
		
		searchFieldHolder.setMargin(true);
		searchFieldHolder.setSpacing(true);
		rootLayout.addComponent(searchFieldHolder);
		userListLayout.setSpacing(true);
		userListLayout.setMargin(true);
		rootLayout.addComponent(userListLayout);
		rootLayout.setSpacing(true);
		addComponent(rootLayout);
	
	}
	
	private void refreshView(){
		userListLayout.removeAllComponents();
		for (Felh userData: userList){
			UserHolder propHolder = new UserHolder(userData);
			userListLayout.addComponent(propHolder);
		}
	}
	@Override
	public void enter(ViewChangeEvent event) {
		initView();
		try {
			userList = Felh.listazas();
		} catch (SQLException e) {
			Notification.show("Hiba a felhasználók lekérése során");
			e.printStackTrace();
		}
		refreshView();
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == searchButton){
		
		}
	}

	
}
