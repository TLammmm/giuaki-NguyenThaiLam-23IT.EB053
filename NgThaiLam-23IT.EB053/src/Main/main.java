package Main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class main {
public static void main(String [] args) {
	Scanner scanner=new Scanner(System.in);
	 boolean exit = false;
	 while (!exit){
         System.out.println("-------- Quản lý nhân viên --------");
         System.out.println("1. Thêm nhân viên");
         System.out.println("2. Sửa thông tin nhân viên");
         System.out.println("3. Xóa nhân viên");
         System.out.println("4. Hiển thị danh sách nhân viên");
         System.out.println("0. Thoát chương trình");
         System.out.print("Nhập lựa chọn của bạn: ");

         int choice = scanner.nextInt();
         scanner.nextLine();

         switch (choice) {
             case 0:
                 exit = true;
                 break;
             case 1:
                 System.out.println("Chọn loại nhân viên:");
                 System.out.println("1. Nhân viên có kinh nghiệm");
                 System.out.println("2. Nhân viên mới tốt nghiệp");
                 System.out.println("3. Thực tập sinh");
                 System.out.print("Nhập lựa chọn của bạn: ");
                 int employeeType = scanner.nextInt();
                 scanner.nextLine();

                 switch (employeeType) {
                 case 1:
                     ExperienceEmployee experienceEmployee = inputExperienceEmployee();
                     insertEmployee(experienceEmployee);
                     break;
                 case 2:
                	 FresherEmployee fresherEmployee = inputFresherEmployee();
                     insertEmployee(fresherEmployee);
                	 break;
                 case 3:
                	 InternEmployee internEmployee = inputInternEmployee();
                     insertEmployee(internEmployee);
                     default:
                         System.out.println("Lựa chọn không hợp lệ!");
                         break;
                 }
                 break;
             case 3:
            	 int IDD;
            	 System.out.print("nhập ID: ");
            	 IDD=scanner.nextInt();
            	 deleteEmployee(IDD);
            	 break;
             default:
                 System.out.println("Lựa chọn không hợp lệ!");
                 break;
         }

         System.out.println();
     }
     System.out.println("Đã thoát chương trình.");
 }
public static void insertEmployee(Employee employee) {
	Connection c=jd.getConnection();
    String sql = "INSERT INTO employee (Id, Fullname, BirthDay, Phone, Email, Type, exyear, skill, gradate, rank, education, major, semester, university) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try {
    	
    PreparedStatement statement = c.prepareStatement(sql);
    statement.setInt(1, employee.getID());
    statement.setString(2, employee.getFullName());
    statement.setString(3, employee.getBirthDay());
    statement.setString(4, employee.getPhone());
    statement.setString(5, employee.getEmail());
    statement.setString(6, employee.getEmployeeType());       
    if (employee instanceof ExperienceEmployee) {
        ExperienceEmployee experience = (ExperienceEmployee)employee;
        statement.setInt(7, experience.getExplnYear());
        statement.setString(8, experience.getSkill());
        statement.setNull(9, Types.NULL);
        statement.setNull(10, Types.NULL);
        statement.setNull(11, Types.NULL);
        statement.setNull(12, Types.NULL);
        statement.setNull(13, Types.NULL);
        statement.setNull(14, Types.NULL);
    } else if (employee instanceof FresherEmployee) {
        FresherEmployee fresher = (FresherEmployee) employee;
        statement.setNull(7, Types.NULL);
        statement.setNull(8, Types.NULL);
        statement.setString(9, fresher.getGraduationDate());
        statement.setString(10, fresher.getGraduationRank());
        statement.setString(11, fresher.getEducation());
        statement.setNull(12, Types.NULL);
        statement.setNull(13, Types.NULL);
        statement.setNull(14, Types.NULL);
    } else if (employee instanceof InternEmployee) {
    	InternEmployee intern = (InternEmployee) employee;
        statement.setNull(7, Types.NULL);
        statement.setNull(8, Types.NULL);
        statement.setNull(9, Types.NULL);
        statement.setNull(10, Types.NULL);
        statement.setNull(11, Types.NULL);
        statement.setString(12, intern.getMajors());
        statement.setString(13, intern.getSemester());
        statement.setString(14, intern.getUniversityName());
    }        
    statement.executeUpdate();
    Employee.setEmployeeCount(Employee.getEmployeeCount()+1);
    
}catch(Exception e) {
	e.printStackTrace();
}
    try {
       BufferedWriter writer = new BufferedWriter(new FileWriter("txt.employee", true));
       writer.write("Id: " + String.valueOf(employee.getID()));       
       writer.newLine();
        writer.write("Fullname: " + employee.getFullName());
        writer.newLine();
        writer.write("BirthDay: " + employee.getBirthDay());
        writer.newLine();       
        writer.close();
        System.out.println("Thêm nhân viên thành công.");
    } catch (IOException e) {
        System.out.println("Lỗi khi ghi vào file: " + e.getMessage());
    }
}

