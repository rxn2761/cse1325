//
// Created by Rodney Nguyen on 11/1/23.
//

#ifndef TRIANGLE_H
#define TRIANGLE_H
#include <string>
#include "Shape.h"
using std::string;


class Triangle: public Shape {
public:
    Triangle(double height, double base);
    string name() const override;
    double area() const override;

private:
    double _height;
    double _base;
};

#endif //TRIANGLE_H