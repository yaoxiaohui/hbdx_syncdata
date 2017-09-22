package com.sync.pojo;

import lombok.Data;

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
}
