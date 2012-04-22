package electroStaticUI;
import java.util.ArrayList;

import javax.vecmath.*;

public class ElectricForceVector extends Vector3d {

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
	
	
	private static final long serialVersionUID = 1L;
	private PointCharge q1;
	private PointCharge q2;
	private FieldPoint p1;
	private double electricForce; //in newtons
	private static int numberOfElectricForceVectors = 0;
	private int id;
	private ArrayList<PointCharge> pointChargesOfThisVector = new ArrayList<PointCharge>();
	private ArrayList<FieldPoint> fieldPointsOfThisVector = new ArrayList<FieldPoint>();
	
	
	public ElectricForceVector(PointCharge q1, PointCharge q2){//force of one charge on another
		this.q1 = q1;
		this.q2 = q2;
		p1 = null;
		this.sub(q2, q1); //not sure why I am subtracting here, ElectrticForceVector extends Vector3d so this shouldn't be necessary...
		electricForce = CoulombsLaw.findForceq1Onq2(q1, q2);
		this.normalize();
		this.scale(electricForce);//the constructor normalizes the vector and then scales it based on the electric force.
		id = numberOfElectricForceVectors++;
		pointChargesOfThisVector.add(q1);
		pointChargesOfThisVector.add(q2);
		q1.addVectorToArrayList(this);
		q2.addVectorToArrayList(this);
	}
	
	public ElectricForceVector(PointCharge q1, FieldPoint p1){//force of a charge at a point
		this.q1 = q1;
		this.p1 = p1;
		q2=null;
		this.sub(p1, q1);
		electricForce = CoulombsLaw.findForceq1Onp1(q1, p1);
		this.normalize();
		this.scale(electricForce);//the constructor normalizes the vector and then scales it based on the electric force.
		id = numberOfElectricForceVectors;
		numberOfElectricForceVectors++;
		q1.addVectorToArrayList(this);
		p1.addVectorToArrayList(this);
	}
	
	public ElectricForceVector(){
		q1 = null; 
		q2 = null;
		p1 = null;
		electricForce = 0;
		id = numberOfElectricForceVectors;
		numberOfElectricForceVectors++;
	}
	
	
	public void add(ElectricForceVector vector){
		this.x += vector.x;
		this.y += vector.y;
		this.z += vector.z;
		
	}
	
	
	
	public void set(ElectricForceVector toCopy){//sets this vector equal to the vector toCopy.
		this.q1 = toCopy.getPointQ1();
		this.q2 = toCopy.getPointQ2();
		this.p1 = toCopy.getPointP1();
		this.electricForce = toCopy.getElectricForce();
		this.x = toCopy.x;
		this.y = toCopy.y;
		this.z = toCopy.z;
		this.pointChargesOfThisVector = toCopy.pointChargesOfThisVector;
		this.fieldPointsOfThisVector = toCopy.fieldPointsOfThisVector;
	}
	
	public void set(double x, double y, double z, double electricForce, PointCharge q1, FieldPoint p1){//sets this vectors values equal to those given
		this.q1 = q1;
		this.p1 = p1;
		this.x = x; 
		this.y = y; 
		this.z = z;
		this.electricForce = electricForce;
		q1.addVectorToArrayList(this);
		p1.addVectorToArrayList(this);
		this.pointChargesOfThisVector.add(q1);
		this.fieldPointsOfThisVector.add(p1);
	}
	
	public void set(double x, double y, double z, double electricForce, PointCharge q1, PointCharge q2){//sets this vectors values equal to those given
		this.q1 = q1; 
		this.q2 = q2;
		this.x = x; 
		this.y = y; 
		this.z = z;
		this.electricForce = electricForce;
		q1.addVectorToArrayList(this);
		q2.addVectorToArrayList(this);
		this.pointChargesOfThisVector.add(q1);
		this.pointChargesOfThisVector.add(q2);
	}
	
	public void set(PointCharge q1, FieldPoint p1){//sets this vectors values equal to those given
		this.q1 = q1;
		this.p1 = p1;
		this.sub(q2, q1);
		this.electricForce = CoulombsLaw.findForceq1Onp1(q1, p1);
		q1.addVectorToArrayList(this);
		p1.addVectorToArrayList(this);
		this.pointChargesOfThisVector.add(q1);
		this.fieldPointsOfThisVector.add(p1);
	}
	
	public void set(PointCharge q1, PointCharge q2){//sets this vectors values equal to those given
		this.q1 = q1;
		this.q2 = q2;
		this.sub(p1, q1);
		this.electricForce = CoulombsLaw.findForceq1Onq2(q1, q2);
		q1.addVectorToArrayList(this);
		q2.addVectorToArrayList(this);
		this.pointChargesOfThisVector.add(q1);
		this.pointChargesOfThisVector.add(q2);
	}
	
	public PointCharge[] returnPointChargeList(){ //creates and returns a new safe array of the pointcharges 
		PointCharge[] listToArray = new PointCharge[this.pointChargesOfThisVector.size()];
		listToArray = (PointCharge[]) this.pointChargesOfThisVector.toArray();
		return listToArray;
	}
	
	public FieldPoint[] returnFieldPointList(){//creates a new safe array of the field points
		FieldPoint[] listToArray = new FieldPoint[this.pointChargesOfThisVector.size()];
		listToArray = (FieldPoint[]) this.pointChargesOfThisVector.toArray();
		return listToArray;
	}
	
	
	public int getId(){
		return id;
	}
	
	public String toString(){
		if(DefaultValues.getInput3d())
		return "Magnitude: " + this.length() + " N. \n <" + x + ", " + y + ", " + z + ">\n";
		else return "Magnitude: " + this.length() + " N. \n <" + x + ", " + y + ">\n";
	}


	public PointCharge getPointQ1(){
		return q1;
	}

	public PointCharge getPointQ2(){
		return q2;
	}
	
	public double getElectricForce() {
		return electricForce;
	}

	public FieldPoint getPointP1(){
		return p1;
	}
			
		
	
	
}
