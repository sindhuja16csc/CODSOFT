import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Student {
    private String name;
    private int rollNo;
    private String grade;
    private String dep;
    public Student(String name, int rollNo, String grade, String dep) {
        this.name = name;
        this.rollNo = rollNo;
        this.grade = grade;
        this.dep = dep;
    }
    public String getName() {
        return name;
    }
    public int getRollNumber() {
        return rollNo;
    }
    public String getGrade() {
        return grade;
    }
    public String getDep()
    {
        return dep;
    }
    @Override
    public String toString() {
        return "Name: " + name + "\n Reg Number: " + rollNo + "\n Grade: " + grade + "\n Department :" + (String) dep;
    }
}
class StudentManagementSystem {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }
    public void removeStudent(int rollNo) {
        students.removeIf(student -> student.getRollNumber() == rollNo);
    }
    public Student findStudent(int rollNo) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNo) {
                return student;
            }
        }
        return null;
    }
    public List<Student> getAllStudents() {
        return students;
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            students = (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
public class Task3 {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Student Management System");
            System.out.println("1. Enroll New Student");
            System.out.println("2. Remove Student Details");
            System.out.println("3. Look Up Student Details");
            System.out.println("4. Get All Students Details");
            System.out.println("5. Save to File");
            System.out.println("6. Load from File");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Registration Number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter Grade: ");
                    String grade = scanner.nextLine();
                    System.out.print("Enter Branch/Sec: ");
                    String dep = scanner.nextLine();
                    Student student = new Student(name, rollNumber, grade,dep);
                    sms.addStudent(student);
                    System.out.println("\n**Successfully Saved the Record**\n");
                    break;
                case 2:
                    System.out.print("Enter Reg number to remove: ");
                    int rollToRemove = scanner.nextInt();
                    scanner.nextLine(); 
                    Student deleteStudent = sms.findStudent(rollToRemove);
                    System.out.println(deleteStudent);
                    System.out.println("Do you want to remove[Y/N]");
                    char ans = scanner.next().charAt(0);
                    if(ans == 'y'||ans == 'Y')
                    {
                        sms.removeStudent(rollToRemove);
                        System.out.println("**Record is Removed**\n");
                    }
                    break;
                case 3:
                    System.out.print("Enter Reg number to search: ");
                    int rollToSearch = scanner.nextInt();
                    scanner.nextLine(); 
                    Student foundStudent = sms.findStudent(rollToSearch);
                    if (foundStudent != null) {
                        System.out.println("Student found: " + foundStudent);
                    } else {
                        System.out.println("**Student not found \n Check the Regno you Entered.**\n");
                    }
                    break;
                case 4:
                    List<Student> allStudents = sms.getAllStudents();
                    if (allStudents.isEmpty()) {
                        System.out.println("**No Students in the System.**\n");
                    } else {
                        for (Student s : allStudents) {
                            System.out.println(s);
                        }
                    }
                    break;
                case 5:
                    sms.saveToFile("students.dat");
                    System.out.println("Data saved to file.");
                    break;
                case 6:
                    sms.loadFromFile("students.dat");
                    System.out.println("Data loaded from file.");
                    break;
                case 7:
                    System.out.println("Exiting application.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
