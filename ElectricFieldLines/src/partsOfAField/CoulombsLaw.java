package partsOfAField;

import javax.vecmath.Point3d;

public class CoulombsLaw {

	public static final Double k = (8.99 * Math.pow(10, 9)); //electrostatic constant K, Nm^2/C^2
	//x, y, z, should be input in meters from origin
	private static Double f;//scalar quantity of Newtons
	
	public static Double findForceq1Onq2(PointCharge q1, PointCharge q2){
		f = (k*q1.absValQ1Q2(q2))/(q1.distanceSquared(q2));//returns the scalar quantity in Newtons
		return f;
	}
	
	public static Double findForceq1Onp1(PointCharge q1, FieldPoint p1){//force at a field point
		f = (k*q1.getCharge())/(q1.distanceSquared(p1));//returns the scalar quantity in Newtons
		return f;
	}
}
