package hu.ingatlankozvetites.views;

import hu.ingatlankozvetites.beans.Ajanlat;
import hu.ingatlankozvetites.beans.Idopont;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//import com.sun.javafx.beans.IDProperty;
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

public class ActAdsLayoutWithFilter extends VerticalLayout implements View, ClickListener{

	private static final long serialVersionUID = 1L;
	private static final boolean EXTENDED= true;
	private static final boolean SIMPLE= false;
	
	private VerticalLayout rootLayout = new VerticalLayout();
	private TextField searchField= new TextField();
	private Button searchButton = new Button("Keresés");
	private Button extendedSearchButton = new Button("Bővített keresés");
	private VerticalLayout propertListLayout = new VerticalLayout();
	private List<Ajanlat> propertyList=new ArrayList<Ajanlat>();
	private HorizontalLayout searchFieldHolder;
	boolean mode=false;
	
	
	private ComboBox forSaleBox = new ComboBox("Eladó");
	private ComboBox houseBox = new ComboBox("Ház");
	private ComboBox gardenBox = new ComboBox("Udvar");
	private ComboBox yardBox = new ComboBox("Kert");
	private ComboBox teracceBox = new ComboBox("Terasz");
	private ComboBox balkonyBox = new ComboBox("Erkély");
	private ComboBox poolBox = new ComboBox("Medence");
	private ComboBox garageBox = new ComboBox("Garázs");
	private List<ComboBox> comboList = new ArrayList<ComboBox>(){
		private static final long serialVersionUID = 1L;
		{
			add(forSaleBox);
			add(houseBox);
			add(gardenBox);
			add(yardBox);
			add(teracceBox);
			add(balkonyBox);
			add(poolBox);
			add(garageBox);
		}};
	private HorizontalLayout checkHorLay=new HorizontalLayout();
	
	private void initView(){
		rootLayout.removeAllComponents();
		searchFieldHolder = new HorizontalLayout();
		searchFieldHolder.addComponent(searchField);
		searchField.setInputPrompt("Kereső szöveg");
		searchFieldHolder.addComponent(searchButton);
		searchButton.addClickListener(this);
		searchFieldHolder.addComponent(extendedSearchButton);
		extendedSearchButton.addClickListener(this);
		
		searchFieldHolder.setMargin(true);
		searchFieldHolder.setSpacing(true);
		rootLayout.addComponent(searchFieldHolder);
		propertListLayout.setSpacing(true);
		propertListLayout.setMargin(true);
		rootLayout.addComponent(propertListLayout);
		rootLayout.setSpacing(true);
		addComponent(rootLayout);
		for (ComboBox chkbox: comboList)
			checkHorLay.addComponent(chkbox);
		checkHorLay.setMargin(true);
		checkHorLay.setSpacing(true);
		initComboboxes();
	}
	private void initComboboxes(){
		for (ComboBox combo: comboList){
			combo.removeAllItems();
			combo.addItem("Igen");
			combo.addItem("Nem");
			combo.setWidth("100px");
			combo.setNullSelectionAllowed(true);
		}
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
		initView();
		propertyList = Ajanlat.osszesListaz();
		refreshView();
		Idopont aj_id = (Idopont)VaadinService.getCurrentRequest().getWrappedSession().getAttribute("AJ_ID");
		if (aj_id != null){
			VaadinService.getCurrentRequest().getWrappedSession().setAttribute("AJ_ID", null);
			try {
				propertyList = Ajanlat.szur(aj_id.getAj_id());
				refreshView();
			} catch (SQLException e) {
				Notification.show("Hiba az ajánlat megjelenítése közben");
			}
		}
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == searchButton){
			if (mode==EXTENDED){
				try {
					propertyList=Ajanlat.szur(searchField.getValue(), 0, "", 0, getSelectedValue(forSaleBox), 
							getSelectedValue(houseBox), getSelectedValue(yardBox),getSelectedValue(gardenBox),
							getSelectedValue(teracceBox), getSelectedValue(balkonyBox), 
							getSelectedValue(poolBox), getSelectedValue(garageBox), null, "");
					refreshView();
				} catch (SQLException e) {
					Notification.show("Hiba a szűrés során");
					e.printStackTrace();
				}
			}else{
				try {
					propertyList=Ajanlat.szur(searchField.getValue(), 0, "", 0, 2, 
							2, 2,2,
							2, 2, 
							2,2, null, "");
					refreshView();
				} catch (SQLException e) {
					Notification.show("Hiba a szűrés során");
					e.printStackTrace();
				}
			}
		}
		if (event.getButton() == extendedSearchButton){
			changeSearchMode();
		}
	}

	private void changeSearchMode() {
		if (mode==EXTENDED){
			mode=SIMPLE;
			extendedSearchButton.setCaption("Bővített keresés");
			rootLayout.removeComponent(checkHorLay);
		}else{
			mode=EXTENDED;
			extendedSearchButton.setCaption("Sima keresés");
			rootLayout.addComponent(checkHorLay,1);
		}
	}
	private int getSelectedValue(ComboBox cmb){
		String value = (String) cmb.getValue();
		if (value==null)return 2;
		if (value.equals("Igen")) return 1;
		if (value.equals("Nem")) return 0;
		return 2;
	}
}
