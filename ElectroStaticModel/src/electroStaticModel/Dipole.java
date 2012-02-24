package electroStaticModel;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

public class Dipole {
	private PointCharge qNegative;
	private PointCharge qPositive;
	private double chargeOfDipole;
	private Point3d center;
	private Vector3d roe;
	private double dipolePotential=0;
	private double graph;
	private double chargeValue;
	public static final double k = (8.99*Math.pow(10, 9));
	
	
	public Dipole(PointCharge qP, PointCharge qN, FieldPoint p){
		
		double q = qP.getCharge();//charge of the dipole
		ElectricForceVector roe = new ElectricForceVector(); //vector from negative to positive represents the direction of the field from the dipole
		Vector3d r1 = new Vector3d(); //vector from charge qP to field point p
		Vector3d r2 = new Vector3d(); //vector from charge qN to field point p
		Vector3d r = new Vector3d(); //vector from the center of the the two charges to the field point
		Point3d c = new Point3d(); //center of dipole
		c.setX((qP.getX()-qN.getX())/2);
		c.setY((qP.getY()-qN.getY())/2);
		c.setZ((qP.getZ()-qN.getZ())/2);
		roe.sub(qP, qN);
		r1.sub(p, qP);
		r1.sub(p, qN);
		double l = roe.length();
		dipolePotential = (k*q*((qN.getCharge()/(qN.distance(p)) + (qP.getCharge()/qP.distance(p)))));
	}
	
	public Dipole(PointCharge qP, PointCharge qN){
		ElectricForceVector roe = new ElectricForceVector(); //vector from negative to positive represents the direction of the field from the dipole
		roe.sub(qP, qN);
		double l = roe.length();
	}
	
	public Dipole(){
	}
	public double getCharge(){
		return qNegative.getCharge();
		 
	}
		
	public static double getk(){
		return k;
	}
	
	public double electricPotential(double x, double y){
	graph = ((k*qNegative.getCharge()*getRoe().length()*x)/(Math.pow((Math.pow(x, 2) + Math.pow(y, 2)), 3)));
		return graph;
	}

	public Vector3d getRoe() {
		return roe;
	}

	public void setRoe(Vector3d roe) {
		this.roe = roe;
	}

}
