package electroStaticUI;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jzy3d.chart.Chart;
import org.jzy3d.chart.controllers.ControllerType;
import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.builder.Builder;
import org.jzy3d.plot3d.builder.Mapper;
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid;
import org.jzy3d.plot3d.primitives.Shape;

public class GraphIt{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4263308775879540994L;
	//private JPanel chartPanel = new JPanel();
	private Chart mainChart = new Chart("swing");
	//default values for generating a chart
	private static double d1 = -10; //lower bound
	private static double d2 = 10; //upper bound
	private static int stepChoice = 50;
	//will display a chart from jzy3d
	public GraphIt(){
	mainChart = makeChart();
	}


	
	public static Chart makeChart(){
	JOptionPane message = new JOptionPane();
	message.showMessageDialog(null, "Your graph is being generated. This may take several seconds.");
		//mapper maps points of a function
		Mapper mapper = new Mapper(){
			public double f(double x, double y){
				return FunctionBuilder.electricPotentialFunction(x, y);
			}
		};
		
		//range and precision for the function to plot
		Range range = new Range(d1, d2);//will need to be changed to a user settable variable
		int steps = stepChoice;//will need to be changed to a user settable variable
		
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
		
	public static void setGraphRange(double _d1, double _d2){
		d1 = _d1;
		d2 = _d2;
	}
	
	public static void setGraphSteps(int stepSize){
		stepChoice = stepSize;
	}
	
	public static int getSteps(){
		return stepChoice;
	}
	
	public static double getLowerBound(){
		return d1;
	}
	
	public static double getUpperBound(){
		return d2;
	}
	
	public void displayFieldLines(){
		
	}
	
	public void dislplayPotential(){
		
	}
	
	public void graph2d(){
		
	}
	
	public void graph3d(){
		
	}
	
	//public JPanel getChartPanel(){
		//return chartPanel;
	//}
		
	
}
