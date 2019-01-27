package com.fleethierarchymanager;

import com.fleethierarchymanager.entitites.City;
import com.fleethierarchymanager.entitites.Employee;

import java.math.BigInteger;

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
    public void shareBonus(Employee employee, BigInteger bonusValue, int ratingSum);


    /**
     * Method to fetch top N Delivery executives
     */
    public void fetchTopDeliveryExecutives(int N);

}
