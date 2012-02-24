package electroStaticModel;
import java.util.ArrayList;

import javax.vecmath.*;

public class FieldPoint extends Point3d {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int numberOfFieldPoints = 0;
	private int id;
	private ArrayList<ElectricForceVector> vectorsToThisPoint = new ArrayList<ElectricForceVector>();
	
	
	public FieldPoint(double x, double y, double z){
		super(x, y, z);
		id = numberOfFieldPoints;
		numberOfFieldPoints++;
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
	
	
}
