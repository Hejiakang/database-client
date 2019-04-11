package dao.daoImplements;

import dao.TemplateDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TutorDao extends TemplateDao {

    public TutorDao() {
        super("tutors",
                new String[]{"id", "phone","password", "name","sex",
                        "birthday", "education", "email", "creditRank"},
                true);
    }

    @Override
    public String checkData(int col, Object o) {
        if (o == null || (o + "").trim().length() == 0
                &&col!=1) return null;
        switch (col) {
            case 1:
                return checkPhone(o);
            case 2:
                return checkName(o);
            case 3:
                return checkSex(o);
            case 4:
                return checkBirthday(o);
            case 5:
                return checkEducation(o);
            case 6:
                return checkEmail(o);
            case 7:
                return checkCreditRank(o);
            default:
                return "未知错误!";
        }
    }

    private String checkSex(Object o) {
        String str = (o + "").trim();
        int male = str.indexOf("男");
        int female = str.indexOf("女");
        if (male == -1 && female == -1)
            return "亲,请注意:世上只有'男'和'女'两种性别";
        if (str.length() > 2)
            return "亲,最多2个字就够写性别了...";
        return null;
    }

    private String checkBirthday(Object o) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(o + "");
        } catch (ParseException e) {
            return "亲,日期格式不对,请按照这个写哦:\n" +
                    "年-月-日";
        }
        return null;

    }

    private String checkEducation(Object o) {
        String str = (o + "").trim();
        if (!str.equals("专科") && !str.equals("本科") && !str.equals("硕士")
                && !str.equals("博士") && !str.equals("其他"))
            return "亲,学历只能选这些哦:\n" +
                    "专科,本科,硕士,博士,其他";
        System.out.println(111111);
        return null;
    }

    private String checkEmail(Object o) {
        String str = (o + "").trim();
        int atLastIndex = str.lastIndexOf("@");
        int atIndex = str.indexOf("@");
        int dotIndex = str.indexOf(".");
        int dotLastIndex = str.lastIndexOf(".");
        int dotCount=0;
        for (int i = 0; i < str.length(); i++)
            if(str.charAt(i)=='.')if(++dotCount>2)
                return "亲,我看不懂你输的什么,麻烦再输一次吧";
        if (atIndex < 1
                || dotIndex - atIndex <= 1
                ||atLastIndex != atIndex
                || dotLastIndex == str.length()-1
                ||dotLastIndex-dotIndex==1)
            return "亲,这个不是电子邮箱吧";
        str=str.replace(".","").replace("@","");
        if(!(str.matches("^[A-Za-z0-9]+")))return "亲,这个不是电子邮箱吧";
        return null;
    }

    private String checkCreditRank(Object o) {
        String str = (o + "").trim();
        int i;
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return "亲,信用等级是数字噢";
        }
        if (i < 1) return "亲,信用最低1分哦";
        if(i>10) return "亲,信用爆表了,最高信用等级为10哦";
        return null;
    }

}
