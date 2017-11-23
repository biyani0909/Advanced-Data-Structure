JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class: 
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        dary.java \
        decoded.java \
        decodeNode.java \
        decoder.java \
		encoder.java \
		Fileread_write.java \
		MinHeap.java \
		Traversal.java \
		PairHeap.java \
		HTreeBinary.java \
	Node.java
		
MAIN = encoder

default: classes

classes: $(CLASSES:.java=.class)

clean: $(RM) *.class
