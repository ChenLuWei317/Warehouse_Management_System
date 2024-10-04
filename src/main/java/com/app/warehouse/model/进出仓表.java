package com.app.warehouse.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
  @TableName("软工2202_09_05_29进出仓表")
public class 进出仓表 implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId("单号")
      private String 单号;

    private String 类型;


      private Integer 物料代码;

    private String 操作人员代码;

    private LocalDateTime 日期;

    private Integer 数量;

    private String 备注;

    public 进出仓表(String 单号, String 类型, Integer 物料代码, String 操作人员代码, LocalDateTime 日期, Integer 数量, String 备注) {
        this.单号 = 单号;
        this.类型 = 类型;
        this.物料代码 = 物料代码;
        this.操作人员代码 = 操作人员代码;
        this.日期 = 日期;
        this.数量 = 数量;
        this.备注 = 备注;
    }

    public String get单号() {
        return 单号;
    }

    public void set单号(String 单号) {
        this.单号 = 单号;
    }

    public String get类型() {
        return 类型;
    }

    public void set类型(String 类型) {
        this.类型 = 类型;
    }

    public Integer get物料代码() {
        return 物料代码;
    }

    public void set物料代码(Integer 物料代码) {
        this.物料代码 = 物料代码;
    }

    public String get操作人员代码() {
        return 操作人员代码;
    }

    public void set操作人员代码(String 操作人员代码) {
        this.操作人员代码 = 操作人员代码;
    }

    public LocalDateTime get日期() {
        return 日期;
    }

    public void set日期(LocalDateTime 日期) {
        this.日期 = 日期;
    }

    public Integer get数量() {
        return 数量;
    }

    public void set数量(Integer 数量) {
        this.数量 = 数量;
    }

    public String get备注() {
        return 备注;
    }

    public void set备注(String 备注) {
        this.备注 = 备注;
    }
}
