//
// Created by Rodney Nguyen on 11/1/23.
//

#ifndef CIRLCE_H
#define CIRLCE_H
#include "Shape.h"
#include <string>
using std::string;


class Circle: public Shape {
public:
    Circle(double radius);
    string name() const override;
    double area() const override;

private:
    double _radius;
};

#endif //CIRLCE_H