package Main;

public class ExperienceEmployee extends Employee{
	int explnYear;
	String skill;
	public ExperienceEmployee(int ID, String fullName, String birthDay, String phone, String email, String employeeType,
			int explnYear, String skill) {
		super(ID, fullName, birthDay, phone, email, "ExperienceEmployee");
		this.explnYear = explnYear;
		this.skill = skill;
	}
	public int getExplnYear() {
		return explnYear;
	}
	public void setExplnYear(int explnYear) {
		this.explnYear = explnYear;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public void showInfo() {
        super.showInfo();
        System.out.println("Experience in Year: " + explnYear);
        System.out.println("Professional Skill: " + skill);
    }

}
