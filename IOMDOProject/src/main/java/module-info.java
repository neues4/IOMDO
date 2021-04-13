module bachelorthesis.IOMDOProject {
    requires javafx.controls;
    requires javafx.fxml;
	requires org.apache.jena.core;

    opens bachelorthesis.IOMDOProject to javafx.fxml;
    exports bachelorthesis.IOMDOProject;
}