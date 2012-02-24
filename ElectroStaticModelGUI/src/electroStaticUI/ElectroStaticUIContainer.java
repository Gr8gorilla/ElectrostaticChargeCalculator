package electroStaticUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;


import javax.swing.JFileChooser;
import javax.swing.JFrame;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jzy3d.chart.Chart;
import org.jzy3d.colors.Color;


public class ElectroStaticUIContainer extends JFrame{
	
	//menu components
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu optionMenu;
	private JMenu dimension;
	private JMenu graphOptions;
	private JMenu export;
	private JMenuItem exitItem;
	private JMenuItem electricFieldLines;
	private JMenuItem electricPotential;
	private JMenuItem setGraphRangeAndSteps;
	private JMenuItem asymptoteReadableFile;
	private JMenuItem newElectricFieldLines;
	private JMenuItem newElectricPotential;
	private JMenuItem d2d;
	private JMenuItem d3d;
	private JMenuItem newFile;
	private JMenuItem open;
	private JMenuItem close;
	private JMenuItem save;
	private JMenuItem saveAs;
	private JPanel container;
	private static JPanel displayPanel;
	private JFileChooser fChooser = new JFileChooser();
	private String saveAsName = "NoSavedFileYet";
	private File saveAsFile = new File(saveAsName);
	private String newFileName = "NoUserInputYet";
	private File makeNewFile = new File(newFileName);
	private JFrame rangeStepFrame;
	private JPanel rangeStepDisplay;
	private JTextField lowerBoundField;
	private JTextField upperBoundField;
	private JTextField rangeField;
	private JTextField currentLowerField;
	private JTextField currentUpperField;
	private JTextField currentRangeField;
	private static Chart chart = new Chart();
	//constructor
	public ElectroStaticUIContainer(){
		
		//title
		setTitle("Electrostatics");
		
		//close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//set layout
		setLayout(new BorderLayout());
		
		//menubar
		buildMenuBar();
		
		//set preferred size
		setMinimumSize(new Dimension(1200, 900));
		
		container = new JPanel(new BorderLayout());
		displayPanel = new JPanel(new BorderLayout());
	
		container.setPreferredSize(new Dimension(1200, 900));
		displayPanel.setPreferredSize(new Dimension(800,800));
		container.add(displayPanel, BorderLayout.CENTER);
		container.setVisible(true);
		
		//pack and display the window
		getContentPane().add(container);
		setVisible(true);
	}
	
	private void buildMenuBar(){
		//create the menu bar
		menuBar = new JMenuBar();
		
		//create file and output menus
		buildFileMenu();
		buildOptionMenu();
		
		
		//add menus to menu bar
		menuBar.add(fileMenu);
		menuBar.add(optionMenu);
		
		//set window's menu bar
		setJMenuBar(menuBar);
	}
	
	private void buildFileMenu(){
		//create Exit menu item
		exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic(KeyEvent.VK_X);
		exitItem.addActionListener(new ExitListener());
		
		//new file
		newFile = new JMenu("New");
		newFile.setMnemonic(KeyEvent.VK_N);
		newFile.addActionListener(new NewFileListener());
		//new file menu items
		//electric field lines
		newElectricFieldLines = new JMenuItem("Electric Field Lines");
		newElectricFieldLines.setMnemonic(KeyEvent.VK_L);
		newElectricFieldLines.addActionListener(new NewFileListener());
		//electric potential or voltage
		newElectricPotential = new JMenuItem("Electric Potenial");
		newElectricPotential.setMnemonic(KeyEvent.VK_V);
		newElectricPotential.addActionListener(new NewFileListener());
		//add the menu items to new file menu
		newFile.add(newElectricFieldLines);
		newFile.add(newElectricPotential);
		
		//open
		open = new JMenuItem("Open");
		open.setMnemonic(KeyEvent.VK_P);
		open.addActionListener(new OpenFileListener());
		
		//close
		close = new JMenuItem("Close");
		close.setMnemonic(KeyEvent.VK_C);
		
		//save
		save = new JMenuItem("Save");
		save.setMnemonic(KeyEvent.VK_S);
		save.addActionListener(new SaveListener());
		
		//saveAs
		saveAs = new JMenuItem("Save As");
		saveAs.setMnemonic(KeyEvent.VK_A);
		saveAs.addActionListener(new SaveAsListener());
		
		//JMenu object for file menu
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		
		fileMenu.add(newFile);
		fileMenu.add(open);
		fileMenu.add(close);
		fileMenu.add(save);
		fileMenu.add(saveAs);
		fileMenu.add(exitItem);
		}
	
