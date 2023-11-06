//
// Created by Rodney Nguyen on 11/1/23.
//

#include "Circle.h"
#include "Shape.h"
#include <string>
#include <cmath>


Circle::Circle(double radius) {
    _radius = radius;
}

string Circle::name() const {
    return "Circle of radius " + std::to_string(_radius);
}

double Circle::area() const {
    return M_PI * pow(_radius, 2);
}