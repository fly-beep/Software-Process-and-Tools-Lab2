package entity;


import javax.swing.*;
import java.awt.*;

public class cal {

    private static final int X = 500;
    private static final int Y = 300;

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



    //saying
    private final String[] SAYING = {"花开不是为了花落，而是为了开的更加灿烂",
                                     "伤痕是士兵一生的荣耀",
                                     "别想一下造出大海，必须先由小河川开始",
                                     "只有千锤百炼，才能成为好钢",
                                     "命运，你残忍的诉说着我的悲痛",
                                     "最值得欣赏的风景，是自己奋斗的足迹",
                                     "觉得自己做得到和做不到，只在一念之间",
                                     "要想人前显贵，必先人后受罪",
                                     "永远成功的秘密，就是每天淘汰自己",
                                     "只要你想，这个世界就会有奇迹"};

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

    public String[] getSAYING() {
        return SAYING;
    }
}