public static  ExperienceEmployee inputExperienceEmployee() {
    Scanner scanner = new Scanner(System.in);
    
    System.out.println("Nhập thông tin nhân viên có kinh nghiệm:");
    
    System.out.print("ID: ");
    int ID = scanner.nextInt();
    scanner.nextLine();
    System.out.print("Họ và tên: ");
    String fullName = scanner.nextLine();
    
    System.out.print("Ngày sinh: ");
    String birthDay = scanner.nextLine();
    
    System.out.print("Số điện thoại: ");
    String phone = scanner.nextLine();
    
    System.out.print("Email: ");
    String email = scanner.nextLine();
    
    System.out.print("Số năm kinh nghiệm: ");
    int expInYear = scanner.nextInt();
    scanner.nextLine();
    
    System.out.print("Kỹ năng chuyên môn: ");
    String proSkill = scanner.nextLine();
    
    ExperienceEmployee experience = new ExperienceEmployee(ID, fullName, birthDay, phone, email,"exper", expInYear, proSkill);
    
    return experience;
}
public static FresherEmployee inputFresherEmployee() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Nhập thông tin nhân viên mới tốt nghiệp:");
    System.out.print("ID: ");
    int ID = scanner.nextInt();
    scanner.nextLine();
    System.out.print("Họ và tên: ");
    String fullName = scanner.nextLine();
    
    System.out.print("Ngày sinh: ");
    String birthDay = scanner.nextLine();
    
    System.out.print("Số điện thoại: ");
    String phone = scanner.nextLine();
    
    System.out.print("Email: ");
    String email = scanner.nextLine();
    
    System.out.print("Ngày tốt nghiệp: ");
    String graduationDate = scanner.nextLine();
    
    System.out.print("Xếp loại tốt nghiệp: ");
    String graduationRank = scanner.nextLine();
    
    System.out.print("Trường đại học: ");
    String education = scanner.nextLine();
    
    FresherEmployee fresher = new FresherEmployee(ID, fullName, birthDay, phone, email, graduationDate, graduationRank, education);
    
    return fresher;
}
public static InternEmployee inputInternEmployee() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Nhập thông tin thực tập sinh:");
    System.out.print("ID: ");
    int ID = scanner.nextInt();
    scanner.nextLine();
    System.out.print("Họ và tên: ");
    String fullName = scanner.nextLine();
    System.out.print("Ngày sinh: ");
    String birthDay = scanner.nextLine();
    System.out.print("Số điện thoại: ");
    String phone = scanner.nextLine();
    System.out.print("Email: ");
    String email = scanner.nextLine();
    System.out.print("Ngành học: ");
    String majors = scanner.nextLine();
    System.out.print("Học kỳ: ");
    String semester = scanner.nextLine();
    System.out.print("Tên trường đại học: ");
    String universityName = scanner.nextLine();
    InternEmployee intern = new InternEmployee(ID, fullName, birthDay, phone, email, majors, semester, universityName);
    return intern;
}
public static void deleteEmployee(int employeeId) {
    try {
    	 String sql = "DELETE FROM employee WHERE ID = ?";
    	    Connection connection = jd.getConnection();
    	    PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, employeeId);
        int row = statement.executeUpdate();
        if (row > 0) {
            System.out.println("Đã xóa nhân viên có ID: " + employeeId);
        } else {
            System.out.println("Không tìm thấy nhân viên có ID: " + employeeId);
        }
    } catch (SQLException e) {
        System.out.println("Lỗi khi xóa nhân viên: " + e.getMessage());
    }     
}
}

