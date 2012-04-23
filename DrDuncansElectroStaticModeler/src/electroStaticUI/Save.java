package electroStaticUI;

import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.io.*;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.commons.io.IOUtils;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jzy3d.chart.Chart;
import org.jzy3d.io.FileImage;
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


public class Save {
	
	/**
     * Save chart as SVG file.
     * Required libs: Apache Batik (batik-svggen.jar, batik-dom.jar, dom.jar).
     *
     * @param chart JFreeChart to save.
     * @param fileName Name of file to save chart in.
     * @param width Width of chart graphic.
     * @param height Height of chart graphic.
     * @return Final file name used.
     * @throws IOException if failed.
     * 
     * To Do: Add a save/open method for saving a file native to this application. It should save the users current charge distribution 
     * to be re-loaded at a later time.
     */
	
	
    static public final String saveChartToSVG(final JFreeChart chart, String fileName, final int width, final int height) throws IOException {
        String result = null;
       
        if (chart != null) {
            if (fileName == null) {
                final String chartTitle = chart.getTitle().getText();
                if (chartTitle != null) {
                    fileName = chartTitle;
                } else {
                    fileName = "chart";
                }
            }
            result = fileName + ".svg";
           
            final DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
            final Document document = domImpl.createDocument(null, "svg", null);
            final SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
           
//            svgGenerator.getGeneratorContext().setEmbeddedFontsOn(true); //embed fonts
           
            //set anti-aliasing bug fix for SVG generator:
            //setting rendering hints of svgGenerator doesn't help as it seems to use the rendering hints from the chart
            final boolean antiAlias = chart.getAntiAlias();
            final RenderingHints rh = chart.getRenderingHints();

           if (antiAlias) {
               rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
               rh.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF); //fix
            } else {
                rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
                rh.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); //fix
            }

//            svgGenerator.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            svgGenerator.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
//            svgGenerator.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//            svgGenerator.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
//            svgGenerator.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//            svgGenerator.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
           
            chart.draw(svgGenerator, new Rectangle2D.Double(0, 0, width, height), new ChartRenderingInfo());
           
            //undo anti-aliasing bugfix settings for chart to use correct settings:
            if (antiAlias) {
               rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                rh.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            } else {
                rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
                rh.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
            }           
           
            final boolean useCSS = true;
            Writer out = null;
            try {
                out = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(new File(result), false)),"iso-8859-1"); //UTF-8
                svgGenerator.stream(out, useCSS);
            } finally {
                svgGenerator.dispose();
                IOUtils.closeQuietly(out);
            }
        }//else: input unavailable
       
        return result;
    }//saveChartToSVG()

    
    public static final String saveChart3dToPNG(final Chart chart, String fileName){
    	String result = null;
    	
    	
    	//give chart a default name if user doesn't enter one
    	if (chart != null) 
            if (fileName == null) 
                fileName = "3DChart";
                
            result = fileName + ".png";
    	
    	
    	try {
			FileImage.savePNG(chart.screenshot(), result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return result;
    }
    
}
