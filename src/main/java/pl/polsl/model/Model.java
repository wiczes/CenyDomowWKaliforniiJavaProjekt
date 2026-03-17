package pl.polsl.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Wiktoria Skorupa
 * @version 1.0
 * 
 */
public class Model {
    
    /** List to store data loaded from the CSV file. */
    private List<House> houses = new ArrayList<>();

    /**
     * Constructor for the Model class.
     * Loads data from the CSV file.
     */
    public Model() {
        loadDataFromCSV("housing.csv");
    }

    
    /**
     * Method that loads data from a CSV file and populates the houses list.
     * @param filePath Path to the CSV file containing data.
     */    
    private void loadDataFromCSV(String filePath) {
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line = br.readLine();
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            
            // Check if the line has the required number of columns.
            if (values.length < 10) {
                System.err.println("Skipped row due to incomplete data: " + line);
                continue;
            }

            try {
                // Parse values or assign default if empty.
                double longitude = values[0].isEmpty() ? 0.0 : Double.parseDouble(values[0]);
                double latitude = values[1].isEmpty() ? 0.0 : Double.parseDouble(values[1]);
                double housingMedianAge = values[2].isEmpty() ? 0.0 : Double.parseDouble(values[2]);
                double totalRooms = values[3].isEmpty() ? 0.0 : Double.parseDouble(values[3]);
                double totalBedrooms = values[4].isEmpty() ? 0.0 : Double.parseDouble(values[4]);
                double population = values[5].isEmpty() ? 0.0 : Double.parseDouble(values[5]);
                double households = values[6].isEmpty() ? 0.0 : Double.parseDouble(values[6]);
                double medianIncome = values[7].isEmpty() ? 0.0 : Double.parseDouble(values[7]);
                double medianHouseValue = values[8].isEmpty() ? 0.0 : Double.parseDouble(values[8]);
                String oceanProximity = values[9].isEmpty() ? "UNKNOWN" : values[9];

                // Create and add an object to the list.
                House house = new House(
                    longitude, latitude, housingMedianAge, totalRooms,
                    totalBedrooms, population, households, medianIncome,
                    medianHouseValue, oceanProximity
                );
                houses.add(house);

            } catch (NumberFormatException e) {
                System.err.println("Number format error in row: " + line);
            }
        }
    } catch (IOException e) {
        System.err.println("Error loading CSV file: " + e.getMessage());
    } catch (ArrayIndexOutOfBoundsException e) {
        System.err.println("Data access error: Check CSV file format.");
    }
    }

    /**
     * Method that filters and sorts the list of houses located near the ocean.
     * @return List of houses near the ocean, sorted by room count.
     */
    public List<House> getOceanHouses() {
        return houses.stream()
                .filter(house -> "NEAR OCEAN".equals(house.getOceanProximity()))
                .sorted(Comparator.comparingDouble(House::getTotalRooms).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Method that filters and sorts the list of houses located inland.
     * @return List of inland houses, sorted by room count.
     */    
    public List<House> getInlandHouses() {
        return houses.stream()
                .filter(house -> "INLAND".equals(house.getOceanProximity()))
                .sorted(Comparator.comparingDouble(House::getTotalRooms).reversed())
                .collect(Collectors.toList());
    }
    
     /**
     * Method that sorts the list of houses by the total number of bedrooms.
     * @return List of houses sorted by the number of bedrooms.
     */   
    public List<House> getHousesSortedByBedrooms() {
        return houses.stream()
                .sorted(Comparator.comparingDouble(House::getTotalBedrooms).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Calculates the percentage change in median house value as longitude changes.
     * @return List of strings showing longitude ranges and percentage changes.
     */    
    public List<String> getMedianValueChangeByLongitude() {
        
        /** List of strings showing longitude ranges and percentage changes.*/
        List<String> results = new ArrayList<>();

        /** Group data by longitude and calculate average median house value for each group.*/
        Map<Double, Double> avgMedianValuesByLongitude = houses.stream()
            .collect(Collectors.groupingBy(
                House::getLongitude, 
                Collectors.averagingDouble(House::getMedianHouseValue)
            ));

        /** Setting previous longitude to null.*/
        Double previousLongitude = null;
        
        /** Setting previous median value to null.*/
        Double previousMedianValue = null;

        for (Map.Entry<Double, Double> entry : avgMedianValuesByLongitude.entrySet()) {
            Double longitude = entry.getKey();
            Double avgMedianValue = entry.getValue();

            if (previousMedianValue != null) {
                /** Calculate percentage change if previous value exists.*/
                double percentChange = ((avgMedianValue - previousMedianValue) / previousMedianValue) * 100;
                results.add("Longitude: " + previousLongitude + " -> " + longitude 
                            + ", Change: " + percentChange + "%");
            }

            previousLongitude = longitude;
            previousMedianValue = avgMedianValue;
        }

        return results;
    }
    
    /**
     * Calculates the Pearson correlation between median house values and proximity to ocean.
     * @return Pearson correlation between house value and proximity.
     */    
    public double calculatePearsonCorrelation() {
        /** Map oceanProximity to numeric values for correlation calculation.*/
        Map<String, Integer> proximityMap = new HashMap<>();
        proximityMap.put("NEAR OCEAN", 1);
        proximityMap.put("NEAR BAY", 2);
        proximityMap.put("INLAND", 3);
        proximityMap.put("<1H OCEAN", 4);
        proximityMap.put("UNKNOWN", 5);

        /** Lists to hold values for calculation.*/
        List<Double> values = new ArrayList<>();
        
        /** Lists to hold proximities for calculation.*/
        List<Double> proximities = new ArrayList<>();

        for (House house : houses) {
            values.add(house.getMedianHouseValue());
            proximities.add((double) proximityMap.getOrDefault(house.getOceanProximity(), 0));
        }

        /** Calculate averages.*/
        double avgValue = values.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        
        /** Calculate proximities.*/
        double avgProximity = proximities.stream().mapToDouble(Double::doubleValue).average().orElse(0);

        /** the numerator of the formula.*/
        double numerator = 0;
        
        /** the sum of squared deviations of X from the mean.*/
        double denomA = 0;
        
        /** the sum of the squares of Y deviations from the mean.*/
        double denomB = 0;

        for (int i = 0; i < values.size(); i++) {
            double diffValue = values.get(i) - avgValue;
            double diffProximity = proximities.get(i) - avgProximity;

            numerator += diffValue * diffProximity;
            denomA += diffValue * diffValue;
            denomB += diffProximity * diffProximity;
        }

        // Return the Pearson correlation coefficient.
        return numerator / Math.sqrt(denomA * denomB);
    }
}
