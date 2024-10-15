package com.app.warehouse.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddUser extends VBox {

    @FXML
    private TextField usernameField; // 与 FXML 文件中的 fx:id 匹配

    @FXML
    private void handleAddUser() {
        // 处理添加用户的逻辑
        String username = usernameField.getText();
        System.out.println("添加用户: " + username);
        // 在这里可以调用后台逻辑来处理用户的添加
    }
}