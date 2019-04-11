package dao;

import java.util.Vector;

public interface Dao {
    /**
     * 规定数据库中密码列的名字必须为2
     */
    int DB_PASSWORD_COL=2;


    /**
     * 规定数据库中用于登录的账户名所在列必须为第1列
     */
    int DB_LOGIN_ACCOUNT_COL=1;


    /**
     * 默认密码
     */
    String DEFAULT_PASSWORD="HelloWorld";



    /**
     * 直接查询某一页(查询所有列)
     * @param currentPage 查询的第CurrentPage页
     * @param count 查询的第CurrentPage页的数量
     * @return 以复合向量形式返回查询的结果
     */
    Vector<Vector<Object>> queryPage(int currentPage,int count);



    /**
     * 模糊查询符合某一列限定条件一页数据(查询所有列)
     * @param currentPage 查询的第CurrentPage页
     * @param count 查询的第CurrentPage页的数量
     * @param col 模糊查询依据的列
     * @param keyword 需要匹配的关键字
     * @return 以复合向量形式返回查询的结果
     */
    Vector<Vector<Object>> queryPage(int currentPage,int count,int col,String keyword);




    /**
     * 查询在数据库表中匹配关键字的所有列
     * @param currentPage
     * @param count
     * @param keyword
     * @return
     */
    Vector<Vector<Object>> queryPage(int currentPage,int count,String keyword);




    /**
     * 模糊查询符合某一列限定条件的总数量
     * @param col 模糊查询依据的列
     * @param keyword 需要匹配的关键字
     * @return 查询到的总行数
     */
    int getTotalCount(int col,String keyword);




    /**
     * 模糊查询与全部列匹配的总行数
     * @param keyword 需要匹配的关键字
     * @return 查询到的总行数
     */
    int getTotalCount(String keyword);



    /**
     * 直接查询数据库中所有行数
     * @return 总行数
     */
    int getTotalCount();



    /**
     * 更改数据库中的某一条记录
     * @param col 列限定条件
     * @param o 要加入被更改位置的新对象
     * @param id 行限定条件,其中id为存储在数据库中的表的主键
     * @return 返回影响的行数
     */
    int update(int col,Object o,int id);



    /**
     * 删除与id相匹配的行
     * @param id 要删除的id所在的行
     * @return 返回影响的行数
     */
    int deleteRow(int id);



    /**
     * 插入完整的一行记录
     * @row 一行完整信息的向量
     * @return 受影响的行数
     */
    int insert(Vector row);



    /**
     * 设置按某一列排序
     * @param col 列对应的序号
     */
    void orderBy(int col);



    /**
     * 设置查询的升序或逆序
     * @param b true表示升序,false表示逆序
     */
    void sequence(boolean b);



    /**
     * @return 数据库中最大的id;-1表示没有查到数据
     */
    int queryLastId();




    /**
     * 返回与用户名和密码匹配的记录的条数;
     * @param user 用户名
     * @param pw 密码
     * @return 匹配的数量
     */
    int matchUser(String user,String pw);




    /**
     * 验证某个数据是否合法
     * @param col 所要验证的数据对应的列名
     * @param o 所要验证的数据
     * @return 返回错误提示信息,如果正确返回null
     */
    String checkData(int col,Object o);




    /**
     * 更改密码
     * @param user 用于登录的用户名
     * @param newPw 新密码
     * @return 数据库中受影响的行数
     */
    int updatePassword(String user,String newPw);




    /**
     * 重置密码为初始密码
     * @param id 唯一标示符号
     * @return 数据库中受影响的行数
     */
    int updatePassword(int id);




    /**
     * 查询表中是否包含密码
     * @return
     */
    boolean isContainPassword();

}
