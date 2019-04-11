package dao;

import util.JDBCUtil;
import util.MD5;

import java.util.Vector;

public abstract class TemplateDao implements Dao{
    protected String tableName;
    protected String[] colName;
    private int orderCol;
    private String sequence="asc";
    private boolean containPassword;

    @Override
    public boolean isContainPassword() {
        return containPassword;
    }

    @Override
    public void orderBy(int col) {
        this.orderCol=switchCol(col);
    }
    @Override
    public void sequence(boolean b) {
        this.sequence=b?"asc":"desc";
    }

    /**
     * 构造方法
     * @param tableName 表名
     * @param colName 字段名数组
     */
    protected TemplateDao(String tableName, String[] colName) {
        this.tableName = tableName;
        this.colName = colName;
    }
    protected TemplateDao(String tableName, String[] colName,
                          boolean containPassword) {
        this(tableName,colName);
        this.containPassword=containPassword;
    }

    protected String getColNameStrIgnorePw(){
        StringBuffer colNameStr=new StringBuffer();
        for (int i = 0; i < colName.length; i++) {
            if(containPassword)if(i==DB_PASSWORD_COL)continue;
            colNameStr.append(colName[i]);
            if(i<colName.length-1)colNameStr.append(",");
        }
        return colNameStr.toString();
    }


    @Override
    public Vector<Vector<Object>> queryPage
            (int currentPage, int count) {
        Vector  v=new Vector();
        v.add((currentPage-1)*count);
        v.add(count);
        String sql="select "+getColNameStrIgnorePw()+" from " +tableName+
                " order by " +colName[orderCol]+" "+sequence+" limit ?,?";
        return JDBCUtil.query(sql,v);
    }


    @Override
    public Vector<Vector<Object>> queryPage
            (int currentPage, int count, int col, String keyword){
        Vector  v=new Vector();
        v.add("%"+keyword+"%");
        v.add((currentPage-1)*count);
        v.add(count);
        String sql="select "+getColNameStrIgnorePw()+" from "+tableName+
                " where "+ colName[switchCol(col)]+" like ? order by "+
                colName[orderCol]+" "+sequence+" limit ?,?";
        return JDBCUtil.query(sql,v);
    }

    @Override
    public Vector<Vector<Object>> queryPage
            (int currentPage, int count, String keyword) {
        Vector v=new Vector();
        StringBuffer sql=new StringBuffer
                ("select "+getColNameStrIgnorePw()+" from "+tableName+" where ");
        for (int i = 0; i < colName.length-1; i++) {
            if(containPassword)if(i==DB_PASSWORD_COL)continue;
            sql.append(colName[i]+" like ? or ");
            v.add("%"+keyword+"%");
        }
        sql.append(colName[colName.length-1]+" like ? order by "+
                colName[orderCol]+" "+sequence+" limit ?,?");
        v.add("%"+keyword+"%");
        v.add((currentPage-1)*count);
        v.add(count);
        return JDBCUtil.query(sql.toString(),v);
    }

    @Override
    public int getTotalCount(int col, String keyword) {
        String sql="select count(1) from "+tableName+" where "+
                colName[switchCol(col)]+" like ?";
        Vector v=new Vector();
        v.add("%"+keyword+"%");
        Vector<Vector<Object>> rsVector=JDBCUtil.query(sql,v);
        return (int)(long)rsVector.get(0).get(0);
    }

    @Override
    public int getTotalCount(String keyword) {
        Vector v=new Vector();
        StringBuffer sql=new StringBuffer
                ("select count(1) from "+tableName+" where ");
        for (int i = 0; i < colName.length; i++) {
            if(containPassword)if(i==DB_PASSWORD_COL)continue;
            sql.append(colName[i]+" like ? or ");
            v.add("%"+keyword+"%");
        }
        sql.reverse().delete(0,4).reverse();
        Vector<Vector<Object>> rsVector=JDBCUtil.query(sql.toString(),v);
        return (int)(long)rsVector.get(0).get(0);
    }

