package hu.ingatlankozvetites.views;

import hu.ingatlankozvetites.beans.Ajanlat;
import hu.ingatlankozvetites.beans.Felh;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class UploadView extends HorizontalLayout implements View, ClickListener{

	private static final long serialVersionUID = 1L;
	private Button saveButton = new Button("Mentés");
	
	VerticalLayout rootLayout=new VerticalLayout();
	private TextField priceField = new TextField("Ár");
	private TextField addressField= new TextField("Cím");
	private TextField dimensionField = new TextField("Terület (m2)");
	private TextField lastModField = new TextField("Utolsó módosítás");
	private TextField agentNameField = new TextField("Hírdető neve");
	private TextField descriptionField = new TextField("Leírás");
	//checkboxes
	
	private CheckBox forSaleBox = new CheckBox("Eladó");
	private CheckBox houseBox = new CheckBox("Ház");
	private CheckBox gardenBox = new CheckBox("Udvar");
	private CheckBox yardBox = new CheckBox("Kert");
	private CheckBox teracceBox = new CheckBox("Terasz");
	private CheckBox balkonyBox = new CheckBox("Erkély");
	private CheckBox poolBox = new CheckBox("Medence");
	private CheckBox garageBox = new CheckBox("Garázs");
	private List<CheckBox> checkList = new ArrayList<CheckBox>(){
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
		
	private Felh felh;
	private Ajanlat data;
	@Override
	public void enter(ViewChangeEvent event) {
		felh=(Felh)VaadinService.getCurrentRequest().getWrappedSession().getAttribute("USER");
		if (felh==null){
			getUI().getNavigator().navigateTo("");
			return;
		}
		
		rootLayout.removeAllComponents();
		initView();
		addComponent(rootLayout);
		rootLayout.setSizeFull();
		Ajanlat data = (Ajanlat)VaadinService.getCurrentRequest().getWrappedSession().getAttribute("PROPERTYTOEDIT");
		if (data != null){
			this.data=data;
			VaadinService.getCurrentRequest().getWrappedSession().setAttribute("PROPERTYTOEDIT", null);
			setDataForEditing();
		}
	}
	
	

	private void initView(){
		HorizontalLayout uplay = new HorizontalLayout();
		
		uplay.addComponent(priceField);
		priceField.setInputPrompt("200 000 Ft");
		uplay.addComponent(addressField);
		addressField.setInputPrompt("6720 Szeged, Próba utca 1000/b");
		uplay.addComponent(dimensionField);
		dimensionField.setInputPrompt("67");
		uplay.setSizeFull();
		uplay.setSpacing(true);
		uplay.setMargin(true);
		
		HorizontalLayout checkBoxesLayout = new HorizontalLayout();
		for (CheckBox box : checkList){
			checkBoxesLayout.addComponent(box);
		}
		checkBoxesLayout.setSizeFull();
		checkBoxesLayout.setSpacing(true);
		checkBoxesLayout.setMargin(true);
		
		
		HorizontalLayout bottomLayout = new HorizontalLayout();
		bottomLayout.addComponent(lastModField);
		lastModField.setEnabled(false);
		SimpleDateFormat sfdt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		lastModField.setValue(sfdt.format(new Date()));
		bottomLayout.addComponent(agentNameField);
		agentNameField.setEnabled(false);
		
		agentNameField.setValue(felh.getFelh_nev());
		bottomLayout.addComponent(saveButton);
		bottomLayout.setSizeFull();
		bottomLayout.setSpacing(true);
		bottomLayout.setMargin(true);
		bottomLayout.setComponentAlignment(saveButton, Alignment.BOTTOM_CENTER);
		
		HorizontalLayout descriptionLayout = new HorizontalLayout();
		descriptionLayout.addComponent(descriptionField);
		descriptionLayout.setSizeFull();
		descriptionLayout.setSpacing(true);
		descriptionLayout.setMargin(true);
		descriptionField.setInputPrompt("Leírás");
		descriptionField.setSizeFull();
		saveButton.addClickListener(this);
		rootLayout.addComponent(uplay);
		rootLayout.addComponent(descriptionLayout);
		rootLayout.addComponent(checkBoxesLayout);
		rootLayout.addComponent(bottomLayout);
	}

	private void setDataForEditing() {
		priceField.setValue(data.getAr()+"");
		addressField.setValue(data.getCim());
		dimensionField.setValue(data.getTerulet()+"");
		descriptionField.setValue(data.getLeiras());
		forSaleBox.setValue(data.getElado() == 1 );
		balkonyBox.setValue(data.getErkely() == 1);
		garageBox.setValue(data.getGarazs() == 1);
		houseBox.setValue(data.getHaz() == 1);
		gardenBox.setValue(data.getKert() == 1);
		poolBox.setValue(data.getMedence() == 1);
		teracceBox.setValue(data.getTerasz() == 1);
		yardBox.setValue(data.getUdvar() == 1);
		agentNameField.setValue(data.getHirdeto_nev());
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == saveButton){
			Ajanlat dataToSave;
			try {
				dataToSave=fetchData();
				if (this.data != null){
					dataToSave.setAj_id(this.data.getAj_id());
				}
			}catch(NumberFormatException e){
				Notification.show("Szöveg helyett számot kell megadni!");
				return;
			} catch (Exception e) {
				Notification.show("Minden szöveges mező kitöltése kötelező");
				return;
			}
			saveProperty(dataToSave);
			
		}
	}
	private Ajanlat fetchData() throws NumberFormatException,Exception{
		Ajanlat data=new Ajanlat();
		if (priceField.getValue()==null || priceField.getValue().equals(""))
			throw new Exception();
		if (addressField.getValue()==null || addressField.getValue().equals(""))
			throw new Exception();
		if (dimensionField.getValue()==null || dimensionField.getValue().equals(""))
			throw new Exception();
		data.setLeiras("");
		data.setAr(Integer.parseInt(priceField.getValue()));
		data.setCim(addressField.getValue());
		data.setTerulet(Integer.parseInt(dimensionField.getValue()));
		data.setHirdeto_nev(agentNameField.getValue());
		data.setElado(Ajanlat.toInt(forSaleBox.getValue()));
		data.setErkely(Ajanlat.toInt(balkonyBox.getValue()));
		data.setGarazs(Ajanlat.toInt(garageBox.getValue()));
		data.setHaz(Ajanlat.toInt(houseBox.getValue()));
		data.setKert(Ajanlat.toInt(gardenBox.getValue()));
		data.setMedence(Ajanlat.toInt(poolBox.getValue()));
		data.setTerasz(Ajanlat.toInt(teracceBox.getValue()));
		data.setUdvar(Ajanlat.toInt(yardBox.getValue()));
		data.setLeiras(descriptionField.getValue());
		return data;
	}
	private void saveProperty(Ajanlat data){
		try {
			if (data.getAj_id() != 0){
				Ajanlat.modos(data);
			}else{
				Ajanlat.felt(data);
			}
			getUI().getNavigator().navigateTo("myuploads");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
