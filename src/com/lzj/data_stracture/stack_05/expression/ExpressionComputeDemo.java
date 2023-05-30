package com.lzj.data_stracture.stack_05.expression;

import java.util.Stack;

/**
 * 栈结构计算表达式演示案例
 * （1）中缀表达式：(3 + 4) * 5 - 6，对计算机而言需要转换成前缀或后缀表达式
 * （2）前缀表达式：- * + 3 4 5 6，从右至左扫描表达式
 * （3）后缀表达式：3 4 + 5 * 6 -，从左至右扫描表达式
 */
public class ExpressionComputeDemo {
    public static void main(String[] args) {

        String expr = "3+4*5-6";  // 表达式
        Stack<Integer> nums = new Stack<>();  // 数栈
        Stack<String> opers = new Stack<>();  // 操作栈


    }
}

/**
 * 中缀表达式实现公式计算
 * （1）创建数栈与符号栈
 * （2）判断操作符优先级，当前扫描到的符号小于等于栈中符号，取出运算再将当前扫描到的符号入栈
 * （3）重复运算得到最后一个结果
 */
class InfixExpression{

}