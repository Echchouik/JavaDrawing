package drawing;

import javax.swing.*;

import drawing.Observer;

import java.awt.*;
import java.util.*;

/**
 * JPanel pouvant afficher des objets de type Shape
 */
public class Drawing extends JPanel implements Iterable<Shape> {

	private static final long serialVersionUID = 1L;

	public ArrayList<Shape> shapes;
	public ArrayList<Shape> shapesGroup;
	public Vector<Observer> observers = new Vector<>();
	int cpt;

	public Drawing() {
		super();
		shapes = new ArrayList<Shape>();
		shapesGroup = new ArrayList<Shape>();
	}

	/**
	 * Impl�mentation de l'interface Iterable<Shape>
	 */
	public Iterator<Shape> iterator() {
		return shapes.iterator();
	}

	/**
	 * return size of shapes
	 */
	public int getShapeSize() {
		return shapes.size();
	}

	/**
	 * return size of group of shapes
	 */
	public int getShapeGroupSize() {
		return shapesGroup.size();
	}

	/**
	 * Ajoute une forme au dessin et redessine
	 */
	public void addShape(Shape s) {
		shapes.add(s);
		cpt++;
		notifyObservers();
		this.repaint();
	}

	/**
	 * ajout des forems selectionnee
	 * @param s
     */
	public void addShapeGroup(Shape s){
		shapesGroup.add(s);
	}

	/**
	 *
	 * @param obs
     */
	public void addObserver(Observer obs) {
		observers.add(obs);
	}

	private void notifyObservers() {
		for (Observer obs : observers) {
			obs.update(cpt);
		}
	}

	/**
	 * Red�finition de la m�thode paintComponent() de JComponent
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Shape s : shapes) {
			s.paint(g);
		}
	}

	/**
	 * Enl�ve toutes les formes et redessine
	 */
	public void clear() {
		shapes.clear();
		this.repaint();
	}

	/**
	 * enleve le groupe des formes selectionnee
	 */
	public void clearShapeGroup(){
		shapesGroup.clear();
	}

	/**
	 * Remet le compteur a zero
	 */
	public void remise() {
		cpt = 0;
		notifyObservers();
	}

}
