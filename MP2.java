
package fmp2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

public class MP2 {
    private static Scanner input = new Scanner(System.in);
    private static HashSet<String> courseCodes = new HashSet<>();
    private static HashSet<String> enrollmentPairs = new HashSet<>();
    private static HashMap<Integer, String> students = new HashMap<>();

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("Course Registration System");
            System.out.println("1. Add student");
            System.out.println("2. Add course");
            System.out.println("3. Enroll");
            System.out.println("4. List student courses");
            System.out.println("5. List courses");
            System.out.println("0. Exit");

            choice = readInt("Choice: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addCourse();
                    break;
                case 3:
                    enroll();
                    break;
                case 4:
                    listStudentCourses();
                    break;
                case 5:
                    listCourses();
                    break;
                case 0:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    System.out.println();
            }
        } while (choice != 0);

        input.close();
    }

    private static void addStudent() {
        int id = readInt("Enter student ID: ");

        if (students.containsKey(id)) {
            System.out.println("Student ID already exists.");
            System.out.println();
            return;
        }

        String name = readText("Enter name: ");
        students.put(id, name);

        System.out.println("Student added.");
        System.out.println();
    }

    private static void addCourse() {
        String code = readText("Enter course code: ");

        if (!code.equals(code.toUpperCase(Locale.ROOT))) {
            System.out.println("Course code must be uppercase.");
            System.out.println();
            return;
        }

        if (courseCodes.add(code)) {
            System.out.println("Course added.");
        } else {
            System.out.println("Duplicate course rejected.");
        }

        System.out.println();
    }

    private static void enroll() {
        int id = readInt("Enter student ID: ");

        if (!students.containsKey(id)) {
            System.out.println("Student not found.");
            System.out.println();
            return;
        }

        String code = readText("Enter course code: ");

        if (!courseCodes.contains(code)) {
            System.out.println("Course not found.");
            System.out.println();
            return;
        }

        String pair = id + ":" + code;

        if (enrollmentPairs.add(pair)) {
            System.out.println("Enrollment added.");
        } else {
            System.out.println("Duplicate enrollment rejected.");
        }

        System.out.println();
    }

    private static void listStudentCourses() {
        int id = readInt("Enter student ID: ");

        if (!students.containsKey(id)) {
            System.out.println("Student not found.");
            System.out.println();
            return;
        }

        System.out.println("Courses for " + students.get(id) + ":");

        boolean found = false;

        for (String code : courseCodes) {
            String pair = id + ":" + code;

            if (enrollmentPairs.contains(pair)) {
                System.out.println(code);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No enrolled courses.");
        }

        System.out.println();
    }

    private static void listCourses() {
        if (courseCodes.isEmpty()) {
            System.out.println("No courses registered.");
            System.out.println();
            return;
        }

        System.out.println("Available courses:");

        for (String code : courseCodes) {
            System.out.println(code);
        }

        System.out.println();
    }

    private static String readText(String message) {
        while (true) {
            System.out.print(message);
            String text = input.nextLine().trim();

            if (!text.isEmpty()) {
                return text;
            }

            System.out.println("Input cannot be empty.");
        }
    }

    private static int readInt(String message) {
        while (true) {
            try {
                return Integer.parseInt(readText(message));
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid whole number.");
            }
        }
    }
}