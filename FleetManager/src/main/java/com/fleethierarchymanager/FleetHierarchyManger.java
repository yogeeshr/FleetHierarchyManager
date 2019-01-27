package com.fleethierarchymanager;

import com.fleethierarchymanager.entitites.Employee;

import java.math.BigInteger;
import java.util.PriorityQueue;

public interface FleetHierarchyManger {

    /**
     * Method to print hierarchy given an employee
     * @param employee
     */
    public void printHierarchy(Employee employee, int level);

    /**
     * Method to share bonus among employees
     * @param employee
     * @param bonusValue
     * @param ratingSum
     */
    public void shareBonus(Employee employee, BigInteger bonusValue, int ratingSum,
                           PriorityQueue<Employee> employeeQueue);


    /**
     * Method to fetch top N Delivery executives
     */
    public void fetchTopDeliveryExecutives(PriorityQueue<Employee> priorityQueue, int N);

}
