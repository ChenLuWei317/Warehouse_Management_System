package com.app.warehouse.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
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
  @TableName("软工2202_09_05_29人员表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId("人员代码")
      private String 人员代码;

    private String 密码;

    private String 姓名;

    private String 性别;

    private LocalDate 出生日期;

    private String 身份证号;

    private String 籍贯;

    private String 家庭地址;

    private String 联系电话;

    private String 备注;

    public User() {
    }

    public User(String 人员代码, String 密码, String 姓名, String 性别, LocalDate 出生日期, String 身份证号, String 籍贯, String 家庭地址, String 联系电话, String 备注) {
        this.人员代码 = 人员代码;
        this.密码 = 密码;
        this.姓名 = 姓名;
        this.性别 = 性别;
        this.出生日期 = 出生日期;
        this.身份证号 = 身份证号;
        this.籍贯 = 籍贯;
        this.家庭地址 = 家庭地址;
        this.联系电话 = 联系电话;
        this.备注 = 备注;
    }

    public String get人员代码() {
        return 人员代码;
    }


    public void set人员代码(String 人员代码) {
        this.人员代码 = 人员代码;
    }

    public String get密码() {
        return 密码;
    }

    public void set密码(String 密码) {
        this.密码 = 密码;
    }

    public String get姓名() {
        return 姓名;
    }

    public void set姓名(String 姓名) {
        this.姓名 = 姓名;
    }

    public String get性别() {
        return 性别;
    }

    public void set性别(String 性别) {
        this.性别 = 性别;
    }

    public LocalDate get出生日期() {
        return 出生日期;
    }

    public void set出生日期(LocalDate 出生日期) {
        this.出生日期 = 出生日期;
    }

    public String get身份证号() {
        return 身份证号;
    }

    public void set身份证号(String 身份证号) {
        this.身份证号 = 身份证号;
    }

    public String get籍贯() {
        return 籍贯;
    }

    public void set籍贯(String 籍贯) {
        this.籍贯 = 籍贯;
    }

    public String get家庭地址() {
        return 家庭地址;
    }

    public void set家庭地址(String 家庭地址) {
        this.家庭地址 = 家庭地址;
    }

    public String get联系电话() {
        return 联系电话;
    }

    public void set联系电话(String 联系电话) {
        this.联系电话 = 联系电话;
    }

    public String get备注() {
        return 备注;
    }

    public void set备注(String 备注) {
        this.备注 = 备注;
    }

    @Override
    public String toString() {
        return "User{" +
                "人员代码='" + 人员代码 + '\'' +
                ", 密码='" + 密码 + '\'' +
                ", 姓名='" + 姓名 + '\'' +
                ", 性别='" + 性别 + '\'' +
                ", 出生日期=" + 出生日期 +
                ", 身份证号='" + 身份证号 + '\'' +
                ", 籍贯='" + 籍贯 + '\'' +
                ", 家庭地址='" + 家庭地址 + '\'' +
                ", 联系电话='" + 联系电话 + '\'' +
                ", 备注='" + 备注 + '\'' +
                '}';
    }
}
