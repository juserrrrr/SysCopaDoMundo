module CopaDoMundoApp {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.naming;
	requires javafx.base;
	requires transitive javafx.graphics;
	
	
	opens application.controller to javafx.graphics, javafx.fxml, javafx.base;
	opens application.model;
	exports application;

}
