package com.sync.pojo;

import lombok.NonNull;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 地区映射
 * Created by yaoxiaohui on 2017/9/25.
 */
public class CountyMapping {

    // 如"1,018180021,CZ-QX"
    public static final List<String> infoList = new ArrayList<String>();
    // 如1,018180021,CZ-QX，信息如<"1","1,018180021,CZ-QX">
    public static final Map<String, String> indexInfoMap = new HashMap<String, String>();
    // 如1,018180021,CZ-QX，信息如<"018180021","1,018180021,CZ-QX">
    public static final Map<String, String> codeInfoMap = new HashMap<String, String>();

    static {

        infoList.add("1,018180021,CZ-QX");
        infoList.add("2,018180022,CZ-RQ");
        infoList.add("3,018180023,CZ-HJ");
        infoList.add("4,018180024,CZ-SN");
        infoList.add("5,018180025,CZ-XX");
        infoList.add("6,018180026,CZ-BT");
        infoList.add("7,018180027,CZ-DG");
        infoList.add("8,018180028,CZ-WQ");
        infoList.add("9,018180029,CZ-NP");
        infoList.add("10,018180030,CZ-MC");
        infoList.add("11,018180031,CZ-YS");
        infoList.add("12,018180032,CZ-HX");
        infoList.add("13,018180033,CZ-HH");
        infoList.add("14,018180034,CZ-CX");
        infoList.add("15,018180035,CZ-SXQ");
        infoList.add("16,018181001,TS-LB");
        infoList.add("17,018181002,TS-LUN");
        infoList.add("18,018181003,TS-KP");
        infoList.add("19,018181004,TS-GY");
        infoList.add("20,018181006,TS-FN");
        infoList.add("21,018181007,TS-ZH");
        infoList.add("22,018181009,TS-LT");
        infoList.add("23,018181010,TS-QX");
        infoList.add("24,018181011,TS-QA");
        infoList.add("25,018181012,TS-LN");
        infoList.add("26,018181013,TS-LX");
        infoList.add("27,018181014,TS-FR");
        infoList.add("28,018181015,TS-YT");
        infoList.add("29,018181017,TS-TSSQ");
        infoList.add("30,018181022,TS-CFDQ");
        infoList.add("31,018182002,QHD-CLX");
        infoList.add("32,018182004,QHD-BDHQ");
        infoList.add("33,018182005,QHD-SHGQ");
        infoList.add("34,018182006,QHD-FNX");
        infoList.add("35,018182007,QHD-LLX");
        infoList.add("36,018182008,QHD-QLX");
        infoList.add("37,018183001,LF-GYQ");
        infoList.add("38,018183002,LF-ACQ");
        infoList.add("39,018183004,LF-BZS");
        infoList.add("40,018183005,LF-SHS");
        infoList.add("41,018183006,LF-XHX");
        infoList.add("42,018183007,LF-YQX");
        infoList.add("43,018183008,LF-GAX");
        infoList.add("44,018183009,LF-WAX");
        infoList.add("45,018183010,LF-DCEX");
        infoList.add("46,018183011,LF-DCX");
        infoList.add("47,018184001,ZJK-CC");
        infoList.add("48,018184002,ZJK-CL");
        infoList.add("49,018184003,ZJK-GY");
        infoList.add("50,018184004,ZJK-HA");
        infoList.add("51,018184005,ZJK-HL");
        infoList.add("52,018184006,ZJK-XHY");
        infoList.add("53,018184007,ZJK-KB");
        infoList.add("54,018184008,ZJK-SY");
        infoList.add("55,018184009,ZJK-WQ");
        infoList.add("56,018184010,ZJK-WX");
        infoList.add("57,018184011,ZJK-XH");
        infoList.add("58,018184012,ZJK-YY");
        infoList.add("59,018184013,ZJK-ZB");
        infoList.add("60,018184014,ZJK-ZL");
        infoList.add("61,018184015,ZJK-QDQ");
        infoList.add("62,018184016,ZJK-QXQ");
        infoList.add("63,018185001,XT-QXQ");
        infoList.add("64,018185002,XT-XTX");
        infoList.add("65,018185003,XT-BXX");
        infoList.add("66,018185004,XT-LCX");
        infoList.add("67,018185005,XT-NGX");
        infoList.add("68,018185006,XT-LXX");
        infoList.add("69,018185007,XT-GZX");
        infoList.add("70,018185008,XT-NHX");
        infoList.add("71,018185009,XT-JLX");
        infoList.add("72,018185010,XT-LYX");
        infoList.add("73,018185011,XT-NQX");
        infoList.add("74,018185012,XT-PXX");
        infoList.add("75,018185013,XT-QHX");
        infoList.add("76,018185014,XT-RX");
        infoList.add("77,018185015,XT-SHX");
        infoList.add("78,018185016,XT-WX");
        infoList.add("79,018185017,XT-XHX");
        infoList.add("80,018185018,XT-NJX");
        infoList.add("81,018185019,XT-QDQ");
        infoList.add("82,018186004,HD-CA");
        infoList.add("83,018186005,HD-CX");
        infoList.add("84,018186006,HD-DM");
        infoList.add("85,018186007,HD-FX");
        infoList.add("86,018186009,HD-GT");
        infoList.add("87,018186010,HD-GP");
        infoList.add("88,018186011,HD-JZ");
        infoList.add("89,018186012,HD-LZ");
        infoList.add("90,018186013,HD-QX");
        infoList.add("91,018186014,HD-QZ");
        infoList.add("92,018186015,HD-SX");
        infoList.add("93,018186016,HD-WX");
        infoList.add("94,018186017,HD-WA");
        infoList.add("95,018186018,HD-YN");
        infoList.add("96,018187001,BD-AG");
        infoList.add("97,018187002,BD-AX");
        infoList.add("98,018187003,BD-BY");
        infoList.add("99,018187004,BD-DX");
        infoList.add("100,018187005,BD-DZ");
        infoList.add("101,018187006,BD-FP");
        infoList.add("102,018187007,BD-GBD");
        infoList.add("103,018187008,BD-GY");
        infoList.add("104,018187009,BD-LS");
        infoList.add("105,018187010,BD-LX");
        infoList.add("106,018187011,BD-LY");
        infoList.add("107,018187012,BD-MC");
        infoList.add("108,018187013,BD-QY");
        infoList.add("109,018187014,BD-QYU");
        infoList.add("110,018187015,BD-RC");
        infoList.add("111,018187016,BD-SP");
        infoList.add("112,018187017,BD-TX");
        infoList.add("113,018187018,BD-WD");
        infoList.add("114,018187019,BD-XS");
        infoList.add("115,018187020,BD-YX");
        infoList.add("116,018187021,BD-ZZ");
        infoList.add("117,018187022,BD-XX");
        infoList.add("118,018188002,SJZ-GC");
        infoList.add("119,018188003,SJZ-GY");
        infoList.add("120,018188004,SJZ-JXX");
        infoList.add("121,018188005,SJZ-JZ");
        infoList.add("122,018188006,SJZ-LC");
        infoList.add("123,018188007,SJZ-LQ");
        infoList.add("124,018188008,SJZ-LS");
        infoList.add("125,018188009,SJZ-PS");
        infoList.add("126,018188010,SJZ-SZ");
        infoList.add("127,018188011,SJZ-WJ");
        infoList.add("128,018188012,SJZ-XJ");
        infoList.add("129,018188013,SJZ-XL");
        infoList.add("130,018188014,SJZ-XT");
        infoList.add("131,018188015,SJZ-YS");
        infoList.add("132,018188016,SJZ-ZD");
        infoList.add("133,018188017,SJZ-ZH");
        infoList.add("134,018188018,SJZ-ZX");
        infoList.add("135,018188020,SJZ-CAQ");
        infoList.add("136,018188021,SJZ-YHQ");
        infoList.add("137,018188022,SJZ-QXQ");
        infoList.add("138,018188023,SJZ-XHQ");
        infoList.add("139,018189015,CD-CDX");
        infoList.add("140,018189016,CD-SLQ");
        infoList.add("141,018189017,CD-LPX");
        infoList.add("142,018189018,CD-FNX");
        infoList.add("143,018189020,CD-XLX");
        infoList.add("144,018189021,CD-PQX");
        infoList.add("145,018189022,CD-KCX");
        infoList.add("146,018189023,CD-LHX");
        infoList.add("147,018189024,CD-WCX");
        infoList.add("148,018720005,HS-AP");
        infoList.add("149,018720006,HS-FC");
        infoList.add("150,018720007,HS-GC");
        infoList.add("151,018720008,HS-JZ");
        infoList.add("152,018720009,HS-JX");
        infoList.add("153,018720010,HS-RY");
        infoList.add("154,018720011,HS-SZ");
        infoList.add("155,018720012,HS-WQ");
        infoList.add("156,018720013,HS-WY");
        infoList.add("157,018720014,HS-ZQ");

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
        if (StringUtils.isEmpty(info)) {
            return "";
        }
        String[] infoArr = info.split(",");
        return infoArr[1];
    }

    /**
     * 给定地区index，返回地区code
     * 如数据"157,18720014,HS-ZQ"，给定157，返回18720014
     *
     * @param addrIndex 地区索引
     * @return
     */
    public static String getAliasByGivenIndex(@NonNull String addrIndex){
        String info = indexInfoMap.get(addrIndex);
        if (StringUtils.isEmpty(info)) {
            return "";
        }
        String[] infoArr = info.split(",");
        return infoArr[2];
    }

    public static Integer getNumOfCounty(){
        return infoList.size();
    }

//    public static void main(String[] args) {
//        String a = getCodeByGivenIndex("157");
//        System.out.println(a);
//    }
}
