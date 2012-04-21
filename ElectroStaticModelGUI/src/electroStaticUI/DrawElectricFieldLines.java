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
	
	/**
	 * Exports a JFreeChart to a SVG file.
	 * 
	 * @param chart JFreeChart to export
	 * @param bounds the dimensions of the viewport
	 * @param svgFile the output file.
	 * @throws IOException if writing the svgFile fails.
	 */
	

}
