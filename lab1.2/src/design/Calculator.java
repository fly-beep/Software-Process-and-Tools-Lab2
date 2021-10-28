package design;

import entity.cal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class Calculator extends JFrame implements ActionListener {

    private entity.cal cal = new cal();

    //定义一些变量
    private JTextField displayText = new JTextField("");
    private JTextField resultText = new JTextField("0");

    // 运算中间结果
    private double resultNum = 0.0;
    // 第一个是否为数字
    private boolean firstDigit = true;
    // 是否为基本的+-*/运算
    private boolean basicOperator = true;
    // 运算结果是否合理
    private boolean operateValidFlag = true;
    // 文本说明
    private boolean sayNotice = false;
    //标识二元式
    private int index = 0;
    // 中间数字
    private double[] num = new double[110];
    private  int count1 = 0;
    // 基本运算符
    private char ope[] = new char[110];
    private int count2 = 0;
    //确定saying的顺序
    private int saynum = 0;
    //确定color顺序
    private int colornum = 0;
    //是否开启声音
    private boolean judgesound = false;
    private int insound = 0;

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

        //添加监听器
        for (int i = 0; i < NUM.length; i++) {
            cal.numbutton[i].addActionListener(this);
        }
        for (int i = 0; i < ADD.length; i++) {
            cal.addbutton[i].addActionListener(this);
        }
        for (int i = 0; i < DIS.length; i++) {
            cal.disbutton[i].addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String label = e.getActionCommand();

        String[] NUM = cal.getNUM();
        String[] ADD = cal.getADD();

        if (label.equals(NUM[11]))
        {
            HandleAC();
            sayNotice = true;
        }
        else if ("0123456789.".contains(label))
        {
            HandleNumber(label);
        }
        else if(label.equals(ADD[0]))
        {
            HandleSaying();
        }
        else if (label.equals(ADD[1]))
        {
            HandleSound();
        }
        else if (label.equals(ADD[2]))
        {
            HandleColor();
        }
        else
            HandleOperator(label);
    }

    //解决AC
    //初始化即可
    public void HandleAC()
    {
        displayText.setText("");
        resultText.setText("0");
        resultNum = 0.0;
        firstDigit = true;
        basicOperator = true;
        operateValidFlag = true;
        sayNotice = false;
        count1 = 0;
        count2 = 0;
    }

    //数字
    public void HandleNumber(String lab)
    {
        if (judgesound)
        {
            if (lab.equals("0"))
            {
                URL soundbyte = null;
                try {
                    soundbyte = new File("./src/sound/0.wav").toURI().toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
                clip.play();
            }
            if (lab.equals("1"))
            {
                URL soundbyte = null;
                try {
                    soundbyte = new File("./src/sound/1.wav").toURI().toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
                clip.play();
            }
            if (lab.equals("2"))
            {
                URL soundbyte = null;
                try {
                    soundbyte = new File("./src/sound/2.wav").toURI().toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
                clip.play();
            }
            if (lab.equals("3"))
            {
                URL soundbyte = null;
                try {
                    soundbyte = new File("./src/sound/3.wav").toURI().toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
                clip.play();
            }
            if (lab.equals("4"))
            {
                URL soundbyte = null;
                try {
                    soundbyte = new File("./src/sound/4.wav").toURI().toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
                clip.play();
            }
            if (lab.equals("5"))
            {
                URL soundbyte = null;
                try {
                    soundbyte = new File("./src/sound/5.wav").toURI().toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
                clip.play();
            }
            if (lab.equals("6"))
            {
                URL soundbyte = null;
                try {
                    soundbyte = new File("./src/sound/6.wav").toURI().toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
                clip.play();
            }
            if (lab.equals("7"))
            {
                URL soundbyte = null;
                try {
                    soundbyte = new File("./src/sound/7.wav").toURI().toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
                clip.play();
            }
            if (lab.equals("8"))
            {
                URL soundbyte = null;
                try {
                    soundbyte = new File("./src/sound/8.wav").toURI().toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
                clip.play();
            }
            if (lab.equals("9"))
            {
                URL soundbyte = null;
                try {
                    soundbyte = new File("./src/sound/9.wav").toURI().toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
                clip.play();
            }
        }

        displayText.setText(displayText.getText() + lab);
        if (firstDigit)
        {
            resultText.setText(lab);
        }
        else if (lab.equals(".") && (!resultText.getText().contains(".")))
        {
            resultText.setText(resultText.getText() + ".");
        }
        else if (!lab.equals("."))
        {
            resultText.setText(resultText.getText() + lab);
        }
        firstDigit = false;
    }

    public void getNumFromText()
    {
        double result = Double.valueOf(resultText.getText());
        num[count1++] = result;
    }

    public double getResult()
    {
        double result = 0.0;

        for (int i = 0; i < count2; i++) {
            if (ope[i] == '-')
            {
                num[i+1] *= -1.0;
                ope[i] = '+';
            }
        }

        for (int i = 0; i < count2; i++) {
            if (ope[i] == '*')
            {
                num[i+1] *= num[i];
                num[i] = 0.0;
            }
            if (ope[i] == '/')
            {
                if (num[i] == 0.0)
                {
                    operateValidFlag = false;
                }
                else
                {
                    num[i+1] = num[i] / num[i+1];
                }
                num[i] = 0.0;
            }
        }

        for (int i = 0; i < count1; i++) {
            result += num[i];
        }

        return result;
    }

    public void HandleOperator(String opt)
    {
        if ("+-*/%^".contains(opt))
        {
            displayText.setText(displayText.getText() + opt);
        }
        if ("+-*/".contains(opt))
        {
            ope[count2++] = opt.charAt(0);
        }
        getNumFromText();
        if (opt.equals("sin"))
        {
            basicOperator = false;
            num[0] = Math.toRadians(num[0]);
            resultNum = Math.sin(num[0]);
            displayText.setText(opt + displayText.getText());
        }
        if (opt.equals("cos"))
        {
            basicOperator = false;
            num[0] = Math.toRadians(num[0]);
            resultNum = Math.cos(num[0]);
            displayText.setText(opt + displayText.getText());
        }
        if (opt.equals("tan"))
        {
            basicOperator = false;
            num[0] = Math.toRadians(num[0]);
            resultNum = Math.tan(num[0]);
            displayText.setText(opt + displayText.getText());
        }
        if (opt.equals("√￣"))
        {
            basicOperator = false;
            resultNum = Math.sqrt(num[0]);
            displayText.setText(opt + displayText.getText());
        }
        if (opt.equals("%"))
        {
            basicOperator = false;
            index = 1;
        }
        if (opt.equals("^"))
        {
            basicOperator = false;
            index = 2;
        }
        if (opt.equals("1/x"))
        {
            basicOperator = false;
            resultNum = num[0];
            if (resultNum == 0.0)
            {
                operateValidFlag = false;
            }
            else
                resultNum = 1.0 / resultNum;
        }
        if (opt.equals("="))
        {
            if (basicOperator)
                resultNum = getResult();
            // 处理函数
            if (index > 0)
            {
                resultNum = HandleIndex(index);
                index = 0;
            }
            // 输出结果
            PrintResult(resultNum);
            basicOperator = true;
        }
        firstDigit = true;
        operateValidFlag = true;
    }

    private double HandleIndex(int index)
    {
        double result = 0.0;
        if (index == 1)
        {
            result = num[0] % num[1];
        }
        if (index == 2)
        {
            result = Math.pow(num[0],num[1]);
        }
        return result;
    }

    public void PrintResult(double result)
    {
        if(operateValidFlag)
        {
            int a1 = (int)result;
            double a2 = result - a1;
            if (a2 == 0.0)
            {
                displayText.setText(String.valueOf(a1));
                resultText.setText(String.valueOf(a1));
            }
            else
            {
                displayText.setText(String.valueOf(result));
                resultText.setText(String.valueOf(result));
            }
        }
        else
            displayText.setText("出现错误啦~~");
        count1 = count2 = 0;
    }

    public void HandleSaying()
    {
        Random random = new Random();
        saynum = random.nextInt(cal.getSAYING().length);
        String[] says = cal.getSAYING();
        HandleAC();
        displayText.setText(says[saynum]);
    }

    public void HandleColor()
    {
        if (colornum == 0)
        {
            displayText.setBackground(Color.LIGHT_GRAY);
        }
        else if(colornum == 1)
        {
            displayText.setBackground(Color.pink);
        }
        else if(colornum == 2)
        {
            displayText.setBackground(Color.gray);
        }
        else if(colornum == 4)
        {
            displayText.setBackground(Color.orange);
        }
        else if(colornum == 5)
        {
            displayText.setBackground(Color.white);
        }
        colornum++;
        if (colornum > 5)
        {
            colornum = 0;
        }
    }

    public void HandleSound()
    {
        if ((insound % 2) == 0)
        {
            judgesound = true;
        }
        else
            judgesound = false;
        insound++;
    }
}