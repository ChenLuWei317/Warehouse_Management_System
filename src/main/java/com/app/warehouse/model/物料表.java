package com.app.warehouse.model;

import com.baomidou.mybatisplus.annotation.IdType;
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
  @TableName("软工2202_09_05_29物料表")
public class 物料表 implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "物料代码", type = IdType.AUTO)
      private Integer 物料代码;

    private String 物料名称;

    private String 规格型号;

    private String 计量单位;

    private Integer 库存数量;

    private String 备注;
}
