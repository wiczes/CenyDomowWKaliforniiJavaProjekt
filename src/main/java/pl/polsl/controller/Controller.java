package pl.polsl.controller;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import pl.polsl.model.House;
import pl.polsl.model.Model;
import pl.polsl.view.View;

/**
 *
 * @author Wiktoria Skorupa
 * @version 1.0
 * 
 */
public class Controller {
    
    /** Reference to the Model that contains data and logic.*/
    private Model model;
    
    /** Reference to the View for displaying data to the user.*/
    private View view;

    /**
     * Constructor for the Controller class.
     */
    public Controller() {
        this.model = new Model(); // Initialize model.
        this.view = new View(model); // Initialize view with model reference.
        this.viewEvent(); // Set up event handling for view actions.
    }

    /**
     * Method to set up action listeners for buttons in the view.
     */    
    private void viewEvent() {
        // Action for displaying houses near the ocean.
        view.oceanButton.setAction(new AbstractAction("SHOW HOUSES NEAR OCEAN") {
            /**
             * Handles the button click event for ocean houses display.
             * @param e The action event triggered by the button click.
             */            
            @Override
            public void actionPerformed(ActionEvent e) {
                List<House> oceanHouses = model.getOceanHouses(); // Retrieve list of ocean houses from model.
                view.updateResultArea(oceanHouses); // Update view with list of ocean houses.
            }
        });
        
        // Action for displaying houses inland
        view.inlandButton.setAction(new AbstractAction("SHOW INLAND HOUSES") {
            /**
             * Handles the button click event for inland houses display.
             * @param e The action event triggered by the button click.
             */            
            @Override
            public void actionPerformed(ActionEvent e) {
                List<House> inlandHouses = model.getInlandHouses(); // Retrieve list of inland houses from model.
                view.updateResultArea(inlandHouses); // Update view with list of inland houses.
            }
        });
        
        // Action for displaying median house value change by longitude.
        view.medianChangeButton.setAction(new AbstractAction("SHOW MEDIAN VALUE CHANGE BY LONGITUDE") {
            /**
             * Handles the button click event for median value change display.
             * @param e The action event triggered by the button click.
             */            
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> changes = model.getMedianValueChangeByLongitude(); // Calculate median value change.
                view.updateResultArea(changes); // Update view with calculated changes.
            }
        });
        
        // Action for sorting houses by number of bedrooms.
        view.sortByBedroomsButton.setAction(new AbstractAction("SORT HOUSEHOLDS BY BEDROOMS") {
            /**
             * Handles the button click event for bedroom-sorted houses display.
             * @param e The action event triggered by the button click.
             */            
            @Override
            public void actionPerformed(ActionEvent e) {
                List<House> sortedHouses = model.getHousesSortedByBedrooms(); // Retrieve sorted list by bedrooms.
                view.updateResultArea(sortedHouses); // Update view with sorted list.
            }
        });
        
        // Action for calculating and displaying Pearson correlation.
        view.correlationButton.setAction(new AbstractAction("CALCULATE PEARSON CORRELATION") {
            /**
             * Handles the button click event for correlation calculation display.
             * @param e The action event triggered by the button click.
             */            
            @Override
            public void actionPerformed(ActionEvent e) {
                double correlation = model.calculatePearsonCorrelation(); // Calculate Pearson correlation.
                view.updateResultArea(List.of("Pearson Correlation: " + correlation)); // Update view with result.
            }
        });
    }
}
