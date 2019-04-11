package view;


import dao.daoImplements.AdminDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Enumeration;


public class LoginView extends JFrame {

    private JTextField userNameField;
    private JPasswordField passwordField;
    private JButton login;
    private JButton register;
    private AdminDao dao=new AdminDao();


    public LoginView(){
        this.setIconImage(new ImageIcon
                ("src/view/icon.jpg").getImage());
        this.setTitle("家教后台系统");
        this.setResizable(false);
        this.setSize(218,134);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        setUi();
        addComponent();
        addEvent();
        this.setDefaultCloseOperation(2);
        this.setVisible(true);
    }
    private void setUi(){

        Enumeration<Object> keys = UIManager.getDefaults().keys();
        Object key;
        Object value;


        while (keys.hasMoreElements()) {
            key = keys.nextElement();
            value = UIManager.get(key);
            /**设置全局的字体*/
            if(value instanceof Font) {
                UIManager.put(key, new Font(Font.DIALOG,Font.PLAIN,12));
            }
        }
    }
    private void addComponent(){
        //用户名文字
        JLabel password = new JLabel("用户名:");
        password.setBounds(30, 10, 54, 15);
        getContentPane().add(password);
        //密码文字
        JLabel userName = new JLabel("密码:");
        userName.setBounds(40, 35, 30, 15);
        this.add(userName);
        //用户名输入框
        userNameField = new JTextField();
        userNameField.setBounds(77, 7, 93, 21);
        userNameField.setColumns(10);
        this.add(userNameField);

        //密码输入框
        passwordField =new JPasswordField();
        passwordField.setBounds(77,32,93,21);
        passwordField.setColumns(10);
        this.add(passwordField);

        //登录按钮
        login = new JButton("登录");
        login.setBounds(30, 60, 60, 23);
        this.add(login);

        //注册按钮
        register = new JButton("注册");
        register.setBounds(113, 60, 60, 23);
        this.add(register);
    }
    private void addEvent(){
        /**
         * 登录按钮监听
         */
        login.addActionListener(e -> loginOperation());
        /**
         * 注册按钮监听并实现注册功能
         */
        register.addActionListener(e -> {
            new RegisterView();
        });
        /**
         * 密码框键盘监听
         */
        passwordField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==10)
                    loginOperation();
            }
        });
    }

    /**
     * 重置输入框
     */
    private void resetInput(){
        userNameField.requestFocus();
        userNameField.selectAll();
        passwordField.setText("");
    }

    /**
     * 登录操作
     */
    private void loginOperation(){
        if(infoIllegal())return;
        String inputUser=userNameField.getText();
        String inputPw=new String(passwordField.getPassword());
        int dbUserCount=dao.matchUser(inputUser,inputPw);
        if(dbUserCount!=1){
            JOptionPane.showMessageDialog(getContentPane(),
                    "用户名或密码错误!请重新输入!",
                    "登录失败", JOptionPane.ERROR_MESSAGE);
            resetInput();
        }else{
            new MainView(inputUser);
            dispose();
        }
    }

    /**
     * 判断用户名或密码非空
     * @return 空为true
     */
    private boolean isEmpty(){
        if(userNameField.getText().equals("")||passwordField.getPassword().length==0){
            JOptionPane.showMessageDialog(getContentPane(),
                    "请输入完整登录信息!",
                    "验证不通过", JOptionPane.WARNING_MESSAGE);
            resetInput();
            return true;
        }
        return false;
    }


    /**
     * 登录合法性验证
     */
    private boolean infoIllegal() {
        if (isEmpty()) return true;
        String user = userNameField.getText();
        String psw = new String(passwordField.getPassword());
        int i;
        for (i = 0; i < psw.length(); i++)
            if (!Character.isDigit(psw.charAt(i))) break;
        if ((!(user.length() >= 3 && user.length() <= 10))
                || (!Character.isLetter(user.charAt(0)))
                || (!(psw.length() >= 6 && psw.length() <= 16))
                || (i == psw.length())) {
            JOptionPane.showMessageDialog(getContentPane(), "您的输入有误,请重试!",
                    "验证不通过", JOptionPane.WARNING_MESSAGE);
            resetInput();
            return true;
        } else return false;
    }






}
