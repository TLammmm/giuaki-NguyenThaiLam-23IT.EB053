package Main;


public class Employee implements IEmployee {
	 private int ID;
	    private String fullName;
	    private String birthDay;
	    private String phone;
	    private String email;
	    private String employeeType;
	    private static int employeeCount = 0;
		public int getID() {
			return ID;
		}
		public void setID(int iD) {
			ID = iD;
		}
		public String getFullName() {
			return fullName;
		}
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}
		public String getBirthDay() {
			return birthDay;
		}
		public void setBirthDay(String birthDay) {
			this.birthDay = birthDay;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getEmployeeType() {
			return employeeType;
		}
		public void setEmployeeType(String employeeType) {
			this.employeeType = employeeType;
		}
		public static int getEmployeeCount() {
			return employeeCount;
		}
		public static void setEmployeeCount(int employeeCount) {
			Employee.employeeCount = employeeCount;
		}
		public Employee(int ID, String fullName, String birthDay, String phone, String email, String employeeType) {
	        this.ID = ID;
	        this.fullName = fullName;
	        this.birthDay = birthDay;
	        this.phone = phone;
	        this.email = email;
	        this.employeeType = employeeType;
	        employeeCount++;
	    }
		 public void showInfo() {
		        System.out.println("ID: " + ID);
		        System.out.println("Full Name: " + fullName);
		        System.out.println("Birth Day: " + birthDay);
		        System.out.println("Phone: " + phone);
		        System.out.println("Email: " + email);
		        System.out.println("Employee Type: " + employeeType);
		    }
		
		
}
