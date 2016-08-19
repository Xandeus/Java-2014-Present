package space;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SimplexTest extends JPanel {
	HandlerClass handler = new HandlerClass();
	TextHandler txt = new TextHandler();
	static boolean lClickD = false, rClickD = false, mClickD = false;
	static int clicks = 0;
	static int count = 0;
	static int octaves = 1;
	static double persistence = 1, amplitude = 1, frequency = 1;
	static String current;
	int mouseX, mouseY;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.black);
		int width = 1000;
		int height = 800;
		int box = 2;
		float scale = .0075f;

		for (int x = 0; x < 1000; x += box) {
			for (int y = 0; y < 800; y += box) {
				double h = SimplexNoise.OctavePerlin(x * scale, y * scale, octaves, persistence, frequency,amplitude); 
				if(h<0){
					int c = (int)((h + 1) / 2.0 * 255.0);
					g.setColor(new Color(0, 0, c));
				}
				else if(h<.1){
					int c = (int)((h + 1) / 2.0 * 255.0);
					g.setColor(new Color(0, c, c));
				}
				else if(h<.2 ){
					int c = (int)((h + 1) / 2.0 * 255.0);
					g.setColor(new Color(c, c, 0));
				}
				else if(h<.9 ){
					int c = (int)((h + 1) / 2.0 * 255.0);
					g.setColor(new Color(0, c, 0));
				}
				else if(h<1){
					int c = (int)((h + 1) / 2.0 * 255.0);
					g.setColor(new Color(c, c, c));
				}

				g.fillRect(x, y, box, box);
			}
		}
		
	}

	public Color randColor() {
		return new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
	}

	static JFrame frame = new JFrame("Simplex Test");

	static JTextField textField = new JTextField();

	public static void frame() throws InterruptedException {
		SimplexTest game = new SimplexTest();
		// Create the radio buttons.
		JRadioButton octavesButton = new JRadioButton("Octaves " + octaves);
		octavesButton.setActionCommand("Octaves");

		JRadioButton persistenceButton = new JRadioButton("Persistence " + persistence);
		persistenceButton.setActionCommand("Persistence");

		JRadioButton frequencyButton = new JRadioButton("Frequency " + frequency);
		frequencyButton.setActionCommand("Frequency");

		JRadioButton amplitudeButton = new JRadioButton("Amplitude " + amplitude);
		amplitudeButton.setActionCommand("Amplitude");

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(octavesButton);
		group.add(persistenceButton);
		group.add(frequencyButton);
		group.add(amplitudeButton);

		// Register a listener for the radio buttons.
		octavesButton.addActionListener(game.handler);
		persistenceButton.addActionListener(game.handler);
		frequencyButton.addActionListener(game.handler);
		amplitudeButton.addActionListener(game.handler);

		// Put the radio buttons in a column in a panel.
		JPanel radioPanel = new JPanel(new GridLayout(1, 0));
		radioPanel.add(octavesButton);
		radioPanel.add(persistenceButton);
		radioPanel.add(frequencyButton);
		radioPanel.add(amplitudeButton);

		textField.addActionListener(game.txt);
		frame.add(game, BorderLayout.CENTER);
		frame.add(radioPanel, BorderLayout.SOUTH);
		radioPanel.add(textField, BorderLayout.NORTH);
		frame.setResizable(false);
		frame.setSize(1000, 800);
		frame.setLocation(300, 10);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addMouseListener(game.handler);
		frame.addMouseMotionListener(game.handler);
		frame.addKeyListener(game.handler);
		while (true) {
			game.repaint();
			game.loop();
			Thread.sleep(5);
		}
	}

	public void actionPerformed(ActionEvent e) {

	}

	public void loop() {
		if (count < 256)
			count++;
		else
			count = 0;
	}

	public static void main(String[] args) throws InterruptedException {
		frame();

	}

	private class TextHandler implements ActionListener{
		public void actionPerformed(ActionEvent evt) {
		    switch(current){
			    case "Frequency":
			    	frequency = Double.parseDouble(textField.getText());
			    	break;
			    case "Amplitude":
			    	amplitude = Double.parseDouble(textField.getText());
			    	break;
			    case "Persistence":
			    	persistence = Double.parseDouble(textField.getText());
			    	break;
			    case "Octaves":
			    	octaves = (int)(Double.parseDouble(textField.getText()));
			    	break;
		    }
		    textField.setText("");
		}
		
	}
	private class HandlerClass implements MouseListener, MouseMotionListener, KeyListener, ActionListener {
		public void mouseClicked(MouseEvent event) {

		}

		public void mousePressed(MouseEvent event) {
			int x = event.getX() - 5;
			int y = event.getY() - 32;
			if (event.getButton() == MouseEvent.BUTTON1) {

			}
			if (event.getButton() == MouseEvent.BUTTON3) {

			}
			if (event.getButton() == MouseEvent.BUTTON2) {

			}

		}

		public void mouseReleased(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON1) {
			}
			if (event.getButton() == MouseEvent.BUTTON3) {

			}
		}

		public void mouseEntered(MouseEvent event) {

		}

		public void mouseExited(MouseEvent event) {

		}

		// These are mouse motion events
		public void mouseDragged(MouseEvent event) {
			int x = event.getX() - 5;
			int y = event.getY() - 32;
			if (lClickD) {
			} else if (rClickD) {
			} else if (mClickD) {
			}
		}

		public void mouseMoved(MouseEvent event) {
			mouseY = event.getY();
			mouseX = event.getX();
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			int keyCode = e.getKeyCode();

			if (keyCode == e.VK_A) {
				System.out.println("BOOP");
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			switch (e.getActionCommand()) {
			case "Octaves":
				current = e.getActionCommand();
				break;
			case "Persistence":
				current = e.getActionCommand();
				break;
			case "Frequency":
				current = e.getActionCommand();
				break;
			case "Amplitude":
				current = e.getActionCommand();
				break;
			}
		}
	}

}