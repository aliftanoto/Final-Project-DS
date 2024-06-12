import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class StudentManagementSystemHashMap {
    private static Map<Integer, Student> students = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int studentIdCounter = 1; // Counter to generate unique student IDs

    // List of available courses
    private static String[] availableCourses = {"Math", "Science", "History", "English"};

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Enroll Course");
            System.out.println("3. Drop Course");
            System.out.println("4. Pay tuition fee");
            System.out.println("5. Search Student Status by ID");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    enrollCourse();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    payTuition();
                    break;
                case 5:
                    searchStudentStatus();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addStudent() {
        int numOfStudents = 0;
        boolean validInput = false;

        // Loop until valid number of students is provided
        while (!validInput) {
            try {
                System.out.println("How many students do you want to add?");
                numOfStudents = scanner.nextInt();
                validInput = true; // If no exception, input is valid
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input from the scanner buffer
            }
        }

        for (int i = 0; i < numOfStudents; i++) {
            System.out.println("\nStudent " + (i + 1));

            System.out.print("Enter student name: ");
            scanner.nextLine(); // Clear the buffer
            String name = scanner.nextLine();

            validInput = false;
            int registrationYear = 0;

            // Loop until valid registration year is provided
            while (!validInput) {
                try {
                    System.out.print("Enter registration Year (2020 - 2024): ");
                    registrationYear = scanner.nextInt();
                    if (registrationYear >= 2020 && registrationYear <= 2024) {
                        validInput = true; // If no exception, input is valid
                    } else {
                        System.out.println("Invalid year. Please enter a year between 2020 and 2024.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid year.");
                    scanner.next(); // Clear the invalid input from the scanner buffer
                }
            }

            // Generate student ID with first two digits as the last two digits of registration year
            int studentId = generateStudentId(registrationYear);

            Student student = new Student(name, registrationYear);
            students.put(studentId, student);
            System.out.println("Student added successfully. Student ID: " + studentId);
        }
    }

    // Method to generate student ID with first two digits as the last two digits of registration year
    private static int generateStudentId(int registrationYear) {
        String yearStr = String.valueOf(registrationYear);
        String idStr = yearStr.substring(2) + String.format("%03d", studentIdCounter++);
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
                    // Check if the course is available
                    if (isValidCourse(course)) {
                        // Check if the student is already enrolled in the course
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

    // Method to check if the course is available
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
            // Convert course name to lowercase for case-insensitive comparison
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
            scanner.next(); // Clear the invalid input from the scanner buffer
        }
    }

    // Method to display available courses
    private static void displayAvailableCourses() {
        System.out.println("Available Courses:");
        for (String course : availableCourses) {
            System.out.println(course);
        }
    }
}

