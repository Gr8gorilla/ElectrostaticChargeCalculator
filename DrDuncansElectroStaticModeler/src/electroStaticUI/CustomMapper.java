package electroStaticUI;

import java.math.BigDecimal;
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




/*
 * Basic Overview of what is going on:
 * This custom mapper class allows me to solve for a charge distribution's electric field and voltage through brute force
 * the elegant solution was too difficult (creating an f(x,y) function for any charge distribution). The brute force method is simple.
 * First, I get a bounding box and step size from the user. I then get the number of charges that will be calculated for. From this
 * I build a grid of field points. I then calculate electric force vectors at each field point. The force vectors from all charges. I then sum
 * these vectors to get the resultant vector. Next, using VoltageAtPoint class, I calculate the voltage at each field point as a result
 * of each charge and sum that, storing it in a variable within the field point. Then I simply plot the vectors at each point on a 2d
 * graph and then plot the surface that represents Voltage on a 3d graph and display it.  
 */



public class CustomMapper {
	
	private ArrayList<FieldPoint> rectFieldPoints;
	private ArrayList<FieldPoint> circFieldPoints;
	private ArrayList<FieldPoint> fieldPoints;
	private double[] xyPoints;
	private static double steps;
	private static double userMin;
	private static double userMax;
	private double range; 
	private BigDecimal stepSize;
	private BigDecimal arraySize;
	private double r;
	private double theta;
	private double x;
	private double y;
	private int thetaSteps;
	private double circle;
	private double thetaStepSize;
	char circleOrRectangle;
	
	
	public CustomMapper(char cOrR){
		circleOrRectangle = cOrR;
		fieldPoints = new ArrayList<FieldPoint>();
		userMax = DefaultValues.getMax();
		userMin = DefaultValues.getMin();
		steps = DefaultValues.getSteps();
		calculateStuff();
		System.out.println("The range is: " + range);
		//makePointsToCheck();
		if(circleOrRectangle == 'r')
			fieldPoints = makeRectFieldPoints();
		
		else fieldPoints = makeCircleFieldPoints(); 
		
		
	}
	
	public ArrayList<FieldPoint>getFieldPoints(){
		return fieldPoints;
	}
	
	
	public int getHowManyFieldPoints(){
		return fieldPoints.size();
	}
	
	public static void setUserStepSize(int uSteps){
		steps = uSteps;
	}
	
	public static double getUserStepSize(){
		return steps;
	}
	
	public static void setUserMin(double uUserMin){
		userMin = uUserMin;
	}
	
	public static double getUserMin(){
		return userMin;
	}
	
	public static void setUserMax(double uUserMax){
		userMax = uUserMax;
	}
	
	public static double getUserMax(){
		return userMax;
	}
	
	/*
	 * The following two methods make a number of field points based on user chosen min, max, and step size. These points are then used to calculate the vectors
	 * that represent the E field of a set of charges. The equation for an Electric Vector field due to a point charge Q is kQ/r^2 * r(hat).
	 * 
	 */
	
	private void calculateStuff(){
		range = Math.abs(userMax-userMin);
	    stepSize = new BigDecimal(1/steps);
	    System.out.println("Stepsize is: " + stepSize.doubleValue());
		arraySize = new BigDecimal(range/stepSize.doubleValue());
		//System.out.println("Array size is: " + arraySize);
	}
	
	
	/*
	 * built in java sin and cos take angle measurements in radians
	 * These values will be calculated and create each field point as it goes as opposed to the method used to create the 
	 * rectangular grid.
	 * thetaSteps will decide how many field points are on each circle of radius r...
	 */
	private ArrayList<FieldPoint> makeCircleFieldPoints(){
		circle = (2*Math.PI);
		r = 0.0;
		thetaSteps = DefaultValues.getThetaSteps();
		theta = circle/thetaSteps;
		thetaStepSize = circle/thetaSteps;
		//double tracker = 0.0;
		
		circFieldPoints = new ArrayList<FieldPoint>();
		while(r<userMax){
			while(theta<circle){
				x = r*Math.cos(theta);
				y = r*Math.sin(theta);
				circFieldPoints.add(new FieldPoint(x, y, 0));
				theta += thetaStepSize;
			}
			r += stepSize.doubleValue();//increment r
			//tracker += stepSize.doubleValue();
			theta = 0.0; //reset theta to 0.0
		}
		return circFieldPoints;
	}
	
	private void makePointsToCheck(){
		xyPoints = new double[(int)Math.round(arraySize.doubleValue())]; //+1 accounts for 0
		userMin = userMin-stepSize.doubleValue();//makes the first value of the array the min value
		for(int i=0; i<xyPoints.length; i++){
			userMin += stepSize.doubleValue();
			xyPoints[i] = userMin;
		}
	}
	
	
	
	private ArrayList<FieldPoint> makeRectFieldPoints(){
		makePointsToCheck();
		rectFieldPoints = new ArrayList<FieldPoint>();
		//System.out.println("The fieldPoints array is " + fieldPoints.length + ".");
			for(int i=0; i<xyPoints.length; i++)
				for (int j=0; j<xyPoints.length; j++)
					rectFieldPoints.add(new FieldPoint(xyPoints[i],xyPoints[j], 0));
			return rectFieldPoints;
			}	
}