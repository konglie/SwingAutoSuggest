import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by konglie on 10/24/16.
 */
public class JFrameKeyboard {
	private static JFrame frame;
	public static void main(String[] args){
		// basic JFrame config
		frame = new JFrame("Keyboard Listener");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(640,480));
		frame.setLocationRelativeTo(null);

		// demo stuffs
		JTextArea logger = new JTextArea();
		logger.setEditable(false);
		frame.getContentPane().add(new JScrollPane(logger), BorderLayout.CENTER);

		JLabel title = new JLabel("Tekan Tombol di keyboard, Escape untuk keluar.");
		frame.getContentPane().add(title, BorderLayout.PAGE_START);

		// the main demo
		setKeyboardListener(logger);

		// bring it on
		frame.pack();
		frame.setVisible(true);
	}

	private static void setKeyboardListener(final JTextArea logger){
		KeyboardFocusManager.getCurrentKeyboardFocusManager()
				.addKeyEventDispatcher(new KeyEventDispatcher() {
					@Override
					public boolean dispatchKeyEvent(KeyEvent e) {
						// listen only on KeyReleased Event
						if(e.getID() != KeyEvent.KEY_TYPED){
							return true;
						}

						if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
							quit();
							return false;
						}

						logger.setText(String.format("%s\nTekan: %s", logger.getText(), e.getKeyChar()));
						return false;
					}
				});
	}

	private static void quit(){
		if(JOptionPane.showConfirmDialog(frame, "Keluar ?", "Konfirmasi", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION){
			return;
		}

		frame.dispose();
		System.exit(0);
	}
}
