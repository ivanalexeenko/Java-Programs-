package log;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stage=primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("log.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Log");
        stage.setScene(scene);
        Controller controller = loader.getController();
        scene.setOnKeyPressed(event ->
            controller.notifyObservers(event.getCode().toString())
        );
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
