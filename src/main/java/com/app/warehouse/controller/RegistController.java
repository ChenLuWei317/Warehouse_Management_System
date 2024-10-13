package com.app.warehouse.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class RegistController {
    @FXML
    private VBox mainVbox; // VBox 控件
    @FXML
    private TextField Name; // 姓名输入

    @FXML
    private ComboBox<String> Gender; // 性别选择

    @FXML
    private DatePicker BirthDate; // 出生日期选择

    @FXML
    private TextField Hometown; // 籍贯输入

    @FXML
    private TextField Address; // 家庭地址输入

    @FXML
    private TextField PhoneNumber; // 联系电话输入

    @FXML
    private TextField Username; // 身份证号

    @FXML
    private PasswordField Password; // 密码


    @FXML
    public void RegistButtonOnClick() {
        // 获取用户输入的信息
        String name = Name.getText();
        String gender = Gender.getValue();
        LocalDate birthDate = BirthDate.getValue();
        String hometown = Hometown.getText();
        String address = Address.getText();
        String phoneNumber = PhoneNumber.getText();
        String username = Username.getText();
        String password = Password.getText();

        // 输入校验（例如是否为空）
        if (name.isEmpty() || gender == null || birthDate == null || hometown.isEmpty() ||
                address.isEmpty() || phoneNumber.isEmpty() || username.isEmpty() || password.isEmpty()) {
            System.out.println("请填写所有字段！");
            return; // 可以提示用户错误信息，并阻止窗口关闭
        }

        // 处理数据（可以存储到数据库或传递到其他组件）
        System.out.println("姓名: " + name);
        System.out.println("性别: " + gender);
        System.out.println("出生日期: " + birthDate);
        System.out.println("籍贯: " + hometown);
        System.out.println("家庭地址: " + address);
        System.out.println("联系电话: " + phoneNumber);
        System.out.println("身份证号: " + username);
        System.out.println("密码: " + password);

    }
    }
