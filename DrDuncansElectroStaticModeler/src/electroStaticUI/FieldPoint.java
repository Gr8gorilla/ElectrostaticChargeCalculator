package electroStaticUI;
import java.util.ArrayList;
import java.util.Iterator;

import javax.vecmath.*;

public class FieldPoint extends Point3d {
	
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
	
	
	/**
	 * class to represent a point in space where electric force and electric potential of a charge distribution can be measured
	 */
	private static final long serialVersionUID = 1L;
	private static int numberOfFieldPoints = 0;
	private int id;
	private ArrayList<ElectricForceVector> vectorsToThisPoint;
	private ElectricForceVector totalVector;
	private double voltageAtThisPoint;
	private double[] xyZasVoltage = {x, y, voltageAtThisPoint}; //order is x coord, y coord, voltageAtThisPoint last as the z value.
	
	public FieldPoint(double x, double y, double z){
		super(x, y, z);
		vectorsToThisPoint = new ArrayList<ElectricForceVector>();
		vectorsToThisPoint.ensureCapacity(DefaultValues.getCurrentPointCharges().length);
		totalVector = new ElectricForceVector();
		id = numberOfFieldPoints++;
	}

	public FieldPoint(){
		super(0,0,0);
		vectorsToThisPoint = new ArrayList<ElectricForceVector>();
		vectorsToThisPoint.ensureCapacity(DefaultValues.getCurrentPointCharges().length);
		totalVector = new ElectricForceVector();
		id = numberOfFieldPoints++;
		}
	
	public void addVectorToArrayList(ElectricForceVector vector){
		vectorsToThisPoint.add(vector);
	}
	
	public ElectricForceVector[] returnVectorList(){//creates and returns a SAFE array of the vectors associated with this point
		ElectricForceVector[] vectorList = new ElectricForceVector[vectorsToThisPoint.size()];
		vectorList = (ElectricForceVector[]) vectorsToThisPoint.toArray();
		return vectorList;
	}
	
	public int getId(){
		return id;
	}
	
	public ArrayList<ElectricForceVector> getVectorsToThisPoint(){
		return vectorsToThisPoint;
	}
	
	public ElectricForceVector getTotalVector(){
		return totalVector;
	}
	
	
	public ElectricForceVector sumVectorsAtThisPoint(){
		Iterator<ElectricForceVector> i = vectorsToThisPoint.iterator();
			if(vectorsToThisPoint.size() > 1){
					while(i.hasNext())
						totalVector.add((ElectricForceVector)i.next());
			}
		
			else if(vectorsToThisPoint.size() == 1) 
				totalVector = (ElectricForceVector) i.next();
			
			else//this should be changed to throwing an exception
				System.out.println("Something broke Jim, there's no Vectors.");
			
			System.out.println("This totalVector is: " + totalVector);
			return totalVector;
	}
	
	

	public double getVoltageAtThisPoint() {
		return voltageAtThisPoint;
	}

	public void setVoltageAtThisPoint(double voltageAtThisPoint) {
		this.voltageAtThisPoint = voltageAtThisPoint;
	}

	public double[] getXyZasVoltage() {
		return xyZasVoltage;
	}
	
	public void setZAsVoltage(double voltage){
		xyZasVoltage[2] = voltage;
	}
}
