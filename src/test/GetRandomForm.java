
package test;

public class GetRandomForm {
    //姓名资源
    private static String firstName1 = "李王张刘";
    private static String firstName2 = "陈杨赵黄周吴徐孙胡朱高林何郭马罗梁宋郑谢韩唐冯";
    private static String firstName3 = "于董萧程曹袁邓许傅沈曾彭吕苏卢蒋蔡贾丁魏薛叶阎余潘杜戴夏钟汪田任姜范方石姚谭廖邹熊金陆郝";
    private static String firstName4 = "孔白崔康毛邱秦江史顾侯邵孟龙万段漕钱汤尹黎易常武乔贺赖龚文庞樊兰殷施陶洪翟安颜倪严牛温芦季俞章鲁葛伍韦申尤毕聂丛焦向柳邢路岳齐沿";
    private static String firstName5 = "梅莫庄辛管祝左涂谷祁时舒耿牟卜路詹关苗凌费纪靳盛童欧甄项曲成游阳裴席卫查屈鲍位覃霍翁隋植甘景薄单包司柏宁柯阮桂闵解强柴华车冉房边辜吉饶刁瞿戚丘古米池滕晋苑邬臧畅宫来嵺苟全褚廉简娄盖符奚木穆党燕郎邸冀谈姬屠连郜晏栾郁商蒙计喻揭窦迟宇敖糜鄢冷卓花仇艾蓝都巩稽井练仲乐虞卞封竺冼原官衣楚佟栗匡宗应台巫鞠僧桑荆谌银扬明沙薄伏岑习胥保和蔺";
    private static String girlName = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽";
    private static String boyName = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";
    //手机号码资源
    private static String[] telFirst = ("130,131,132,133,134,135,136,137,138,139,150,151,152,157,158,159,155,156,153,173,176,166,180,182,185,186,188,189").split(",");
    //地址资源
    private static String[] district = "中原区,二七区,管城回族区,金水区,上街区,惠济区".split(",");
    private static String[] zhongYuan = "林山寨街道,建设路街道,棉纺路街道,秦岭路街道,桐柏路街道,三官庙街道,绿东村街道,汝河路街道".split(",");
    private static String[] erQi = "淮河路街道,解放路街道,铭功路街道,一马路街道,蜜蜂张街道,五里堡街道,大学路街道,建中街街道".split(",");
    private static String[] guanCheng = "北下街街道,西大街街道,南关街道,城东路街道,东大街街道,二里岗街道,陇海马路街道,紫荆山南路街道".split(",");
    private static String[] jinShui = "兴达路街道,经八路街道,花园路街道,人民路街道,杜岭街街道,大石桥街道,南阳路街道,南阳新村街道".split(",");
    private static String[] shangJie = "济源路街道,中心路街道,新安路街道,工业路街道,矿山街道,峡窝镇".split(",");
    private static String[] huiJi = "新城街道,刘寨街道,老鸦陈街道,长兴路街道,迎宾路街道,大河路街道,花园口镇,古荥镇".split(",");
    private static String[] road = "重庆大厦,黑龙江路,十梅庵街,遵义路,湘潭街,瑞金广场,仙山街,仙山东路,仙山西大厦,白沙河路,赵红广场,机场路,民航街,长城南路,流亭立交桥,虹桥广场,长城大厦,礼阳路,风岗街,中川路,白塔广场,兴阳路,文阳街,绣城路,河城大厦,锦城广场,崇阳街,华城路,康城街,正阳路,和阳广场,中城路,江城大厦,顺城路,安城街,山城广场,春城街,国城路,泰城街,德阳路,明阳大厦,春阳路,艳阳街,秋阳路,硕阳街,青威高速,瑞阳街,丰海路,双元大厦,惜福镇街道,夏庄街道,古庙工业园,中山街,太平路,广西街,潍县广场,博山大厦,湖南路,济宁街,芝罘路,易州广场,荷泽四路,荷泽二街,荷泽一路,荷泽三大厦,观海二广场,广西支街,观海一路,济宁支街,莒县路,平度广场,明水路,蒙阴大厦,青岛路,湖北街,江宁广场,郯城街,天津路,保定街,安徽路,河北大厦,黄岛路,北京街,莘县路,济南街,宁阳广场,日照街,德县路,新泰大厦,荷泽路,山西广场,沂水路,肥城街,兰山路,四方街,平原广场,泗水大厦,浙江路,曲阜街,寿康路,河南广场,泰安路,大沽街,红山峡支路,西陵峡一大厦,台西纬一广场,台西纬四街,台西纬二路,西陵峡二街,西陵峡三路,台西纬三广场,台西纬五路,明月峡大厦,青铜峡路,台西二街,观音峡广场,瞿塘峡街,团岛二路,团岛一街,台西三路,台西一大厦,郓城南路,团岛三街,刘家峡路,西藏二街,西藏一广场,台西四街,三门峡路,城武支大厦,红山峡路,郓城北广场,龙羊峡路,西陵峡街,台西五路,团岛四街,石村广场,巫峡大厦,四川路,寿张街,嘉祥路,南村广场,范县路,西康街,云南路,巨野大厦,西江广场,鱼台街,单县路,定陶街,滕县路,钜野广场,观城路,汶上大厦,朝城路,滋阳街,邹县广场,濮县街,磁山路,汶水街,西藏路,城武大厦,团岛路,南阳街,广州路,东平街,枣庄广场,贵州街,费县路,南海大厦,登州路,文登广场,信号山支路,延安一街,信号山路,兴安支街,福山支广场,红岛支大厦,莱芜二路,吴县一街,金口三路,金口一广场,伏龙山路,鱼山支街,观象二路,吴县二大厦,莱芜一广场,金口二街,海阳路,龙口街,恒山路,鱼山广场,掖县路,福山大厦,红岛路,常州街,大学广场,龙华街,齐河路,莱阳街,黄县路,张店大厦,祚山路,苏州街,华山路,伏龙街,江苏广场,龙江街,王村路,琴屿大厦,齐东路,京山广场,龙山路,牟平街,延安三路,延吉街,南京广场,东海东大厦,银川西路,海口街,山东路,绍兴广场,芝泉路,东海中街,宁夏路,香港西大厦,隆德广场,扬州街,郧阳路,太平角一街,宁国二支路,太平角二广场,天台东一路,太平角三大厦,漳州路一路,漳州街二街,宁国一支广场,太平角六街,太平角四路,天台东二街,太平角五路,宁国三大厦,澳门三路,江西支街,澳门二路,宁国四街,大尧一广场,咸阳支街,洪泽湖路,吴兴二大厦,澄海三路,天台一广场,新湛二路,三明北街,新湛支路,湛山五街,泰州三广场,湛山四大厦,闽江三路,澳门四街,南海支路,吴兴三广场,三明南路,湛山二街,二轻新村镇,江南大厦,吴兴一广场,珠海二街,嘉峪关路,高邮湖街,湛山三路,澳门六广场,泰州二路,东海一大厦,天台二路,微山湖街,洞庭湖广场,珠海支街,福州南路,澄海二街,泰州四路,香港中大厦,澳门五路,新湛三街,澳门一路,正阳关街,宁武关广场,闽江四街,新湛一路,宁国一大厦,王家麦岛,澳门七广场,泰州一路,泰州六街,大尧二路,青大一街,闽江二广场,闽江一大厦,屏东支路,湛山一街,东海西路,徐家麦岛函谷关广场,大尧三路,晓望支街,秀湛二路,逍遥三大厦,澳门九广场,泰州五街,澄海一路,澳门八街,福州北路,珠海一广场,宁国二路,临淮关大厦,燕儿岛路,紫荆关街,武胜关广场,逍遥一街,秀湛四路,居庸关街,山海关路,鄱阳湖大厦,新湛路,漳州街,仙游路,花莲街,乐清广场,巢湖街,台南路,吴兴大厦,新田路,福清广场,澄海路,莆田街,海游路,镇江街,石岛广场,宜兴大厦,三明路,仰口街,沛县路,漳浦广场,大麦岛,台湾街,天台路,金湖大厦,高雄广场,海江街,岳阳路,善化街,荣成路,澳门广场,武昌路,闽江大厦,台北路,龙岩街,咸阳广场,宁德街,龙泉路,丽水街,海川路,彰化大厦,金田路,泰州街,太湖路,江西街,泰兴广场,青大街,金门路,南通大厦,旌德路,汇泉广场,宁国路,泉州街,如东路,奉化街,鹊山广场,莲岛大厦,华严路,嘉义街,古田路,南平广场,秀湛路,长汀街,湛山路,徐州大厦,丰县广场,汕头街,新竹路,黄海街,安庆路,基隆广场,韶关路,云霄大厦,新安路,仙居街,屏东广场,晓望街,海门路,珠海街,上杭路,永嘉大厦,漳平路,盐城街,新浦路,新昌街,高田广场,市场三街,金乡东路,市场二大厦,上海支路,李村支广场,惠民南路,市场纬街,长安南路,陵县支街,冠县支广场,小港一大厦,市场一路,小港二街,清平路,广东广场,新疆路,博平街,港通路,小港沿,福建广场,高唐街,茌平路,港青街,高密路,阳谷广场,平阴路,夏津大厦,邱县路,渤海街,恩县广场,旅顺街,堂邑路,李村街,即墨路,港华大厦,港环路,馆陶街,普集路,朝阳街,甘肃广场,港夏街,港联路,陵县大厦,上海路,宝山广场,武定路,长清街,长安路,惠民街,武城广场,聊城大厦,海泊路,沧口街,宁波路,胶州广场,莱州路,招远街,冠县路,六码头,金乡广场,禹城街,临清路,东阿街,吴淞路,大港沿,辽宁路,棣纬二大厦,大港纬一路,贮水山支街,无棣纬一广场,大港纬三街,大港纬五路,大港纬四街,大港纬二路,无棣二大厦,吉林支路,大港四街,普集支路,无棣三街,黄台支广场,大港三街,无棣一路,贮水山大厦,泰山支路,大港一广场,无棣四路,大连支街,大港二路,锦州支街,德平广场,高苑大厦,长山路,乐陵街,临邑路,嫩江广场,合江路,大连街,博兴路,蒲台大厦,黄台广场,城阳街,临淄路,安邱街,临朐路,青城广场,商河路,热河大厦,济阳路,承德街,淄川广场,辽北街,阳信路,益都街,松江路,流亭大厦,吉林路,恒台街,包头路,无棣街,铁山广场,锦州街,桓台路,兴安大厦,邹平路,胶东广场,章丘路,丹东街,华阳路,青海街,泰山广场,周村大厦,四平路,台东西七街,台东东二路,台东东七广场,台东西二路,东五街,云门二路,芙蓉山村,延安二广场,云门一街,台东四路,台东一街,台东二路,杭州支广场,内蒙古路,台东七大厦,台东六路,广饶支街,台东八广场,台东三街,四平支路,郭口东街,青海支路,沈阳支大厦,菜市二路,菜市一街,北仲三路,瑞云街,滨县广场,庆祥街,万寿路,大成大厦,芙蓉路,历城广场,大名路,昌平街,平定路,长兴街,浦口广场,诸城大厦,和兴路,德盛街,宁海路,威海广场,东山路,清和街,姜沟路,雒口大厦,松山广场,长春街,昆明路,顺兴街,利津路,阳明广场,人和路,郭口大厦,营口路,昌邑街,孟庄广场,丰盛街,埕口路,丹阳街,汉口路,洮南大厦,桑梓路,沾化街,山口路,沈阳街,南口广场,振兴街,通化路,福寺大厦,峄县路,寿光广场,曹县路,昌乐街,道口路,南九水街,台湛广场,东光大厦,驼峰路,太平山,标山路,云溪广场,太清路".split(",");

