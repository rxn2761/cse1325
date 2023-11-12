#include <iostream>
#include <ostream>
#include "Time.h"
#include <iomanip>


int main() {
    try
    {
        //Example of operator+
        Time t1(10, 30, 150);
        Time t2(12, 30, 20);

        std::cout << "Time t1: ";
        std::cout << t1 << std::endl;
        std::cout << "Time t2: ";
        std::cout << t2 << std::endl;

        Time sum = t1 + t2;
        std::cout << "Time t1 + t2: ";
        std::cout << sum << std::endl;

        //Example of pre-increment
        Time t3(10, 30, 15);
        std::cout << "Original Time t3: ";
        std::cout << t3 << std::endl;
        ++t3;
        std::cout << "Time t3 after pre-increment: ";
        std::cout << t3 << std::endl;

        //Example of post-increment
        Time t4(23, 59, 59);
        std::cout << "Original Time t4: ";
        std::cout << t4 << std::endl;
        Time t5 = t4++;
        std::cout << "Time t4 after post-increment: ";
        std::cout << t4 << std::endl;
        std::cout << "Time t5 (result of post-increment): ";
        std::cout << t5 << std::endl;

        //Example of operator==
        Time t6(10, 30, 15);
        Time t7(10, 30, 15);
        Time t8(12, 15, 45);

        std::cout << "Time t6: ";
        std::cout << t6 << std::endl;
        std::cout << "Time t7: ";
        std::cout << t7 << std::endl;
        std::cout << "Time t8: ";
        std::cout << t8 << std::endl;

        if (t6 == t7) {
            std::cout << "t6 is equal to t7." << std::endl;
        }
        else {
            std::cout << "t6 is not equal to t7." << std::endl;
        }

        if (t6 == t8) {
            std::cout << "t6 is equal to t8." << std::endl;
        }
        else {
            std::cout << "t6 is not equal to t8." << std::endl;
        }

        //Example of operator!=
        Time t9(10, 30, 15);
        Time t10(10, 30, 15);
        Time t11(12, 15, 45);

        std::cout << "Time t9: ";
        std::cout << t9 << std::endl;
        std::cout << "Time t10: ";
        std::cout << t10 << std::endl;
        std::cout << "Time t11: ";
        std::cout << t11 << std::endl;

        if (t9 != t10) {
            std::cout << "t9 is not equal to t10." << std::endl;
        }
        else {
            std::cout << "t9 is equal to t10." << std::endl;
        }

        if (t9 != t11) {
            std::cout << "t9 is not equal to t11." << std::endl;
        }
        else {
            std::cout << "t9 is equal to t11." << std::endl;
        }

        //Example of operator<
        Time t12(10, 30, 15);
        Time t13(12, 15, 45);
        Time t14(10, 30, 15);

        std::cout << "Time t12: ";
        std::cout << t12 << std::endl;
        std::cout << "Time t13: ";
        std::cout << t13 << std::endl;
        std::cout << "Time t14: ";
        std::cout << t14 << std::endl;

        if (t12 < t13) {
            std::cout << "t12 is less than t13." << std::endl;
        }
        else {
            std::cout << "t12 is not less than t13." << std::endl;
        }

        if (t12 < t14) {
            std::cout << "t12 is less than t14." << std::endl;
        }
        else {
            std::cout << "t12 is not less than t14." << std::endl;
        }
        //Example of operator>
        Time t15(10, 30, 15);
        Time t16(12, 15, 45);
        if (t15 > t16) {
            std::cout << "t15 is greater than t16." << std::endl;
        }
        else {
            std::cout << "t15 is not greater than t16." << std::endl;
        }
        //Example of operator<=
        Time t17(10, 30, 15);
        Time t18(12, 15, 45);
        Time t19(10, 30, 15);

        std::cout << "Time t17: ";
        std::cout << t17 << std::endl;
        std::cout << "Time t18: ";
        std::cout << t18 << std::endl;
        std::cout << "Time t19: ";
        std::cout << t19 << std::endl;

        if (t17 <= t18) {
            std::cout << "t17 is less than or equal to t18." << std::endl;
        }
        else {
            std::cout << "t17 is greater than t18." << std::endl;
        }

        if (t17 <= t19) {
            std::cout << "t17 is less than or equal to t19." << std::endl;
        }
        else {
            std::cout << "t17 is greater than t19." << std::endl;
        }

        //Example of operator>=
        Time t20(10, 30, 15);
        Time t21(12, 15, 45);
        Time t22(10, 30, 15);

        std::cout << "Time t20: ";
        std::cout << t20 << std::endl;
        std::cout << "Time t21: ";
        std::cout << t21 << std::endl;
        std::cout << "Time t22: ";
        std::cout << t22 << std::endl;

        if (t20 >= t21) {
            std::cout << "t20 is greater than or equal to t21." << std::endl;
        }
        else {
            std::cout << "t20 is less than t21." << std::endl;
        }

        if (t20 >= t22) {
            std::cout << "t20 is greater than or equal to t22." << std::endl;
        }
        else {
            std::cout << "t20 is less than t22." << std::endl;
        }

        //Example of operator<<
        Time t23(10, 30, 15);
        std::cout << "Time t23: " << t23 << std::endl;

        //Example of operator>>
        Time t24(10, 30, 15);
        std::cout << "Enter a time in HH:MM:SS format: ";
        if (std::cin >> t24) {
            std::cout << "You entered: " << t24 << std::endl;
        }
        else {
            std::cout << "Invalid input format." << std::endl;
        }
    }
    catch(std::out_of_range& e)
    {
        std::cerr << "Exception: " << e.what() << std::endl;
    }

    return 0;
}