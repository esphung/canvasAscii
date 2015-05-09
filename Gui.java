/*
* @Author: Eric Phung
* @Date:   2015-03-31 23:49:37
* @Last Modified by:   home
* @Last Modified time: 2015-04-09 14:45:55
*/
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Gui extends JFrame implements ActionListener{
	// instance variables
	public static int ticks = 0; // counter
	public static Canvas canvasObject = new Canvas(); // custom textbox
	public static JLabel labelOutputObject = new JLabel("Hello Label");
	public static JButton previousBtnObject = new JButton("<<");
	public static JButton deleteBtnObject = new JButton("Delete");
	public static JButton loadBtnObject = new JButton("LOad");
	public static JButton saveBtnObject = new JButton("Save");
	public static JButton nextBtnObject = new JButton(">>");
	public static JButton newBtnObject = new JButton("Hello New Book!");
	public static JButton tutorialBtnObject = new JButton("Hello Tutorial!");
	public static String output; // transfer txt
	public static Container pnlMain = new Container();
	public static Panel pnlButtons = new Panel();
	public static Gui currentPage = new Gui();

	// null
	public Gui(){}// end null

	// welcome page contsructor
	public Gui(String title,String tutorialBtnLbl,String newBtnLbl,String loadBtnLbl){
		super(title);
		this.canvasObject = canvasObject;
		this.pnlMain = this.getContentPane();
		this.pnlButtons = pnlButtons;
		this.labelOutputObject = new JLabel(title);//screen tutorial?
		this.tutorialBtnObject = new JButton(tutorialBtnLbl);
		//this.loadBtnObject = new JButton(loadBtnLbl);
		this.newBtnObject = new JButton(newBtnLbl);

		//set up main panel
		pnlMain.setLayout(new BorderLayout());
		pnlMain.add(labelOutputObject, BorderLayout.CENTER); // set lblOutput
		//pnlMain.add(canvasObject, BorderLayout.CENTER); // set canvas
		labelOutputObject.setOpaque(true);
		labelOutputObject.setBackground(Color.GRAY);
		labelOutputObject.setHorizontalAlignment(JLabel.CENTER);
		labelOutputObject.setFont(new Font("Monospace", Font.BOLD, 40));
		pnlMain.add(pnlButtons, BorderLayout.SOUTH);

		//set up buttons panel
		pnlButtons.setLayout(new FlowLayout());
		pnlButtons.add(tutorialBtnObject);
		pnlButtons.add(loadBtnObject);
		pnlButtons.add(newBtnObject);

		//set up events
		tutorialBtnObject.addActionListener(this);
		loadBtnObject.addActionListener(this);
		this.newBtnObject.addActionListener(this);

		//handle window
		this.setDefaultCloseOperation(Gui.EXIT_ON_CLOSE);
		this.pack();
		this.setSize(500, 400);
		this.setVisible(true);

	} // end welcome page constructor

	// single constructor
	public Gui(String str){
		super(str);
		currentPage.dispose();
		this.canvasObject = canvasObject;
		Container pnlMain = this.getContentPane();
		Panel pnlButtons = new Panel();

		//set up main panel
		pnlMain.setLayout(new BorderLayout());
		//pnlMain.add(labelOutputObject, BorderLayout.CENTER); // set lblOutput
		pnlMain.add(canvasObject, BorderLayout.CENTER); // set canvas
		labelOutputObject.setOpaque(true);
		labelOutputObject.setBackground(Color.GRAY);
		labelOutputObject.setHorizontalAlignment(JLabel.CENTER);
		labelOutputObject.setFont(new Font("Monospace", Font.BOLD, 40));
		pnlMain.add(pnlButtons, BorderLayout.SOUTH);

		//set up buttons panel
		pnlButtons.setLayout(new FlowLayout());
		pnlButtons.add(previousBtnObject);
		pnlButtons.add(deleteBtnObject);
		pnlButtons.add(saveBtnObject);
		pnlButtons.add(nextBtnObject);

		//set up events
		this.previousBtnObject.addActionListener(this);
		this.deleteBtnObject.addActionListener(this);
		this.saveBtnObject.addActionListener(this);
		this.nextBtnObject.addActionListener(this);

		//handle window
		this.setDefaultCloseOperation(Gui.EXIT_ON_CLOSE);
		this.pack();
		this.setSize(500, 400);
		this.setVisible(true);

	} // end constructor

	// action listener
	public void actionPerformed(ActionEvent e){
		String output = canvasObject.pageList.get(ticks);
		if (e.getSource() == previousBtnObject){
			output = previousBtnEvent(output); // call prevBtn event
		} else if (e.getSource() == deleteBtnObject){
				output = deleteBtnEvent();
		} else if (e.getSource() == saveBtnObject){
				output = saveBtnEvent();
		} else if (e.getSource() == loadBtnObject){
				output = loadBtnEvent();
		} else if (e.getSource() == tutorialBtnObject){
				output = tutorialBtnEvent();
		} else if(e.getSource() == nextBtnObject){
			output = nextBtnEvent(output); // call next btn event
		} else if(e.getSource() == newBtnObject){
			output = newBtnEvent();
	  }
	  else {
	    output = "trouble...";
	    System.out.println(e.getActionCommand());
	  } // end if
	  labelOutputObject.setText(output);
	  canvasObject.setText(output);
	  System.out.println(canvasObject.pageList);
	} // end actionPerformed

	// event functions
	public static String previousBtnEvent(String str){
		if (ticks >= 1) {
			ticks--; // dec counter
			System.out.println(ticks); // console log
		} else{
			System.out.println("I can't go back further!");
		}// end if/else
		output = canvasObject.pageList.get(ticks);
		return output; // halt
	} // end prev event

	public static String nextBtnEvent(String str){
		if (ticks < canvasObject.pageList.size()-1) {
			ticks++; // inc counter
			System.out.println(ticks); // console log
		} else if (ticks >= canvasObject.pageList.size()-1){
			ticks++;
			canvasObject.pageList.add("");
			System.out.println(ticks); // console log
		} else {
			System.out.println("Hmmm...limit reached");
		} // end if/else
		output = canvasObject.pageList.get(ticks);
		return output; // halt
	} // end next event

	public static String deleteBtnEvent(){
		output = "Deleted Page";// store delete txt
		canvasObject.pageList.set(ticks,output); // set delete
		canvasObject.saveCanvas("UserCanvas.txt");
		return output; // return delete
	} // end delete event
	public static String saveBtnEvent(){
		output = canvasObject.getText(); // collect current field
		canvasObject.pageList.set(ticks,output); // set current field
		canvasObject.saveCanvas("UserCanvas.txt"); // save curent list
		return output; // return current field
	} // end save event
	public static String loadBtnEvent(){
		try{
			// load previous user book
			loadUserPage();
			output = canvasObject.pageList.get(ticks);
			//System.out.println(canvasObject.pageList);
		}catch(Exception e){
			System.out.println("Error messages\ncannot completely convey.\nWe now know shared loss.");
		}// end try/catch
		return output;
	} // end load event

	public static String newBtnEvent(){
		loadNewPage();
		output = "Type anything here then save it to get started!";
		return output;
	}// end new btn event

	public static String tutorialBtnEvent(){
		loadTutorialPage();
		output = "this is the tutorial book!";
		System.out.println(output);
		return output;
	} // end tutorial btn event

	public static void loadNewPage(){
		currentPage = new Gui("This is a new Canvas!");
		//canvasObject.defaultCanvas(); // write 10 pages
		canvasObject.pageList.add("");
		canvasObject.setText(canvasObject.pageList.get(0));
	} // end load new

	public static void loadWelcomePage(){
		currentPage = new Gui("FLipbook!","Tutorial Book","New Book","Previous Book");
		canvasObject.pageList.add("");
		//canvasObject.defaultCanvas(); // write 10 pages
		canvasObject.setText(canvasObject.pageList.get(0));

	} // end load welcome page

	public static void loadTutorialPage(){
		currentPage = new Gui("Welcome to the Tutorial Canvas!");
		canvasObject.tutorialCanvas();
		canvasObject.setText(canvasObject.pageList.get(0));

	} // end load tutorial

	public static void loadUserPage(){
		Gui currentPage = new Gui("This is your canvas!");
		canvasObject.userCanvas();
		canvasObject.setText(canvasObject.pageList.get(0));
	} // end load previous user project


	// new student
	public static Canvas setCanvas(String filename){
		Canvas newCanvasObject = new Canvas(filename);
		newCanvasObject.saveCanvas("UserCanvas.txt");
		return newCanvasObject;
	} // end set and save student

	public static Canvas getCanvas(String filename){
		Canvas newCanvasObject = new Canvas(filename);
		return newCanvasObject;
	} // end get student from file



	// main
	public static void main(String[] args) {
		// load welcome page
		loadWelcomePage();

		// load new book
		//loadNewPage();

		// load tutorial book
		//loadTutorialPage();

		// load previos user book
		//loadUserPage();

	} // end main


} // end class definition