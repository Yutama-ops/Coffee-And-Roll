/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author yutamabudiman
 */
//Create JFrame container 
public class CNR_GUI { 
    public static void main(String[] args) { //main method 
        JFrame frame = new JFrame("CashReg"); 
        frame.setLayout(new GridLayout(3, 3, 10, 10));

        for (int j = 1; j<7; j++){
            JPanel panel = new JPanel(new GridLayout(3, 3, 10, 10));
            for (int i = 1; i<7; i++){
                JButton button = new JButton("Button"+ Integer.toString(i));
                panel.add(button);
            }
            frame.add(panel);
        }




        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        //frame.getContentPane().add(new CashRegGUI()); //create GUI panel 
        frame.pack(); 
        frame.setVisible(true); 
    } //end of main 
} //end CNR_GU



 

