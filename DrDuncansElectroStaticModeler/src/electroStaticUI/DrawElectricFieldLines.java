package electroStaticUI;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;

import org.eclipse.swt.graphics.GC;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.VectorRenderer;
import org.jfree.data.xy.VectorSeries;
import org.jfree.data.xy.VectorSeriesCollection;
import org.jfree.data.xy.VectorXYDataset;
import org.jfree.experimental.swt.SWTGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

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



public class DrawElectricFieldLines {

	private VectorSeries eFieldVectors;
	private CustomMapper mapper;
	private JFreeChart eFieldChart;
	private VectorSeriesCollection dataSet;
	private XYPlot graph;
	private VectorRenderer renderer;
	private ChartPanel graphChart;
	
	/*
	 * Constructor
	 */
	public DrawElectricFieldLines(CustomMapper cMapper){
		mapper = cMapper;
		sumTotalVector();
		createDataset();
		renderer = new VectorRenderer();
		graph = new XYPlot(dataSet, new NumberAxis("Axis X"), new NumberAxis("Axis Y"), renderer);
		 eFieldChart = new JFreeChart(graph);
	     eFieldChart.setTitle("Electric Field Vectors");
	     graphChart = new ChartPanel(eFieldChart);
	     graphChart.setVisible(true);
	}
	
	
	/*
	 * getters, no need for setters
	 */
	public ChartPanel getVectorPlot(){
		return graphChart;
	}
	
	
	public JFreeChart getChart() { 
        return eFieldChart;
    }
	
	public ChartPanel getChartPanel(){
		return graphChart;
	}
	
	/*
	 * when we got here, our total vectors field was empty, so our method in VectorCalculator was basically duplicated and now 
	 * our totalVector field in FieldPoint class was not null.
	 */
	private void sumTotalVector(){
		Iterator<FieldPoint> itr = mapper.getFieldPoints().iterator();
		while(itr.hasNext())
			itr.next().sumVectorsAtThisPoint();
	}
	
	/*
	 * creates a data set for rendering vectors
	 */
	private VectorXYDataset createDataset() {
		eFieldVectors = new VectorSeries("Electric Field");
		for(int i=0; i<mapper.getFieldPoints().size(); i++){
			eFieldVectors.add(mapper.getFieldPoints().get(i).x, mapper.getFieldPoints().get(i).y,mapper.getFieldPoints().get(i).getTotalVector().x, mapper.getFieldPoints().get(i).getTotalVector().y);
			//System.out.println(eFieldVectors.toString());
		}
		dataSet = new VectorSeriesCollection();
		dataSet.addSeries(eFieldVectors);
		return dataSet;
	}
	
}
