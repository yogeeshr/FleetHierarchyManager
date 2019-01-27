package com.fleethierarchymanager;

import com.fleethierarchymanager.entitites.City;
import com.fleethierarchymanager.entitites.Employee;
import com.fleethierarchymanager.entitites.FleetManagementData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Util {

    /**
     * Method to add employee and manager relation, honoring different cases where employee has just one manager
     * -> City manager has only Fleet Manager under them
     * -> Fleet manager has only Delivery executives under them
     *
     * @param mainEmployee
     * @param reportees
     * @throws IllegalStateException
     */
    public static synchronized void addReportee(Employee mainEmployee, List<Employee> reportees)
            throws IllegalStateException {

        // Fail Case : Delivery executive cannot have reportee
        if (mainEmployee.getEmployeeType().equals(EmployeeTypeEnum.DELIVERY_EXECUTIVE)) {
            throw new IllegalStateException(EmployeeTypeEnum.DELIVERY_EXECUTIVE + " cannot have any reportee");
        }

        // Fail Case : Main employee is City manager and has reportees where one or more reportees is not Fleet Manager
        if (mainEmployee.getEmployeeType().equals(EmployeeTypeEnum.CITY_MANAGER)) {
            boolean isAllReporteeFleetManager = Util.checkEmployeeAndType(reportees, EmployeeTypeEnum.FLEET_MANAGER);

            if (!isAllReporteeFleetManager) {
                throw new IllegalStateException(" One of the reportees for city manager ID : " + mainEmployee.getId() +
                        " is not FleetManager ");
            }
        }

        // Fail Case : Main employee is Fleet manager and one or more reportees is not Delivery Executive
        if (mainEmployee.getEmployeeType().equals(EmployeeTypeEnum.FLEET_MANAGER)) {
            boolean isAllReporteeFleetManager = Util.checkEmployeeAndType(reportees, EmployeeTypeEnum.DELIVERY_EXECUTIVE);

            if (!isAllReporteeFleetManager) {
                throw new IllegalStateException(" One of the reportees for city manager ID : " + mainEmployee.getId() +
                        " is not FleetManager ");
            }
        }

        int ratingSum = 0;
        
        // adding managers to reportees
        for (Employee employee : reportees) {
            ratingSum = ratingSum+employee.getRating();
            if (!addManager(mainEmployee, employee)) {
                throw new IllegalStateException(" Employee " + employee.getId() +
                        " already has a manager and cannot be added");
            }
        }

        // adding all reporteed to manager
        mainEmployee.getReportees().addAll(reportees);
        mainEmployee.setReporteesRatingSum(ratingSum);

    }


    /**
     * Method to add manager to employee
     *
     * @param manager
     * @param reportee
     * @return
     */
    private static synchronized boolean addManager(Employee manager, Employee reportee) {
        if (reportee.getManager().getId() == 0) {
            return false;
        }
        reportee.setManager(manager);
        return true;
    }

    /**
     * Method to check if all sent employee is of particular Type
     *
     * @param employees
     * @param employeeTypeEnum
     * @return
     */
    private static boolean checkEmployeeAndType(List<Employee> employees, EmployeeTypeEnum employeeTypeEnum) {

        for (Employee employee : employees) {
            if (!employee.getEmployeeType().equals(employeeTypeEnum)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Utility method to generate static data for creating hierarchy
     *
     * @return fleetManagementData
     */
    public static FleetManagementData createHierarchy() {

        FleetManagementData fleetManagementData = new FleetManagementData();

        // * * *  Create City 1's Data * * *
        City city = new City(1, "Mysore", 12.2958, 76.6394);

        Employee employee = new Employee(1, 5, "Ram", BigInteger.valueOf(1000000),
                BigInteger.valueOf(0), 0,  new ArrayList<Employee>(), null,
                EmployeeTypeEnum.CITY_MANAGER);

        // Adding Fleet Manager's to City 1
        List<Employee> employees = new ArrayList<Employee>();
        List<Employee> deEmployees = new ArrayList<Employee>();

        Employee temp = new Employee(3, 3, "Lav", BigInteger.valueOf(500000), BigInteger.valueOf(0),
                0, new ArrayList<Employee>(), employee, EmployeeTypeEnum.FLEET_MANAGER);
        fleetManagementData.getIdToEmployeeMapping().put(temp.getId(), temp);

        // Adding Delivery Manager to FLeet Manager 1 reportee
        Employee deEmployee = new Employee(8, 1, "Krish", BigInteger.valueOf(250000), BigInteger.valueOf(0),
                0, new ArrayList<Employee>(), employee, EmployeeTypeEnum.DELIVERY_EXECUTIVE);
        fleetManagementData.getIdToEmployeeMapping().put(deEmployee.getId(), deEmployee);
        deEmployees.add(deEmployee);

        deEmployee = new Employee(9, 2, "Manu", BigInteger.valueOf(250000), BigInteger.valueOf(0),
                0, new ArrayList<Employee>(), employee, EmployeeTypeEnum.DELIVERY_EXECUTIVE);
        fleetManagementData.getIdToEmployeeMapping().put(deEmployee.getId(), deEmployee);
        deEmployees.add(deEmployee);

        deEmployee = new Employee(10, 3, "Rajeev", BigInteger.valueOf(250000), BigInteger.valueOf(0),
                0, new ArrayList<Employee>(), employee, EmployeeTypeEnum.DELIVERY_EXECUTIVE);
        fleetManagementData.getIdToEmployeeMapping().put(deEmployee.getId(), deEmployee);
        deEmployees.add(deEmployee);

        deEmployee = new Employee(11, 4, "Rajath", BigInteger.valueOf(250000), BigInteger.valueOf(0),
                0, new ArrayList<Employee>(), employee, EmployeeTypeEnum.DELIVERY_EXECUTIVE);
        fleetManagementData.getIdToEmployeeMapping().put(deEmployee.getId(), deEmployee);
        deEmployees.add(deEmployee);

        deEmployee = new Employee(12, 5, "Sharath", BigInteger.valueOf(250000), BigInteger.valueOf(0),
                0, new ArrayList<Employee>(), employee, EmployeeTypeEnum.DELIVERY_EXECUTIVE);
        fleetManagementData.getIdToEmployeeMapping().put(deEmployee.getId(), deEmployee);
        deEmployees.add(deEmployee);

        deEmployee = new Employee(13, 5, "Parth", BigInteger.valueOf(250000), BigInteger.valueOf(0),
                0, new ArrayList<Employee>(), employee, EmployeeTypeEnum.DELIVERY_EXECUTIVE);
        fleetManagementData.getIdToEmployeeMapping().put(deEmployee.getId(), deEmployee);
        deEmployees.add(deEmployee);

        // Add DE's to FM1 of city 1
        Util.addReportee(temp, deEmployees);

        employees.add(temp);

        deEmployees = new ArrayList<Employee>();

        // FM 2 of city 1
        temp = new Employee(4, 3, "Kush", BigInteger.valueOf(500000), BigInteger.valueOf(0),
                0, new ArrayList<Employee>(), employee, EmployeeTypeEnum.FLEET_MANAGER);
        fleetManagementData.getIdToEmployeeMapping().put(temp.getId(), temp);

        deEmployee = new Employee(14, 1, "Nikhil", BigInteger.valueOf(250000), BigInteger.valueOf(0),
                0, new ArrayList<Employee>(), employee, EmployeeTypeEnum.DELIVERY_EXECUTIVE);
        fleetManagementData.getIdToEmployeeMapping().put(deEmployee.getId(), deEmployee);
        deEmployees.add(deEmployee);

        deEmployee = new Employee(15, 2, "Rohit", BigInteger.valueOf(250000), BigInteger.valueOf(0),
                0, new ArrayList<Employee>(), employee, EmployeeTypeEnum.DELIVERY_EXECUTIVE);
        fleetManagementData.getIdToEmployeeMapping().put(deEmployee.getId(), deEmployee);
        deEmployees.add(deEmployee);

        deEmployee = new Employee(16, 5, "Nitin", BigInteger.valueOf(250000), BigInteger.valueOf(0),
                0, new ArrayList<Employee>(), employee, EmployeeTypeEnum.DELIVERY_EXECUTIVE);
        fleetManagementData.getIdToEmployeeMapping().put(deEmployee.getId(), deEmployee);
        deEmployees.add(deEmployee);

        Util.addReportee(temp, deEmployees);

        employees.add(temp);

        deEmployees = new ArrayList<Employee>();

        // FM 3 of city 1
        temp = new Employee(5, 3, "Raj", BigInteger.valueOf(500000), BigInteger.valueOf(0),
                0, new ArrayList<Employee>(), employee, EmployeeTypeEnum.FLEET_MANAGER);
        fleetManagementData.getIdToEmployeeMapping().put(temp.getId(), temp);

        deEmployee = new Employee(17, 1, "Mayank", BigInteger.valueOf(250000), BigInteger.valueOf(0),
                0, new ArrayList<Employee>(), employee, EmployeeTypeEnum.DELIVERY_EXECUTIVE);
        fleetManagementData.getIdToEmployeeMapping().put(deEmployee.getId(), deEmployee);
        deEmployees.add(deEmployee);

        deEmployee = new Employee(18, 2, "Aditya", BigInteger.valueOf(250000), BigInteger.valueOf(0),
                0, new ArrayList<Employee>(), employee, EmployeeTypeEnum.DELIVERY_EXECUTIVE);
        fleetManagementData.getIdToEmployeeMapping().put(deEmployee.getId(), deEmployee);
        deEmployees.add(deEmployee);

        deEmployee = new Employee(19, 3, "Yash", BigInteger.valueOf(250000), BigInteger.valueOf(0),
                0, new ArrayList<Employee>(), employee, EmployeeTypeEnum.DELIVERY_EXECUTIVE);
        fleetManagementData.getIdToEmployeeMapping().put(deEmployee.getId(), deEmployee);
        deEmployees.add(deEmployee);

        deEmployee = new Employee(20, 4, "Vimal", BigInteger.valueOf(250000), BigInteger.valueOf(0),
                0, new ArrayList<Employee>(), employee, EmployeeTypeEnum.DELIVERY_EXECUTIVE);
        fleetManagementData.getIdToEmployeeMapping().put(deEmployee.getId(), deEmployee);
        deEmployees.add(deEmployee);

        Util.addReportee(temp, deEmployees);

        employees.add(temp);

        deEmployees = new ArrayList<Employee>();

        // Add FM's to CM of City 1
        Util.addReportee(employee, employees);

        // Add city 1 to city manager mapping
        fleetManagementData.getCityToCityManagerMapping().put(city, employee);

        // add if to employee mapping
        fleetManagementData.getIdToEmployeeMapping().put(employee.getId(), employee);


        // = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =

        // * * *  Create City 2's Data * * *
        city = new City(2, "Bengaluru", 12.9716, 77.5946);

        employee = new Employee(2, 5, "Pandu", BigInteger.valueOf(1000000),
                BigInteger.valueOf(0),0,  new ArrayList<Employee>(), null,
                EmployeeTypeEnum.CITY_MANAGER);

        // Adding Fleet Manager's to City 2
        employees = new ArrayList<Employee>();

        temp = new Employee(6, 4, "Bheem", BigInteger.valueOf(500000), BigInteger.valueOf(0),
                0, new ArrayList<Employee>(), employee, EmployeeTypeEnum.FLEET_MANAGER);
        fleetManagementData.getIdToEmployeeMapping().put(temp.getId(), temp);
        employees.add(temp);

        deEmployee = new Employee(21, 1, "Ajay", BigInteger.valueOf(250000), BigInteger.valueOf(0),
                0, new ArrayList<Employee>(), employee, EmployeeTypeEnum.DELIVERY_EXECUTIVE);
        fleetManagementData.getIdToEmployeeMapping().put(deEmployee.getId(), deEmployee);
        deEmployees.add(deEmployee);

        deEmployee = new Employee(22, 2, "Aniket", BigInteger.valueOf(250000), BigInteger.valueOf(0),
                0, new ArrayList<Employee>(), employee, EmployeeTypeEnum.DELIVERY_EXECUTIVE);
        fleetManagementData.getIdToEmployeeMapping().put(deEmployee.getId(), deEmployee);
        deEmployees.add(deEmployee);

        // add DE's to FM2 of city 2
        Util.addReportee(temp, deEmployees);

        deEmployees = new ArrayList<Employee>();

        temp = new Employee(7, 5, "Arjun", BigInteger.valueOf(500000), BigInteger.valueOf(0),
                0, new ArrayList<Employee>(), employee, EmployeeTypeEnum.FLEET_MANAGER);
        fleetManagementData.getIdToEmployeeMapping().put(temp.getId(), temp);
        employees.add(temp);

        deEmployee = new Employee(23, 1, "Ajay", BigInteger.valueOf(250000), BigInteger.valueOf(0),
                0, new ArrayList<Employee>(), employee, EmployeeTypeEnum.DELIVERY_EXECUTIVE);
        fleetManagementData.getIdToEmployeeMapping().put(deEmployee.getId(), deEmployee);
        deEmployees.add(deEmployee);

        deEmployee = new Employee(24, 2, "Aniket", BigInteger.valueOf(250000), BigInteger.valueOf(0),
                0, new ArrayList<Employee>(), employee, EmployeeTypeEnum.DELIVERY_EXECUTIVE);
        fleetManagementData.getIdToEmployeeMapping().put(deEmployee.getId(), deEmployee);
        deEmployees.add(deEmployee);

        deEmployee = new Employee(25, 5, "Yash", BigInteger.valueOf(250000), BigInteger.valueOf(0),
                0, new ArrayList<Employee>(), employee, EmployeeTypeEnum.DELIVERY_EXECUTIVE);
        fleetManagementData.getIdToEmployeeMapping().put(deEmployee.getId(), deEmployee);
        deEmployees.add(deEmployee);

        deEmployee = new Employee(26, 4, "Vimal", BigInteger.valueOf(250000), BigInteger.valueOf(0),
                0, new ArrayList<Employee>(), employee, EmployeeTypeEnum.DELIVERY_EXECUTIVE);
        fleetManagementData.getIdToEmployeeMapping().put(deEmployee.getId(), deEmployee);
        deEmployees.add(deEmployee);

        Util.addReportee(temp, deEmployees);

        deEmployees = new ArrayList<Employee>();

        Util.addReportee(employee, employees);

        // Add city 2 to city manager mapping
        fleetManagementData.getCityToCityManagerMapping().put(city, employee);

        // add if to employee mapping
        fleetManagementData.getIdToEmployeeMapping().put(employee.getId(), employee);

        return fleetManagementData;

    }

}
