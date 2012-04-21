package electroStaticUI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	
	public CalculatorPanel(){
		
		xyLocation = new JTextField(12);
		displayVoltage  = new JTextField(8);
		displayVector = new JTextField(8);
		
		xyLocLabel = new JLabel("Enter X Y Point");
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
		
		
	}
	
	private void getFieldPoint(){
		StringBuilder sb = new StringBuilder();
	}

}