	private void buildOptionMenu(){
		
		//create ElectricFieldLine menu item
		electricFieldLines = new JMenuItem("Electric Field Lines");
		electricFieldLines.setMnemonic(KeyEvent.VK_F);
		electricFieldLines.addActionListener(new OptionMenuListener());
		
		//create ElectricPotential menu item
		electricPotential = new JMenuItem("Electric Potential");
		electricPotential.setMnemonic(KeyEvent.VK_P);
		electricPotential.addActionListener(new OptionMenuListener());
		
		//asymptote menu item
		asymptoteReadableFile = new JMenuItem("Asymptote");
		asymptoteReadableFile.setMnemonic(KeyEvent.VK_A);
		asymptoteReadableFile.addActionListener(new OptionMenuListener());
		//make the export menu 
		export  = new JMenu("Export");
		export.setMnemonic(KeyEvent.VK_X);
		export.add(asymptoteReadableFile);
		
		//create Dimension menu
		dimension = new JMenu("Dimension");
		dimension.setMnemonic(KeyEvent.VK_D);
		//create 2d menu item
		d2d = new JMenuItem("2D");
		d2d.setMnemonic(KeyEvent.VK_2);
		d2d.addActionListener(new OptionMenuListener());
		//create 3d menu item
		d3d = new JMenuItem("3D");
		d3d.setMnemonic(KeyEvent.VK_3);
		d3d.addActionListener(new OptionMenuListener());
		//add dimension choices to it
		dimension.add(d2d);
		dimension.add(d3d);
		
		//create Graph menu item
		graphOptions = new JMenu("Graph");
		graphOptions.setMnemonic(KeyEvent.VK_G);
		//create graphOptions menu items NOTE make one menu item for each and have a single pop up window with fields for all three values.
		setGraphRangeAndSteps = new JMenuItem("Range/Steps");
		setGraphRangeAndSteps.setMnemonic(KeyEvent.VK_S);
		setGraphRangeAndSteps.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				rangeAndStepsSetter();
				
			}
		});
		//add choices to Graph menu
		graphOptions.add(setGraphRangeAndSteps);
		
		//create output menu object
		optionMenu = new JMenu("Options");
		optionMenu.setMnemonic(KeyEvent.VK_O);
		
		//add the items to it
		optionMenu.add(dimension);
		optionMenu.add(electricFieldLines);
		optionMenu.add(electricPotential);
		optionMenu.add(export);
		optionMenu.add(graphOptions);
	}
	
	
	
	//method for setting the range and step of the graph from the options menu
