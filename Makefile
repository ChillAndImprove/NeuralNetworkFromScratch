JC       := javac
JCFLAGS  := -d

JVM      := java
JVMFLAGS := -cp

OUT_DIR  := out

default: run

.PHONY: run
run: prepare
	@$(JVM) NeuronalesNetz

.PHONY: prepare
prepare:
	@$(JC) NeuronalesNetz.java

.PHONY: clean
clean:
	rm *.class
