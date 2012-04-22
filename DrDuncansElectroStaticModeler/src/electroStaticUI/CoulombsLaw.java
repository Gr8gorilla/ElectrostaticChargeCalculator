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



public class CoulombsLaw {

	public static final Double k = (8.99 * Math.pow(10, 9)); //electrostatic constant K, Nm^2/C^2
	private static Double f;//scalar quantity of Newtons
	private static double scaleDistance = DefaultValues.getLengthMod();
	
	/*
	 * should be only place charge is calculated, only use of k variable in regards to force calculations,
	 * this classes methods use the scaleDistance variable on its distances...
	 */
	public static Double findForceq1Onq2(PointCharge q1, PointCharge q2){
		if (q1.distance(q2) > .20)//gets rid of noise and NAN's
		f = ((k*q1.absValQ1Q2(q2))/(Math.pow((q1.distance(q2)*scaleDistance), 2)));//returns the scalar quantity in Newtons
		else f = 0.0;
		return f;
	}
	
	public static Double findForceq1Onp1(PointCharge q1, FieldPoint p1){//force at a field point
		if(q1.distance(p1) > .20)//gets rid of noise and NAN's
		f = (k*q1.getCharge())/(Math.pow((q1.distance(p1)*scaleDistance), 2));//returns the scalar quantity in Newtons
		else f = 0.0;
		return f;
	}
	
	
}
