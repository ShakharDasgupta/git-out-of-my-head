help:
	@echo "The following make commands are valid, and can be run as 'make COMMAND':"
	@echo ""
	@echo "  run  compiles and runs the program from source"
	@echo "  jar  compiles the program into a jar file"
	@echo ""

run:
	ant run

jar:
	ant jar
