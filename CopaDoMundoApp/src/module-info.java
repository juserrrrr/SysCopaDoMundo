module CopaDoMundoApp {
	requires javafx.controls;
	requires javafx.fxml;
	
	
	opens application.controller to javafx.graphics, javafx.fxml;
	exports application;

}
