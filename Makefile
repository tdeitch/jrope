default: build benchmark

build:
	javac *.java

test:
	java -ea Test

benchmark:
	java -ea Benchmark

clean:
	rm *.class
