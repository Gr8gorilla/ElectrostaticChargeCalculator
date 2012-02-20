package partsOfAField;
import java.util.ArrayList;
import java.util.Scanner;

import javax.vecmath.*;
public class PointCharge extends Point3d {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double charge; //value in Coulombs
	public static final Double e = (1.6*Math.pow(10, -19)); //Value of the fundamental unit of Charge
	private double resultOfQ1Q2;
	private ArrayList<ElectricForceVector> vectorsFromThisCharge = new ArrayList<ElectricForceVector>();
	private static int numberOfPointCharges = 0;
	private int id; //will be the index value of this charge;
	private static int numberOfCharges;
	static Scanner kb = new Scanner(System.in);
	
	
	public PointCharge(Double theCharge){//charge at 0,0,0
		super();
	charge = theCharge;
	id = numberOfPointCharges++;
	}
	
	public PointCharge(Double theCharge, double x){//sets the charge and the x coordinate
		super(x, 0, 0);
		charge = theCharge;
		id = numberOfPointCharges++;
	}
	
	public PointCharge(Double theCharge, double x, double y){//sets the x and y variables, leaves z at 0
		super(x, y ,0);
		charge = theCharge;
		id = numberOfPointCharges++;
	}
	
	public PointCharge(Double theCharge, double x, double y, double z){//sets all three variables
		super(x, y, z);
		charge = theCharge;
		id = numberOfPointCharges++;
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
		vectorsFromThisCharge.add(vectorFromThisCharge);
	}
	
	public ElectricForceVector[] returnVectorList(){//creates and returns a SAFE array of the vectors associated with this point
		ElectricForceVector[] vectorList = new ElectricForceVector[vectorsFromThisCharge.size()];
		vectorList = (ElectricForceVector[]) vectorsFromThisCharge.toArray();
		return vectorList;
	}
	
	public String toString(){
		return "X: " + x + "Y: "+ y + "Z: " + z + "Charge: " + charge;
	}
	
	public double absValQ1Q2(PointCharge q2){//calculates the absval of this charge times another charge
		resultOfQ1Q2 = (Math.abs(charge)*Math.abs(q2.getCharge()));
		return resultOfQ1Q2;
	}
	
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
}
