import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private ArrayList<String> registeredStudents;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public boolean hasAvailableSlot() {
        return registeredStudents.size() < capacity;
    }

    public boolean registerStudent(String studentId) {
        if (hasAvailableSlot()) {
            registeredStudents.add(studentId);
            return true;
        }
        return false;
    }

    public boolean removeStudent(String studentId) {
        return registeredStudents.remove(studentId);
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getAvailableSlots() {
        return capacity - registeredStudents.size();
    }
}

class Student {
    private String studentId;
    private String name;
    private ArrayList<String> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public boolean registerCourse(String courseCode) {
        if (!registeredCourses.contains(courseCode)) {
            registeredCourses.add(courseCode);
            return true;
        }
        return false;
    }

    public boolean dropCourse(String courseCode) {
        return registeredCourses.remove(courseCode);
    }

    public String getStudentId() {
        return studentId;
    }
}

class CourseRegistrationSystem {
    private Map<String, Course> courses;
    private Map<String, Student> students;

    public CourseRegistrationSystem() {
        courses = new HashMap<>();
        students = new HashMap<>();
    }

    public void addCourse(String code, String title, String description, int capacity, String schedule) {
        System.out.println(
                "=================================================================================================================");

        if (!courses.containsKey(code)) {
            courses.put(code, new Course(code, title, description, capacity, schedule));
        }
    }

    public void addStudent(String studentId, String name) {
        System.out.println(
                "=================================================================================================================");

        if (!students.containsKey(studentId)) {
            students.put(studentId, new Student(studentId, name));
        }
    }

    public void listCourses() {

        System.out.println(
                "=================================================================================================================");
        for (Course course : courses.values()) {
            System.out.println("Course Code: " + course.getCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Capacity: " + course.getCapacity());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println("Available Slots: " + course.getAvailableSlots());
            System.out.println();
        }
    }

    public void registerStudentForCourse(String studentId, String courseCode) {
        System.out.println(
                "=================================================================================================================");

        if (students.containsKey(studentId) && courses.containsKey(courseCode)) {
            Student student = students.get(studentId);
            Course course = courses.get(courseCode);
            if (course.registerStudent(studentId)) {
                student.registerCourse(courseCode);
                System.out.println("Student " + studentId + " registered for course " + courseCode);
            } else {
                System.out.println("No available slots for course " + courseCode);
            }
        } else {
            System.out.println("Invalid student ID or course code");
        }
    }

    public void removeStudentFromCourse(String studentId, String courseCode) {
        System.out.println(
                "=================================================================================================================");

        if (students.containsKey(studentId) && courses.containsKey(courseCode)) {
            Student student = students.get(studentId);
            Course course = courses.get(courseCode);
            if (course.removeStudent(studentId)) {
                student.dropCourse(courseCode);
                System.out.println("Student " + studentId + " dropped from course " + courseCode);
            } else {
                System.out.println("Student " + studentId + " is not registered in course " + courseCode);
            }
        } else {
            System.out.println("Invalid student ID or course code");
        }
    }

    public static void main(String[] args) {
        System.out.println(
                "=================================================================================================================");

        CourseRegistrationSystem system = new CourseRegistrationSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Course");
            System.out.println("2. Add Student");
            System.out.println("3. List Courses");
            System.out.println("4. Register Student for Course");
            System.out.println("5. Remove Student from Course");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {

                case 1:
                    System.out.println(
                            "=================================================================================================================");

                    System.out.print("Enter course code: ");
                    String code = scanner.nextLine();
                    System.out.print("Enter course title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter course description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter course capacity: ");
                    int capacity = scanner.nextInt();
                    scanner.nextLine(); // consume the newline
                    System.out.print("Enter course schedule: ");
                    String schedule = scanner.nextLine();
                    system.addCourse(code, title, description, capacity, schedule);
                    break;
                case 2:
                    System.out.println(
                            "=================================================================================================================");

                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    system.addStudent(studentId, name);
                    break;
                case 3:

                    system.listCourses();
                    System.out.println(
                            "=================================================================================================================");

                    break;
                case 4:
                    System.out.println(
                            "=================================================================================================================");

                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextLine();
                    System.out.print("Enter course code: ");
                    code = scanner.nextLine();
                    system.registerStudentForCourse(studentId, code);
                    break;
                case 5:
                    System.out.println(
                            "=================================================================================================================");

                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextLine();
                    System.out.print("Enter course code: ");
                    code = scanner.nextLine();
                    system.removeStudentFromCourse(studentId, code);
                    break;
                case 6:
                    System.out.println(
                            "=================================================================================================================");

                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
