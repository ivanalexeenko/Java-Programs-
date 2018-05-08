package log;


import javafx.scene.control.TextArea;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Helga on 3/26/2018.
 */
public class ObserverTextArea extends TextArea implements Observer {
    public ObserverTextArea() {
        super();
    }

    @Override
    public void update(Observable o, Object arg) {
        this.setText(this.getText() + arg.toString() + "\n");
    }
}
