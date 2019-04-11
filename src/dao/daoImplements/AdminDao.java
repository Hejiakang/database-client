package dao.daoImplements;

import dao.TemplateDao;
import util.JDBCUtil;

import java.util.Vector;

public class AdminDao  extends TemplateDao {
    public AdminDao(){
        super("admin",
                new String[]{"id","accountName","password","registerCode"},
                true);
    }

    public int matchRegisterCode(String registerCode) {
        Vector<String> v=new Vector();
        v.add(registerCode);
        String sql="select count(1) from "+tableName+
                " where "+colName[2]+"=?";
        Vector<Vector<Object>> rsVector=JDBCUtil.query(sql,v);
        return (int)(long)rsVector.get(0).get(0);
    }
    public int insertUser(String registerCode,String userName,String password){
        String sql="insert into "+tableName+"("+colName[1]+","+colName[2]+","+colName[3]+") values(?,?,?)";
        Vector v=new Vector();
        v.add(userName);
        v.add(registerCode);
        v.add(password);
        return JDBCUtil.update(sql,v);
    }

    @Override
    public String checkData(int col, Object o) {
        return null;
    }
}
