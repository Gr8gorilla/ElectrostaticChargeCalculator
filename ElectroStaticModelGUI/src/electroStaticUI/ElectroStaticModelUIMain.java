package electroStaticUI;
import java.awt.BorderLayout;


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


public class ElectroStaticModelUIMain {

	 
	
	
	//finally this works to create and display the graph window properly!!!!!!!
	public static void main(String[] args) {
		
		UserInput getInput = new UserInput();
		ElectroStaticUIContainer esuic = new ElectroStaticUIContainer();
		getInput.setVisible(true);
		esuic.addPanelToContainer(getInput, BorderLayout.PAGE_START);
		
	}

}







/*
 This was the original test code to get the graph up and other various tests. 
 
// TODO Auto-generated method stub
JPanel container = new JPanel(new BorderLayout());
JPanel displayPanel = new JPanel(new BorderLayout());
ElectroStaticUIContainer uiContainer = new ElectroStaticUIContainer();
UserInput userInterface = new UserInput();
Chart chart = new Chart();

//make the chart
chart = userInterface.getChart();

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
*/