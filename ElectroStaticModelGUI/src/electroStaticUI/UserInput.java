package electroStaticUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jzy3d.chart.Chart;
import org.jzy3d.chart.controllers.mouse.ChartMouseController;

import electroStaticModel.PointCharge;


public class UserInput extends JPanel{

	private JFrame chargeDataFrame;
	private JLabel charges;
	private JTextField getNumberOfCharges;
	private JButton enter;
	private JButton loadCharges;
	private JButton randomCharges;
	private JButton graphIt;
	private JPanel uIPanel;
	private File fileOfCharges;
	private int numberOfCharges;
	private JTextField charge;
	private JTextField xPosition;
	private JTextField yPosition;
	private int confirm;
	private Chart chart = new Chart("swing");
	private int okButtonPushes = 0;
	private static PointCharge[] chargesToCalc;
	private Double chargeValue;
	private Double xValue;
	private Double yValue;
	private JTextField chargeModifierField;
	private String[] chargeModList = {"none", "centi", "milli",  "micro", "nano", "pico", "femto"};
	private JComboBox chargeModComboBox;
	private ChartMouseController rotateIt; 
	//private JTextField zPosition;
	
	
	public UserInput(){
		charges = new JLabel("How Many Charges");
		getNumberOfCharges = new JTextField(5);
		enter = new JButton("Enter");
		loadCharges = new JButton("Load A File");
		randomCharges = new JButton("Random Charges");
		graphIt = new JButton("Graph It!");
		
		add(charges);
		add(getNumberOfCharges);
		add(enter);
		add(loadCharges);
		add(randomCharges);
		add(graphIt);
		getNumberOfCharges.setEditable(true);
		graphIt.setVisible(false);
		
		//add event listeners to teh buttonz
		enter.addActionListener(new EnterChargesListener());
		loadCharges.addActionListener(new LoadChargesListener());
		randomCharges.addActionListener(new RandomChargesListener());
		
		setPreferredSize(new Dimension(800, 100));
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void getChargeData(){
		numberOfCharges = Integer.parseInt(getNumberOfCharges.getText());
		chargesToCalc = new PointCharge[numberOfCharges];
		chargeDataFrame = new JFrame();
		chargeDataFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel chargeDataPanel = new JPanel();
		chargeDataFrame.setTitle("Data For Charge #: " + (1+okButtonPushes));
		JLabel chargeLabel = new JLabel("Charge");
		JLabel chargeUnitLabel = new JLabel("C");
		charge = new JTextField(10);
		charge.setEditable(true);
		chargeModComboBox = new JComboBox(chargeModList);
		chargeModComboBox.setSelectedIndex(5);
		chargeModComboBox.setVisible(true);
		JLabel xPositionLabel = new JLabel("X Value");
		xPosition = new JTextField(10);
		xPosition.setEditable(true);
		JLabel yPositionLabel = new JLabel("Y Value");
		yPosition = new JTextField(10);
		yPosition.setEditable(true);
		//JLabel zPositionLabel = new JLabel("Z Value");
		//JTextField zPosition = new JTextField(5);
		//zPosition.setEditable(true);
		JButton okButton = new JButton("OK");
		chargeDataPanel.add(chargeLabel);
		chargeDataPanel.add(charge);
		chargeDataPanel.add(chargeModComboBox);
		chargeDataPanel.add(chargeUnitLabel);
		chargeDataPanel.add(xPositionLabel);
		chargeDataPanel.add(xPosition);
		chargeDataPanel.add(yPositionLabel);
		chargeDataPanel.add(yPosition);
		chargeDataPanel.add(okButton);
		chargeDataPanel.setMinimumSize(new Dimension(600, 100));
		chargeDataPanel.setVisible(true);
		chargeDataFrame.add(chargeDataPanel);
		chargeDataFrame.setVisible(true);
		chargeDataFrame.setMinimumSize(new Dimension(600, 100));
		xPosition.setText("0");
		yPosition.setText("0");
		charge.setText("0");
		/*
		 * okButton anonymous action listener takes the data entered into the JTextfields and creates point charges from it.
		 */
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					//do {
						//chargesToCalc[okButtonPushes] = new PointCharge(Double.parseDouble(charge.getText()), Double.parseDouble(xPosition.getText()), Double.parseDouble(yPosition.getText()));
						chargeValue = Double.parseDouble(charge.getText());
						xValue = Double.parseDouble(xPosition.getText());
						yValue = Double.parseDouble(yPosition.getText());
						chargesToCalc[okButtonPushes] = new PointCharge(chargeValue, xValue, yValue);
						int chargeModIndex = chargeModComboBox.getSelectedIndex();
						FunctionBuilder.setChargeModifierIndex(chargeModIndex);
						charge.setText("0");
						xPosition.setText("0");
						yPosition.setText("0");
						okButtonPushes++;
						chargeDataFrame.setTitle("Data For Charge #: " + (1+okButtonPushes));
							if(okButtonPushes == numberOfCharges){
								confirm = JOptionPane.showConfirmDialog(null, "You have entered " + okButtonPushes + " charges. Press OK to confirm. \nPress Cancel to re-enter the charges", null ,JOptionPane.OK_CANCEL_OPTION);
								if(confirm == JOptionPane.OK_OPTION){
									chart = GraphIt.makeChart();
									rotateIt = new ChartMouseController(chart);
									ElectroStaticUIContainer.removeGraphFromdisplayPanel();
									ElectroStaticUIContainer.addGraphToDisplayPanel(chart);
									okButtonPushes = 0;
									chargeDataFrame.setVisible(false);
									chargeDataFrame.revalidate();
									chargeDataFrame.dispose();
								}
								else if(confirm == JOptionPane.CANCEL_OPTION)
									okButtonPushes = 0;
							}
							
						}
						//while(okButtonPushes < numberOfCharges);
						
				//}
		});
	}
	
	//inner listener classes these need to be changed to anonymous inner classes like the okButton from the chargeDataFrame.
	
	
	
	
	private class EnterChargesListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			getChargeData();
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
	
	public static PointCharge[] getChargesToCalculate(){
		return chargesToCalc;
	}
	
	
	
	public Chart getChart(){
		return chart;
	}
	
}
