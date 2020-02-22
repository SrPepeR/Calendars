package calendars.year;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import calendars.month.MonthCalendar;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class YearCalendar extends VBox implements Initializable {

	@FXML
	GridPane grid;
	@FXML
	Button leftButton;
	@FXML
	Label yearLabel;
	@FXML
	Button rightButton;

	MonthCalendar enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre, diciembre;
	MonthCalendar[] months = { enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre,
			diciembre };

	IntegerProperty year = new SimpleIntegerProperty();
	BooleanProperty styled = new SimpleBooleanProperty(false);

	public YearCalendar() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/YearCalendar.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		year.addListener(e -> yearLabel.setText("" + year.get()));
		year.set(LocalDate.now().getYear());
		this.year.set(LocalDate.now().getYear());
		initMonths();
		addMonthsToCalendar();
		styled.addListener(e -> setStyle());
		setStyle();

		// ACTIONS
		leftButton.setOnAction(e -> year.set(year.get() - 1));
		rightButton.setOnAction(e -> year.set(year.get() + 1));
	}

	private void initMonths() {
		for (int i = 0; i < months.length; i++) {
			// Inicializa los monthCalendar y les asigna el año actual y el mes
			months[i] = new MonthCalendar();
			months[i].setYear(year.get());
			months[i].setMonth(i + 1);
			// Bindea la propiedad year del YearCalendar con la del MonthCalendar
			year.bindBidirectional(months[i].yearProperty());
		}
	}

	private void addMonthsToCalendar() {
		// Añade los MonthCalendar al grid
		int count = 0;
		for (int column = 0; column <= 2; column++) {
			for (int row = 0; row <= 3; row++) {
				grid.add(months[count], row, column);
				count++;
			}
		}
	}

	private void setStyle() {
		if (styled.get()) {
			this.getStylesheets().add(getClass().getResource("/css/MonthCalendarStyle.css").toString());
		}else {
			if (this.getStylesheets().size() > 0) {
				this.getStylesheets().remove(0);
			}
		}
	}
	

	public final void setStyled(final boolean styled) {
		this.styled.set(styled);
	}
	

}
