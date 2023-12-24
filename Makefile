test:
	javac -cp .:junit.jar:hamcrest.jar *.java
	java -cp .:junit.jar:hamcrest.jar Main
	cat RESULTS.txt

clean:
	rm *.class

windows-test:
	del RESULTS.txt
	javac -cp junit.jar;. ./*.java
	java -cp junit.jar;hamcrest.jar;. Main
	type RESULTS.txt
	
windows-clean:
	del /s *.class
