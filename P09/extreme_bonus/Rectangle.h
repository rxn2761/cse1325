//
// Created by Rodney Nguyen on 11/1/23.
//

#ifndef RECTANGLE_H
#define RECTANGLE_H
#include "Shape.h"
#include <string>
using std::string;


class Rectangle: public Shape {
public:
    Rectangle(double height, double width);
    string name() const override;
    double area() const override;
    void draw() const override;

private:
    double _height;
    double _width;
};

#endif //RECTANGLE_H