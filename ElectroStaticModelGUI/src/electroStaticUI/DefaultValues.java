package electroStaticUI;

import org.jfree.chart.JFreeChart;

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
 * this is a class that holds static values accessed by many other classes within the package...
 */
public class DefaultValues {

	private static double min = (-2.5);
	private static double max = 2.5;
	private static int steps = 5;
	private static int thetaSteps = 36;
	private static char circOrRect = 'r'; //not useful yet, polar looks weird.....
	private static double[] scale = { 1.0, .01, .001, .000001, .000000001, .000000000001, .000000000000001 };
	private static int chargeModIndex = 5;
	private static int lengthModIndex = 0;
	private static double chargeMod = scale[chargeModIndex];
	private static double lengthMod = scale[lengthModIndex];
	private static boolean input3d = false;//should be set with a menu
	private static PointCharge[] currentPointCharges;
	private static JFreeChart chartToSave;
	
	
	public static void setChartToSave(JFreeChart currentChart){
		chartToSave = currentChart;
	}
	
	public static JFreeChart getChartToSave(){
		return chartToSave;
	}
	
	public static void allocatePointChargeArray(int size){
		currentPointCharges = new PointCharge[size];
	}
	
	public static void setCurrentPointCharges(PointCharge[] pointCharges){
		currentPointCharges = pointCharges;
	}
	
	public static PointCharge[] getCurrentPointCharges(){
		return currentPointCharges;
	}
	
	
	public static void setInput3d(boolean dimension){
		input3d = dimension;
	}
	
	public static boolean getInput3d(){
		return input3d;
	}
	
	public static void setLengthModIndex(int setLengthMod){
		lengthModIndex = setLengthMod;
	}
	
	public static double getLengthMod(){
		return lengthMod;
	}
	
	public static void setChargeModIndex(int newChargeModIndex){
		chargeModIndex = newChargeModIndex;
	}
	
	public static double getChargeMod(){
		return chargeMod;
	}
	
	public static void setCircOrRect(char setCOrR){
		circOrRect = setCOrR;
	}
	
	public static char getCircOrRect(){
		return circOrRect;
	}
	
	public static int getThetaSteps(){
		return thetaSteps;
	}
	
	public static void setThetaSteps(int changeThetaSteps){
		thetaSteps = changeThetaSteps;
	}
	
	public static void setMin(double uMin){
		min = uMin;
	}
	
	public static double getMin(){
		return min;
	}
	
	public static void setMax(double uMax){
		max = uMax;
	}
	
	public static double getMax(){
		return max;
	}
	
	public static void setSteps(int uSteps){
		steps = uSteps;
	}
	
	public static int getSteps(){
		return steps;
	}
}
