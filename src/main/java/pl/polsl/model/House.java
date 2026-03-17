package pl.polsl.model;

/**
 * Class represents a house.
 *
 * @version 1.0
 * @author Wiktoria Skorupa
 * 
 */
public class House {
    
    /** A measure of how far west a house is; a higher value is farther west. */
    private double longitude;
    
    /** A measure of how far north a house is; a higher value is farther north. */
    private double latitude;
    
    /** Median age of a house within a block; a lower number is a newer building. */
    private double housingMedianAge;
    
    /** Total number of rooms in the house. */
    private double totalRooms;
    
    /** Total number of rooms within a block. */
    private double totalBedrooms;
    
    /** Total number of people residing within a block.*/
    private double population;
    
    /** Total number of households, a group of people residing within a home unit, for a block.*/
    private double households;
    
    /** Median income for households within a block of houses (measured in tens of thousands of US Dollars).*/
    private double medianIncome;
    
    /** Median house value for households within a block (measured in US Dollars).*/
    private double medianHouseValue;
    
    /** Location of the house w.r.t ocean/sea. */
    private String oceanProximity;

    /**
     * Constructor for creating a new House instance with specified attributes.
     *
     * @param longitude       A measure of how far west a house is; a higher value is farther west.
     * @param latitude        A measure of how far north a house is; a higher value is farther north.
     * @param housingMedianAge Median age of a house within a block; a lower number is a newer building.
     * @param totalRooms      Total number of rooms within a block.
     * @param totalBedrooms   Total number of bedrooms within a block.
     * @param population      Total number of people residing within a block.
     * @param households      Total number of households, a group of people residing within a home unit, for a block.
     * @param medianIncome    Median income for households within a block of houses (measured in tens of thousands of US Dollars).
     * @param medianHouseValue Median house value for households within a block (measured in US Dollars).
     * @param oceanProximity  Location of the house w.r.t ocean/sea.
     */    
    public House(double longitude, double latitude, double housingMedianAge, double totalRooms, 
                 double totalBedrooms, double population, double households, 
                 double medianIncome, double medianHouseValue, String oceanProximity) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.housingMedianAge = housingMedianAge;
        this.totalRooms = totalRooms;
        this.totalBedrooms = totalBedrooms;
        this.population = population;
        this.households = households;
        this.medianIncome = medianIncome;
        this.medianHouseValue = medianHouseValue;
        this.oceanProximity = oceanProximity;
    }

    /**
     * Getter.
     * @return The total rooms in the house.
     */    
    public double getTotalRooms() {
        return totalRooms;
    }
    
    /**
     * Getter.
     * @return The total bedrooms in the house.
     */    
    public double getTotalBedrooms() {
        return totalBedrooms;
    }

    /**
     * Getter.
     * @return The proximity to the ocean.
     */     
    public String getOceanProximity() {
        return oceanProximity;
    }
    
    /**
     * Getter.
     * @return The longitude value.
     */ 
    public double getLongitude(){
        return longitude;
    }
    
    /**
     * Getter.
     * @return The median house value.
     */ 
    public double getMedianHouseValue(){
        return medianHouseValue;
    }

    /**
     * Method that converts a complete representation of the House to string to display it in the result section.
     * @return A string representation of the house.
     */    
    @Override
    public String toString() {
        return "House [Total Rooms: " + totalRooms + ", Total Bedrooms: " + totalBedrooms + ", Median House Value: " + medianHouseValue + ", Location: " + oceanProximity + "]";
    }
}
