package com.fleethierarchymanager.entitites;

import com.fleethierarchymanager.EmployeeTypeEnum;
import lombok.*;

import java.math.BigInteger;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Employee entity keeping track of all data of an employee
 * @author : yogeesh.srkvs@gmail.com
 */
public class Employee {

    /**
     * Unique if of employee
     */
    int id;

    /**
     * Rating of employee
     */
    int rating = 0;

    /**
     * Name of employee
     */
    String name;

    /**
     * Salary of employee
     */
    BigInteger salary;

    /**
     * Bonus of employee
     */
    BigInteger bonus;

    /**
     * sum of ratings of the reportees : maintained since it helps in calculating correct sum
     */
    int reporteesRatingSum = 0;

    /**
     * Reportees of employee
     */
    ArrayList<Employee> reportees;

    /**
     * Manager of the employee
     */
    Employee manager;

    /**
     * Type of employee
     */
    EmployeeTypeEnum employeeType;

    @Override
    public String toString() {

        return " Employee : [ id : " +id + " | Name : " + name + " | Salary : " + salary + " | ReporteesRatingSum " +
                reporteesRatingSum + " | Rating " + rating + " | Type " + employeeType + " | Bonus " + bonus +
                " ]";

    }
}
