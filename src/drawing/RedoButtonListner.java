package drawing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 08/12/15.
 */
public class RedoButtonListner implements ActionListener {
    Drawing drawing;

    public RedoButtonListner(Drawing drawing) {
        this.drawing = drawing;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        drawing.redo();
    }
}
