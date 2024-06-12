import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class DSTesting {
    private static Map<Integer, Student> students = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int studentIdCounter = 1; // Counter to generate unique student IDs

    // List of available courses
    private static String[] availableCourses = {"Math", "Science", "History", "English"};

    public static void main(String[] args) {
        // Testing: Add 100 students
        long startTime = System.nanoTime();
        add100Students();
        long endTime = System.nanoTime();
        long addDuration = endTime - startTime;
        System.out.println("Time taken to add 100 students: " + addDuration + " nanoseconds");

        // Testing: Search for 100 students
        startTime = System.nanoTime();
        search100Students();
        endTime = System.nanoTime();
        long searchDuration = endTime - startTime;
        System.out.println("Time taken to search 100 students: " + searchDuration + " nanoseconds");
    }

    private static void add100Students() {
        for (int i = 0; i < 100; i++) {
            String name = "Student" + (i + 1);
            int registrationYear = 2022;
            int studentId = generateStudentId(registrationYear);
            Student student = new Student(name, registrationYear);
            students.put(studentId, student);
        }
    }

    private static void search100Students() {
        for (int i = 1; i <= 100; i++) {
            int studentId = generateStudentId(2022, i);
            Student student = students.get(studentId);
        }
    }

    // Existing methods for generating student ID and other functionalities
    private static int generateStudentId(int registrationYear) {
        String yearStr = String.valueOf(registrationYear);
        String idStr = yearStr.substring(2) + String.format("%03d", studentIdCounter++);
        return Integer.parseInt(idStr);
    }

    private static int generateStudentId(int registrationYear, int idCounter) {
        String yearStr = String.valueOf(registrationYear);
        String idStr = yearStr.substring(2) + String.format("%03d", idCounter);
        return Integer.parseInt(idStr);
    }

    private static void enrollCourse() {
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        if (students.containsKey(studentId)) {
            Student student = students.get(studentId);
            boolean done = false;
            while (!done) {
                displayAvailableCourses();
                System.out.print("Enter course to enroll (or 'done' to finish): ");
                String course = scanner.next();
                if (course.equalsIgnoreCase("done")) {
                    done = true;
                } else {
                    if (isValidCourse(course)) {
                        if (student.getEnrolledCourses().contains(course)) {
                            System.out.println(student.getName() + " is already enrolled in course: " + course);
                        } else {
                            student.enrollCourse(course);
                        }
                    } else {
                        System.out.println("Course not available. Please select from the available courses.");
                    }
                }
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private static boolean isValidCourse(String course) {
        for (String availableCourse : availableCourses) {
            if (availableCourse.equalsIgnoreCase(course)) {
                return true;
            }
        }
        return false;
    }

    private static void dropCourse() {
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        if (students.containsKey(studentId)) {
            Student student = students.get(studentId);
            System.out.print("Enter course to drop: ");
            String course = scanner.next();
            course = course.toLowerCase();
            if (student.getEnrolledCourses().contains(course)) {
                student.dropCourse(course);
            } else {
                System.out.println(student.getName() + " is not enrolled in course: " + course);
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    public static void payTuition() {
        System.out.println("Enter student ID");
        int studentId = scanner.nextInt();
        if (students.containsKey(studentId)) {
            Student student = students.get(studentId);
            System.out.println("Student " + student.getName() + "'s balance: $" + student.getBalance());
            System.out.println("Enter amount to pay: $");
            int amount = scanner.nextInt();
            student.payTuition(amount);

        } else {
            System.out.println("Student not found. Enter the correct ID");
        }
    }

    private static void searchStudentStatus() {
        System.out.print("Enter student ID: ");
        try {
            int studentId = scanner.nextInt();
            if (students.containsKey(studentId)) {
                Student student = students.get(studentId);
                System.out.println("Student Name: " + student.getName());
                System.out.println("Registration Year: " + student.getRegistrationYear());
                System.out.println("Enrolled Courses: " + student.getEnrolledCourses());
                System.out.println("Student balance: $" + student.getBalance());
            } else {
                System.out.println("Student not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a numeric student ID.");
            scanner.next();
        }
    }

    private static void displayAvailableCourses() {
        System.out.println("Available Courses:");
        for (String course : availableCourses) {
            System.out.println(course);
        }
    }
}

