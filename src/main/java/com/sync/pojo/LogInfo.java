package com.sync.pojo;

import lombok.Data;

/**
 * @Author ljw
 * @Description : 同步日志bean
 * @Date Created in 13:47 2017/9/04.
 * @Modified By :
 */
@Data
public class LogInfo {
    private int id;
    private String content;
    private String create_time;
}
