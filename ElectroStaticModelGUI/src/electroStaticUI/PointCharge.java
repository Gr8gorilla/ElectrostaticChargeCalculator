package electroStaticUI;
import java.util.ArrayList;
import java.util.Scanner;

public class PointCharge extends javax.vecmath.Point3d {

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
	 * note that the values of charges are normally very small, for example the value of a single proton is 1.6x10^(-19) coulombs. An electron has a value oppositve of this
	 */
	private static final long serialVersionUID = 1L;
	private Double charge; //value in Coulombs 
	public static final Double e = (1.6*Math.pow(10, -19)); //Value of the fundamental unit of Charge
	private double resultOfQ1Q2;
	private ArrayList<Integer> vectorsFromThisCharge = new ArrayList<Integer>();
	private static int numberOfPointCharges = 0;
	private int id; //will be the index value of this charge;
	private static int numberOfCharges;
	private static double proton = 1.6*Math.pow(10, -19);
	private static double electron = -1.6*Math.pow(10, -19);
	private double volHere = 0;
	static Scanner kb = new Scanner(System.in);
	private double chargeModifier = DefaultValues.getChargeMod();
	
	
	/*
	 * constructors, follow the convention of Tuple3d
	 */
	public PointCharge(PointCharge aCharge){//constructor that clones another charge
		this.x = aCharge.x;
		this.y = aCharge.y;
		this.z = aCharge.z;
		this.charge = aCharge.charge;
		this.id = aCharge.id;
		this.vectorsFromThisCharge = aCharge.vectorsFromThisCharge;
	}
	
	public PointCharge(){
		super(0,0,0);
		charge = 0.0;
		id = numberOfPointCharges++;
	}
	
	public PointCharge(Double theCharge){//charge at 0,0,0
		super(0,0,0);
	charge = chargeModifier*theCharge;
	id = numberOfPointCharges++;
	}
	
	public PointCharge(Double theCharge, double x){//sets the charge and the x coordinate
		super(x, 0, 0);
		charge = chargeModifier*theCharge;
		id = numberOfPointCharges++;
	}
	
	public PointCharge(Double theCharge, double x, double y){//sets the x and y variables, leaves z at 0
		super(x,y,0);
		charge = chargeModifier*theCharge;
		id = numberOfPointCharges++;
	}
	
	public PointCharge(Double theCharge, double x, double y, double z){//sets all three variables
		super(x,y,z);
		charge = chargeModifier*theCharge;
		id = numberOfPointCharges++;
	}
	/*
	 * end constructors
	 */
	
	
	public double voltageFromThisCharge(FieldPoint fPoint){
		
		/*
		 * this method calculates voltage based on distance and is using the length mod...
		 */
		
		volHere = (this.charge/(this.distance(fPoint)*DefaultValues.getLengthMod()));
		//System.out.println("This Charges Voltage is " + volHere + ".");
		return volHere;
	}
	
	public static int getNumberOfCharges(){
		return numberOfCharges;
	}
	
	public static int getHowManyCharges(){
		System.out.println("How many charges would you like to caclulate force for?");//Attempt to allocate the array size during program runtime....may not work.
	    numberOfCharges = kb.nextInt();
		System.out.println(numberOfCharges + " charges");
		return numberOfCharges;
	}
	
	public void addVectorToArrayList(ElectricForceVector vectorFromThisCharge){//keeps track of vectors associated with this charge
		vectorsFromThisCharge.add(vectorFromThisCharge.getId());
	}
	
	public ElectricForceVector[] returnVectorList(){//creates and returns a SAFE array of the vectors associated with this point
		ElectricForceVector[] vectorList = new ElectricForceVector[vectorsFromThisCharge.size()];
		vectorList = (ElectricForceVector[]) vectorsFromThisCharge.toArray();
		return vectorList;
	}
	
	public String toString(){
		return "X: " + x + " Y: "+ y + " Z: " + z + " Charge: " + charge;
	}
	
	/*
	 * calculate one charge (Q*Q) for force calculations, only used for one charge and another, not used in field point calculations....
	 */
	public double absValQ1Q2(PointCharge q2){//calculates the absval of this charge times another charge
		resultOfQ1Q2 = (Math.abs(charge)*Math.abs(q2.getCharge()));
		return resultOfQ1Q2;
	}
	
	/*
	 * getters and setters
	 */
	
	public int getTotalCharges(){
		return numberOfPointCharges;
	}
	
	public int getId(){
		return id;
	}
	
	public Double getCharge() {
		return charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

	public static double getProton() {
		return proton;
	}

	public static double getElectron() {
		return electron;
	}
}