    @Override
    public int getTotalCount() {
        Vector<Vector<Object>> v=JDBCUtil.query
                ("select count(1) from "+tableName);
        return (int)(long)v.get(0).get(0);
    }

    @Override
    public int update(int col, Object o, int id) {
        String sql="update "+tableName+" set "+
                colName[switchCol(col)]+"=? where id=?";
        Vector v=new Vector();
        v.add(o);
        v.add(id);
        return JDBCUtil.update(sql,v);
    }

    @Override
    public int deleteRow(int id) {
        String sql="delete from "+tableName+" where id=?";
        Vector v=new Vector();
        v.add(id);
        return JDBCUtil.update(sql,v);
    }

    @Override
    public int insert(Vector row) {
        StringBuffer sql = new StringBuffer("insert into " + tableName+" values(");
        Vector v=new Vector();
        for (int i=0;i<row.size();i++){
            if(containPassword&&i==DB_PASSWORD_COL){
                sql.append("?,");
                v.add("HelloWorld");
            }
            sql.append("?,");
            v.add(row.get(i)==null?"":(row.get(i)+"").trim());
        }
        sql.reverse().delete(0,1).reverse();
        sql.append(")");
        return JDBCUtil.update(sql.toString(),v);
    }

    @Override
    public int queryLastId() {
        Vector<Vector<Object>> rsVector = JDBCUtil.query("select "+colName[0]+" from "
                + tableName + " order by " + colName[0] + " desc limit 1");
        return (rsVector==null||rsVector.size()==0?-1:(int)rsVector.get(0).get(0));
    }

    @Override
    public int matchUser(String user,String pw) {
        Vector<String> v=new Vector();
        v.add(user);
        v.add(MD5.GetMD5Code(pw));
        String sql="select count(1) from "+tableName+" where "+
                colName[DB_LOGIN_ACCOUNT_COL]+"=?"+" and "+colName[DB_PASSWORD_COL]+"=?";
        Vector<Vector<Object>> rsVector=JDBCUtil.query(sql,v);
        return (int)(long)rsVector.get(0).get(0);
    }
    @Override
    public int updatePassword(String user,String newPw){
        String sql="update "+tableName+" set "+colName[DB_PASSWORD_COL]+
                "=? where "+colName[DB_LOGIN_ACCOUNT_COL]+"=?";
        Vector v=new Vector();
        v.add(MD5.GetMD5Code(newPw));
        v.add(user);
        return JDBCUtil.update(sql,v);
    }
    @Override
    public int updatePassword(int id){
        String sql="update "+tableName+" set "+colName[DB_PASSWORD_COL]+
                "=? where "+colName[0]+"=?";
        Vector v=new Vector();
        v.add(MD5.GetMD5Code(DEFAULT_PASSWORD));
        v.add(id);
        return JDBCUtil.update(sql,v);
    }


    /**
     * 开始:信息验证方法
     */
    protected String checkPhone(Object o) {
        String str = ((o==null?"":o) + "").trim();
        if(str.length()==0)return "亲,手机号码不能为空哦";
        if (!str.matches("[0-9]+")) return "亲,手机号码是数字噢";
        if (str.length() != 11) return "亲,手机号码是11位噢";
        return null;
    }


    protected String checkName(Object o){
        String str=(o+"").trim();
        if(!str.matches("[\\u4e00-\\u9fa5]+"))
            return "亲,只能是中国姓名哦";
        if(str.length()<2)
            return "亲,姓名最少2个字噢";
        if(str.length()>5)
            return "亲,姓名最多5个汉噢";
        return null;
    }
    /**
     * 结束:信息验证方法
     */

    /**
     * 用于把表格的列转换为数据库中的列,主要是解决密码位置问题
     * @param col 表格中对应的列数
     * @return  数据库中对应的列数
     */
    private int switchCol(int col){
        return containPassword&&col>=DB_PASSWORD_COL?col+1:col;
    }

}
