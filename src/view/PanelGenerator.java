package view;

import dao.Dao;

import javax.swing.*;

import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;


public class PanelGenerator extends JPanel {
    private Vector titles;
    private Dao dao;
    private Vector<Integer[]> modifiedUnit = new Vector<>();//存放修改过的单元格的行和列
    private Integer[] lastModifiedUnit=new Integer[]{-1,-1};//上次添加的modifiedUnit,用于避免重复
    private int lastId;
    private Vector<Vector>addRowVector=new Vector<>();
    private int currentPage = 1;
    private int count = 30;//每页显示的数量
    private JTextField queryTf;
    private JComboBox jComboBox;

    private JTable jt;
    private int[] colWidth;//列宽数组
    private DefaultTableModel dtm;
    private JScrollPane jsp;

    private JPanel jpSouth;

    private JButton firstPage;
    private JButton previousPage;
    private JButton nextPage;
    private JButton lastPage;
    private JTextField countTf;//每页显示个数输入框

    private JPopupMenu jPopupMenu;
    private JMenuItem itemAdd;
    private JMenuItem itemUpdate;
    private JMenuItem itemDel;
    private JMenuItem itemRefresh;
    private JMenuItem itemResetPw;


    private String oldValue;//表示表格单元格编辑前的值,用于监听单元格的值否改变

    private JTextField totalPageLabelShow;//总页数显示
    private JTextField currentPageTf;//当前页码
    private JComboBox orderComboBox;//排序依据选择框
    private JRadioButton descOrder;//逆序按钮

    private JButton addButton;
    private JButton delButton;
    private JButton saveButton;
    PanelGenerator(Dao dao, Vector titles){this(dao,titles,null);}
    PanelGenerator(Dao dao, Vector titles,int[] colWidth) {
        this.dao = dao;
        this.titles = titles;
        this.colWidth = colWidth;
        this.setLayout(new BorderLayout(3, 1));
        addTableComponent();
        addNorthComponent();//添加北部组件
        addSouthComponent();//添加南部组件
        jPopupMenuInit();
        addEvent();
    }

