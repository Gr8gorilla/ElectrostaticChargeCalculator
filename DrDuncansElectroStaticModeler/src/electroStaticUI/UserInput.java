package electroStaticUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jfree.chart.JFreeChart;
import org.jzy3d.chart.Chart;
import org.jzy3d.chart.controllers.mouse.ChartMouseController;


public class UserInput extends JPanel{

	/*
	 * Copyright 2012 Shaun Sharpton
	 * 
	 * This file is part of "Dr Duncan's Electrostatic Charge modeler"!
	 * 
	 * 
	 *   "Dr Duncan's Electrostatic Charge modeler" is free software: you can redistribute it and/or modify
	 *   it under the terms of the GNU General Public License as published by
	 *   the Free Software Foundation, either version 3 of the License, or
	 *   (at your option) any later version.
	 *   
	 *   "Dr Duncan's Electrostatic Charge modeler" is distributed in the hope that it will be useful,
	 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
	 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	 *   GNU General Public License for more details.
	 *   
	 *   You should have received a copy of the GNU General Public License
	 *   along with "Dr Duncan's Electrostatic Charge modeler".  If not, see <http://www.gnu.org/licenses/>.
	 */
	
	
	private static final long serialVersionUID = 1L;
	private JFrame chargeDataFrame;
	private JLabel charges;
	private JTextField getNumberOfCharges;
	private JButton enter;
	private JButton circleRectangle;
	private JButton calculator;
	private JButton graphIt;
	private JPanel uIPanel;
	private File fileOfCharges;
	private int numberOfCharges;
	private JTextField charge;
	private JTextField xPosition;
	private JTextField yPosition;
	private int confirm;
	private static Chart chart = new Chart("swing");
	private static JFreeChart electricFieldChart;
	private int okButtonPushes = 0;
	private PointCharge[] chargesToCalc;
	private Double chargeValue;
	private Double xValue;
	private Double yValue;
	private String[] chargeModList = {"none", "centi", "milli",  "micro", "nano", "pico", "femto"};
	private JComboBox<String> chargeModComboBox;
	@SuppressWarnings("unused")
	private ChartMouseController rotateIt; 
	@SuppressWarnings("unused")
	private VectorCalculator vc;
	private CustomMapper mapper;
	@SuppressWarnings("unused")
	private VectorCalculator calcVec;
	@SuppressWarnings("unused")
	private VoltageAtPoint volts;
	private ManualPolygons manGraph;
	private DrawElectricFieldLines drawVecs;
	private String circleRectTT;
	private String calculatorTT;
	private JLabel lengthModLabel;
	private JComboBox<String> lengthModCombo;
	private String[] lengthModList = {"none", "centi", "milli",  "micro", "nano", "pico", "femto"};
	private JFrame calculatorFrame;
	private CalculatorPanel theCalculator;
	//private JTextField zPosition;
	
	
	

	
	public UserInput(){
		charges = new JLabel("How Many Charges");
		getNumberOfCharges = new JTextField(5);
		enter = new JButton("Enter");
		circleRectangle = new JButton("Circle/Rectangle");
		calculator = new JButton("Calculator");
		graphIt = new JButton("Graph It!");
		lengthModLabel = new JLabel("Meters");
		lengthModCombo = new JComboBox<String>(lengthModList);
		
		add(lengthModCombo);
		add(lengthModLabel);
		add(charges);
		add(getNumberOfCharges);
		add(enter);
		//add(circleRectangle);
		add(calculator);
		add(graphIt);
		getNumberOfCharges.setEditable(true);
		graphIt.setVisible(false);
		
		//set lengthModCombo default
		lengthModCombo.setSelectedIndex(0);
		
		//set some tool tips
		circleRectTT = "Plot points in rectangle or circle.";
		circleRectangle.setToolTipText(circleRectTT);
		
		calculatorTT = "Open calculator";
		calculator.setToolTipText(calculatorTT);
		
		//add event listeners to teh buttonz
		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				DefaultValues.setLengthModIndex(lengthModCombo.getSelectedIndex());
				getChargeData();
			}
		});
		
		
		circleRectangle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(DefaultValues.getCircOrRect() == 'c')
					DefaultValues.setCircOrRect('r');
				else if(DefaultValues.getCircOrRect() == 'r')
					DefaultValues.setCircOrRect('c');
			}
		});
		
		calculator.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				calculatorFrame = new JFrame();
				calculatorFrame.setTitle("Calculator");
				
				//set some defaults
				calculatorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				calculatorFrame.setPreferredSize(new Dimension(400, 200));
				calculatorFrame.add(theCalculator);
				
				calculatorFrame.pack();
				calculatorFrame.setVisible(true);
				
				
			}
		});
		
		setPreferredSize(new Dimension(400, 50));
	}
	
	/*
	 * method to collect user data and set variables based on that data. should probably be moved to it's own class
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void getChargeData(){
		numberOfCharges = Integer.parseInt(getNumberOfCharges.getText());
		DefaultValues.allocatePointChargeArray(numberOfCharges);
		chargesToCalc = new PointCharge[numberOfCharges];
		mapper = new CustomMapper(DefaultValues.getCircOrRect()); 
		chargeDataFrame = new JFrame();
		chargeDataFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
						int chargeModIndex = chargeModComboBox.getSelectedIndex();
						DefaultValues.setChargeModIndex(chargeModIndex);
						chargeValue = Double.parseDouble(charge.getText());
						xValue = Double.parseDouble(xPosition.getText());
						yValue = Double.parseDouble(yPosition.getText());
						chargesToCalc[okButtonPushes] = new PointCharge(chargeValue, xValue, yValue);
						charge.setText("0");
						xPosition.setText("0");
						yPosition.setText("0");
						okButtonPushes++;
						chargeDataFrame.setTitle("Data For Charge #: " + (1+okButtonPushes));
							if(okButtonPushes == numberOfCharges){
								confirm = JOptionPane.showConfirmDialog(null, "You have entered " + okButtonPushes + " charges. Press OK to confirm. \nPress Cancel to re-enter the charges", null ,JOptionPane.OK_CANCEL_OPTION);
								if(confirm == JOptionPane.OK_OPTION){
									DefaultValues.setCurrentPointCharges(chargesToCalc);
									ElectroStaticUIContainer.removeGraphFromdisplayPanel();
									theCalculator = new CalculatorPanel();
									calcVec = new VectorCalculator(mapper);
									volts = new VoltageAtPoint(mapper.getFieldPoints());
									manGraph = new ManualPolygons(mapper);
									chart = manGraph.delaunayBuild();
									drawVecs = new DrawElectricFieldLines(mapper);
									ElectroStaticUIContainer.add3DGraphToDisplayPanel(chart);
									ElectroStaticUIContainer.addVectorGraphToDisplayPanel(drawVecs.getChart());
									setVectorChartToSave();
									setChart3dToSave();
									rotateIt = new ChartMouseController(chart);
									okButtonPushes = 0;
									chargeDataFrame.removeAll();
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
	
	
	
	
	
	/*	
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
	*/
	
	public void setChart3dToSave(){
		DefaultValues.set3dChartToSave(chart);
	}
	
	public void setVectorChartToSave(){
		DefaultValues.setChartToSave(drawVecs.getChart());
	}
	
	public PointCharge[] getChargesToCalculate(){
		return chargesToCalc;
	}
	
	
	
	public Chart getChart(){
		return chart;
	}

	
	public static JFreeChart getElectricFieldChart() {
		return electricFieldChart;
	}

	
	
}
