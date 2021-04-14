module bachelorthesis.IOMDOProject {
    requires javafx.controls;
    requires javafx.fxml;
	requires org.apache.jena.core;

	requires transitive javafx.base;
	requires transitive javafx.graphics;
	

	
	 


    opens bachelorthesis.IOMDOProject to javafx.fxml;
    opens bachelorthesis.IOMDOProject.controller to javafx.fxml;
    
    exports bachelorthesis.IOMDOProject;
    exports bachelorthesis.IOMDOProject.controller;
    exports bachelorthesis.IOMDOProject.model;
    exports bachelorthesis.IOMDOProject.view;
    
}