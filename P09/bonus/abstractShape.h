//
// Created by Rodney Nguyen on 11/1/23.
//

#ifndef ABSTRACTSHAPE_H
#define ABSTRACTSHAPE_H
#include <string>
using std::string;


class abstractShape {
public:
    virtual string name() const;
    virtual double area() const;
    string toString();
};

#endif //ABSTRACTSHAPE_H