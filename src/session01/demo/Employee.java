package session01.demo;

import java.util.Date;
import java.util.Scanner;

public class Employee {
    private String empId;
    private String fullName;
    private boolean gender;
    private Date birthday;
    private String address;
    private float yearInWorks;
    private double salary;

    Scanner sc = new Scanner(System.in);
    public Employee() {
    }

    public Employee(String empId, String fullName, boolean gender, Date birthday, String address, float yearInWorks, double salary) {
        this.empId = empId;
        this.fullName = fullName;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.yearInWorks = yearInWorks;
        this.salary = salary;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        if(empId.matches("E\\\\w{3}")){
            this.empId = empId;
        }else{
            System.out.println("Invalid Employee ID");
        }
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if(fullName.length()>2){
            this.fullName = fullName;
        }else{
            System.out.println("Invalid Full Name");
        }

    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if(address.length()>2){
            this.address = address;
        }else{
            System.out.println("Invalid Address");
        }
    }

    public float getYearInWorks() {
        return yearInWorks;
    }

    public void setYearInWorks(float yearInWorks) {
        if(yearInWorks>0){
            this.yearInWorks = yearInWorks;
        }else{
            System.out.println("Invalid Year in Works");
        }
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if(salary>=0){
            this.salary = salary;
        }else{
            System.out.println("Invalid Salary");
        }
    }

    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }
}
