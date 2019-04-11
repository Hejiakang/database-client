package util;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Vector;

public class JDBCUtil {
    private static Properties properties;//配置文件
    /**
     * 静态代码块用于加载驱动
     */
    static {
        FileReader fr= null;
        try {
            fr = new FileReader("src/util/JDBC.properties");
            properties=new Properties();
            properties.load(fr);
            Class.forName(properties.getProperty("DRIVER"));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 连接数据库
     */
    private static Connection getConnection(){
        Connection c= null;
        try {
            c = DriverManager.getConnection(
                    properties.getProperty("DATABASE"),
                    properties.getProperty("USERNAME"),
                    properties.getProperty("PASSWORD"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    /**
     * 执行查询操作
     */
    public static Vector<Vector<Object>> query(String sql, Vector v){
        Vector<Vector<Object>> vector=new Vector();
        Connection c=getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=c.prepareStatement(sql);
            if(v!=null)for(int i = 1; i <= v.size(); i++)ps.setObject(i,v.get(i-1));
            rs=ps.executeQuery();
            int columnC=rs.getMetaData().getColumnCount();
            while(rs.next()){
                Vector tmpVector=new Vector();
                for(int i = 1; i <= columnC; i++)tmpVector.add(rs.getObject(i));
                vector.add(tmpVector);
            }
        } catch (Exception e) {
            return null;
        }finally {
            close(rs,ps,c);
        }
        return vector;
    }
    /**
     * 查询的重载
     */
    public static Vector<Vector<Object>> query(String sql){
        return query(sql,null);
    }
    /**
     * 执行增删改操作
     */
    public static int update(String sql, Vector v){
        Connection c=getConnection();
        PreparedStatement ps=null;
        try {
            ps=c.prepareStatement(sql);
            if(v!=null)for(int i = 1; i <= v.size(); i++)ps.setObject(i,v.get(i-1));
            return ps.executeUpdate();
        }catch(MySQLIntegrityConstraintViolationException e){
            return 0;
        }
        catch (SQLException e) {
            return -1;
        }finally {
            close(null,ps,c);
        }
    }
    public static int update(String sql){
        return update(sql,null);
    }


    /**
     * 释放资源
     */
    private static void close(ResultSet rs, PreparedStatement ps, Connection c){
        try {
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            if(c!=null)c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
