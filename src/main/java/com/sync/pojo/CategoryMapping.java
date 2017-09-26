package com.sync.pojo;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yaoxiaohui on 2017/9/25.
 */
public class CategoryMapping {

    // 如"1,融合新装,1,2,XZ-RHXZ"
    private static final List<String> infoList = new ArrayList<String>();
    // 如1,融合新装,1,2,XZ-RHXZ，信息如<"1","1,融合新装,1,2,XZ-RHXZ">
    private static final Map<String, String> indexInfoMap = new HashMap<String, String>();
    // 如1,融合新装,1,2,XZ-RHXZ，信息如<"融合新装","1,融合新装,1,2,XZ-RHXZ">
    private static final Map<String, String> nameInfoMap = new HashMap<String, String>();

    static{
        infoList.add("1,融合新装,1,2,XZ-RHXZ");
        infoList.add("2,单宽新装,1,2,XZ-DKXZ");
        infoList.add("3,单C新装,1,2,XZ-DCXZ");
        infoList.add("4,单TV新装,1,2,XZ-DTVXZ");
        infoList.add("5,单C离网,2,2,CFLH-DCLW");
        infoList.add("6,融合手机离网,2,2,CFLH-RHSJLW");
        infoList.add("7,融合宽带离网,2,2,CFLH-RHKDLW");
        infoList.add("8,融合离网,2,2,CFLH-RHLW");
        infoList.add("9,单宽离网,2,2,CFLH-DKLW");
        infoList.add("10,融合拆分,2,2,CFLH-RHCF");
        infoList.add("11,中高危客户挽留,2,2,CFLH-ZGWHKWL");
        infoList.add("12,附属产品加包,3,2,JZ-FSCPJB");
        infoList.add("13,智能租网,3,2,JZ-ZNZW");
        infoList.add("14,租机续约,4,2,XFXY-ZJXY");
        infoList.add("15,缴赠,4,2,XFXY-JF");
        infoList.add("16,手机换新,5,2,HJHKHGX-SJHX");
        infoList.add("17,普转光,5,2,HJHKHGX-PZG");
        infoList.add("18,宽带提速,6,2,TCQY-KDJS");
        infoList.add("19,不限流量套餐迁移,6,2,TCQY-BXLLTCQY");
        infoList.add("20,流量花房,7,2,XSHD-LLHF");
        infoList.add("21,成就系统,7,2,XSHD-CJXT");
        infoList.add("22,问题挖掘,0,1,WTWJ");
        infoList.add("23,宽带故障,30,2,WTWJ-KDGZ");
        infoList.add("24,宽带网速慢,30,2,WTWJ-KDWSM");
        infoList.add("25,移动网络信号差,30,2,WTWJ-YDXHC");
        infoList.add("26,家庭WIFI信号差,30,2,WTWJ-JTWIFIXHC");
        infoList.add("27,越级倾向,30,2,WTWJ-YJQX");

        String[] infoArr;
        for (String loopinfo : infoList) {
            infoArr = loopinfo.split(",");
            indexInfoMap.put(infoArr[0], loopinfo);
            nameInfoMap.put(infoArr[1], loopinfo);
        }
    }

    private CategoryMapping() {
    }

    /**
     * 给定业务name，返回业务索引
     * 如数据"1,融合新装,1,2,XZ-RHXZ"，给定融合新装，返回1
     *
     * @param name 业务名称
     * @return
     */
    public static Integer getIndexByGivenName(@NonNull String name){
        String info = nameInfoMap.get(name);
        String[] infoArr = info.split(",");
        return Integer.valueOf(infoArr[0]);
    }

    /**
     * 给定业务index，返回业务name
     * 如数据"1,融合新装,1,2,XZ-RHXZ"，给定1，返回融合新装
     *
     * @param index 索引
     * @return
     */
    public static String getNameByGivenIndex(@NonNull String index){
        String info = indexInfoMap.get(index);
        String[] infoArr = info.split(",");
        return infoArr[1];
    }

    /**
     * 给定业务index，返回业务alias
     * 如数据"1,融合新装,1,2,XZ-RHXZ"，给定1，XZ-RHXZ
     * @param index 索引
     * @return
     */
    public static String getAliasByGivenIndex(@NonNull String index){
        String info = indexInfoMap.get(index);
        String[] infoArr = info.split(",");
        return infoArr[4];
    }

    /**
     * 给定业务name，返回业务alias
     * 如数据"1,融合新装,1,2,XZ-RHXZ"，给定1，返回XZ-RHXZ
     *
     * @param name 业务名称
     * @return
     */
    public static String getAliasByGivenName(@NonNull String name){
        String info = nameInfoMap.get(name);
        String[] infoArr = info.split(",");
        return infoArr[4];
    }

    public static Integer getNumOfCategory(){
        return infoList.size();
    }

//
//    public static void main(String[] args) {
//                String a = getIndexByGivenName("越级倾向");
//        System.out.println(a);
//    }
}
