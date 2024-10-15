package com.app.warehouse.controller;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import javafx.application.Application;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
                String selectedItem = newValue.getValue();
                // 更新当前选择的内容 Label
                contentLabel.setText("当前显示: " + selectedItem);
                contentArea.getChildren().add(contentLabel); // 将内容标签添加到内容区域

                // 根据选中的项展示不同内容
                switch (selectedItem) {
                    case "添加用户":
                        showPage("添加用户");
                        break;
                    case "修改用户":
                        showPage("这是修改用户页面");
                        break;
                    case "人员信息":
                        showPage("这是人员信息页面");
                        break;
                    case "物品入库":
                        showPage("这是物品入库页面");
                        break;
                    case "物品出库":
                        showPage("这是物品出库页面");
                        break;
                    case "出库请求":
                        showPage("这是出库请求页面");
                        break;
                    case "入库请求":
                        showPage("这是入库请求页面");
                        break;
                    case "出入库记录":
                        showPage("这是出入库记录页面");
                        break;
                    case "物品信息":
                        showPage("这是物品信息页面");
                        break;
                    case "查询":
                        showPage("这是查询页面");
                        break;
                    case "进出仓流量":
                        showPage("这是进出仓流量页面");
                        break;
                    case "物料统计":
                        showPage("这是物料统计页面");
                        break;
                    case "进出仓打印":
                        showPage("这是进出仓打印页面");
                        break;
                    case "账本":
                        showPage("这是账本页面");
                        break;
                    default:
                        // 处理未知的选项，保持 contentArea 清空状态
                        break;
                }
            }
        });

        // 默认选择根节点
        navTree.getSelectionModel().select(navTree.getRoot());
    }
    private void showPage(String selectedItem) {
        // 根据选中的项展示不同内容
        switch (selectedItem) {
            case "添加用户":
                loadAddUserPage();
                break;
        }
    }
    private void loadAddUserPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddUser.fxml")); // 替换为您的 FXML 文件的路径
            VBox addUserPage = loader.load(); // 加载 FXML
            contentArea.getChildren().clear(); // 清空现有内容
            contentArea.getChildren().add(addUserPage); // 添加新页面
        } catch (IOException e) {
            e.printStackTrace(); // 处理异常
        }
    }
//
}
