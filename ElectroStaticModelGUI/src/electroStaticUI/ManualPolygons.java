package electroStaticUI;

import java.util.ArrayList;
import java.util.Iterator;
import org.jzy3d.chart.Chart;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.plot3d.builder.delaunay.Triangulation;
import org.jzy3d.plot3d.builder.delaunay.jdt.Delaunay_Triangulation;
import org.jzy3d.plot3d.builder.delaunay.jdt.Point_dt;
import org.jzy3d.plot3d.builder.delaunay.jdt.Triangle_dt;
import org.jzy3d.plot3d.primitives.Point;
import org.jzy3d.plot3d.primitives.Polygon;
import org.jzy3d.plot3d.primitives.Shape;
import org.jzy3d.plot3d.rendering.canvas.Quality;
import org.jzy3d.plot3d.rendering.legends.colorbars.ColorbarLegend;

public class ManualPolygons {

	/*
	 * Copyright 2012 Shaun Sharpton
	 * 
	 * This file is part of "Dr Duncan's Electrostatic Charge modeler"!
	 * 
	 * 
	 *   "Dr Duncan's Electrostatic Charge modeler" is free software: you can redistribute it and/or modify
	 *   it under the terms of the GNU General Public License as published by
	 *   the Free Software Foundation, either version 3 of the License, or
	 *   (at your option) any later version.
	 *   
	 *   "Dr Duncan's Electrostatic Charge modeler" is distributed in the hope that it will be useful,
	 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
	 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	 *   GNU General Public License for more details.
	 *   
	 *   You should have received a copy of the GNU General Public License
	 *   along with "Dr Duncan's Electrostatic Charge modeler".  If not, see <http://www.gnu.org/licenses/>.
	 */
	
	private Chart chart;
	private double[][] dataPoints;
	private ArrayList <Polygon> polygons;
	private CustomMapper bMap;
	private Shape surface;
	private Triangulation triangulation;

	
	public ManualPolygons(CustomMapper cMap){
		bMap = cMap;
		makeDataArray();
		//System.out.println("There are " + coordList.size() + " 3d Coordinates in the list.");
	}

	
	
	public Chart delaunayBuild(){

		chart = new Chart(Quality.Advanced);
	    surface = new Shape();
	    chart.getScene().getGraph().add(surface);
	    surface.setLegend(new ColorbarLegend(surface, chart.getView().getAxe().getLayout()));

	    triangulation = new Delaunay_Triangulation();
	    for(int i=0; i<dataPoints.length; i++)
	    	triangulation.insertPoint(new Point_dt(dataPoints[i][0], dataPoints[i][1], dataPoints[i][2]));
	    polygons = new ArrayList<Polygon>(triangulation.trianglesSize());
	    Iterator<Triangle_dt> trianglesIter;
	    trianglesIter = triangulation.trianglesIterator();
	    
	    while(trianglesIter.hasNext()){
	    	Triangle_dt triangle = trianglesIter.next();
	    	if(triangle.isHalfplane()){
	    		continue;
	    	}
	    	Coord3d c1 = triangle.p1().getAsCoord3d();
	        Coord3d c2 = triangle.p2().getAsCoord3d();
	        Coord3d c3 = triangle.p3().getAsCoord3d();

	        Polygon polygon = new Polygon();
	        polygon.add(new Point(c1));
	        polygon.add(new Point(c2));
	        polygon.add(new Point(c3));

	        polygons.add(polygon);
	    	
	        Shape surface = new Shape(polygons);
	        surface.setColorMapper(new ColorMapper(new ColorMapRainbow(), surface.getBounds().getZmin(), surface.getBounds().getZmax(), new org.jzy3d.colors.Color(1,1,1,1f)));
	        surface.setWireframeDisplayed(true);
	        surface.setWireframeColor(org.jzy3d.colors.Color.BLACK);
	        surface.setLegendDisplayed(false);
	        
	        chart = new Chart("swing");
		    chart.getScene().getGraph().add(surface);
	    }
	    return chart;
	}
	
	    private void makeDataArray(){
			dataPoints = new double[bMap.getFieldPoints().size()][3];
			for(int i=0; i<dataPoints.length; i++){
				for(int j=0; j<dataPoints[i].length; j++){
					dataPoints[i][j] = bMap.getFieldPoints().get(i).getXyZasVoltage()[j];
				}
			}
		}
}
