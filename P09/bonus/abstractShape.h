//
// Created by Rodney Nguyen on 11/1/23.
//

#ifndef ABSTRACTSHAPE_H
#define ABSTRACTSHAPE_H
#include <string>
using std::string;


class abstractShape {
public:
    virtual string name() const = 0;
    virtual double area() const = 0;
    string toString();
};

#endif //ABSTRACTSHAPE_H