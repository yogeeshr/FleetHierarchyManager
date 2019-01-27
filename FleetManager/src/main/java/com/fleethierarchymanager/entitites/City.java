package com.fleethierarchymanager.entitites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
/**
 * City entity to keep all city data in one place
 * @author : yogeesh.srkvs@gmail.com
 */
public class City {

    /**
     * unique id of the city
     */
    int id;

    /**
     * Name of the city
     */
    String name;

    /**
     * Longitude of the city
     */
    private double longitude;

    /**
     * Latitude of the city
     */
    private double latitude;

    @Override
    public boolean equals(Object obj) {
        return (this.id==((City)obj).id);
    }

    @Override
    public int hashCode() {
        return this.id;
    }

}