    private void addTableComponent(){
        Vector<Vector<Object>> rsVector = dao.queryPage(currentPage, count);
        dtm = new DefaultTableModel(rsVector, titles);
        jt = new JTable(dtm) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) return false;
                return super.isCellEditable(row, column);
            }
        };
        jt.setRowHeight(25);
        setColumnWidth(colWidth);
        jsp = new JScrollPane(jt);
        this.add(jsp, "Center");
    }

    private void addNorthComponent(){
        JPanel jpNorth = new JPanel();
        JPanel innerJpLeft = new JPanel();
        saveButton=new JButton(new ImageIcon
                ("src/view/saveButton.png"));
        saveButton.setPreferredSize(new Dimension(24,24));
        saveButton.setBackground(new Color(238,238,238));
        saveButton.setToolTipText("保存");
        addButton=new JButton(new ImageIcon
                ("src/view/addButton.png"));
        addButton.setPreferredSize(new Dimension(23,23));
        addButton.setToolTipText("添加");
        addButton.setBackground(new Color(238,238,238));
        innerJpLeft.add(saveButton);
        innerJpLeft.add(addButton);
        jpNorth.setLayout(new BorderLayout());
        jpNorth.add(innerJpLeft,"West");


        JPanel jpNorthEast=new JPanel();
        delButton=new JButton(new ImageIcon("src/view/deleteButton.jpg"));
        delButton.setToolTipText("删除");
        delButton.setBackground(new Color(238,238,238));
        delButton.setPreferredSize(new Dimension(24,24));
        JLabel blankNorthEast=new JLabel("        ");
        jpNorthEast.add(blankNorthEast);
        jpNorthEast.add(delButton,"East");
        jpNorth.add(jpNorthEast,"East");


        JPanel innerJpCenter=new JPanel();
        Vector jcOptions = new Vector();
        jcOptions.add("搜索全部");
        jcOptions.addAll(titles);
        jComboBox = new JComboBox(jcOptions);
        innerJpCenter.add(jComboBox);
        JLabel queryLabel = new JLabel(new ImageIcon("src/view/query.png"));
        innerJpCenter.add(queryLabel);
        queryTf = new JTextField(10);
        innerJpCenter.add(queryTf);
        JLabel orderLabel = new JLabel("     排序依据:");
        innerJpCenter.add(orderLabel);
        orderComboBox = new JComboBox(titles);
        innerJpCenter.add(orderComboBox,"Center");
        descOrder = new JRadioButton("逆序");
        innerJpCenter.add(descOrder);
        jpNorth.add(innerJpCenter);



        this.add(jpNorth, "North");
    }

    private void addSouthComponent(){
        firstPage = new JButton("首页");
        previousPage = new JButton("上一页");
        nextPage = new JButton("下一页");
        lastPage = new JButton("尾页");
        jpSouth = new JPanel();
        jpSouth.setLayout(new BorderLayout());
        //下方东部布局
        JPanel innerJpRight = new JPanel();
        JLabel blankSouthEast=new JLabel("        ");
        innerJpRight.add(blankSouthEast);
        JLabel countJl = new JLabel("显示条数/页:");
        innerJpRight.add(countJl);
        countTf = new JTextField(3);
        countTf.setText(count + "");
        innerJpRight.add(countTf);
        jpSouth.add(innerJpRight,"East");

        //下方中间布局
        JPanel innerJp = new JPanel();
        innerJp.add(firstPage);
        innerJp.add(previousPage);
        innerJp.add(nextPage);
        innerJp.add(lastPage);
        jpSouth.add(innerJp, "Center");


        //下方西部布局
        JPanel jpSouthWest=new JPanel();
        JLabel totalPageLabel = new JLabel("总页:");
        jpSouthWest.add(totalPageLabel);
        totalPageLabelShow = new JTextField(3);
        totalPageLabelShow.setEditable(false);
        totalPageLabelShow.setText(initGetTotalPage() + "");
        jpSouthWest.add(totalPageLabelShow);
        JLabel currentPageLabel = new JLabel("当前页:");
        jpSouthWest.add(currentPageLabel);
        currentPageTf = new JTextField(3);
        currentPageTf.setText("1");
        jpSouthWest.add(currentPageTf);
        jpSouth.add(jpSouthWest,"West");
        this.add(jpSouth, "South");
    }

    private void jPopupMenuInit() {
        jPopupMenu = new JPopupMenu();
        itemUpdate = new JMenuItem("保存");
        itemAdd = new JMenuItem("新增");
        itemRefresh=new JMenuItem("刷新");
        itemDel = new JMenuItem("删除");
        itemResetPw=new JMenuItem("重置密码");
        jPopupMenu.add(itemUpdate);
        jPopupMenu.add(itemAdd);
        jPopupMenu.add(itemRefresh);
        if(dao.isContainPassword())jPopupMenu.add(itemResetPw);
        jPopupMenu.add(itemDel);
    }


    private void addEvent() {
        /**
         * 开始:选页按钮添加事件
         */
        firstPage.addActionListener(e -> {
            currentPage = 1;
            showPage();
        });
        previousPage.addActionListener(e -> {
            if (currentPage > 1)currentPage--;
                showPage();
        });
        nextPage.addActionListener(e -> {
            if (currentPage < getTotalPage()) currentPage++;
            showPage();
        });
        lastPage.addActionListener(e -> {
            currentPage = getTotalPage();
            showPage();
        });
        /**
         * 结束:选页按钮添加事件
         */



        /**
         * 开始:当前页框事件
         */
        currentPageTf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    jt.requestFocus();
                }
            }
        });
        currentPageTf.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {

                String str = currentPageTf.getText();
                if (str.length() == 0) {
                    currentPageTf.setText(currentPage + "");
                    return;
                }
                for (int i = 0; i < str.length(); i++)
                    if (!Character.isDigit(str.charAt(i))) {
                        currentPageTf.setText(currentPage + "");
                        return;
                    }
                int page = Integer.parseInt(str);
                if (page < 1 || page > getTotalPage()) {
                    currentPageTf.setText(currentPage + "");
                    return;
                }
                currentPage = page;
                showPage();
            }
        });

        /**
         * 结束:当前页框事件
         */


        /**
         * 开始:每页显示条数框事件
         */
        countTf.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String str = countTf.getText();
                int l = str.length();
                if (l == 0) {
                    countTf.setText(count + "");
                    return;
                }
                for (int i = 0; i < l; i++) {
                    if (!Character.isDigit(str.charAt(i))) {
                        countTf.setText(count + "");
                        return;
                    }
                }
                int newCount = Integer.parseInt(str);
                if (newCount < 1 || newCount > 999) {
                    countTf.setText(count + "");
                    return;
                }
                countTf.setText(newCount + "");
                count = newCount;
                currentPage = 1;
                showPage();
            }
        });
        countTf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    jt.requestFocus();
                }
            }
        });
        /**
         * 结束:每页显示条数框事件
         */

        /**
         * 开始:排序依据下拉框事件
         */
        orderComboBox.addActionListener(e -> {
            dao.orderBy(orderComboBox.getSelectedIndex());
            currentPage = 1;
            showPage();
        });
        /**
         * 结束:排序依据下拉框事件
         */

        /**
         * 开始:降序按钮添加事件
         */
        descOrder.addItemListener(e -> {
            if (e.getStateChange() == 1) dao.sequence(false);
            else dao.sequence(true);
            currentPage = 1;
            showPage();
        });
        /**
         * 结束:降序按钮添加事件
         */


        /**
         * 开始:搜索框添加监视事件
         */
        queryTf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 10) jt.requestFocus();
            }
        });
        queryTf.addCaretListener(e -> {
            currentPage = 1;
            showPage();
        });
        /**
         * 结束:搜索框添加监视事件
         */


        /**
         * 开始:按列搜索选择框事件
         */
        jComboBox.addActionListener(e -> {
            currentPage = 1;
            showPage();
        });
        /**
         * 结束:按列搜索选择框事件
         */


        /**
         * 开始:JTable右键触发菜单
         */
        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 3) {
                    if(jt.isEditing()){
                        jt.getCellEditor().stopCellEditing();
                        return;
                    }
                    jPopupMenu.show(jt, e.getX(), e.getY());
                }
            }
        });
        /**
         * 结束:JTable右键触发菜单
         */



        /**
         * 开始:"添加"按钮事件监听
         */
        itemAdd.addActionListener(e -> addRow());
        addButton.addActionListener(e -> addRow());
        /**
         * 结束:"添加"按钮事件监听
         */


        /**
         * 开始:"保存"按钮监听
         */
        itemUpdate.addActionListener(e -> saveCurrentPage());
        saveButton.addActionListener(e -> saveCurrentPage());
        /**
         * 结束:"保存"按钮监听
         */

        /**
         * 开始:刷新按钮事件
         */
        itemRefresh.addActionListener(e -> {if(addRowVector.size()==0)showPage();});
        /**
         * 结束:刷新按钮事件
         */

        /**
         * 开始:"重置密码"按钮监听
         */
        itemResetPw.addActionListener(e -> resetPassword());
        /**
         * 结束:"重置密码"按钮监听
         */


        /**
         * 开始:"删除"按钮监听
         */
        itemDel.addActionListener(e -> deleteRows());
        delButton.addActionListener(e -> deleteRows());
        /**
         * 结束:"删除"按钮监听
         */


        /**
         * 开始:表格中某个单元格的值发生改变时触发的事件
         */
        dtm.addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getLastRow();
                int col = e.getColumn();
                if (row == -1 || col == -1) return;
                String newValue = jt.getValueAt(row, col)==null?"":
                        jt.getValueAt(row,col).toString();
                String errorInfo=dao.checkData(col,newValue);
                if(errorInfo!=null){
                    JOptionPane.showMessageDialog(this,
                            errorInfo, "很遗憾...",JOptionPane.ERROR_MESSAGE);
                    {
                        /**如果数据库中的数据本身就已经是错误的,
                         为了防止表中再设置一次错误值后发生死循环并导致程序无法退出,
                         故对旧值也进行了判断
                         **/
                        if (dao.checkData(col, oldValue)==null)
                            dtm.setValueAt(oldValue, row, col);
                        return;
                    }
                }
                if(addRowVector.size()!=0)return;
                if (!newValue.equals(oldValue)){
                    //保证modifiedUnit里面不添加重复元素
                    if(row==lastModifiedUnit[0]&&col==lastModifiedUnit[1])return;
                    modifiedUnit.add(new Integer[]{row, col});
                    lastModifiedUnit=new Integer[]{row, col};
                }
            }
        });
        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                recordOldValue();
            }
        });
        jt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                recordOldValue();
            }
        });
        /**
         * 结束:表格中某个单元格的值发生改变时触发的事件
         */

        /**
         * 开始:去除表格单元格的编辑状态
         */
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(jt.isEditing())jt.getCellEditor().stopCellEditing();
            }
        });
        /**
         * 结束:去除表格单元格的编辑状态
         */

    }

    /**
     * 开始:重置密码
     */
    private void resetPassword(){
        if(jt.getSelectedRows().length!=1){
            JOptionPane.showMessageDialog(this, "亲,请选中要重置的用户!(只能选择1个用户哦)",
                    "很遗憾", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(addRowVector.size()!=0){
            JOptionPane.showMessageDialog(this,
                    "亲,还没添加此用户呢,别乱点!", "失败了", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int tmp = JOptionPane.showConfirmDialog(this, "是否确认重置密码?",
                "警告", JOptionPane.YES_NO_OPTION);
        if(tmp!=0)return;
        int rs=dao.updatePassword((int) dtm.getValueAt(jt.getSelectedRow(),0));
        if(rs==1)JOptionPane.showMessageDialog(this, "亲,密码已重置为\"HelloWorld\"" +
                        "\n请提醒用户及时修改",
                "恭喜", JOptionPane.INFORMATION_MESSAGE);
        else JOptionPane.showMessageDialog(this, "亲,重置失败了,请稍后重试一下吧!",
                "很遗憾", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * 结束:重置密码
     */



    /**
     * 开始:记录旧值
     */
    private void recordOldValue(){
        int row = jt.getSelectedRow();
        int col = jt.getSelectedColumn();
        if(row==-1||col==-1)return;
        if (row >= 0 && col >= 0)
            oldValue = jt.getValueAt(row, col) == null ? "" : jt.getValueAt(row, col).toString();
    }
    /**
     * 结束:记录旧值
     */


    /**
     * 开始:展示某一页
     */
    private void showPage() {
        if(!prepareShowPage())return;
        totalPageLabelShow.setText(getTotalPage() + "");
        Vector<Vector<Object>> rsVector;
        int jcbId = jComboBox.getSelectedIndex();
        String keyword = queryTf.getText().trim();
        if (keyword.length() == 0) rsVector = dao.queryPage(currentPage, count);
        else if (jcbId == 0) rsVector = dao.queryPage(currentPage, count, keyword);
        else rsVector = dao.queryPage(currentPage, count, --jcbId, keyword);
        dtm.setDataVector(rsVector, titles);
        currentPageTf.setText(currentPage + "");
        setColumnWidth(colWidth);
    }
    /**
     * 结束:展示某一页
     */

    /**
     * 开始:显示新的一页前的准备工作
     */
    private boolean prepareShowPage(){
        return prepareShowPage("");
    }
    //此处为共用方法并重载prepareShowPage(),方便关闭窗口时调用
    public boolean prepareShowPage(String tip){
        if (modifiedUnit.size()!=0||addRowVector.size()!=0) {
            int tmp = JOptionPane.showConfirmDialog(this,
                    "是否保存"+tip+"修改?", "提示", JOptionPane.YES_NO_OPTION);
            if (tmp == 0) {
                if(!saveCurrentPage())return false;
            }
            else {
                modifiedUnit.clear();
                lastModifiedUnit=new Integer[]{-1,-1};
                addRowVector.clear();
            }
        }
        return true;
    }
    /**
     * 结束:显示新的一页前的准备工作
     */


    /**
     * 开始:获得总页数
     *
     * @return 获得总页数
     */
    private int getTotalPage() {
        int totalCount;
        String keyword = queryTf.getText().trim();
        int jComboBoxId = jComboBox.getSelectedIndex();
        if (keyword.length() == 0) totalCount = dao.getTotalCount();
        else if (jComboBoxId == 0) totalCount = dao.getTotalCount(keyword);
        else totalCount = dao.getTotalCount(--jComboBoxId, keyword);
        if (totalCount == 0) return 1;
        return totalCount % count == 0 ? totalCount / count : totalCount / count + 1;
    }

    //用于初始化时获取总页数
    private int initGetTotalPage() {
        int totalCount = dao.getTotalCount();
        if (totalCount == 0) return 1;
        return totalCount % count == 0 ? totalCount / count : totalCount / count + 1;
    }
    /**
     * 结束:获得总页数
     */


    /**
     * 开始:设置列宽度
     */
    private void setColumnWidth(int[] width){
        if(width==null)return;
        for (int i = 0; i < width.length; i++){
            if(width[i]!=0)
            jt.getColumnModel().getColumn(i).setPreferredWidth(width[i]);
        }

    }
    /**
     * 结束:设置列宽度
     */

    /**
     * 保存当前页
     */
    private boolean saveCurrentPage() {
        if(addRowVector.size()!=0){
            if(insertThisPage()){
                addRowVector.clear();
                currentPage=getTotalPage();
                return true;
            }return false;
        }
        if(modifiedUnit.size()==0)return true;
        boolean isSuccess=true;
        Vector<Integer[]> errorRows=new Vector<>();
        for (Integer[] i : modifiedUnit) {
            int id = (int) dtm.getValueAt(i[0], 0);
            int result = dao.update(i[1], (dtm.getValueAt(i[0], i[1])+"").trim(), id);
            if (result == 0){
                JOptionPane.showMessageDialog(this,
                        "第"+(i[0]+1)+"行第"+(i[1]+1)+"列保存失败了,可能存在重复数据...",
                        "错误",JOptionPane.ERROR_MESSAGE);
                isSuccess=false;
                errorRows.add(new Integer[]{i[0],i[1]});
            }
            if(result==-1){
                JOptionPane.showMessageDialog(this,
                        "第"+(i[0]+1)+"行第"+(i[1]+1)+"列保存失败了!请检查操作合法性哦!",
                        "错误", JOptionPane.ERROR_MESSAGE);
                errorRows.add(new Integer[]{i[0],i[1]});
                isSuccess=false;
            }
        }

        if(errorRows.size()!=0){
            modifiedUnit=errorRows;
            jt.setRowSelectionInterval(errorRows.get(0)[0],errorRows.get(0)[0]);
        }else {
            modifiedUnit.clear();
            lastModifiedUnit=new Integer[]{-1,-1};
        }

        return isSuccess;
    }



    /**
     * 开始:添加一行且不保存到数据库,当本页添加满后提示是否保存到数据库
     */
    private void addRow() {
        if(addRowVector.size()==0){
            if (modifiedUnit.size() != 0) {
                int tmp = JOptionPane.showConfirmDialog(
                        this, "是否保存本页修改?",
                        "提示", JOptionPane.YES_NO_OPTION);
                if (tmp == 0) saveCurrentPage();
                else {
                    modifiedUnit.clear();
                    lastModifiedUnit=new Integer[]{-1,-1};
                }
            }
            lastId=dao.queryLastId();//第一次添加时需要从数据库中查询最大id值
            Vector colVector = new Vector();
            colVector.add(lastId+1);
            addRowVector.add(colVector);
            dtm.setDataVector(addRowVector,titles);
            jt.setRowSelectionInterval(dtm.getRowCount() - 1, dtm.getRowCount() - 1);
            return;
        }
        if (addRowVector.size() >= count){
            int tmp = JOptionPane.showConfirmDialog(
                    this, "保存本页所有内容?",
                    "提示", JOptionPane.YES_NO_OPTION);
            if (tmp == 0) {
                saveCurrentPage();
                addRow();
                return;
            }
            else {
                addRowVector.clear();
                showPage();
                return;
            }
        }
        Vector colVector=new Vector();
        colVector.add(lastId+addRowVector.size()+1);
        addRowVector.add(colVector);
        dtm.setDataVector(addRowVector,titles);
        jt.setRowSelectionInterval(dtm.getRowCount() - 1, dtm.getRowCount() - 1);
    }
    /**
     * 结束:添加一行
     */

    /**
     * 开始:删除一行或多行
     */
    private void deleteRows() {
        if (modifiedUnit.size()!=0) {
            int tmp = JOptionPane.showConfirmDialog(this,
                    "是否保存本页修改?", "提示", JOptionPane.YES_NO_OPTION);
            if (tmp == 0) {
                if(!saveCurrentPage())return;
            }
            else {
                modifiedUnit.clear();
                lastModifiedUnit=new Integer[]{-1,-1};
            }
        }
        int[] rows = jt.getSelectedRows();
        if (jt.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "请选择要删除的行!",
                    "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int[] id = new int[rows.length];
        for (int i = 0; i < rows.length; i++)
            id[i] = (int) jt.getValueAt(rows[i], 0);
        if(addRowVector.size()!=0){
            /**
             * 因为用于构成dtm的结果集vector的值改变会导致出现死机,所以此处进行了结果集备份
             */
            Vector<Vector> cloneVector = (Vector<Vector>)dtm.getDataVector().clone();
            for(int i=0;i<id.length;i++){
                int dtmRowCount=dtm.getRowCount();
                for (int j = 0; j <dtmRowCount ; j++) {
                    if((int)dtm.getValueAt(j,0)==id[i]){
                        dtm.removeRow(j);
                        cloneVector.remove(j);
                        ++lastId;
                        dtmRowCount=dtm.getRowCount();
                    }
                }
            }
            dtm.setDataVector(cloneVector,titles);
            addRowVector=cloneVector;
            return;
        }
        int tmp = JOptionPane.showConfirmDialog(this, "是否确认删除?",
                "警告", JOptionPane.YES_NO_OPTION);

        if (tmp == 0) {
            for (int i = 0; i < id.length; i++)
                if (dao.deleteRow(id[i]) != 1)
                    JOptionPane.showMessageDialog(this, "删除失败了...",
                            "很遗憾", JOptionPane.ERROR_MESSAGE);
        }else return;
        showPage();
    }
    /**
     * 结束:删除一行或多行
     */


    /**
     * 开始:把当前页数据添加到数据库
     * @return true代表全部添加成功,false代表有失败情况
     */
    private boolean insertThisPage(){
        Vector<Vector> vector=dtm.getDataVector();
        Vector<Integer> errorRows=new Vector<>();
        for(int i=0;i<dtm.getRowCount();++i){
            int insertResult=dao.insert(vector.get(i));
            if(insertResult==0){
                JOptionPane.showMessageDialog(this,
                        "第"+(i+1)+"行信息重复,保存失败",
                        "提示",JOptionPane.INFORMATION_MESSAGE);
                errorRows.add(i);
            }else if(insertResult==-1){
                JOptionPane.showMessageDialog(this,
                        "第"+(i+1)+"行信息不合法,保存失败",
                        "提示",JOptionPane.INFORMATION_MESSAGE);
                errorRows.add(i);
            }
        }
        if(errorRows.size()!=0) {
            Vector<Vector> errorVector = new Vector<>();
            for (Integer i : errorRows)
                errorVector.add(vector.get(i));
            dtm.setDataVector(errorVector, titles);
            addRowVector=errorVector;
            lastId=dao.queryLastId();
            jt.setRowSelectionInterval(0,addRowVector.size()-1);

            return false;
        }else return true;
    }
    /**
     * 结束:把当前页数据添加到数据库
     */



}
