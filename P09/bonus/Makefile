CXXFLAGS += --std=c++17

shapes: Shapes.o abstractShape.o Circle.o Triangle.o *.h
	$(CXX) $(CXXFLAGS) Shapes.o abstractShape.o Circle.o Triangle.o -o shapes

Circle.o: Circle.cpp *.h
	$(CXX) $(CXXFLAGS) -c Circle.cpp -o Circle.o

Triangle.o: Triangle.cpp *.h
	$(CXX) $(CXXFLAGS) -c Triangle.cpp -o Triangle.o

abstractShape.o: abstractShape.cpp *.h
	$(CXX) $(CXXFLAGS) -c abstractShape.cpp -o abstractShape.o

Shapes.o: Shapes.cpp *.h
	$(CXX) $(CXXFLAGS) -c Shapes.cpp -o Shapes.o

clean:
	rm -f *.o *.gch *~ a.out shapes
