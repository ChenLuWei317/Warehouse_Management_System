package com.app.warehouse.controller;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import javafx.application.Application;


import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class AdminController {
    @FXML
    private TreeView<String> navTree;  // 左侧多级导航栏
    @FXML
    private VBox contentArea;  // 右侧内容显示区域
    @FXML
    private Label contentLabel;  // 显示当前选择的内容

    @FXML
    public void initialize() {
        // 处理导航栏点击事件
        navTree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            contentArea.getChildren().clear(); // 清空右侧内容区域

            if (newValue != null && newValue.getValue() != null) {
                contentLabel.setText("当前显示: " + newValue.getValue());
                contentArea.getChildren().add(contentLabel);
            }
        });

        // 默认选择根节点
        navTree.getSelectionModel().select(navTree.getRoot());
    }
}