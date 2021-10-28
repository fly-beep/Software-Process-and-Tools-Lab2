package entity;


import javax.swing.*;
import java.awt.*;

public class cal {

    private static final int X = 500;
    private static final int Y = 300;

    private static final int HEIGHT = 600;


    public static int getX() {
        return X;
    }

    public static int getY() {
        return Y;
    }

    //第三次迭代增加的功能
    private final String[] ADD = {"saying","sound","color"};
    public JButton[] addbutton = new JButton[ADD.length];

    //数字
    private final String[] NUM = {"7","8","9","4","5","6","1","2","3","0",".","AC"};
    public JButton[] numbutton = new JButton[NUM.length];

    //相关功能
    private final String[] DIS = {"+","%","sin","-","1/x","cos","*","√￣","tan","/","^","="};
    public JButton[] disbutton = new JButton[DIS.length];

    //设置字体格式
    public Font getFont()
    {
        Font font = new Font(null, Font.BOLD, 14);
        return font;
    }

    public String[] getADD() {
        return ADD;
    }

    public String[] getNUM() {
        return NUM;
    }

    public String[] getDIS() {
        return DIS;
    }
}
