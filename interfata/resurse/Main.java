/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfata.resurse;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class BackgroundImageJFrame extends JFrame
{
JButton b1;
JLabel l1;
    public BackgroundImageJFrame()
    {
    setTitle("Background Color for JFrame");
    setSize(800,600);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
/*
    One way
    -----------------
    setLayout(new BorderLayout());
    JLabel background=new JLabel(new ImageIcon("src\\interfata\\resurse\\lock.png"));
    add(background);
    background.setLayout(new FlowLayout());
    background.add(l1);
    background.add(b1);
*/
// Another way
    setLayout(new BorderLayout());
    setContentPane(new JLabel(new ImageIcon("src\\interfata\\resurse\\lock.png")));
    setLayout(new FlowLayout());
    // Just for refresh :) Not optional!
    //setSize(399,399);
   // setSize(400,400);
   validate();
    }
    public static void main(String args[])
    {
    new BackgroundImageJFrame();
    }
}