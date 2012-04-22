package electroStaticUI;

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
 * This class finds the vectors from each charge to each field point. Each of my calculation classes relies on my custom mapper
 * to create it's data. The actual numbers are manipulated inside the classes that make up electricForce vectors, 
 * this class just calls and organizes those methods. 
 */
public class VectorCalculator {

	private ElectricForceVector[][] electricFieldVectors;
	private CustomMapper thisMap;
	private FieldPoint pointToCalc;
	private boolean oneFieldPoint;
	
	
	/*
	 * this constructor is used for actually drawing the vectors at each mapped point
	 */
	public VectorCalculator(CustomMapper aMap){
		oneFieldPoint = false;
		thisMap = aMap;
		electricFieldVectors = new ElectricForceVector[DefaultValues.getCurrentPointCharges().length][aMap.getHowManyFieldPoints()];
		//System.out.println("Size is " + electricFieldVectors.length + " and " + electricFieldVectors[0].length + ".");
		calculateVectors();
		//printVectorArray();
	}
	
	/*
	 * used in calculator class for allowing user to choose an arbitrary point and getting the value of the e-field and potential voltage there
	 */
	public VectorCalculator(FieldPoint singleFieldPoint){
		oneFieldPoint = true;
		pointToCalc = singleFieldPoint;
		electricFieldVectors  = new ElectricForceVector[DefaultValues.getCurrentPointCharges().length][1];
		calculateVectors();
	}
	
	private void calculateVectors(){
		for(int i=0; i<DefaultValues.getCurrentPointCharges().length; i++)
			for(int j=0; j<electricFieldVectors[i].length; j++){//the if statement below makes sure we don't calculate anything outside our boundaries.
				if((DefaultValues.getCurrentPointCharges()[i].x <= DefaultValues.getMax()) && (DefaultValues.getCurrentPointCharges()[i].x >= DefaultValues.getMin()) && (DefaultValues.getCurrentPointCharges()[i].y<= DefaultValues.getMax()) && (DefaultValues.getCurrentPointCharges()[i].y>= DefaultValues.getMin()))
				electricFieldVectors[i][j] = new ElectricForceVector(DefaultValues.getCurrentPointCharges()[i], getFieldPointForVector(j));
				else electricFieldVectors[i][j] = new ElectricForceVector();//if the charge was out of bounds it makes a blank vector there.
			}
	}
	
	/*
	 * method so that I don't have to write new methods for calculating vectors for single field points.
	 */
	private FieldPoint getFieldPointForVector(int i){
		if(!oneFieldPoint)
			pointToCalc = thisMap.getFieldPoints().get(i);
		return pointToCalc;
			
	}
	
	/*
	 * findTotalVectorAtFieldPoints sums all vectors at each field point.  This did not produce the expected outcome within the DrawElectricFieldLines Class. 
	 * Upon writing the same method within that class, I got the expected results with vectors in the correct 
	 */
	public void findTotalVectorAtFieldPoints(){
		//System.out.println("Vectors in arrayList " + thisMap.getFieldPoints()[0].getVectorsToThisPoint().size()  + ".");
		if(!oneFieldPoint)
		for(int i=0; i<thisMap.getFieldPoints().size(); i++)
			thisMap.getFieldPoints().get(i).sumVectorsAtThisPoint();
		else pointToCalc.sumVectorsAtThisPoint();
	}
	
	@SuppressWarnings("unused")
	private void printVectorArray(){
		for(int i=0; i<electricFieldVectors.length; i++){
			System.out.println(electricFieldVectors[i].toString());
		}
	}
	
	
}