private void rangeAndStepsSetter(){
		rangeStepFrame = new JFrame();
		rangeStepFrame.setTitle("Graph Range and Steps");
		rangeStepFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		rangeStepFrame.setMinimumSize(new Dimension(400, 200));
		rangeStepDisplay = new JPanel();
		rangeStepDisplay.setMinimumSize(new Dimension(400, 200));
		JLabel rangeLowerBound = new JLabel("Lower Bound");
		lowerBoundField = new JTextField(6);
		lowerBoundField.setEditable(true);
		JLabel rangeUpperBound = new JLabel("Upper Bound");
		upperBoundField = new JTextField(6);
		upperBoundField.setEditable(true);
		JLabel rangeLabel = new JLabel("Steps");
		rangeField = new JTextField(6);
		rangeField.setEditable(true);
		JLabel currentLowerBound = new JLabel("Current Lower Bound");
		currentLowerField = new JTextField(6);
		currentLowerField.setText(Double.toString(GraphIt.getLowerBound()));
		currentLowerField.setEditable(false);
		JLabel currentUpperBound = new JLabel("Current Upper Bound");
		currentUpperField = new JTextField(6);
		currentUpperField.setText(Double.toString(GraphIt.getUpperBound()));
		currentUpperField.setEditable(false);
		JLabel currentRange = new JLabel("Current Steps");
		currentRangeField = new JTextField(6);
		currentRangeField.setText(Integer.toString(GraphIt.getSteps()));
		currentRangeField.setEditable(false);
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				GraphIt.setGraphRange(Double.parseDouble(lowerBoundField.getText()), Double.parseDouble(upperBoundField.getText()));
				GraphIt.setGraphSteps(Integer.parseInt(rangeField.getText()));
				rangeStepFrame.setVisible(false);
				rangeStepFrame.dispose();
			}
		});
		
		rangeStepDisplay.add(currentLowerBound);
		rangeStepDisplay.add(currentLowerField);
		rangeStepDisplay.add(currentUpperBound);
		rangeStepDisplay.add(currentUpperField);
		rangeStepDisplay.add(currentRange);
		rangeStepDisplay.add(currentRangeField);
		rangeStepDisplay.add(rangeLowerBound);
		rangeStepDisplay.add(lowerBoundField);
		rangeStepDisplay.add(rangeUpperBound);
		rangeStepDisplay.add(upperBoundField);
		rangeStepDisplay.add(rangeLabel);
		rangeStepDisplay.add(rangeField);
		rangeStepDisplay.add(okButton);
		rangeStepDisplay.setVisible(true);
		rangeStepFrame.add(rangeStepDisplay);
		rangeStepFrame.setVisible(true);
		rangeStepFrame.pack();
	}
	
	//method to paint the chart onto the display panel
	public static void addGraphToDisplayPanel(Chart chartOfUserData){
		chart = chartOfUserData;
		displayPanel.add((JComponent) chart.getCanvas());
		displayPanel.revalidate();
	}
	
	public static void removeGraphFromdisplayPanel(){
		displayPanel.removeAll();
		displayPanel.revalidate();
	}
	
	public void addPanelToContainer(JPanel panelToAdd, String borderLayout){
		container.add(panelToAdd, borderLayout);
		panelToAdd.repaint();
		container.revalidate();
	}
	
	
	
	
	
	/*
	 * GUI best practices recommend that I make each of these event listeners anonymous inner classes, I need to do this as I the actual calls to each.  
	 */
	
	//handles exit from file menu event
	private class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
	
	private class GraphOptionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}

	
	private class OptionMenuListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	private class SaveListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//code to save the working graph as an object..we will make it a .pes (physics electro statics)
		}
	}
	
	private class SaveAsListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JFileChooser fChooser = new JFileChooser();
			int status = fChooser.showSaveDialog(null);
			if (status == JFileChooser.APPROVE_OPTION)
				saveAsName = fChooser.getSelectedFile().getName();
			//saveAsFile = code goes here
		}
	}
	
	private class NewFileListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JFileChooser fChooser = new JFileChooser();
			int status = fChooser.showOpenDialog(null);
			if (status == JFileChooser.APPROVE_OPTION)
				saveAsName = fChooser.getSelectedFile().getName();
		}
	}
	
	private class OpenFileListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JFileChooser fChooser = new JFileChooser();
			int status = fChooser.showOpenDialog(null);
			if (status == JFileChooser.APPROVE_OPTION)
				saveAsName = fChooser.getSelectedFile().getName();
		}
	}
}
