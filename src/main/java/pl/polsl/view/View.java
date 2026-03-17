package pl.polsl.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.*;
import pl.polsl.model.House;
import pl.polsl.model.Model;

/**
 *
 * @author Wiktoria Skorupa
 * @version 1.0
 *
 */
public class View {
    
    /** Main application frame.*/
    private JFrame frame;
    
    /** Panel to display results.*/
    private JPanel resultPanel;
    
    /** Reference to the Model to access data.*/
    private Model model;
    
    /** Button to display data about houses near ocean.*/
    public JButton oceanButton;
    
    /** Button to display data about inland houses.*/
    public JButton inlandButton;
    
    /** Button to display data about median change.*/
    public JButton medianChangeButton;
    
    /** Button to display data about houses sorted by the number of bedrooms.*/
    public JButton sortByBedroomsButton;
    
    /** Button to display Pears correlation coefficient.*/
    public JButton correlationButton;
    
    /** Text area to display the output.*/
    public JTextArea resultArea;

     /**
     * Constructor for the View class.
     * @param model The model instance for data access
     */   
    public View(Model model) {
        this.model = model;
        prepareGUI();
    }

    /**
     * Method that updates the result area with a list of items.
     * @param items The list of items to display in the result area
     */    
    public void updateResultArea(List<?> items) {
        resultArea.setText("");
        for (Object item : items) {
            resultArea.append(item.toString() + "\n");
        }
    }

    /**
     * Method that sets up the main GUI components and layout of the application.
     */    
    private void prepareGUI() {
        // Initialize main frame settings
        frame = new JFrame("CALIFORNIA HOUSE PRICES");
        
        // Initialize the result panel
        resultPanel = new JPanel();
        
        // Initialize buttons for various actions
        oceanButton = new JButton();
        inlandButton = new JButton();
        medianChangeButton = new JButton();
        sortByBedroomsButton = new JButton();
        correlationButton = new JButton();
        
        // Initialize result area and disable text editing
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        // Configure frame settings
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(700, 700));
        frame.setLayout(new GridLayout(6, 1, 10, 10));
        frame.setResizable(false);

        // Configure result panel layout and add the result area in a scroll paneL
        resultPanel.setLayout(new GridLayout());
        resultPanel.add(new JScrollPane(resultArea));

        // Add components to the frame
        frame.add(oceanButton);
        frame.add(inlandButton);
        frame.add(medianChangeButton);
        frame.add(sortByBedroomsButton);
        frame.add(correlationButton);
        frame.add(resultPanel);
        
        // Make the frame visible
        frame.setVisible(true);
    }
}
