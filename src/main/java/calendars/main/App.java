package calendars.main;

import calendars.year.YearCalendar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
	
	YearCalendar calendar = new YearCalendar();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(calendar);
		// SIN CSS
//		calendar.setStyled(false);
		// CON CSS
		calendar.setStyled(true);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Calendario");
		primaryStage.getIcons().add(new Image(getClass().getResource("/images/calendar.png").toString()));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
