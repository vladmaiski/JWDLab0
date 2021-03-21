package com.epam.jwd.app;

import com.epam.jwd.model.Point;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    private static final int MAX_POINTS_AMOUNT = 10;
    private static final int MIN_POINTS_AMOUNT = 1;
    private static final double MAX_COORDINATE = 10;

    public static void main(String[] args) {
        Point[] points = inputPoints();
        outputPoints(points);
    }

    private static Point[] inputPoints() {
        int pointsAmount = inputValue("Input amount of point", MAX_POINTS_AMOUNT, MIN_POINTS_AMOUNT);
        Point[] points = new Point[pointsAmount];
        for (int i = 0; i < pointsAmount; i++) {
            Point currentPoint = inputPoint("Input " + (i + 1) + " point coords(Example: \"3.1 3\")", MAX_COORDINATE);
            points[i] = currentPoint;
        }
        return points;
    }

    private static int inputValue(String message, int maxValue, int minValue) {
        Scanner scanner = new Scanner(System.in);
        int value = 0;
        boolean isIncorrect = true;
        do {
            System.out.println(message);
            try {
                value = Integer.parseInt(scanner.nextLine());

                if (value > maxValue || value < minValue) {
                    String range = "[" + minValue + ", " + maxValue + "]";
                    System.out.println("Input value in range " + range);
                    logger.error(range + "value is " + value);
                } else
                    isIncorrect = false;

            } catch (NumberFormatException e) {
                System.out.println("Input integer value");
                logger.error(e);
            }
        } while (isIncorrect);
        logger.info("Points amount is " + value);
        return value;
    }

    private static Point inputPoint(String message, double pointRange) {
        Scanner scanner = new Scanner(System.in);
        boolean isIncorrect = true;
        Point resultPoint = null;
        do {
            System.out.println(message);
            try {
                String[] pointsCoords = scanner.nextLine().split(" ", 2);
                double coordinateX = Double.parseDouble(pointsCoords[0]);
                double coordinateY = Double.parseDouble(pointsCoords[1]);
                if (!checkCoordinate(coordinateX, pointRange) || !checkCoordinate(coordinateY, pointRange)) {
                    String range = "[" + pointRange + ", " + (-pointRange) + "]";
                    System.out.println("Inter coordinates in range " + range);
                    logger.error(range + " value are " + coordinateX + " " + coordinateY);
                } else {
                    logger.debug("Coordinates are" + coordinateX + ", " + coordinateY);
                    resultPoint = new Point(coordinateX, coordinateY);
                    isIncorrect = false;
                }
            } catch (NumberFormatException e) {
                String errorMsg = "Incorrect values";
                System.out.println(errorMsg);
                logger.error(errorMsg + e);
            } catch (ArrayIndexOutOfBoundsException e) {
                String errorMsg = "Empty or not enough values";
                logger.error(errorMsg + e);
                System.out.println(errorMsg);
            }
        } while (isIncorrect);
        return resultPoint;
    }

    private static void outputPoints(Point[] points) {
        for (Point currentPoint : points) {
            String pointStr = currentPoint.toString();
            System.out.println(pointStr);
            logger.info(pointStr);
        }
    }

    private static boolean checkCoordinate(double value, double range) {
        if (value > range || value < -range)
            return false;
        return true;
    }
}


