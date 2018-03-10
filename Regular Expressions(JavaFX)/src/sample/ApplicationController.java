package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class ApplicationController {
    @FXML
    private TextField inputField;
    @FXML
    private Shape circle;
    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private TextArea dateInput;
    @FXML
    private ListView dateOutput;
    private ObservableList<String> dateItems;
    private PatternChecker patternChecker;
    private String patternType = "";
    private ArrayList dates;

    ApplicationController() {

        patternChecker = new PatternChecker();
        dateItems = FXCollections.observableArrayList();
        dates = new ArrayList();
        inputField = new TextField();
        circle = new Circle();
        choiceBox = new ChoiceBox(FXCollections.observableArrayList("Natural","Integer","Float","Data","Time","Email"));
        dateInput = new TextArea();
        dateOutput = new ListView();

        inputField.setId("INPUT");
        circle.setId("CIRCLE");
        choiceBox.setId("TYPES_OF_PATTERNS");
        dateInput.setId("DATE_INPUT");
        dateOutput.setId("DATE_OUTPUT");

        inputField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = inputField.getText();
                boolean isRight = false;
                switch(patternType) {
                    case "Natural": {
                        isRight = patternChecker.isPattern(text,PatternChecker.NATURAL_NUMBER_PATTERN);
                        break;
                    }
                    case "Integer": {
                        isRight = patternChecker.isPattern(text,PatternChecker.INTEGER_NUMBER_PATTERN);
                        break;
                    }
                    case "Float": {
                        isRight = patternChecker.isPattern(text,PatternChecker.FLOAT_NUMBER_PATTERN);
                        break;
                    }
                    case "Data": {
                        isRight = patternChecker.isPattern(text,PatternChecker.DATA_PATTERN);
                        break;
                    }
                    case "Time": {
                        isRight = patternChecker.isPattern(text,PatternChecker.TIME_PATTERN);
                        break;
                    }
                    case "Email": {
                        isRight = patternChecker.isPattern(text,PatternChecker.EMAIL_PATTERN);
                        break;
                    }
                    default: {

                    }
                }
                if(isRight) {
                    circle.setFill(Color.GREEN);
                }
                else  {
                    circle.setFill(Color.RED);
                }

            }
        });
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                patternType = choiceBox.getItems().get(newValue.intValue()).toString();
            }
        });
        dateInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String text = dateInput.getText();
                dates = patternChecker.findAllPatterns(text,PatternChecker.DATA_PATTERN);
                dateItems.clear();
                dateItems.addAll(dates);
                dateOutput.setItems(dateItems);
            }
        });




    }
}






