package electroStaticUI;
import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JFrame;
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
	
	
	//will display a chart from jzy3d
	public GraphIt(){
	mainChart = makeTestChart();
	//chartPanel.setLayout(new java.awt.BorderLayout());
	//chartPanel.add((JComponent)chart.getCanvas());
	//chartPanel.setVisible(true);
	}


	
	public static Chart makeTestChart(){
		//mapper maps points of a function
		Mapper mapper = new Mapper(){
			public double f(double x, double y){
				return ((-3)*x)/(Math.pow(x, 2) + Math.pow(y, 2));
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
