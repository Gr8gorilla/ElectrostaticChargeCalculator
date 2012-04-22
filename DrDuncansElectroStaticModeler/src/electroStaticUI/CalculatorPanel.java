package electroStaticUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

/*
 * let the user enter a point and find the Potential Voltage and Electric force vector at this point
 */

public class CalculatorPanel extends JPanel {
	
	private FieldPoint findValueHere;
	private JTextField xyLocation;
	private JTextField displayVoltage;
	private JTextField displayVector;
	private JLabel xyLocLabel;
	private JLabel voltDispLabel;
	private JLabel vectDispLabel;
	private JButton calculate;
	private String xyLocString;
	private Double x;
	private Double y;
	private StringTokenizer st;
	private VectorCalculator vc;
	@SuppressWarnings("unused")
	private VoltageAtPoint vp;
	private int badValOK;
	private String xValue;
	private String yValue;
	
	public CalculatorPanel(){
				
		
		xyLocation = new JTextField(12);
		displayVoltage  = new JTextField(8);
		displayVector = new JTextField(8);
		
		xyLocLabel = new JLabel("Enter X,Y Point");
		voltDispLabel = new JLabel("Voltage");
		vectDispLabel = new JLabel("Electric Force");
		
		calculate = new JButton("Calculate");
		
		this.add(xyLocLabel);
		this.add(xyLocation);
		this.add(voltDispLabel);
		this.add(displayVoltage);
		this.add(vectDispLabel);
		this.add(displayVector);
		this.add(calculate);
		
		//set editables
		xyLocation.setEditable(true);
		displayVoltage.setEditable(false);
		displayVector.setEditable(false);
		
		//add a tool tip
		xyLocLabel.setToolTipText("Enter your X & Y like so: 1.25, .75 ");
		
		calculate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				getFieldPoint();
				calculateValues();
				displayVoltage.setText(Double.toString(findValueHere.getVoltageAtThisPoint()));
				displayVector.setText(findValueHere.getTotalVector().toString());
			}
		});
		
		this.setPreferredSize(new Dimension(400, 100));
		this.setVisible(true);
	}
	
	private void getFieldPoint(){
		xyLocString = xyLocation.getText();
		st = new StringTokenizer(xyLocString, ",\t\n\r\f");
		try{
		x = Double.parseDouble(st.nextToken());
		y = Double.parseDouble(st.nextToken());
		System.out.println("X equals: " + x);
		System.out.println("Y equals: " + y);
		}
		catch (NumberFormatException e){
			badValOK = JOptionPane.showConfirmDialog(getParent(), "You have entered your values incorrectly.", null, JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE);
			if(badValOK == JOptionPane.OK_OPTION)
			xValue = JOptionPane.showInputDialog("Enter the X value: ");
			yValue = JOptionPane.showInputDialog("Enter the Y value: ");
			x = Double.parseDouble(xValue);
			y = Double.parseDouble(yValue);
		}
		findValueHere = new FieldPoint(x, y, 0);
		
	}
	
	private void calculateValues(){
		vc = new VectorCalculator(findValueHere);
		findValueHere.sumVectorsAtThisPoint();
		vc.findTotalVectorAtFieldPoints();
		vp = new VoltageAtPoint(findValueHere);
		
	}

}
