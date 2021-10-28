//R3 change

package design;

import entity.cal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Calculator extends JFrame{

    private entity.cal cal = new cal();
	
    private JTextField displayText = new JTextField("");



    public static void main(String[] args) {
             EventQueue.invokeLater(() -> {
                Calculator calculator = null;
                try {
                    calculator = new Calculator();
                    calculator.setVisible(true);
                    calculator.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        });
    }

    public Calculator() throws IOException {
        super();
        //初始化
        init();
        // 设置计算器的背景颜色
        this.setBackground(Color.LIGHT_GRAY);
        // 设置标题
        this.setTitle("顾海耀和王尧的计算器");
        // 设置计算器边框
        this.setLocation(cal.getX(), cal.getY());
        // 禁止修改计算器大小
        this.setResizable(true);
        // 自动调整大小
        this.pack();
    }


    /**
     *进行窗口初始化
     */
    private void init() throws IOException {
        //获取字体
        Font font = cal.getFont();

        // 文本框内容右对齐
        displayText.setHorizontalAlignment(JTextField.RIGHT);
        // 禁止修改结果文本框
        displayText.setEditable(false);
        // 设置文本框背景颜色
        displayText.setBackground(Color.WHITE);
        // 设置文本框字体
        displayText.setFont(font);

        String[] NUM = cal.getNUM();
        String[] ADD = cal.getADD();
        String[] DIS = cal.getDIS();

        //ADD画板
        JPanel addPanel = new JPanel();
        addPanel.setLayout(new GridLayout(1,3,10,10));
        String[] addstr = cal.getADD();
        for (int i = 0;i < addstr.length;i++)
        {
            cal.addbutton[i] = new JButton(addstr[i]);
            addPanel.add(cal.addbutton[i]);
            cal.addbutton[i].setFont(font);
            cal.addbutton[i].setForeground(Color.black);
        }

        // 将文本框放在一个画板
        JPanel text = new JPanel();
        text.setLayout(new BorderLayout());
        text.add("Center", displayText);

        //NUM画板
        JPanel numPanel = new JPanel();
        numPanel.setLayout(new GridLayout(4,4,3,3));
        String[] numstr = cal.getNUM();
        for (int i = 0; i < numstr.length; i++) {
            cal.numbutton[i] = new JButton(numstr[i]);
            numPanel.add(cal.numbutton[i]);
            cal.numbutton[i].setFont(font);
            cal.numbutton[i].setForeground(Color.MAGENTA);
        }

        //DIS画板
        JPanel disPanel = new JPanel();
        disPanel.setLayout(new GridLayout(4,3,3,3));
        String[] disstr = cal.getDIS();
        for (int i = 0; i < disstr.length; i++) {
            cal.disbutton[i] = new JButton(disstr[i]);
            disPanel.add(cal.disbutton[i]);
            cal.disbutton[i].setFont(font);
            cal.disbutton[i].setForeground(Color.blue);
        }
        cal.disbutton[0].setForeground(Color.red);
        cal.disbutton[3].setForeground(Color.red);
        cal.disbutton[6].setForeground(Color.red);
        cal.disbutton[9].setForeground(Color.red);


        //NUM和DIS的组合面板
        JPanel dnPanel = new JPanel();
        dnPanel.setLayout(new BorderLayout(3,3));
        dnPanel.add(numPanel,BorderLayout.WEST);
        dnPanel.add(disPanel,BorderLayout.CENTER);

        getContentPane().setLayout(new BorderLayout(3,3));

        getContentPane().add("Center", addPanel);
        getContentPane().add("South", dnPanel);
        getContentPane().add("North", text);
    }
}
