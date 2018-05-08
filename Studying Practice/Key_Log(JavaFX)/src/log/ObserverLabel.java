package log;

import javafx.scene.control.Label;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Helga on 3/26/2018.
 */
public class ObserverLabel extends Label implements Observer  {

    public ObserverLabel() {
        super();
    }

    @Override
    public void update(Observable o, Object arg) {
        this.setText(arg.toString());
    }
}
