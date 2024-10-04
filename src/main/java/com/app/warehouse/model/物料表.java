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
 * @since 2024-09-24
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

    public 物料表(Integer 物料代码, String 物料名称, String 规格型号, String 计量单位, Integer 库存数量, String 备注) {
        this.物料代码 = 物料代码;
        this.物料名称 = 物料名称;
        this.规格型号 = 规格型号;
        this.计量单位 = 计量单位;
        this.库存数量 = 库存数量;
        this.备注 = 备注;
    }

    public Integer get物料代码() {
        return 物料代码;
    }

    public void set物料代码(Integer 物料代码) {
        this.物料代码 = 物料代码;
    }

    public String get物料名称() {
        return 物料名称;
    }

    public void set物料名称(String 物料名称) {
        this.物料名称 = 物料名称;
    }

    public String get规格型号() {
        return 规格型号;
    }

    public void set规格型号(String 规格型号) {
        this.规格型号 = 规格型号;
    }

    public String get计量单位() {
        return 计量单位;
    }

    public void set计量单位(String 计量单位) {
        this.计量单位 = 计量单位;
    }

    public Integer get库存数量() {
        return 库存数量;
    }

    public void set库存数量(Integer 库存数量) {
        this.库存数量 = 库存数量;
    }

    public String get备注() {
        return 备注;
    }

    public void set备注(String 备注) {
        this.备注 = 备注;
    }
}
