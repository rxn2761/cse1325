//
// Created by Rodney Nguyen on 11/1/23.
//

#ifndef TRIANGLE_H
#define TRIANGLE_H
#include "abstractShape.h"
#include <string>
using std::string;


class Triangle: public abstractShape {
public:
    Triangle(double height, double base);
    string name() const override;
    double area() const override;

private:
    double _height;
    double _base;
};

#endif //TRIANGLE_H