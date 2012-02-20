package electroStaticUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import partsOfAField.PointCharge;

public class UserInput extends JPanel{

	private JFrame chargeDataFrame;
	private JLabel charges;
	private JTextField getNumberOfCharges;
	private JButton enter;
	private JButton loadCharges;
	private JButton randomCharges;
	private JButton blankButton;
	private JPanel uIPanel;
	private File fileOfCharges;
	private int numberOfCharges;
	private PointCharge[] chargesForCalcs;
	private JTextField charge;
	private JTextField xPosition;
	private JTextField yPosition;
	private int confirm;
	//private JTextField zPosition;
	int okButtonPushes = 0;
	
	public UserInput(){
		charges = new JLabel("How Many Charges");
		getNumberOfCharges = new JTextField(5);
		enter = new JButton("Enter");
		loadCharges = new JButton("Load A File");
		randomCharges = new JButton("Random Charges");
		
		add(charges);
		add(getNumberOfCharges);
		add(enter);
		add(loadCharges);
		add(randomCharges);
		
		getNumberOfCharges.setEditable(true);
		
		
		//add event listeners to teh buttonz
		enter.addActionListener(new EnterChargesListener());
		loadCharges.addActionListener(new LoadChargesListener());
		randomCharges.addActionListener(new RandomChargesListener());
		
		setPreferredSize(new Dimension(800, 100));
	}
	
	private void getChargeData(){
		
		numberOfCharges = Integer.parseInt(getNumberOfCharges.getText());
		chargesForCalcs = new PointCharge[numberOfCharges];
		chargeDataFrame = new JFrame();
		chargeDataFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel chargeDataPanel = new JPanel();
		chargeDataFrame.setTitle("Data For Charge #: " + (okButtonPushes+1));
		JLabel chargeLabel = new JLabel("Charge(in Coulombs)");
		charge = new JTextField(5);
		charge.setEditable(true);
		JLabel xPositionLabel = new JLabel("X Value");
		xPosition = new JTextField(5);
		xPosition.setEditable(true);
		JLabel yPositionLabel = new JLabel("Y Value");
		yPosition = new JTextField(5);
		yPosition.setEditable(true);
		//JLabel zPositionLabel = new JLabel("Z Value");
		//JTextField zPosition = new JTextField(5);
		//zPosition.setEditable(true);
		JButton okButton = new JButton("OK");
		chargeDataPanel.add(chargeLabel);
		chargeDataPanel.add(charge);
		chargeDataPanel.add(xPositionLabel);
		chargeDataPanel.add(xPosition);
		chargeDataPanel.add(yPositionLabel);
		chargeDataPanel.add(yPosition);
		chargeDataPanel.add(okButton);
		chargeDataPanel.setMinimumSize(new Dimension(50,50));
		chargeDataPanel.setVisible(true);
		chargeDataFrame.add(chargeDataPanel);
		chargeDataFrame.setVisible(true);
		chargeDataFrame.setMinimumSize(new Dimension(50, 50));
		
		
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					do {
						chargesForCalcs[okButtonPushes] = new PointCharge(Double.parseDouble(charge.getText()), Double.parseDouble(xPosition.getText()), Double.parseDouble(yPosition.getText()));
						charge.setText(null);
						xPosition.setText(null);
						yPosition.setText(null);
						okButtonPushes++;
							if(okButtonPushes == numberOfCharges){
								confirm = JOptionPane.showConfirmDialog(null, "You have entered " + okButtonPushes + " charges. Press OK to confirm. \nPress Cancel to re-enter the charges", null ,JOptionPane.OK_CANCEL_OPTION);
								if(confirm == JOptionPane.OK_OPTION)
									chargeDataFrame.dispose();
								else if(confirm == JOptionPane.CANCEL_OPTION)
									okButtonPushes = 0;
							}
						
						}
						while(okButtonPushes < numberOfCharges);
					}
		});
	}
	
	//inner listener classes
	
	private class EnterChargesListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			getChargeData();
			getNumberOfCharges.setText(null);
		}
	}
		
	private class LoadChargesListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JFileChooser loadChargeChooser = new JFileChooser();
			int status = loadChargeChooser.showOpenDialog(null);
			if (status == JFileChooser.APPROVE_OPTION)
				fileOfCharges = loadChargeChooser.getSelectedFile();
		}
	}
	
	private class RandomChargesListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	public PointCharge[] getChargeArray(){
		return chargesForCalcs;
	}
	
}
