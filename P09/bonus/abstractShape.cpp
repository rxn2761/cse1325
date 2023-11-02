//
// Created by Rodney Nguyen on 11/1/23.
//

#include "abstractShape.h"


string abstractShape::name() const {
    return "";
}

double abstractShape::area() const {
    return 0;
}

string abstractShape::toString() {
    return name() + " with area " + std::to_string(area());
}