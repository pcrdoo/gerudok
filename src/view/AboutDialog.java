package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
/**
 * View class which contains information about PCR team.
 * 
 * @author Igor Bakovic
 */
@SuppressWarnings("serial")
public class AboutDialog extends JDialog {

	/**
	 * Constructor.
	 * @param parent Parent frame
	 */
	public AboutDialog(JFrame parent) {
		super(parent);

		JPanel panel = new JPanel(new FlowLayout());
		JButton btnClose = new JButton("Close");
		JLabel textZaZeku = new JLabel(
				"<html>Tim: 201_2 Pres Centar Racun <br>clanovi redom: <br>Nikola Jovanovic RN 23/2015 <br>Ognjen Djuricic RN 24/2015 <br>David Davidovic RN 25/2015 <br>Igor Bakovic RN 11/2015</html>");

		try {
			BufferedImage pcrImage = ImageIO.read(MainView.class.getResourceAsStream("slika_tima.png"));
			JLabel lblPcrImage = new JLabel(new ImageIcon(pcrImage));
			panel.add(lblPcrImage);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

		setTitle("O nasem timu");

		panel.add(textZaZeku);
		panel.add(btnClose);
		getContentPane().add(panel);

		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AboutDialog.this.setVisible(false);
			}
		});

		setPreferredSize(new Dimension(550, 450));
		pack();
		setLocationRelativeTo(parent);
	}
}
