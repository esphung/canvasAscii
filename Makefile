all:
	javac *.java
	touch SerialCanvas.txt
	touch TutorialCanvas.txt
	touch UserCanvas.txt
	touch BlankCanvas.txt
run:
	java Gui
clean:
	rm -rf *.class
	rm -rf *.txt