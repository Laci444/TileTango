package com.unideb.inf.tiletango.backend;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Board b = new Board(6);
        System.out.println(b.getCircle());
        Scanner input = new Scanner(System.in);

        while (true) {
            int number = input.nextInt();
            if (number == -1) {
                break;
            }
            number -= 1;
            System.out.println("Move: " + b.tryMove(number));
            System.out.println("isSolved: " + b.isSolved());
            System.out.println("Circle: " + b.getCircle());
            System.out.println("Middle: " + b.getMiddle());
            if (b.isSolved()) {
                break;
            }
        }
        System.out.println("End of game");
    }
}
