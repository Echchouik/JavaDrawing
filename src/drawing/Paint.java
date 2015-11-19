package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
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

	// Constructeur avec paramettre
	public Paint(CounterController controller) {
		clearButton = new JButton("Clear");
		circleButton = new JButton("Circle");
		rectangleButton = new JButton("Rectangle");
		counterLab = new JLabel();
		labelCounter = new JLabel();

		// listeners pour les boutons compteur
		circleButton.addActionListener(controller);
		rectangleButton.addActionListener(controller);
		clearButton.addActionListener(controller);

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

		statusPanel.add(labelCounter);
		statusPanel.add(counterLab);

		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		mainPanel.add(drawing, BorderLayout.CENTER);

		// listeners pour les boutons dessin
		clearButton.addActionListener(new ClearButtonListener(drawing));
		circleButton.addActionListener(new CircleButtonListener(drawing));
		rectangleButton.addActionListener(new RectangleButtonListener(drawing));

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
