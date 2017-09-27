package com.sync.pojo;

import lombok.Data;

/**
 * @Author ljw
 * @Description : 业务分类bean
 * @Date Created in 13:47 2017/9/04.
 * @Modified By :
 */
@Data
public class CategoryBean {
      private String  id;
      private String  name;
      private String  pId;
      private String  level;
      private String  roleWord;
      private String  defaultRoleWord;
      private String  sort;
      private String  alias;
}
