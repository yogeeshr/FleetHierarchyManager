package com.fleethierarchymanager;

import com.fleethierarchymanager.entitites.City;
import com.fleethierarchymanager.entitites.Employee;
import com.fleethierarchymanager.entitites.FleetManagementData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

@Getter
@Setter
@AllArgsConstructor
public class FleetHierarcyManagerDriver implements FleetHierarchyManger {

    public static void main(String[] args) {

        FleetHierarcyManagerDriver fleetHierarcyManagerDriver = new FleetHierarcyManagerDriver();

        System.out.println("\n\n = = . . . Creating Hierarchy . . .  = = \n\n");
        FleetManagementData fleetManagementData = Util.createHierarchy();

        // print hierarchy of Employee
        System.out.println("\n\n = = Print employee hierarchy given id as 6 = = \n\n");
        Employee employee = fleetManagementData.getIdToEmployeeMapping().get(1);
        fleetHierarcyManagerDriver.printHierarchy(employee, 1);

        // Give bonus to city manager 1 with 1,00,000
        System.out.println("\n\n = = Giving 100000 Bonus to city 1 : Bengaluru = = \n\n");
        City city = new City(1, "Mysore", 12.2958, 76.6394);
        employee = fleetManagementData.getCityToCityManagerMapping().get(city);
        fleetHierarcyManagerDriver.shareBonus(employee, BigInteger.valueOf(100000), employee.getReporteesRatingSum(),
                fleetManagementData.getEmployeePriorityQueue());

        // print hierarchy of Employee
        System.out.println("\n\n = = Print employee hierarchy given id as 1 After updating bonus = = \n\n");
        employee = fleetManagementData.getIdToEmployeeMapping().get(1);
        fleetHierarcyManagerDriver.printHierarchy(employee, 1);

        // Print top N DEs with max bonus/salary
        System.out.println("\n\n = = Top 5 Delievery executives based on bonus/salary = = \n\n");
        fleetHierarcyManagerDriver.fetchTopDeliveryExecutives(fleetManagementData.getEmployeePriorityQueue(), 5);

    }

    /**
     * Method to
     * @param employee
     */
    public void printHierarchy(Employee employee, int level) {
        List<Employee> reportees = employee.getReportees();

        System.out.print("|");

        for (int i=1; i<=level; i++) {
            System.out.print("-\t");
        }

        System.out.print(employee.getEmployeeType()+" ------------> ");

        Employee tEmployee = employee.getManager();

        System.out.println(employee + " | Level : "+level + " | reports to "+
                ((tEmployee!=null)? (tEmployee.getName() + " Id : "+ tEmployee.getId()): "NONE"));

        System.out.println("|");

        for (Employee tempEmployee : employee.getReportees()) {
            printHierarchy(tempEmployee, level+1);
        }
    }

    /**
     * Method to share bonus for all employees in a city
     * @param employee
     * @param bonusValue
     * @param ratingSum
     */
    public void shareBonus(Employee employee, BigInteger bonusValue, int ratingSum,
                           PriorityQueue<Employee> priorityQueue) {

        if (employee==null) {
            return;
        }

        employee.setBonus(bonusValue);

        if (employee.getEmployeeType().equals(EmployeeTypeEnum.DELIVERY_EXECUTIVE)) {
            priorityQueue.add(employee);
        }

        for (Employee tempEmployee : employee.getReportees()) {
            shareBonus( tempEmployee,
                        BigInteger.valueOf((int)(((float)tempEmployee.getRating()/ratingSum)*bonusValue.intValue())),
                        tempEmployee.getReporteesRatingSum(), priorityQueue);

        }

    }

    /**
     * Fetch top N delivery executives . . . based on bonus/salary
     * @param priorityQueue
     * @param N
     */
    public void fetchTopDeliveryExecutives(PriorityQueue<Employee> priorityQueue, int N) {

        PriorityQueue<Employee> employeePriorityQueue = new PriorityQueue<Employee>();
        employeePriorityQueue.addAll(priorityQueue);

        int n=N;

        while (!priorityQueue.isEmpty() && n>0) {
            --n;
            System.out.println(priorityQueue.remove());
        }

    }
}
