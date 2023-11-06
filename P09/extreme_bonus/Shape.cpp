//
// Created by Rodney Nguyen on 11/1/23.
//

#include "Shape.h"


string Shape::name() const {
    return "";
}

double Shape::area() const {
    return 0;
}

string Shape::toString() {
    return name() + " with area " + std::to_string(area());
}

void Shape::draw() const {

}