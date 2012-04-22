package electroStaticUI;

import java.util.ArrayList;


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


public class VoltageAtPoint {
	
	
	/*
	 * Voltage at a point is calc'd kq/r, if multiple charges affect the point the voltage is the sum of the effects.
	 * These methods require that the electric force vectors to each field point be calculated. This is built into
	 */
		
	private double voltageHere = 0;
	private FieldPoint thisPoint;
	
		/*
		 * This class calculates the voltage at a chosen FieldPoint or array of FieldPoints. It then sets the voltage variable within 
		 * the fieldPoint. The ElectricForceVectors from all charges must be calculated before this class can be called.
		 */
	public VoltageAtPoint(FieldPoint point){
		thisPoint = point;
		point.setVoltageAtThisPoint(findVoltage());
		point.setZAsVoltage(voltageHere);
	}
	
	public VoltageAtPoint(ArrayList<FieldPoint> mappedPoints){
		for(int i=0; i<mappedPoints.size(); i++){
			thisPoint = mappedPoints.get(i);
			mappedPoints.get(i).setVoltageAtThisPoint(findVoltage());
			mappedPoints.get(i).setZAsVoltage(voltageHere);
			voltageHere = 0;
		}
	}
	
	public VoltageAtPoint(FieldPoint[] mappedPoints){
		for(int i=0; i<mappedPoints.length; i++){
			thisPoint = mappedPoints[i]; //load the point in the array into the calculator
			mappedPoints[i].setVoltageAtThisPoint(findVoltage()); //set the variable in the FieldPoint to the correct voltage
			mappedPoints[i].setZAsVoltage(voltageHere);
			//System.out.println("The Z value is now " + voltageHere + ".");
			voltageHere = 0; //reset voltageHere variable
			
		}
	}
	
	
	//calculate the voltage using the method within the pointCharge class.......
	private double findVoltage(){
		for(int i=0; i<DefaultValues.getCurrentPointCharges().length; i++)
			voltageHere += (DefaultValues.getCurrentPointCharges()[i].voltageFromThisCharge(thisPoint));
		//System.out.println("The voltage at this point is " + voltageHere + ".");
		return voltageHere;
	}

	public double getVoltageHere() {
		return voltageHere;
	}

	
}