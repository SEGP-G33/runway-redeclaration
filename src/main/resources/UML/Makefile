# This version of PlantUML only works with java 15 or earlier (idk why...)
# run "make incr1" to get uml diagrans of increment 1

JAVA = /home/snapper/.jdks/corretto-15.0.2/bin/java    # insert path to bin/java here
PUML_JAR = ./plantuml-1-2022.4.jar    # insert path to plantuml jar file here

incr1:
	$(JAVA) -jar $(PUML_JAR) incr-1-usecase.puml

incr2:
	$(JAVA) -jar $(PUML_JAR) incr-2-usecase.puml

incr3:
	$(JAVA) -jar $(PUML_JAR) incr-3-usecase.puml

clean:
	- rm incr-1-usecase.png
	- rm incr-2-usecase.png
	rm incr-3-usecase.png
