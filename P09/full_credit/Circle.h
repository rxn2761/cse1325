//
// Created by Rodney Nguyen on 11/1/23.
//

#ifndef CIRCLE_H
#define CIRCLE_H
#include <string>
#include "Shape.h"
using std::string;


class Circle: public Shape {
public:
    Circle(double radius);
    string name() const override;
    double area() const override;

private:
    double _radius;
};

#endif //CIRCLE_H