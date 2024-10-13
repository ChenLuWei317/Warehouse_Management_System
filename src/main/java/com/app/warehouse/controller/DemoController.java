package com.app.warehouse.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DemoController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Button closeButton;

    @FXML
    private void initialize() {
        // 初始化代码，如果需要的话
    }

    @FXML
    private void handleClose() {
        // 获取当前Stage并关闭它
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}