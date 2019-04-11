package dao.daoImplements;

import dao.TemplateDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class CourseDao extends TemplateDao {
    public CourseDao(){
        super("courses",new String[]{"id","tutorId","course",
                "lessonTime","price","parentId","releaseDate"});
    }


    @Override
    public String checkData(int col,Object o) {
        if(o==null||(o+"").trim().length()==0
                &&col!=1&&col!=6)return null;
        switch (col) {
            case 1: return checkTutorId(o);
            case 2: return checkCourse(o);
            case 3: return checkLessonTime(o);
            case 4: return checkPrice(o);
            case 5: return checkParentId(o);
            case 6: return checkReleaseDate(o);
            default:return "未知错误!";
        }
    }


    private String checkTutorId(Object o){
        String str=((o==null?"":o)+"").trim();
        if(str.length()==0)return "亲,总得说明是谁提供的课程吧";
        if(!str.matches("[0-9]+"))
        return "亲,序号必须为纯数字噢";
        else return null;
    }

    private String checkCourse(Object o){
        return null;
    }
    private String  checkLessonTime(Object o){
        return null;
    }
    private String checkPrice(Object o){
        return null;
    }
    private String checkParentId(Object o){
        String str=(o+"").trim();
        if(!str.matches("[0-9]+"))
            return "亲,序号必须为纯数字噢";
        else return null;
    }
    private String checkReleaseDate(Object o){
        String str=((o==null?"":o)+"").trim();
        if(str.length()==0)return "亲,任何发生过的事情都有时间吧,咱不能违背客观物理定律";
        SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        sdf.setLenient(false);
        try {
            sdf.parse(str);
        } catch (ParseException e) {
            return "亲,时间格式不对,请参考下面格式噢:" +
                    "\n年-月-日 时:分:秒";
        }
        return null;
    }
}
