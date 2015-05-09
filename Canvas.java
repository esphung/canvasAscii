/*
* @Author: Eric Phung
* @Date:   2015-03-31 22:02:51
* @Last Modified by:   home
* @Last Modified time: 2015-04-09 14:49:41
*/
import javax.swing.*; // JTextarea
import java.io.*; // Serializable
import java.util.*; // List

public class Canvas extends JTextArea implements Serializable{

	// static vars
	public static List<String> pageList = new ArrayList<>(); // strings
	static final long serialVersionUID = 5318008L; // serial id

	// instance vars

	// constructor
	public Canvas(){
		this.pageList = pageList;
	} // end null

	public Canvas(String filename){
		this.pageList = pageList;
	} // end null

	// console log
	public void displayMe(){
		for (int i = 0; i < this.pageList.size(); i++) {
			System.out.println(this.pageList.get(i)); // show pagelist
		} // end for
	} // end show console log

	public void tutorialCanvas(){
		this.pageList.add("Welcome to the tutorial book! Click the '>>' button to continue.");
		this.pageList.add("Second page we will learn...");
		this.pageList.add("This is the third page...");
		this.pageList.add("... and this one is the fourth!");
		this.pageList.add("the fifth page this is !");
		this.pageList.add("OnwarD! to the sixth");
		this.pageList.add("seven is my favorite number");
		this.pageList.add("I bet you knew this was eight");
		this.pageList.add("Hmm i lost count...");
		this.pageList.add("nine! it is definately page nine!");
		this.pageList.add("Wtf finally!... we made it to page ten");
		this.saveCanvas("TutorialCanvas.txt"); // create/save
		this.loadCanvas("TutorialCanvas.txt"); // load
	} // end tutorial canvas

	public void userCanvas(){
		this.pageList.add("");
		this.loadCanvas("UserCanvas.txt"); // load
	} // end user canvas

	public void defaultCanvas(){
		this.fillPages(10);
		this.saveCanvas("DefaultCanvas.txt"); // create/save
		//this.loadCanvas("DefaultCanvas.txt"); // load
	} // end default canvas

	// save
	public void saveCanvas(String filename){
		Canvas myCanvas = this;
		try {
			FileOutputStream fo = new FileOutputStream(filename);
			ObjectOutputStream obOut = new ObjectOutputStream(fo);
			obOut.writeObject(this.pageList);
		} catch (Exception e){
			System.out.println(e.getMessage());
		} // end try
	} // end saveCanvas

	// get canvas page
	public Canvas loadCanvas(String filename){
		Canvas myCanvas = new Canvas();
		try {
			FileInputStream fIn = new FileInputStream(filename);
			ObjectInputStream obIn = new ObjectInputStream(fIn);
			myCanvas.pageList = (List<String>)obIn.readObject();
		}catch (Exception e){
			System.out.println(e.getMessage()+"\nI can't load it!");
		} // end try
		return myCanvas;
	} // end loadCanvas

	// write pages
	public void fillPages(int num){
		for (int i = 0; i < num; i++) {
			this.pageList.add("Hello string number "+i);
		} // end for
	} // end test fill

/*
	public static void main(String[] args) {
		//Canvas canvasObject = new Canvas("UserCanvas.txt");
		//canvasObject.fillPages(10); // fill pages test
		//canvasObject.saveCanvas("UserCanvas.txt"); // save current pages

		//canvasObject.userCanvas(); // load previous pages

		//canvasObject.defaultCanvas(); // default hello pages

		//canvasObject.tutorialCanvas(); // tutorial pages


		//canvasObject.displayMe(); // display current pages
	} // end main
*/


} // end class definition