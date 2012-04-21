package electroStaticUI;


/*
 * this is a class that holds static values accessed by many other classes within the package...
 */
public class DefaultValues {

	private static double min = (-2.5);
	private static double max = 2.5;
	private static int steps = 5;
	private static int thetaSteps = 36;
	private static char circOrRect = 'r';
	private static double[] scale = { 1.0, .01, .001, .000001, .000000001, .000000000001, .000000000000001 };
	private static int chargeModIndex = 5;
	private static int lengthModIndex = 0;
	private static double chargeMod = scale[chargeModIndex];
	private static double lengthMod = scale[lengthModIndex];
	
	
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
