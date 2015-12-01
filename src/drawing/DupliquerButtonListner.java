package drawing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 01/12/15.
 */
public class DupliquerButtonListner implements ActionListener {
    private Drawing drawing;

    public DupliquerButtonListner(Drawing drawing){
        this.drawing = drawing;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        drawing.dupliquer();
    }
}
