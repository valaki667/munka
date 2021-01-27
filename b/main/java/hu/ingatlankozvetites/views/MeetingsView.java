package hu.ingatlankozvetites.views;

import hu.ingatlankozvetites.beans.Felh;
import hu.ingatlankozvetites.beans.Idopont;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

public class MeetingsView extends VerticalLayout implements View, ClickListener{

	private static final long serialVersionUID = 1L;
	
	private VerticalLayout rootLayout = new VerticalLayout();
	
	private VerticalLayout meetingListLayout = new VerticalLayout();
	private List<Idopont> meetingList=new ArrayList<Idopont>();
	
	
	private void initView(){
		rootLayout.removeAllComponents();
	
		meetingListLayout.setSpacing(true);
		meetingListLayout.setMargin(true);
		rootLayout.addComponent(meetingListLayout);
		rootLayout.setSpacing(true);
		addComponent(rootLayout);
	}

	private void refreshView(){
		meetingListLayout.removeAllComponents();
		for (Idopont propertyData: meetingList){
			MeetingsHolder propHolder = new MeetingsHolder(propertyData);
			meetingListLayout.addComponent(propHolder);
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
			Idopont szur= new Idopont();
			szur.setErdeklodo(felh.getFelh_nev());
			szur.setUgynok("");
			meetingList = Idopont.getIdopont(szur);
			szur.setErdeklodo("");
			szur.setUgynok(felh.getFelh_nev());
			meetingList.addAll(Idopont.getIdopont(szur));
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
