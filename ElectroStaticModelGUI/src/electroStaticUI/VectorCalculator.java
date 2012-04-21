package electroStaticUI;



/*
 * This class finds the vectors from each charge to each field point. Each of my calculation classes relies on my custom mapper
 * to create it's data.
 */
public class VectorCalculator {

	private ElectricForceVector[][] electricFieldVectors;
	private CustomMapper thisMap;
	private PointCharge[] charges;
	
	
	public VectorCalculator(CustomMapper aMap){
		charges = UserInput.getChargesToCalculate();
		thisMap = aMap;
		electricFieldVectors = new ElectricForceVector[UserInput.getChargesToCalculate().length][aMap.getHowManyFieldPoints()];
		//System.out.println("Size is " + electricFieldVectors.length + " and " + electricFieldVectors[0].length + ".");
		calculateVectors();
		//printVectorArray();
	}
	
	private void calculateVectors(){
		for(int i=0; i<charges.length; i++)
			for(int j=0; j<electricFieldVectors[i].length; j++){//the if statement below makes sure we don't calculate anything outside our boundaries.
				if((charges[i].x <= DefaultValues.getMax()) && (charges[i].x >= DefaultValues.getMin()) && (charges[i].y<= DefaultValues.getMax()) && (charges[i].y>= DefaultValues.getMin()))
				electricFieldVectors[i][j] = new ElectricForceVector(charges[i], thisMap.getFieldPoints().get(j));
				else electricFieldVectors[i][j] = new ElectricForceVector();//if the charge was out of bounds it makes a blank vector there.
			}
	}
	
	
	/*
	 * findTotalVectorAtFieldPoints sums all vectors at each field point.  This did not produce the expected outcome within the DrawElectricFieldLines Class. 
	 * Upon writing the same method within that class, I got the expected results with vectors in the correct 
	 */
	public void findTotalVectorAtFieldPoints(){
		//System.out.println("Vectors in arrayList " + thisMap.getFieldPoints()[0].getVectorsToThisPoint().size()  + ".");
		for(int i=0; i<thisMap.getFieldPoints().size(); i++)
			thisMap.getFieldPoints().get(i).sumVectorsAtThisPoint();
	}
	
	@SuppressWarnings("unused")
	private void printVectorArray(){
		for(int i=0; i<electricFieldVectors.length; i++){
			System.out.println(electricFieldVectors[i].toString());
		}
	}
	
	
}
