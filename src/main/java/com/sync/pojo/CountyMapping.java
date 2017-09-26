package com.sync.pojo;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yaoxiaohui on 2017/9/25.
 */
public class CountyMapping {

    // 如"1,18180021,CZ-QX"
    private static final List<String> infoList = new ArrayList<String>();
    // 如1,18180021,CZ-QX，信息如<"1","1,18180021,CZ-QX">
    private static final Map<String, String> indexInfoMap = new HashMap<String, String>();
    // 如1,18180021,CZ-QX，信息如<"18180021","1,18180021,CZ-QX">
    private static final Map<String, String> codeInfoMap = new HashMap<String, String>();

    static {

        infoList.add("1,18180021,CZ-QX");
        infoList.add("2,18180022,CZ-RQ");
        infoList.add("3,18180023,CZ-HJ");
        infoList.add("4,18180024,CZ-SN");
        infoList.add("5,18180025,CZ-XX");
        infoList.add("6,18180026,CZ-BT");
        infoList.add("7,18180027,CZ-DG");
        infoList.add("8,18180028,CZ-WQ");
        infoList.add("9,18180029,CZ-NP");
        infoList.add("10,18180030,CZ-MC");
        infoList.add("11,18180031,CZ-YS");
        infoList.add("12,18180032,CZ-HX");
        infoList.add("13,18180033,CZ-HH");
        infoList.add("14,18180034,CZ-CX");
        infoList.add("15,18180035,CZ-SXQ");
        infoList.add("16,18181001,TS-LB");
        infoList.add("17,18181002,TS-LUN");
        infoList.add("18,18181003,TS-KP");
        infoList.add("19,18181004,TS-GY");
        infoList.add("20,18181006,TS-FN");
        infoList.add("21,18181007,TS-ZH");
        infoList.add("22,18181009,TS-LT");
        infoList.add("23,18181010,TS-QX");
        infoList.add("24,18181011,TS-QA");
        infoList.add("25,18181012,TS-LN");
        infoList.add("26,18181013,TS-LX");
        infoList.add("27,18181014,TS-FR");
        infoList.add("28,18181015,TS-YT");
        infoList.add("29,18181017,TS-TSSQ");
        infoList.add("30,18181022,TS-CFDQ");
        infoList.add("31,18182002,QHD-CLX");
        infoList.add("32,18182004,QHD-BDHQ");
        infoList.add("33,18182005,QHD-SHGQ");
        infoList.add("34,18182006,QHD-FNX");
        infoList.add("35,18182007,QHD-LLX");
        infoList.add("36,18182008,QHD-QLX");
        infoList.add("37,18183001,LF-GYQ");
        infoList.add("38,18183002,LF-ACQ");
        infoList.add("39,18183004,LF-BZS");
        infoList.add("40,18183005,LF-SHS");
        infoList.add("41,18183006,LF-XHX");
        infoList.add("42,18183007,LF-YQX");
        infoList.add("43,18183008,LF-GAX");
        infoList.add("44,18183009,LF-WAX");
        infoList.add("45,18183010,LF-DCEX");
        infoList.add("46,18183011,LF-DCX");
        infoList.add("47,18184001,ZJK-CC");
        infoList.add("48,18184002,ZJK-CL");
        infoList.add("49,18184003,ZJK-GY");
        infoList.add("50,18184004,ZJK-HA");
        infoList.add("51,18184005,ZJK-HL");
        infoList.add("52,18184006,ZJK-XHY");
        infoList.add("53,18184007,ZJK-KB");
        infoList.add("54,18184008,ZJK-SY");
        infoList.add("55,18184009,ZJK-WQ");
        infoList.add("56,18184010,ZJK-WX");
        infoList.add("57,18184011,ZJK-XH");
        infoList.add("58,18184012,ZJK-YY");
        infoList.add("59,18184013,ZJK-ZB");
        infoList.add("60,18184014,ZJK-ZL");
        infoList.add("61,18184015,ZJK-QDQ");
        infoList.add("62,18184016,ZJK-QXQ");
        infoList.add("63,18185001,XT-QXQ");
        infoList.add("64,18185002,XT-XTX");
        infoList.add("65,18185003,XT-BXX");
        infoList.add("66,18185004,XT-LCX");
        infoList.add("67,18185005,XT-NGX");
        infoList.add("68,18185006,XT-LXX");
        infoList.add("69,18185007,XT-GZX");
        infoList.add("70,18185008,XT-NHX");
        infoList.add("71,18185009,XT-JLX");
        infoList.add("72,18185010,XT-LYX");
        infoList.add("73,18185011,XT-NQX");
        infoList.add("74,18185012,XT-PXX");
        infoList.add("75,18185013,XT-QHX");
        infoList.add("76,18185014,XT-RX");
        infoList.add("77,18185015,XT-SHX");
        infoList.add("78,18185016,XT-WX");
        infoList.add("79,18185017,XT-XHX");
        infoList.add("80,18185018,XT-NJX");
        infoList.add("81,18185019,XT-QDQ");
        infoList.add("82,18186004,HD-CA");
        infoList.add("83,18186005,HD-CX");
        infoList.add("84,18186006,HD-DM");
        infoList.add("85,18186007,HD-FX");
        infoList.add("86,18186009,HD-GT");
        infoList.add("87,18186010,HD-GP");
        infoList.add("88,18186011,HD-JZ");
        infoList.add("89,18186012,HD-LZ");
        infoList.add("90,18186013,HD-QX");
        infoList.add("91,18186014,HD-QZ");
        infoList.add("92,18186015,HD-SX");
        infoList.add("93,18186016,HD-WX");
        infoList.add("94,18186017,HD-WA");
        infoList.add("95,18186018,HD-YN");
        infoList.add("96,18187001,BD-AG");
        infoList.add("97,18187002,BD-AX");
        infoList.add("98,18187003,BD-BY");
        infoList.add("99,18187004,BD-DX");
        infoList.add("100,18187005,BD-DZ");
        infoList.add("101,18187006,BD-FP");
        infoList.add("102,18187007,BD-GBD");
        infoList.add("103,18187008,BD-GY");
        infoList.add("104,18187009,BD-LS");
        infoList.add("105,18187010,BD-LX");
        infoList.add("106,18187011,BD-LY");
        infoList.add("107,18187012,BD-MC");
        infoList.add("108,18187013,BD-QY");
        infoList.add("109,18187014,BD-QYU");
        infoList.add("110,18187015,BD-RC");
        infoList.add("111,18187016,BD-SP");
        infoList.add("112,18187017,BD-TX");
        infoList.add("113,18187018,BD-WD");
        infoList.add("114,18187019,BD-XS");
        infoList.add("115,18187020,BD-YX");
        infoList.add("116,18187021,BD-ZZ");
        infoList.add("117,18187022,BD-XX");
        infoList.add("118,18188002,SJZ-GC");
        infoList.add("119,18188003,SJZ-GY");
        infoList.add("120,18188004,SJZ-JXX");
        infoList.add("121,18188005,SJZ-JZ");
        infoList.add("122,18188006,SJZ-LC");
        infoList.add("123,18188007,SJZ-LQ");
        infoList.add("124,18188008,SJZ-LS");
        infoList.add("125,18188009,SJZ-PS");
        infoList.add("126,18188010,SJZ-SZ");
        infoList.add("127,18188011,SJZ-WJ");
        infoList.add("128,18188012,SJZ-XJ");
        infoList.add("129,18188013,SJZ-XL");
        infoList.add("130,18188014,SJZ-XT");
        infoList.add("131,18188015,SJZ-YS");
        infoList.add("132,18188016,SJZ-ZD");
        infoList.add("133,18188017,SJZ-ZH");
        infoList.add("134,18188018,SJZ-ZX");
        infoList.add("135,18188020,SJZ-CAQ");
        infoList.add("136,18188021,SJZ-YHQ");
        infoList.add("137,18188022,SJZ-QXQ");
        infoList.add("138,18188023,SJZ-XHQ");
        infoList.add("139,18189015,CD-CDX");
        infoList.add("140,18189016,CD-SLQ");
        infoList.add("141,18189017,CD-LPX");
        infoList.add("142,18189018,CD-FNX");
        infoList.add("143,18189020,CD-XLX");
        infoList.add("144,18189021,CD-PQX");
        infoList.add("145,18189022,CD-KCX");
        infoList.add("146,18189023,CD-LHX");
        infoList.add("147,18189024,CD-WCX");
        infoList.add("148,18720005,HS-AP");
        infoList.add("149,18720006,HS-FC");
        infoList.add("150,18720007,HS-GC");
        infoList.add("151,18720008,HS-JZ");
        infoList.add("152,18720009,HS-JX");
        infoList.add("153,18720010,HS-RY");
        infoList.add("154,18720011,HS-SZ");
        infoList.add("155,18720012,HS-WQ");
        infoList.add("156,18720013,HS-WY");
        infoList.add("157,18720014,HS-ZQ");

        String[] infoArr;
        for (String loopinfo : infoList) {
            infoArr = loopinfo.split(",");
            indexInfoMap.put(infoArr[0], loopinfo);
            codeInfoMap.put(infoArr[1], loopinfo);
        }
    }

    private CountyMapping() {
    }

    /**
     * 给定地区code，返回地区索引
     * 如数据"157,18720014,HS-ZQ"，给定18720014，返回157
     *
     * @param addrCode 地区编码
     * @return
     */
    public static Integer getIndexByGivenCode(@NonNull String addrCode){
        String info = codeInfoMap.get(addrCode);
        String[] infoArr = info.split(",");
        return Integer.valueOf(infoArr[0]);
    }

    /**
     * 给定地区index，返回地区code
     * 如数据"157,18720014,HS-ZQ"，给定157，返回18720014
     *
     * @param addrIndex 地区索引
     * @return
     */
    public static String getCodeByGivenIndex(@NonNull String addrIndex){
        String info = indexInfoMap.get(addrIndex);
        String[] infoArr = info.split(",");
        return infoArr[1];
    }

    public static Integer getNumOfCounty(){
        return infoList.size();
    }

//    public static void main(String[] args) {
//        String a = getCodeByGivenIndex("157");
//        System.out.println(a);
//    }
}
