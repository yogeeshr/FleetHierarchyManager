package com.fleethierarchymanager;

import com.fleethierarchymanager.entitites.City;
import com.fleethierarchymanager.entitites.Employee;
import com.fleethierarchymanager.entitites.FleetManagementData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

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
        fleetHierarcyManagerDriver.shareBonus(employee, BigInteger.valueOf(100000), employee.getReporteesRatingSum());

        // print hierarchy of Employee
        System.out.println("\n\n = = Print employee hierarchy given id as 1 After updating bonus = = \n\n");
        employee = fleetManagementData.getIdToEmployeeMapping().get(1);
        fleetHierarcyManagerDriver.printHierarchy(employee, 1);

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
    public void shareBonus(Employee employee, BigInteger bonusValue, int ratingSum) {

        employee.setBonus(bonusValue);

        for (Employee tempEmployee : employee.getReportees()) {

            shareBonus( tempEmployee,
                        BigInteger.valueOf((int)(((float)tempEmployee.getRating()/ratingSum)*bonusValue.intValue())),
                        tempEmployee.getReporteesRatingSum() );

        }

    }

    public void fetchTopDeliveryExecutives(int N) {

    }
}
