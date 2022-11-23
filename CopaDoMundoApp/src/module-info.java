module CopaDoMundoApp {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.naming;
	requires javafx.graphics;
	
	
	opens application.controller to javafx.graphics, javafx.fxml;
	exports application;

}
