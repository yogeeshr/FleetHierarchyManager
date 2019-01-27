# FleetHierarchyManager

Fun project to learn OOPs concepts


## Assumptions
* Each city has only one city manager
* An FM Can report to only one city manager
* Implemented sample case for 2 city managers
* Inititally city manager's rating put as 5, code will adopt if we want to give bonus to whole swiggy instead of city wise
* Data Sample : https://github.com/yogeeshr/FleetHierarchyManager/blob/master/FleetManager/Diagrams/Sample_Fleet_Data.png
* Initially bonus for all employee will be zero until allocated 
* Level 1 is highest and greater the level lower the hierarchy
* Data is hardcoded and static data is created using Util class
* All data is in code itself and no DB is being used

## Note
* Priority queue has been used to keep track of all DE's with (bonus/salary) so that top N can be got
* Interface is used in order to implement 3 functionalities ( printHerarchy, top 5 DE's and Give Bonus as per rating ratio )
* CompareTo has been overriden in order to make priorityQueue as max heap which is required getting top N DE's : https://github.com/yogeeshr/FleetHierarchyManager/blob/master/FleetManager/src/main/java/com/fleethierarchymanager/entitites/Employee.java#L81
* Main data started is maintained in Entity : https://github.com/yogeeshr/FleetHierarchyManager/blob/master/FleetManager/src/main/java/com/fleethierarchymanager/entitites/FleetManagementData.java

## Extendability 
* Code is extensible if we need to pull more than 5 top DE;s as well
* New type of employee can easily be added by adding a type to enum

## Code standars
* No line is greater than 120 column long
* Optimized all imports in java files
* Tried to follow google java coding style : https://google.github.io/styleguide/javaguide.html
* Code has been done starting from ERD and converting into Java classes
* Followed SOLID principle throught
* Code is thread safe at some method where required, same is achieved by usind Synchronized keyword of Java

## Artifacts 
* Sequence Diagram : https://github.com/yogeeshr/FleetHierarchyManager/blob/master/FleetManager/Diagrams/Sequence_Diagram.png
* ERD : <github_link>
* Sample code run and it's output : https://github.com/yogeeshr/FleetHierarchyManager/blob/master/FleetManager/Output/Hierarchy_Output.txt
