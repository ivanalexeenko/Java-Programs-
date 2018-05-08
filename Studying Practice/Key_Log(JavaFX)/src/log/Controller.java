package log;

import javafx.fxml.FXML;

import java.util.Observable;

public class Controller extends Observable {

    @FXML
    ObserverTextArea textArea = new ObserverTextArea();

    @FXML
    ObserverLabel label = new ObserverLabel();

    @Override
    public void notifyObservers(Object o) {
        this.setChanged();
        super.notifyObservers(o);
    }

    public void initialize() {
        this.addObserver(label);
        this.addObserver(textArea);
    }
}
