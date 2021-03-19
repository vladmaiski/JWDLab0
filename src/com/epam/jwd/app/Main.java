package com.epam.jwd.app;

import com.epam.jwd.model.Point;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class Main {

    //private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Point[] points = inputPoints();
        outputArr(points);
    }

    private static Point[] inputPoints() {
        Scanner sc = new Scanner(System.in);
        int pointsAmount = 0;
        boolean isIncorrect = true;
        do {
            System.out.println("Input amount of point");
            try {
                pointsAmount = Integer.parseInt(sc.nextLine());
                isIncorrect = false;
            } catch (NumberFormatException e) {
                System.out.println("Input integer value");
                //TODO logger
            }
        } while (isIncorrect);

        Point[] points = new Point[pointsAmount];

        for (int i = 0; i < pointsAmount; i++) {
            isIncorrect = true;
            do {
                System.out.println("Input " + (i + 1) + " point coords(Example: \"3.1 3\"");
                try {
                    String[] pointsCoords = sc.nextLine().split(" ", 2);
                    double coordX = Double.parseDouble(pointsCoords[0]);
                    double coordY = Double.parseDouble(pointsCoords[1]);
                    points[i] = new Point(coordX, coordY);
                    isIncorrect = false;
                } catch (NumberFormatException e) {
                    //TODO logger
                    System.out.println("Incorrect values");
                } catch (ArrayIndexOutOfBoundsException e) {
                    //TODO logger
                    System.out.println("Empty or not enough values");
                }
            } while (isIncorrect);
        }
        return points;
    }

    public static void outputArr(Object[] arr) {
        for(Object obj: arr) {
            System.out.println(obj.toString());
        }
    }

}


