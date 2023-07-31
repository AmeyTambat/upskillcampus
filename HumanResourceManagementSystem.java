package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Employee {
    private String name;
    private String employeeID;
    private String designation;
    private String department;
    private String contactInfo;

    public Employee(String name, String employeeID, String designation, String department, String contactInfo) {
        this.name = name;
        this.employeeID = employeeID;
        this.designation = designation;
        this.department = department;
        this.contactInfo = contactInfo;
    }

    public String getName() {
        return name;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeID + ", Name: " + name + ", Designation: " + designation +
                ", Department: " + department + ", Contact Info: " + contactInfo;
    }
}

class Leave {
    private String employeeID;
    private String leaveType;
    private String startDate;
    private String endDate;
    private boolean approved;

    public Leave(String employeeID, String leaveType, String startDate, String endDate) {
        this.employeeID = employeeID;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approved = false;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeID + ", Leave Type: " + leaveType + ", Start Date: " + startDate +
                ", End Date: " + endDate + ", Approved: " + approved;
    }
}

public class HRMS {
    private List<Employee> employees;
    private List<Leave> leaveRecords;

    public HRMS() {
        employees = new ArrayList<>();
        leaveRecords = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        System.out.println("Employee added successfully.");
    }

    public void viewEmployeeDetails() {
        if (employees.isEmpty()) {
            System.out.println("No employee records found.");
        } else {
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }

    public void updateEmployee(String employeeID) {
        boolean found = false;
        for (Employee employee : employees) {
            if (employee.getEmployeeID().equalsIgnoreCase(employeeID)) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                System.out.print("Enter new designation: ");
                String newDesignation = scanner.nextLine();
                System.out.print("Enter new department: ");
                String newDepartment = scanner.nextLine();
                System.out.print("Enter new contact information: ");
                String newContactInfo = scanner.nextLine();

                employee = new Employee(newName, employeeID, newDesignation, newDepartment, newContactInfo);
                found = true;
                System.out.println("Employee details updated successfully.");
                break;
            }
        }
        if (!found) {
            System.out.println("Employee not found.");
        }
    }

    public void deleteEmployee(String employeeID) {
        boolean found = false;
        Employee employeeToRemove = null;
        for (Employee employee : employees) {
            if (employee.getEmployeeID().equalsIgnoreCase(employeeID)) {
                employeeToRemove = employee;
                found = true;
                break;
            }
        }
        if (found) {
            employees.remove(employeeToRemove);
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void markAttendance(String employeeID, String attendanceStatus) {
        boolean found = false;
        for (Employee employee : employees) {
            if (employee.getEmployeeID().equalsIgnoreCase(employeeID)) {
                System.out.println("Attendance marked for Employee ID: " + employeeID + ", Status: " + attendanceStatus);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Employee not found.");
        }
    }

    public void addLeave(Leave leave) {
        leaveRecords.add(leave);
        System.out.println("Leave request added successfully.");
    }

    public void viewLeaveRecords() {
        if (leaveRecords.isEmpty()) {
            System.out.println("No leave records found.");
        } else {
            for (Leave leave : leaveRecords) {
                System.out.println(leave);
            }
        }
    }

    public void approveLeave(String leaveID) {
        boolean found = false;
        for (Leave leave : leaveRecords) {
            if (leave.getEmployeeID().equalsIgnoreCase(leaveID) && !leave.isApproved()) {
                leave.setApproved(true);
                found = true;
                System.out.println("Leave request approved for Employee ID: " + leaveID);
                break;
            }
        }
        if (!found) {
            System.out.println("Leave request not found or already approved.");
        }
    }

    public void rejectLeave(String leaveID) {
        boolean found = false;
        for (Leave leave : leaveRecords) {
            if (leave.getEmployeeID().equalsIgnoreCase(leaveID) && !leave.isApproved()) {
                leaveRecords.remove(leave);
                found = true;
                System.out.println("Leave request rejected for Employee ID: " + leaveID);
                break;
            }
        }
        if (!found) {
            System.out.println("Leave request not found or already approved.");
        }
    }

    public void searchEmployee(String searchQuery) {
        boolean found = false;
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase(searchQuery) ||
                    employee.getEmployeeID().equalsIgnoreCase(searchQuery) ||
                    employee.getDepartment().equalsIgnoreCase(searchQuery)) {
                System.out.println(employee);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No employee found matching the search query.");
        }
    }

    public static void main(String[] args) {
        HRMS hrms = new HRMS();
        Scanner scanner = new Scanner(System.in);

        int choice = -1;
        while (choice != 0) {
            System.out.println("----- HRMS Menu -----");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employee Details");
            System.out.println("3. Update Employee Details");
            System.out.println("4. Delete Employee");
            System.out.println("5. Mark Attendance");
            System.out.println("6. Add Leave Request");
            System.out.println("7. View Leave Records");
            System.out.println("8. Approve Leave");
            System.out.println("9. Reject Leave");
            System.out.println("10. Search Employee");
            System.out.println("0. Exit HRMS");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter employee ID: ");
                    String employeeID = scanner.nextLine();
                    System.out.print("Enter designation: ");
                    String designation = scanner.nextLine();
                    System.out.print("Enter department: ");
                    String department = scanner.nextLine();
                    System.out.print("Enter contact information: ");
                    String contactInfo = scanner.nextLine();

                    Employee employee = new Employee(name, employeeID, designation, department, contactInfo);
                    hrms.addEmployee(employee);
                    break;
                case 2:
                    hrms.viewEmployeeDetails();
                    break;
                case 3:
                    System.out.print("Enter employee ID to update: ");
                    String updateEmployeeID = scanner.nextLine();
                    hrms.updateEmployee(updateEmployeeID);
                    break;
                case 4:
                    System.out.print("Enter employee ID to delete: ");
                    String deleteEmployeeID = scanner.nextLine();
                    hrms.deleteEmployee(deleteEmployeeID);
                    break;
                case 5:
                    System.out.print("Enter employee ID for attendance marking: ");
                    String attendanceEmployeeID = scanner.nextLine();
                    System.out.print("Enter attendance status (Present/Absent/Leave): ");
                    String attendanceStatus = scanner.nextLine();
                    hrms.markAttendance(attendanceEmployeeID, attendanceStatus);
                    break;
                case 6:
                    System.out.print("Enter employee ID for leave request: ");
                    String leaveEmployeeID = scanner.nextLine();
                    System.out.print("Enter leave type: ");
                    String leaveType = scanner.nextLine();
                    System.out.print("Enter start date: ");
                    String startDate = scanner.nextLine();
                    System.out.print("Enter end date: ");
                    String endDate = scanner.nextLine();

                    Leave leave = new Leave(leaveEmployeeID, leaveType, startDate, endDate);
                    hrms.addLeave(leave);
                    break;
                case 7:
                    hrms.viewLeaveRecords();
                    break;
                case 8:
                    System.out.print("Enter leave request ID to approve: ");
                    String approveLeaveID = scanner.nextLine();
                    hrms.approveLeave(approveLeaveID);
                    break;
                case 9:
                    System.out.print("Enter leave request ID to reject: ");
                    String rejectLeaveID = scanner.nextLine();
                    hrms.rejectLeave(rejectLeaveID);
                    break;
                case 10:
                    System.out.print("Enter search query (name, employee ID, or department): ");
                    String searchQuery = scanner.nextLine();
                    hrms.searchEmployee(searchQuery);
                    break;
                case 0:
                    System.out.println("Exiting HRMS. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }
}
