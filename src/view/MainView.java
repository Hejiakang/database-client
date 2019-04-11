package view;

import dao.daoImplements.AdminDao;
import dao.daoImplements.CourseDao;
import dao.daoImplements.ParentDao;
import dao.daoImplements.TutorDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MainView extends JFrame {
    JTabbedPane mainJtp;//标签组
    String userName;//配合修改密码时使用
    JPanel innerJp;//为了使"系统管理界面"居中

    JPanel parentPanel;
    JPanel tutorPanel;
    JPanel coursePanel;

    JPasswordField oldPwTextField;
    JPasswordField newPwTextField;
    JPasswordField newPwTextFieldConfirm;
    JButton confirmButton;

    JLabel targetLabel;

    public MainView(String userName){
        this.userName=userName;
        this.setTitle("欢迎"+userName+",这里是家教系统后台管理");
        this.setSize(1200,912);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("src/view/icon.jpg").getImage());

        mainJtp=new JTabbedPane();
        addComponent();
        addEvent();
        this.add(mainJtp);
        this.setDefaultCloseOperation(2);
        this.setVisible(true);
    }

    /**
     * 添加组件
     */
    private void addComponent(){

        /**
         * 添加主面板组件
         */
        Vector titleParent = new Vector(Arrays.asList("序号","手机","姓名","地址"));
        parentPanel=new PanelGenerator(new ParentDao(),titleParent,new int[]{200,250,250,500});
        Vector titleTutor=new Vector(Arrays.asList
                ("序号","手机","姓名","性别","出生日期","教育情况","电子邮箱","信用等级"));
        tutorPanel=new PanelGenerator(new TutorDao(),titleTutor,new int[]{117,178,138,97,178,117,258,117});
        Vector titleCourse=new Vector(Arrays.asList
                ("序号","家教序号","课程内容","授课时间","价格/小时","预约者序号","发布日期"));
        coursePanel=new PanelGenerator(new CourseDao(),titleCourse);
        mainJtp.add(tutorPanel,"家教管理");
        mainJtp.add(parentPanel,"家长管理");
        mainJtp.add(coursePanel,"课程管理");
        Vector titleAdmin=new Vector(Arrays.asList(new String[]{"序号","管理员","注册码"}));
        Vector<Vector<Object>> vector=new AdminDao().queryPage(1,999);
        JTable jt=new JTable(vector,titleAdmin){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jt.setRowHeight(25);
        jt.getColumnModel().getColumn(0).setPreferredWidth(200);
        jt.getColumnModel().getColumn(1).setPreferredWidth(400);
        jt.getColumnModel().getColumn(2).setPreferredWidth(600);
        JScrollPane jsp=new JScrollPane(jt);
        mainJtp.add(jsp,"管理员信息");

        /**
         * 系统管理页面
         */
        JPanel changePsw=new JPanel();
        changePsw.setLayout(null);

        JLabel captionLabel = new JLabel("管理员密码修改");
        captionLabel.setFont(new Font("宋体", Font.PLAIN, 43));
        captionLabel.setBounds(0, 94, 319, 47);
        changePsw.add(captionLabel);

        JLabel oldPwLabel = new JLabel("旧密码:");
        oldPwLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        oldPwLabel.setBounds(0, 177, 75, 25);
        changePsw.add(oldPwLabel);

        JLabel newPwLabel = new JLabel("新密码:");
        newPwLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        newPwLabel.setBounds(0, 212, 75, 25);
        changePsw.add(newPwLabel);

        JLabel newPwLabelConfirm = new JLabel("新密码:");
        newPwLabelConfirm.setFont(new Font("宋体", Font.PLAIN, 20));
        newPwLabelConfirm.setBounds(0, 247, 75, 26);
        changePsw.add(newPwLabelConfirm);

        oldPwTextField = new JPasswordField();
        oldPwTextField.setBounds(96, 177, 208, 26);
        changePsw.add(oldPwTextField);
        oldPwTextField.setColumns(10);


        newPwTextField = new JPasswordField();
        newPwTextField.setBounds(96, 212, 208, 26);
        changePsw.add(newPwTextField);
        newPwTextField.setColumns(10);

        newPwTextFieldConfirm = new JPasswordField();
        newPwTextFieldConfirm.setBounds(96, 247, 208, 25);
        changePsw.add(newPwTextFieldConfirm);
        newPwTextFieldConfirm.setColumns(10);

        confirmButton = new JButton("确认");
        confirmButton.setFont(new Font("宋体", Font.PLAIN, 22));
        confirmButton.setBounds(210, 309, 93, 35);
        changePsw.add(confirmButton);

        targetLabel=new JLabel();
        targetLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        targetLabel.setForeground(Color.red);
        targetLabel.setBounds(0, 360, 300, 50);
        changePsw.add(targetLabel);

        innerJp=new JPanel();
        innerJp.setLayout(new BorderLayout());
        innerJp.add(changePsw,"Center");
        alignmentCenter();

        mainJtp.add(innerJp,"系统管理");

    }


    /**
     * 添加事件
     */
    private void addEvent(){
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                alignmentCenter();
            }
        });
        newPwTextFieldConfirm.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==10)operateChangePw();
            }
        });
        confirmButton.addActionListener(e -> operateChangePw());

        /**
         * 密码框得到焦点时错误提示信息消失;
         */
        oldPwTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                targetLabel.setText("");
            }
        });
        newPwTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                targetLabel.setText("");
            }
        });
        newPwTextFieldConfirm.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                targetLabel.setText("");
            }
        });

        /**
         * 关闭窗口事件,当有数据没有保存时提醒用户保存
         */
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ((PanelGenerator)parentPanel).prepareShowPage("对家长用户表的");
                ((PanelGenerator)tutorPanel).prepareShowPage("对家教用户表的");
                ((PanelGenerator)coursePanel).prepareShowPage("对课程信息表的");
            }
        });


    }

    /**
     * 使系统管理界面的内容居中显示
     */
    void alignmentCenter (){
        StringBuffer whitespace =new StringBuffer();
        for (int i = 0; i < (this.getWidth()-320)/6; i++)
            whitespace.append(" ");
        JLabel blankLeft=new JLabel(whitespace.toString());
        innerJp.add(blankLeft,"West");
    }

    /**
     * 更改密码操作
     */
    private void operateChangePw(){
        String oldPw=new String(oldPwTextField.getPassword());
        String newPw=new String(newPwTextField.getPassword());
        String newPwConfirm=new String(newPwTextFieldConfirm.getPassword());
        String tipOldPw=pwIllegalValue(oldPw);
        if(tipOldPw!=null){
            targetLabel.setText(tipOldPw);
            return;
        }
        String tipNewPw=pwIllegalValue(newPw);
        if(tipNewPw!=null){
            targetLabel.setText(tipNewPw);
            return;
        }
        if(!newPw.equals(newPwConfirm)){
            targetLabel.setText("亲,手抖了吧?两次密码不一样哦!");
            return;
        }
        AdminDao adminDao = new AdminDao();
        int matchedUser=adminDao.matchUser(userName,oldPw);
        if(matchedUser!=1){
            targetLabel.setText("哎,旧密码输错了...");
            return;
        }else{
            int result=adminDao.updatePassword(userName,newPw);
            if(result==1){
                targetLabel.setText("");
                JOptionPane.showMessageDialog(null,
                        "更改密码成功!","恭喜",JOptionPane.INFORMATION_MESSAGE);
                oldPwTextField.setText("");
                newPwTextField.setText("");
                newPwTextFieldConfirm.setText("");
            }else{
                targetLabel.setText("");
                JOptionPane.showMessageDialog(null,
                        "保存失败了,请看看是不是出错了",
                        "很遗憾",JOptionPane.ERROR_MESSAGE);
                oldPwTextField.setText("");
                newPwTextField.setText("");
                newPwTextFieldConfirm.setText("");
            }
        }
    }

    /**
     * 存数据库前对密码合法性进行验证
     */
    private String pwIllegalValue(String pw){
        String failInfo;
        int i;
        for (i = 0; i < pw.length(); i++)
            if (!Character.isDigit(pw.charAt(i))) break;
        if (!(pw.length() >= 6 && pw.length() <= 16))
            failInfo = "亲,密码必须为6-16位字符哦";
        else if (i == pw.length())
            failInfo = "亲,为了您的安全,密码不能为纯数字哦";
        else return null;
        return failInfo;
    }


}
