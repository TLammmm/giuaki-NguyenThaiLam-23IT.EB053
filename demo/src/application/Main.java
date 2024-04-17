package application;


import java.util.concurrent.ExecutionException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//
//public class Main extends Application {
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        FXMLLoader loader = new FXMLLoader();
//        Parent root = loader.load(getClass().getResource("/view/loginandsignupview.fxml").openStream());
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();
//    }
//    
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/login.fxml"));
        Parent root = loader.load();
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(600);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Lammart");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}