package calendars.month;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Year;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MonthCalendar extends VBox implements Initializable {
	
	Months[] months = {
			Months.ENERO,
			Months.FEBRERO,
			Months.MARZO,
			Months.ABRIL,
			Months.MAYO,
			Months.JUNIO,
			Months.JULIO,
			Months.AGOSTO,
			Months.SEPTIEMBRE,
			Months.OCTUBRE,
			Months.NOVIEMBRE,
			Months.DICIEMBRE
	};
	
	private int week = 0;
	private LocalDate now = LocalDate.now();
	
    @FXML
    private Label monthLabel;

    @FXML
    private GridPane grid;
    
	IntegerProperty yearProperty = new SimpleIntegerProperty(LocalDate.now().getYear());
	IntegerProperty monthProperty = new SimpleIntegerProperty(1);
	
	public MonthCalendar() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MonthCalendar.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		yearProperty.addListener(e -> yearChanged());
		monthProperty.addListener(e -> yearChanged());
		yearChanged();
	}
	
	private void yearChanged() {
		this.week = 0;
		
		monthLabel.setText(getName());
		
		grid.getChildren().clear();
		
		Calendar calendar = Calendar.getInstance();
		Label day = new Label();
		
		Months month = this.months[monthProperty.get() -1];
		
		if (month.equals(Months.FEBRERO) && Year.isLeap(this.yearProperty.get())) {
			for (int i = 1; i <= month.getDays() + 1; i++) {
				calendar.set(getYear(), getMonth() - 1, i);
				int dayOfWeek = dayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));
				
				day = new Label("" + i);
				day.setAlignment(Pos.CENTER);
				//Para cambiar el estilo si es domingo
				if (dayOfWeek == 6) {
					day.setId("sunday");
				}
				
				//Para cambiar el estilo si es hoy
				if (isToday(i)) {
					day.setId("today");
				}
				
				//Para evitar la separación generada por empezar la semana de Calendar el domingo
				if (dayOfWeek == 0 && i > 1) {
					week++;
				}
				
				day.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				this.grid.add(day, dayOfWeek, week);
				calendar.clear();
			}
		}else {
			for (int i = 1; i <= month.getDays(); i++) {
				calendar.set(getYear(), getMonth() - 1, i);
				int dayOfWeek = dayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));
				
				day = new Label("" + i);
				day.setAlignment(Pos.CENTER);
				//Para cambiar el estilo si es domingo
				if (dayOfWeek == 6) {
					day.setId("sunday");
				}
				
				//Para cambiar el estilo si es hoy
				if (isToday(i)) {
					day.setId("today");
				}
				
				if (dayOfWeek == 0 && i > 1) {
					week++;
				}
				
				day.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				this.grid.add(day, dayOfWeek, week);
				calendar.clear();
			}
		}
	}
	
	private boolean isToday(int day) {
		if (yearProperty.get() == now.getYear() && monthProperty.get() == now.getMonthValue() && day == now.getDayOfMonth()) {
			return true;
		}
		return false;
	}

	private int dayOfWeek(int calendarDayOfWeek){
		int dayOfWeek = 0;
		
		switch (calendarDayOfWeek) {
		case 2:
			dayOfWeek = 0;
			break;
		case 3:
			dayOfWeek = 1;
			break;
		case 4:
			dayOfWeek = 2;
			break;
		case 5:
			dayOfWeek = 3;
			break;
		case 6:
			dayOfWeek = 4;
			break;
		case 7:
			dayOfWeek = 5;
			break;
		case 1:
			dayOfWeek = 6;
			break;
		}
		return dayOfWeek;
	}
	
	private String getName() {
		String nameOfMonth = "";
		switch (this.monthProperty.get()) {
		case 1:
			nameOfMonth = Months.ENERO.getName();
			break;
		case 2:
			nameOfMonth = Months.FEBRERO.getName();
			break;
		case 3:
			nameOfMonth = Months.MARZO.getName();
			break;
		case 4:
			nameOfMonth = Months.ABRIL.getName();
			break;
		case 5:
			nameOfMonth = Months.MAYO.getName();
			break;
		case 6:
			nameOfMonth = Months.JUNIO.getName();
			break;
		case 7:
			nameOfMonth = Months.JULIO.getName();
			break;
		case 8:
			nameOfMonth = Months.AGOSTO.getName();
			break;
		case 9:
			nameOfMonth = Months.SEPTIEMBRE.getName();
			break;
		case 10:
			nameOfMonth = Months.OCTUBRE.getName();
			break;
		case 11:
			nameOfMonth = Months.NOVIEMBRE.getName();
			break;
		case 12:
			nameOfMonth = Months.DICIEMBRE.getName();
			break;
		}
		return nameOfMonth;
	}
	
	//ENUM de meses
	public enum Months{
		ENERO(1, "Enero", 31),
		FEBRERO(2, "Febrero", 28),
		MARZO(3, "Marzo", 31),
		ABRIL(4, "Abril", 30),
		MAYO(5, "Mayo", 31),
		JUNIO(6, "Junio", 30),
		JULIO(7, "Julio", 31),
		AGOSTO(8, "Agosto", 31),
		SEPTIEMBRE(9, "Septiembre", 30),
		OCTUBRE(10, "Octubre", 31),
		NOVIEMBRE(11, "Noviembre", 30),
		DICIEMBRE(12, "Diciembre", 31);
		
		private int numberOfMonth;
		private String nameOfMonth;
		private int numberOfDays;
		
		private Months(int numberOfMonth, String name, int numberOfDays) {
			this.numberOfMonth = numberOfMonth;
			this.nameOfMonth = name;
			this.numberOfDays = numberOfDays;
		}
		
		public int getNumber() {
			return this.numberOfMonth;
		}
		public String getName() {
			return this.nameOfMonth;
		}
		public int getDays() {
			return this.numberOfDays;
		}
		
	}
	
	
	public final IntegerProperty yearProperty() {
		return this.yearProperty;
	}
	
	public final int getYear() {
		return this.yearProperty().get();
	}
	
	public final void setYear(final int year) {
		this.yearProperty().set(year);
	}
	
	public final IntegerProperty monthProperty() {
		return this.monthProperty;
	}
	
	public final int getMonth() {
		return this.monthProperty().get();
	}
	
	public final void setMonth(final int month) {
		this.monthProperty().set(month);
	}
	
	
}
