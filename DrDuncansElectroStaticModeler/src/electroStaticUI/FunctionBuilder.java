package electroStaticUI;

import electroStaticModel.PointCharge;

public class FunctionBuilder {
	
	private static final Double k = (8.99 * Math.pow(10, 9)); //electrostatic constant K, Nm^2/C^2
	private static PointCharge[] userCharges;
	private static Object sumFunction[];
	private static double electricPotential;
	private static double[] chargeModifier = {1.0, Math.pow(10, -2), Math.pow(10, -3), Math.pow(10, -6), Math.pow(10, -9), Math.pow(10, -12), Math.pow(10, -15)};
	private static int chargeModifierIndex = 5;

	public static double electricPotentialFunction(double x, double y){
		userCharges = new PointCharge[UserInput.getChargesToCalculate().length];
		for(int i=0; i<userCharges.length; i++)
			userCharges[i] = UserInput.getChargesToCalculate()[i];
		sumFunction = new Object[userCharges.length];
		for(int i=0; i<userCharges.length; i++){
			sumFunction[i] = new Object();
			sumFunction[i] = (userCharges[i].getCharge()*chargeModifier[chargeModifierIndex])/(Math.sqrt(Math.pow((x-userCharges[i].x), 2) + Math.pow(y-userCharges[i].y, 2)));
			//sumFunction[i] = ((k*(userCharges[i].getCharge()*x)/(Math.sqrt((Math.pow((x-userCharges[i].x), 2) + Math.pow((y-userCharges[i].y), 2))))) + (k*(userCharges[i].getCharge()*y)/(Math.sqrt((Math.pow(x-(userCharges[i].x), 2) + Math.pow((y-userCharges[i].y), 2))))));
		}
		
		for(int j=0; j<sumFunction.length; j++){
			electricPotential += k*(double)(sumFunction[j]);
		}
		
		return electricPotential;
	}
	
	public static void setChargeModifierIndex(int index){
		chargeModifierIndex = index;
	}
	
	public double getSummedChargesValue(){
		return electricPotential;
	}
}
