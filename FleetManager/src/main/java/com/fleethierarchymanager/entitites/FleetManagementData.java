package com.fleethierarchymanager.entitites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * FleetManagementData entity keeping main data that serves as entry point to system in order to perform operations
 * @author : yogeesh.srkvs@gmail.com
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FleetManagementData {

    Map<City, Employee> cityToCityManagerMapping = new HashMap<City, Employee>();

    Map<Integer, Employee> idToEmployeeMapping = new HashMap<Integer, Employee>();

    PriorityQueue<Employee> employeePriorityQueue = new PriorityQueue<Employee>();

}
