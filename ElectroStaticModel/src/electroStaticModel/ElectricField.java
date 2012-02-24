package electroStaticModel;

import java.util.Scanner;
import javax.vecmath.*;

import org.jzy3d.chart.Chart;
import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.builder.Builder;
import org.jzy3d.plot3d.builder.Mapper;
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid;
import org.jzy3d.plot3d.primitives.Shape;


public class ElectricField {
	
	private int chargesInField;
	private PointCharge[] chargesThatMakeThisField;	
	private double kQR;
	private double xPosition;
	private double yPosition;
	private double q;
	private static final Double k = (8.99 * Math.pow(10, 9)); //electrostatic constant K, Nm^2/C^2
	
	public ElectricField(PointCharge[] chargesToMakeThisField){	
		this.chargesInField = chargesInField;
		chargesThatMakeThisField = chargesToMakeThisField;
	}
	
		public Chart makeChart(){
		//mapper maps points of a function
		Mapper mapper = new Mapper(){
			public double f(double x, double y){
				return ((k*q*(xPosition + yPosition))/(Math.sqrt(Math.pow((Math.pow(x-xPosition, 2)+Math.pow(y-yPosition, 2)), 3))));
			}
		};
		
		//range and precision for the function to plot
		Range range = new Range(-150, 150);
		int steps = 50;
		
		//create object to represent the function over this range
		Shape surface = (Shape)Builder.buildOrthonormal(new OrthonormalGrid(range, steps, range, steps), mapper);
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
}