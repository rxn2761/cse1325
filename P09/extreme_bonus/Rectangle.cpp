//
// Created by Rodney Nguyen on 11/1/23.
//

#include "Rectangle.h"
#include "abstractShape.h"
#include <string>
#include <iostream>


Rectangle::Rectangle(double height, double width) {
    _height = height; _width = width;
}

string Rectangle::name() const {
    string returnString;
    returnString = "(" + std::to_string(_height) + " X " +  std::to_string(_width) + ") / 2";
    return returnString + " Rectangle";
}

double Rectangle::area() const {
    double Area = (_height * _width) / 2;
    return Area;
}

void Rectangle::draw() const {
    for (int i = 0; i < _height; ++i) {
        for (int j = 0; j < _width; ++j) {
            std::cout << "* ";
        }
        std::cout << std::endl;
    }
}