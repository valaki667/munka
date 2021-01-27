package hu.ingatlankozvetites.views;

import java.util.Date;
/*import java.util.Spliterator;
import java.util.function.Consumer;*/

import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class TimePickerDialog extends Window implements ClickListener {
	private static final long serialVersionUID = 1L;
	PropertyHolder propertyHolder;
	DateField dateField = new DateField("Időpont");
	public TimePickerDialog(PropertyHolder propertyHolder) {
		super("Időpont választó"); // Set window caption
		center();
		this.propertyHolder=propertyHolder;
		// Some basic content for the window
		VerticalLayout content = new VerticalLayout();
		dateField.setValue(new Date());
		dateField.setResolution(Resolution.HOUR);
		
		content.addComponent(dateField);
		content.setMargin(true);
		setContent(content);

		// Disable the close button
		setClosable(false);

		// Trivial logic for closing the sub-window
		Button ok = new Button("OK");
		ok.addClickListener(this);
		content.addComponent(ok);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		propertyHolder.date.setTime(dateField.getValue().getTime());
		close();
		
	}
}