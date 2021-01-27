package hu.ingatlankozvetites.views;

import hu.ingatlankozvetites.beans.Ajanlat;
import hu.ingatlankozvetites.beans.Felh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class MyUploadsView extends VerticalLayout implements View, ClickListener{

	private static final long serialVersionUID = 1L;
	private static final boolean EXTENDED= true;
	private static final boolean SIMPLE= false;
	
	private VerticalLayout rootLayout = new VerticalLayout();
	
	private VerticalLayout propertListLayout = new VerticalLayout();
	private List<Ajanlat> propertyList=new ArrayList<Ajanlat>();
	
	
	private void initView(){
		rootLayout.removeAllComponents();
	
		propertListLayout.setSpacing(true);
		propertListLayout.setMargin(true);
		rootLayout.addComponent(propertListLayout);
		rootLayout.setSpacing(true);
		addComponent(rootLayout);
	}

	private void refreshView(){
		propertListLayout.removeAllComponents();
		for (Ajanlat propertyData: propertyList){
			PropertyHolder propHolder = new PropertyHolder(propertyData);
			propertListLayout.addComponent(propHolder);
		}
	}
	@Override
	public void enter(ViewChangeEvent event) {
		Felh felh=(Felh)VaadinService.getCurrentRequest().getWrappedSession().getAttribute("USER");
		if (felh==null){
			getUI().getNavigator().navigateTo("");
			return;
			}
		initView();
		try {
			propertyList = Felh.getMyProperties(felh.getFelh_nev());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refreshView();
	}

	@Override
	public void buttonClick(ClickEvent event) {
	
	}
}
