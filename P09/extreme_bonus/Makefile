CXXFLAGS += --std=c++17

shapes: Shapes.o abstractShape.o Circle.o Rectangle.o *.h
	$(CXX) $(CXXFLAGS) Shapes.o abstractShape.o Circle.o Rectangle.o -o shapes

Circle.o: Circle.cpp *.h
	$(CXX) $(CXXFLAGS) -c Circle.cpp -o Circle.o

Rectangle.o: Rectangle.cpp *.h
	$(CXX) $(CXXFLAGS) -c Rectangle.cpp -o Rectangle.o

abstractShape.o: abstractShape.cpp *.h
	$(CXX) $(CXXFLAGS) -c abstractShape.cpp -o abstractShape.o

Shapes.o: Shapes.cpp *.h
	$(CXX) $(CXXFLAGS) -c Shapes.cpp -o Shapes.o

clean:
	rm -f *.o *.gch *~ a.out shapes
