package electroStaticUI;

public class CoulombsLaw {

	public static final Double k = (8.99 * Math.pow(10, 9)); //electrostatic constant K, Nm^2/C^2
	private static Double f;//scalar quantity of Newtons
	private static double scaleDistance = DefaultValues.getLengthMod();
	
	
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
