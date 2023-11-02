//
// Created by Rodney Nguyen on 11/1/23.
//

#include "Triangle.h"
#include "Shape.h"


Triangle::Triangle(double height, double base) {
    _height = height; _base = base;
}

string Triangle::name() const {
    string returnString;
    returnString = "(" + std::to_string(_height) + " X " +  std::to_string(_base) + ") / 2";
    return returnString + " Triangle";
}

double Triangle::area() const {
    double Area = (_height * _base) / 2;
    return Area;
}