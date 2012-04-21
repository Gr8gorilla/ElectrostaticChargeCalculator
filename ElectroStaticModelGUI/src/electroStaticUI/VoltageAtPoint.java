package electroStaticUI;

import java.util.ArrayList;
import java.util.Iterator;

public class VoltageAtPoint {
	
	
	/*
	 * Voltage at a point is calc'd kq/r, if multiple charges affect the point the voltage is the sum of the effects.
	 * These methods require that the electric force vectors to each field point be calculated. This is built into
	 */
		
	private PointCharge[] charges;
	private double voltageHere = 0;
	private FieldPoint thisPoint;
	
		/*
		 * This class calculates the voltage at a chosen FieldPoint or array of FieldPoints. It then sets the voltage variable within 
		 * the fieldPoint. The ElectricForceVectors from all charges must be calculated before this class can be called.
		 */
	public VoltageAtPoint(FieldPoint point){
		thisPoint = point;
		getSetUp();
		point.setVoltageAtThisPoint(findVoltage());
	}
	
	public VoltageAtPoint(ArrayList<FieldPoint> mappedPoints){
		for(int i=0; i<mappedPoints.size(); i++){
			thisPoint = mappedPoints.get(i);
			getSetUp();
			mappedPoints.get(i).setVoltageAtThisPoint(findVoltage());
			mappedPoints.get(i).setZAsVoltage(voltageHere);
			voltageHere = 0;
		}
	}
	
	public VoltageAtPoint(FieldPoint[] mappedPoints){
		for(int i=0; i<mappedPoints.length; i++){
			thisPoint = mappedPoints[i]; //load the point in the array into the calculator
			getSetUp();
			mappedPoints[i].setVoltageAtThisPoint(findVoltage()); //set the variable in the FieldPoint to the correct voltage
			mappedPoints[i].setZAsVoltage(voltageHere);
			//System.out.println("The Z value is now " + voltageHere + ".");
			voltageHere = 0; //reset voltageHere variable
			
		}
	}
	
	private void getSetUp(){
		charges = new PointCharge[UserInput.getChargesToCalculate().length];
		for(int j=0; j<charges.length; j++)
				charges[j] = new PointCharge(thisPoint.getVectorsToThisPoint().get(j).getPointQ1());
	}
	
	
	//calculate the voltage usign the method within the pointCharge class.......
	private double findVoltage(){
		for(int i=0; i<charges.length; i++)
			voltageHere += (charges[i].voltageFromThisCharge(thisPoint));
		//System.out.println("The voltage at this point is " + voltageHere + ".");
		return voltageHere;
	}

	public double getVoltageHere() {
		return voltageHere;
	}

	
}