CXXFLAGS += --std=c++17

shapes: Shapes.o Shape.o Circle.o Triangle.o *.h
	$(CXX) $(CXXFLAGS) Shapes.o Shape.o Circle.o Triangle.o -o shapes

Circle.o: Circle.cpp *.h
	$(CXX) $(CXXFLAGS) -c Circle.cpp -o Circle.o

Triangle.o: Triangle.cpp *.h
	$(CXX) $(CXXFLAGS) -c Triangle.cpp -o Triangle.o

Shape.o: Shape.cpp *.h
	$(CXX) $(CXXFLAGS) -c Shape.cpp -o Shape.o

Shapes.o: Shapes.cpp *.h
	$(CXX) $(CXXFLAGS) -c Shapes.cpp -o Shapes.o

clean:
	rm -f *.o *.gch *~ a.out shapes