    //邮箱域名
    private static final String[] emailSuffix = "@gmail.com,@yahoo.com,@msn.com,@hotmail.com,@aol.com,@ask.com,@live.com,@qq.com,@0355.net,@163.com,@163.net,@263.net,@3721.net,@yeah.net,@googlemail.com,@126.com,@sina.com,@sohu.com,@yahoo.com.cn".split(",");
    //课程资源
    private static String[] courses = "语文,数学,数学,外语,外语,生物,物理,化学,政治,历史,地理".split(",");
    private static String[] grades = "小学,初一,初二,初三,高一,高二,高三".split(",");
    //星期
    private static String[] week = "星期一,星期二,星期三,星期四,星期五,星期六,星期日,星期六,星期日,星期六,星期日".split(",");


    /**
     * 产生姓名
     */
    public static String[] getName() {
        int nameLength;
        int p = getNum(1, 500);
        if (p >= 1 && p <= 400) nameLength = 2;
        else if (p < 499) nameLength = 1;
        else nameLength = 3;
        String nameSet;
        String sex;
        if (getNum(0, 1) == 0) {
            nameSet = boyName;
            sex = "男";
        } else {
            nameSet = girlName;
            sex = "女";
        }
        String[] array = nameSet.split("");
        StringBuffer name = new StringBuffer();

        for (int i = 0; i < nameLength; ++i) {
            if (getNum(0, 100) != 50) {
                name.append(array[getNum(0, array.length - 1)]);
            } else name.append((char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1))) + "");
        }
        return new String[]{getFirstName() + name.toString(), sex};
    }

    /**
     * 产生手机号码
     *
     * @return
     */
    public static String getPhone() {
        StringBuffer phone = new StringBuffer(telFirst[getNum(0, telFirst.length - 1)]);
        int last = getNum(10000000, 99999999);
        phone.append(last);
        phone.setCharAt(3, (char) getNum(48, 57));
        return phone.toString();
    }


    /**
     * 获取2014年1月11日到2018年4月16之间的一个具体的时间
     *
     * @return
     */
    public static String getDateTime() {
        StringBuffer s = new StringBuffer();
        String date = getDate();
        s.append(date);
        int p = getNum(1, 13);
        if (p <= 7) {
            if (getNum(0, 1) == 0) {
                int h = getNum(9, 11);
                s.append(" " + (h == 9 ? "09" : h));
            } else s.append(" " + getNum(14, 17));
        } else if (p <= 12) {
            int tmp = getNum(1, 3);
            switch (tmp) {
                case 1:
                    s.append(" 0" + getNum(5, 8));
                    break;
                case 2:
                    s.append(" " + getNum(12, 13));
                    break;
                case 3:
                    s.append(" " + getNum(18, 22));
            }
        } else {
            if (getNum(1, 3) == 1) s.append(" " + 23);
            else s.append(" 0" + getNum(0, 4));
        }
        for (int i = 0; i < 2; i++) {
            int m = getNum(0, 59);
            s.append(":" + (m < 10 ? "0" : "") + m);
        }
        return s.toString();
    }

    /**
     * 产生一个生日,1940-2001
     */

    public static String getBirthday() {
        int year;
        int month;
        int day;
        int p = getNum(1, 100);//p表示概率
        if (p < 60) year = getNum(1994, 1999);
        else if (p < 70) year = getNum(2000, 2001);
        else if (p < 90) year = getNum(1988, 1993);
        else if (p < 100) year = getNum(1970, 1987);
        else year = getNum(1940, 1969);
        month = getNum(1, 12);
        day=getDay(year,month);
        StringBuffer date = new StringBuffer();
        date.append(year);
        date.append("-");
        date.append(month < 10 ? "0" + month : month);
        date.append("-");
        date.append(day < 10 ? "0" + day : day);
        if (year == 2018 && month > 4) return getDate();
        if (year == 2018 && month == 4 && day > 16) return getDate();
        if (year == 2014 && month == 1 && day < 11) return getDate();
        return date.toString();
    }

    /**
     * 根据年月获取月份中的某一天
     * @param year
     * @param month
     * @return
     */
    private static int getDay(int year,int month){
        int day;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = getNum(1, 31);
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day = getNum(1, 30);
                break;
            default:
                day = (year % 400 == 0 || year % 4 == 0 && year % 100 != 0) ? getNum(1, 29) : getNum(1, 28);
        }
        return day;
    }

    /**
     * 获取2014年1月11日到2018年4月16之间的一个日期
     */
    private static String getDate() {
        int year;
        int month;
        int day;
        int p = getNum(1, 100);//p表示概率
        if (p < 60) year = getNum(0, 1) == 0 ? 2017 : 2018;
        else if (p < 70) year = 2015;
        else if (p < 90) year = 2016;
        else year = 2014;
        month = getNum(1, 12);
        day=getDay(year,month);
        StringBuffer date = new StringBuffer();
        date.append(year);
        date.append("-");
        date.append(month < 10 ? "0" + month : month);
        date.append("-");
        date.append(day < 10 ? "0" + day : day);
        if (year == 2018 && month > 4) return getDate();
        if (year == 2018 && month == 4 && day > 16) return getDate();
        if (year == 2014 && month == 1 && day < 11) return getDate();
        return date.toString();
    }

    /**
     * 获取一个星期中的某个时间段
     * @return
     */
    public static String getDayTime() {
        String[] dayTime = "上午,下午,晚上".split(",");
        return week[getNum(0, week.length - 1)] + dayTime[getNum(0, dayTime.length - 1)];
    }

    /**
     * 获取20元到150元之间的一个价格
     * @return
     */
    public static String getPrice() {
        int r;
        int p = getNum(1, 9);
        if (p <= 4) r = getNum(5, 8) * 10;
        else if (p <= 8) {
            r = (getNum(0, 1) == 0 ? getNum(3, 4) : getNum(9, 10)) * 10;
        } else {
            r = getNum(1, 6) == 1 ? 20 : getNum(11, 15) * 10;
        }
        r = getNum(0, 1) == 0 ? r + 5 : r;
        return r + "元/小时";
    }

    /**
     * 获取一个姓氏
     * @return 姓氏
     */
    private static String getFirstName() {
        String firstName;
        if (getNum(1, 1000) == 520) firstName = "欧阳";
        else {
            int p = getNum(1, 100);//p用来控制概率
            if (p <= 30) firstName = firstName1;
            else if (p <= 55) firstName = firstName2;
            else if (p <= 80) firstName = firstName3;
            else if (p <= 95) firstName = firstName4;
            else firstName = firstName5;
        }
        String[] array = firstName.split("");
        return array[getNum(0, array.length - 1)];
    }

    /**
     * 获取一个完整邮箱
     */
    public static String getEmail() {
        StringBuffer email = new StringBuffer();
        if (getNum(0, 1) == 0) {
            email.append(getNum(10000000, 99999999));
            int p = getNum(0, 100);
            if (p < 100) email.append(getNum(0, 9));
            if (p < 50) email.append(getNum(0, 9));
            email.append("@qq.com");
        } else {
            email.append(genName());
            email.append(emailSuffix[(int) (Math.random() * emailSuffix.length)]);
        }
        return email.toString();
    }


    /**
     * 获取一个1-10之间的信用等级
     */
    public static int getCommnent() {
        int p = getNum(1, 10);
        int comment;
        if (p <= 5) comment = 10;
        else if (p <= 8) comment = getNum(8, 9);
        else if (p <= 9) comment = getNum(6, 7);
        else comment = getNum(1, 5);
        return comment;
    }

    /**
     * 受教育情况
     */
    public static String getEducation(int year) {
        if (year > 1994) {
            int p = getNum(1, 10);
            if (p < 10) return "本科";
            else return "专科";
        } else {
            int p = getNum(1, 100);
            if (p < 50) return "本科";
            else if (p < 70) return "专科";
            else if (p < 90) return "硕士";
            else return "博士";
        }
    }

    /**
     * 获取一个地址
     */
    public static String getAddress() {

        StringBuffer address = new StringBuffer("郑州市");
        int districtId = getNum(0, 5);
        address.append(district[districtId]);
        switch (districtId) {
            case 0:
                address.append(zhongYuan[getNum(0, zhongYuan.length - 1)]);
                break;
            case 1:
                address.append(erQi[getNum(0, erQi.length - 1)]);
                break;
            case 2:
                address.append(guanCheng[getNum(0, guanCheng.length - 1)]);
                break;
            case 3:
                address.append(jinShui[getNum(0, jinShui.length - 1)]);
                break;
            case 4:
                address.append(shangJie[getNum(0, shangJie.length - 1)]);
                break;
            case 5:
                address.append(huiJi[getNum(0, huiJi.length - 1)]);
        }
        address.append(road[getNum(0, road.length - 1)]);
        address.append(String.valueOf(getNum(11, 150)) + (getNum(0, 2) == 1 ? "单元" : "号"));
        address.append("-" + getNum(1, 20) + "-" + getNum(1, 10));
        return address.toString();
    }


    /**
     * 产生一个begin到end之间的随机数,包含begin和end
     */
    public static int getNum(int begin, int end) {
        return (int) (Math.random() * (end - begin + 1) + begin);
    }

    /**
     * 获取一个常用小写字母
     */
    private static char commonLetter() {
        int i = (int) (Math.random() * 5);
        switch (i) {
            case 0:
                i = 'a';
                break;
            case 1:
                i = 'e';
                break;
            case 2:
                i = 'i';
                break;
            case 3:
                i = 'o';
                break;
            case 4:
                i = 'u';
                break;
        }
        return (char) i;
    }

    /**
     * 获取一个小写字母
     * @return
     */
    private static char lowerCase() {
        int i = (int) (Math.random() * 26) + 97;
        return (char) i;
    }

    /**
     * 获取一门中小学课程名
     * @return
     */
    public static String getCourse() {
        String course;
        int grade = getNum(0, grades.length - 1);
        course = grades[grade];
        switch (grade) {
            case 0:
                course += courses[getNum(0, 4)];
                break;
            case 1:
                course += courses[getNum(0, 8)];
                break;
            case 2:
                course += courses[getNum(0, 9)];
                break;
            default:
                course += courses[getNum(0, courses.length - 1)];
        }
        return course;
    }


    /**
     * 获取邮箱@之前的一串
     * @return
     */
    private static String genName() {
        String name = "" + lowerCase();
        name += commonLetter();
        name += lowerCase();
        int nameLength = getNum(3, 8);
        for (int j = 3; j < nameLength; ++j) {
            if (j == 4 || j == 6) name += commonLetter();
            else name += lowerCase();
        }
        return name;
    }

}