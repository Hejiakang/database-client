package dao.daoImplements;

import dao.TemplateDao;

public class ParentDao extends TemplateDao {
    public ParentDao(){
        super("parents",
                new String[]{"id","phone","password","name","address"},
                true);
    }
    @Override
    public String checkData(int col,Object o){
        if(o==null||(o+"").trim().length()==0
                &&col!=1)return null;
        switch (col) {
            case 1: return checkPhone(o);
            case 2: return checkName(o);
            case 3: return checkAddress(o);
            default:return "未知错误!";
        }
    }




    private String checkAddress(Object o){
        String str=(o+"").trim();
        if(str.length()<=5)return "亲,地址太短了吧";
        return null;
    }

}
