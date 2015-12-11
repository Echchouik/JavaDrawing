package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 * Classe Interface graphique pour l'application de dessin
 */
public class Paint implements Observer {

	private JFrame frame;
	private JButton clearButton;
	private JButton circleButton;
	private JButton rectangleButton;
	private JPanel buttonPanel;
	private JPanel mainPanel;
	private Drawing drawing;
	private JPanel statusPanel;
	private JLabel counterLab;
	private JLabel labelCounter;
	private JButton dupliquerButton;
	private JButton undoButton;
	private JButton redoButton;

	// Constructeur avec paramettre
	public Paint(CounterController controller) {
		clearButton = new JButton("Clear");
		circleButton = new JButton("Circle");
		rectangleButton = new JButton("Rectangle");
		counterLab = new JLabel();
		labelCounter = new JLabel();
		dupliquerButton = new JButton("Dupliquer");
		undoButton = new JButton("Undo");
		redoButton = new JButton("Redo");

		// listeners pour les boutons compteur
		circleButton.addActionListener(controller);
		rectangleButton.addActionListener(controller);
		clearButton.addActionListener(controller);
        dupliquerButton.addActionListener(controller);
		undoButton.addActionListener(controller);
		redoButton.addActionListener(controller);
	}

	public void run() {
		frame = new JFrame("Paint");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel(new BorderLayout());
		statusPanel = new JPanel();
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		frame.add(statusPanel, BorderLayout.SOUTH);
		statusPanel.setPreferredSize(new Dimension(frame.getWidth(), 24));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		counterLab = new JLabel("0");
		counterLab.setHorizontalAlignment(SwingConstants.LEFT);
		labelCounter = new JLabel("Nombre de formes dessinée :  ");

		drawing = new Drawing();
		drawing.setBackground(Color.WHITE);
		buttonPanel = new JPanel();
		buttonPanel.add(clearButton);
		buttonPanel.add(circleButton);
		buttonPanel.add(rectangleButton);
		buttonPanel.add(dupliquerButton);
		buttonPanel.add(undoButton);
		buttonPanel.add(redoButton);

		statusPanel.add(labelCounter);
		statusPanel.add(counterLab);

		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		mainPanel.add(drawing, BorderLayout.CENTER);

		// listeners pour les boutons dessin
		clearButton.addActionListener(new ClearButtonListener(drawing));
		circleButton.addActionListener(new CircleButtonListener(drawing));
		rectangleButton.addActionListener(new RectangleButtonListener(drawing));
		dupliquerButton.addActionListener(new DupliquerButtonListner(drawing));
		undoButton.addActionListener(new UndoButtonListner(drawing));
		redoButton.addActionListener(new RedoButtonListner(drawing));

		// listeners pour la zone de dessin
		DrawingMouseListener l = new DrawingMouseListener(drawing);
		drawing.addMouseListener(l);
		drawing.addMouseMotionListener(l);

		frame.getContentPane().add(mainPanel);
		frame.setSize(640, 480);
		frame.setVisible(true);
	}

	// Model
	public static Drawing draw = new Drawing();
	
	// Controller
	public static CounterController controller = new CounterController(draw);

	public static void main(String[] args) {
		
		// Vue
		Paint app = new Paint(controller);
		draw.addObserver(app);
		app.run();
	}

	@Override
	public void update(int value) {
		counterLab.setText(Integer.toString(value));
	}
}
