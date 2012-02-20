package partsOfAField;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import org.jzy3d.chart.Chart;
import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.builder.Builder;
import org.jzy3d.plot3d.builder.Mapper;
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid;
import org.jzy3d.plot3d.primitives.Shape;

public class ElectricPotential {
	
	
	
	public static final Double k = (8.99 * Math.pow(10, 9)); //electrostatic constant K, Nm^2/C^2
	private PointCharge[] pointCharges;
	
	
	public ElectricPotential(PointCharge[] pointCharges){
		this.pointCharges = pointCharges;
		
	}
	
	
	
	public static double electricPotentialOfChargeAtPoint(PointCharge q, FieldPoint p){
		double electricPotential = ((k*q.getCharge())/(q.distance(p)));
		return electricPotential;
	}
	
	private Chart makeTestChart(){
		//mapper maps points of a function
		Mapper mapper = new Mapper(){
			public double f(double x, double y){
				return x+y;
			}
		};
		
		//range and precision for the function to plot
		Range range = new Range(-150, 150);
		int steps = 50;
		
		//create object to represent the function over this range
		Shape surface = (Shape) Builder.buildOrthonormal(new OrthonormalGrid(range, steps, range, steps), mapper);
		surface.setColorMapper(new ColorMapper(new ColorMapRainbow(), surface.getBounds().getZmin(), surface.getBounds().getZmax(), new Color(1, 1, 1, .5f)));
        surface.setWireframeDisplayed(true);
        surface.setWireframeColor(Color.BLACK);
        //surface.setFace(new ColorbarFace(surface));
        surface.setFaceDisplayed(true);
        //surface.setFace2dDisplayed(true); // opens a colorbar on the right part of the display

        // Create a chart
        Chart chart = new Chart("swing");
        chart.getScene().getGraph().add(surface);
        return chart;
	}
	
	
	
	
	
	
	/*public static double electricPotentialOfManyPoints(PointCharge[] qS, FieldPoint p){
	double electricPotential = 0;
	for(int i=0; i<qS.length; i++)
		electricPotential += ((k*qS[i].getCharge())/qS[i].distance(p));
	return electricPotential; 
	}
	*/
}
