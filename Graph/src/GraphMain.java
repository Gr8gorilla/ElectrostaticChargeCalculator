import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.jzy3d.chart.Chart;

import electroStaticUI.ElectroStaticUIContainer;
import electroStaticUI.GraphIt;
import electroStaticUI.UserInput;


public class GraphMain {

	 
	
	
	//finally this works to create and display the graph window properly!!!!!!!
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JPanel container = new JPanel(new BorderLayout());
		JPanel displayPanel = new JPanel(new BorderLayout());
		ElectroStaticUIContainer uiContainer = new ElectroStaticUIContainer();
		UserInput userInterface = new UserInput();
		Chart chart = new Chart();
		
		//make the chart
		chart = GraphIt.makeTestChart();
		
		//set panel sizes
		container.setPreferredSize(new Dimension(1200, 900));
		displayPanel.setPreferredSize(new Dimension(800,800));
		
		
		//add chart to display panel
		displayPanel.add((JComponent)chart.getCanvas());
		displayPanel.setVisible(true);
		
		//add the ui components to a container panel
		container.add(userInterface, BorderLayout.PAGE_START);
		container.add(displayPanel, BorderLayout.CENTER);
		container.setVisible(true);
		
		//add the desired contents to the window
		uiContainer.getContentPane().add(container);
		uiContainer.setVisible(true);
	}

}
