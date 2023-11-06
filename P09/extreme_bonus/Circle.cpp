//
// Created by Rodney Nguyen on 11/1/23.
//

#include "Circle.h"
#include "Shape.h"
#include <string>
#include <cmath>
#include <iostream>
using std::string;


Circle::Circle(double radius) {
    _radius = radius;
}

string Circle::name() const {
    return "Circle of radius " + std::to_string(_radius);
}

double Circle::area() const {
    return M_PI * pow(_radius, 2);
}

void Circle::draw() const {
    int diameter = static_cast<int>(2 * _radius);
    int centerX = diameter / 2;
    int centerY = diameter / 2;

    for (int y = 0; y <= diameter; ++y) {
        for (int x = 0; x <= diameter; ++x) {
            int dx = x - centerX;
            int dy = y - centerY;
            if (dx * dx + dy * dy <= _radius * _radius) {
                std::cout << "* ";
            }
            else {
                std::cout << "  ";
            }
        }
        std::cout << std::endl;
    }
}