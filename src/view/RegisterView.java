package view;

import dao.daoImplements.AdminDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegisterView extends JFrame {
    private JTextField textFieldRegisterCode;
    private JTextField textFieldUserName;
    private JPasswordField textFieldPassword;
    private JPasswordField textFieldPasswordConfirm;


    public RegisterView() {
        this.setIconImage(new ImageIcon
                ("src/view/icon.jpg").getImage());
        this.setDefaultCloseOperation(2);
        this.setTitle("管理员注册");
        this.setSize(370, 300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        addComponents();
        addEvent();
        this.setVisible(true);

    }

    private void addComponents() {
        getContentPane().setLayout(null);

        JLabel registerCodeLabel = new JLabel("注册码:");
        registerCodeLabel.setFont(new Font("宋体", Font.PLAIN, 15));
        registerCodeLabel.setBounds(76, 86, 54, 15);
        getContentPane().add(registerCodeLabel);

        JLabel userLabel = new JLabel("用户名:");
        userLabel.setFont(new Font("宋体", Font.PLAIN, 15));
        userLabel.setBounds(76, 111, 54, 15);
        getContentPane().add(userLabel);

        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setFont(new Font("宋体", Font.PLAIN, 15));
        passwordLabel.setBounds(76, 136, 38, 15);
        getContentPane().add(passwordLabel);

        JLabel confirmPwLabel = new JLabel("确认密码:");
        confirmPwLabel.setFont(new Font("宋体", Font.PLAIN, 15));
        confirmPwLabel.setBounds(76, 161, 68, 15);
        getContentPane().add(confirmPwLabel);

        JLabel registerCaption = new JLabel("管理员注册");
        registerCaption.setFont(new Font("宋体", Font.PLAIN, 23));
        registerCaption.setBounds(118, 24, 121, 36);
        getContentPane().add(registerCaption);

        textFieldRegisterCode = new JTextField();
        textFieldRegisterCode.setBounds(152, 83, 127, 21);
        getContentPane().add(textFieldRegisterCode);
        textFieldRegisterCode.setColumns(10);

        textFieldUserName = new JTextField();
        textFieldUserName.setBounds(152, 108, 127, 21);
        getContentPane().add(textFieldUserName);
        textFieldUserName.setColumns(10);

        textFieldPassword = new JPasswordField();
        textFieldPassword.setBounds(152, 133, 127, 21);
        getContentPane().add(textFieldPassword);
        textFieldPassword.setColumns(10);

        textFieldPasswordConfirm = new JPasswordField();
        textFieldPasswordConfirm.setBounds(152, 158, 127, 21);
        getContentPane().add(textFieldPasswordConfirm);
        textFieldPasswordConfirm.setColumns(10);

        JButton confirmRegisterButton = new JButton("确认注册");
        confirmRegisterButton.setFont(new Font("宋体", Font.PLAIN, 15));
        confirmRegisterButton.addActionListener(e -> register());
        confirmRegisterButton.setBounds(133, 206, 95, 27);
        getContentPane().add(confirmRegisterButton);
    }
    /**
     * 添加事件
     */
    private void addEvent(){
        textFieldPasswordConfirm.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==10)register();
            }
        });
    }

    /**
     * 注册合法性验证
     */
    private boolean infoIllegal() {
        if (isEmpty()) return true;
        String user = textFieldUserName.getText();
        String psw = new String(textFieldPassword.getPassword());
        String pswConfirm=new String(textFieldPasswordConfirm.getPassword());
        int i;
        for (i = 0; i < psw.length(); i++)
            if (!Character.isDigit(psw.charAt(i))) break;

        String failInfo = "请按照以下格式输入:\n" +
                "用户名:3-10位字符且以字母开头\n" +
                "密码:6-16位字符且不能为纯数字\n";
        if (!(user.length() >= 3 && user.length() <= 10))
            failInfo = "用户名长度不合法!" + failInfo;
        else if (!Character.isLetter(user.charAt(0)))
            failInfo = "用户名必须以字母开头!" + failInfo;
        else if (!(psw.length() >= 6 && psw.length() <= 16))
            failInfo = "密码长度不合法!" + failInfo;
        else if (i == psw.length())
            failInfo = "密码过于简单!" + failInfo;
        else if(!psw.equals(pswConfirm))
            failInfo = "两次密码输入不一致!";
        else return false;
        JOptionPane.showMessageDialog(getContentPane(),
                failInfo, "验证不通过", JOptionPane.WARNING_MESSAGE);
        resetInput();
        textFieldUserName.requestFocus();
        textFieldUserName.selectAll();
        return true;
    }

    /**
     * 判断用户名或密码非空
     * @return 空为true
     */
    private boolean isEmpty(){
        if(textFieldUserName.getText().equals("")
                ||textFieldPassword.getPassword().length==0
                ||textFieldPasswordConfirm.getPassword().length==0
                ||textFieldRegisterCode.getText().equals("")){
            JOptionPane.showMessageDialog(getContentPane(),
                    "请输入完整登录信息!",
                    "验证不通过", JOptionPane.WARNING_MESSAGE);
            resetInput();
            return true;
        }
        return false;
    }


    /**
     * 注册操作
     */
    private void register(){
        if(infoIllegal())return;
        AdminDao adminDao = new AdminDao();
        String registerCode=textFieldRegisterCode.getText();
        int registerCodeCount=adminDao.matchRegisterCode(registerCode);
        if(registerCodeCount==0){
            JOptionPane.showMessageDialog(getContentPane(),
                    "注册码错误,您无权注册!","注册失败",
                    JOptionPane.WARNING_MESSAGE);
            resetInput();
            textFieldRegisterCode.requestFocus();
            textFieldRegisterCode.selectAll();
            return;
        }
        String newRegisterCode=(int)(Math.random()*(99999999-10000000+1)+10000000)+"";
        String inputUser=textFieldUserName.getText();
        String inputPassword=new String(textFieldPassword.getPassword());
        int successCount=adminDao.insertUser
                (newRegisterCode+(int)(Math.random()*(99999999-10000000+1)+10000000),
                        inputUser,inputPassword);
        if(successCount==0){
            JOptionPane.showMessageDialog(getContentPane(),
                    "此用户已存在!","注册失败",
                    JOptionPane.WARNING_MESSAGE);
            resetInput();
            textFieldUserName.requestFocus();
            textFieldUserName.selectAll();
        }else if(successCount==-1){
            JOptionPane.showMessageDialog(getContentPane(),
                    "此用户已存在!","注册失败",
                    JOptionPane.WARNING_MESSAGE);
            resetInput();
            textFieldUserName.requestFocus();
            textFieldUserName.selectAll();
        }else{
                JOptionPane.showMessageDialog(getContentPane(),
                        "注册成功!您的账户名为" +
                                textFieldUserName.getText(),"注册成功",
                        JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
    }


    private void resetInput(){
        textFieldPassword.setText("");
        textFieldPasswordConfirm.setText("");
    }


}