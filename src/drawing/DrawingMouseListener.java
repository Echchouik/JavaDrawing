package drawing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * Listener pour g�rer la souris dans la zone de dessin
 */
public class DrawingMouseListener implements MouseMotionListener, MouseListener {

	Drawing drawing;
	Shape currentShape = null;

	public DrawingMouseListener(Drawing d) {
		drawing = d;
	}

	/**
	 * Bouge la forme s�lectionn�e (si une forme est s�lectionn�e)
	 */
	public void mouseDragged(MouseEvent e) {
		if (currentShape != null) {
			currentShape.setOrigin(e.getPoint());
			drawing.repaint();
		}
        if (drawing.shapesGroup.size() != 0) {
            for (Shape s : drawing.shapesGroup) {
                s.setOrigin(e.getPoint());
                drawing.repaint();
            }
        }
	}

	/**
	 * S�lectionne la forme sur laquelle l'utilisateur a cliqu�
	 */
	public void mousePressed(MouseEvent e) {
		for (Shape s : drawing) {
			if (s.isOn(e.getPoint())) {
				currentShape = s;
				break;
			}
		}
	}

	/**
	 * D�s�lectionne la forme
	 */
	public void mouseReleased(MouseEvent e) {
        if (e.getButton() == 1) {
            currentShape = null;
			if (drawing.getShapeGroupSize() != 0) {
				drawing.clearShapeGroup();
			}

        }
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

    /**
     * Selection un group de formes
     * @param e
     */
	@Override
	public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 3) {
            for (Shape s : drawing) {
                if (s.isOn(e.getPoint())) {
                    drawing.shapesGroup.add(s);
                }
            }
        }
		
	}
}
