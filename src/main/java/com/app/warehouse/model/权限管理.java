package com.app.warehouse.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 魏陈露
 * @since 2024-09-16
 */
@Getter
@Setter
  @TableName("软工2202_09_05_29权限管理")
public class 权限管理 implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId("人员代码")
      private String 人员代码;

    private Integer 人员档案管理;

    private Integer 物料档案管理;

    private Integer 进出仓管理;

    private Integer 管理权限;

    private Integer 统计打印;
}
