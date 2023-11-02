//
// Created by Rodney Nguyen on 11/1/23.
//

#ifndef SHAPE_H
#define SHAPE_H
#include <string>
using std::string;


class Shape {
public:
    virtual string name() const;
    virtual double area() const;
    string toString();
};

#endif //SHAPE_H