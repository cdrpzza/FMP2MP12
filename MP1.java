package fmp2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class MP1 {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Student> students = new ArrayList<>();

    private static class Student {
        private int id;
        private String name;
        private double grade;

        private Student(int id, String name, double grade) {
            this.id = id;
            this.name = name;
            this.grade = grade;
        }

        private void display() {
            System.out.println(id + " - " + name + " - " + grade);
        }
    }

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("Student Grade System");
            System.out.println("1. Add student");
            System.out.println("2. List students");
            System.out.println("3. Find student");
            System.out.println("4. Rank students");
            System.out.println("5. Remove failing students");
            System.out.println("0. Exit");

            choice = readInt("Choice: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    showStudents(students);
                    break;
                case 3:
                    findStudent();
                    break;
                case 4:
                    rankStudents();
                    break;
                case 5:
                    removeFailing();
                    break;
                case 0:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);

        input.close();
    }

    private static void addStudent() {
        int id = readInt("Enter ID: ");

        for (Student s : students) {
            if (s.id == id) {
                System.out.println("ID already exists.");
                System.out.println("Student count: " + students.size());
                System.out.println();
                return;
            }
        }

        String name = readText("Enter name: ");
        Double grade = readGrade();

        if (grade == null) {
            System.out.println("Student not added.");
            System.out.println("Student count: " + students.size());
            System.out.println();
            return;
        }

        students.add(new Student(id, name, grade));
        System.out.println("Student added.");
        System.out.println("Student count: " + students.size());
        System.out.println();
    }

    private static void showStudents(ArrayList<Student> list) {
        if (list.isEmpty()) {
            System.out.println("No students to display.");
            System.out.println();
            return;
        }

        System.out.println("ID - Name - Grade");

        for (Student s : list) {
            s.display();
        }

        System.out.println();
    }

    private static void findStudent() {
        if (students.isEmpty()) {
            System.out.println("No students registered.");
            System.out.println();
            return;
        }

        int id = readInt("Enter ID to find: ");
        System.out.println();

        for (Student s : students) {
            if (s.id == id) {
                System.out.println("ID - Name - Grade");
                s.display();
                System.out.println();
                return;
            }
        }

        System.out.println("Student not found.");
        System.out.println();
    }

    private static void rankStudents() {
        ArrayList<Student> ranked = new ArrayList<>(students);

        Comparator<Student> order = new Comparator<Student>() {
            @Override
            public int compare(Student a, Student b) {
                int result = Double.compare(b.grade, a.grade);

                if (result == 0) {
                    return Integer.compare(a.id, b.id);
                }

                return result;
            }
        };

        ranked.sort(order);
        System.out.println("Students ranked by grade:");
        showStudents(ranked);
    }

    private static void removeFailing() {
        if (students.isEmpty()) {
            System.out.println("No students registered.");
            System.out.println();
            return;
        }

        Iterator<Student> it = students.iterator();
        int removed = 0;

        while (it.hasNext()) {
            Student s = it.next();

            if (s.grade < 75) {
                it.remove();
                removed++;
            }
        }

        System.out.println("Students removed: " + removed);
        System.out.println("Student count: " + students.size());
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
            System.out.println();
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

    private static Double readGrade() {
        try {
            double grade = Double.parseDouble(
                    readText("Enter grade (0-100): "));

            if (Double.isFinite(grade) && grade >= 0 && grade <= 100) {
                return grade;
            }

            System.out.println("Grade must be from 0 to 100.");
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid number.");
        }

        return null;
    }
}