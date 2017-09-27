package com.sync.pojo;

import lombok.Data;
/**
 * @Author ljw
 * @Description : 工单信息表bean
 * @Date Created in 13:47 2017/9/04.
 * @Modified By :
 */
@Data
public class WorkOrderBean {

       private String id;
       private String serialNum; 
       private String customerServiceId;
       private String callTime; 
       private String phoneNum; 
       private String audioPath; 
       private String acceptLocation; 
       private String phoneLocation;
       private String keyword; 
       private String emotion; 
       private String matchCategory;
       private String sourceAudioPath;
       private String textContent;//语音文本
       private String countyAlias;//地区表的别名 例如：SJZ-JZ
       private String isAnalyze;//是否已经分析
       private String cid;//地区id

       //workorder表中的字段
       public static String[] WORKORDERFeilds =
               {
                       "serialNum",
                       "customerServiceId",
                       "phoneNum",
                       "acceptLocation",
                       "phoneLocation",
                       "sourceAudioPath"
               };

}
