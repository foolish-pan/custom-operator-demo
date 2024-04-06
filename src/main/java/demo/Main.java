package demo;

import equation.Equation;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Equation equation = new Equation();

        System.out.println("请输入表达式:");

        Scanner sc = new Scanner(System.in);

        String equationStr = sc.next();

        System.out.println("表达式的结果为:" + equation.evalEquationWithCustomOperator(equationStr));

    }

}