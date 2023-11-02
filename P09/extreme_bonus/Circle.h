//
// Created by Rodney Nguyen on 11/1/23.
//

#ifndef CIRCLE_H
#define CIRCLE_H
#include "abstractShape.h"
#include <string>
using std::string;


class Circle: public abstractShape {
public:
    Circle(double radius);
    string name() const override;
    double area() const override;
    void draw() const override;

private:
    double _radius;
};

#endif //CIRCLE_H