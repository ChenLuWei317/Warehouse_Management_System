package com.app.warehouse.controller;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import javafx.application.Application;


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

public class AdminController extends Application {

    private VBox contentArea;  // 右侧内容显示区域
    private ListView<String> navList;  // 左侧主导航栏

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("仓库管理系统");

        // 创建主布局
        BorderPane root = new BorderPane();

        // 初始化左侧导航栏
        navList = new ListView<>();
        navList.getItems().addAll("出入库管理", "人员管理", "仓库管理", "报表打印");
        root.setLeft(navList);

        // 创建右侧内容区域
        contentArea = new VBox();
        root.setCenter(contentArea);

        // 处理导航栏点击事件
        navList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) return;  // 防止空选择项
            contentArea.getChildren().clear(); // 清空右侧内容区域

            switch (newValue) {
                case "人员管理":
                    showUserManagement();
                    break;
                case "仓库管理":
                    showWarehouseManagement();
                    break;
                case "出入库管理":
                    showInOutManagement();
                    break;
                case "报表打印":
                    showReportPrinting();
                    break;
                case "    添加用户":
                    showAddUser();
                    break;
                case "    修改密码":
                    showChangePassword();
                    break;
                case "    人员信息":
                    showUserInfo();
                    break;
                case "    物品信息":
                    showItemInfo();
                    break;
                case "    仓库信息":
                    showWarehouseInfo();
                    break;
                case "    物品入库":
                    showItemIn();
                    break;
                case "    物品出库":
                    showItemOut();
                    break;
                case "    入库请求":
                    showInRequest();
                    break;
                case "    出库请求":
                    showOutRequest();
                    break;
                case "    报表打印":
                    showPrintReport();
                    break;
                case "    进出仓记录":
                    showInOutRecords();
                    break;
            }
        });

        // 默认显示人员管理
        navList.getSelectionModel().selectFirst();

        // 创建场景并显示
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    // 显示“人员管理”子功能
    private void showUserManagement() {
        navList.getItems().setAll("出入库管理", "人员管理", "    人员信息", "    添加用户", "    修改密码", "仓库管理", "报表打印");
        if (navList.getItems().contains("人员管理")) {
            navList.getSelectionModel().select("人员管理"); // 手动选择“人员管理”
        }
    }

    private void showWarehouseManagement() {
        navList.getItems().setAll("出入库管理", "人员管理", "仓库管理", "    物品信息", "    仓库信息", "报表打印");
        if (navList.getItems().contains("仓库管理")) {
            navList.getSelectionModel().select("仓库管理"); // 手动选择“仓库管理”
        }
    }

    private void showInOutManagement() {
        navList.getItems().setAll("出入库管理", "    物品入库", "    物品出库", "    入库请求", "    出库请求", "人员管理", "仓库管理", "报表打印");
        if (navList.getItems().contains("出入库管理")) {
            navList.getSelectionModel().select("出入库管理"); // 手动选择“出入库管理”
        }
    }

    private void showReportPrinting() {
        navList.getItems().setAll("出入库管理", "人员管理", "仓库管理", "报表打印", "    报表打印", "    进出仓记录");
        if (navList.getItems().contains("报表打印")) {
            navList.getSelectionModel().select("报表打印"); // 手动选择“报表打印”
        }
    }


    // 显示具体的功能界面
    private void showAddUser() {
        contentArea.getChildren().clear(); // 清空内容区域

        // 创建输入框和标签
        Label usernameLabel = new Label("账户:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("密码:");
        PasswordField passwordField = new PasswordField(); // 使用PasswordField来隐藏输入的密码

        Button submitButton = new Button("提交");

        // 设置按钮点击事件
        submitButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            // 处理提交逻辑（例如验证输入，保存用户等）
            System.out.println("账户: " + username + ", 密码: " + password);
            // 这里可以添加更多处理逻辑，比如清空输入框、显示成功消息等
        });

        // 创建布局
        VBox layout = new VBox(10); // 使用VBox布局，间隔为10
        layout.setPadding(new Insets(20)); // 设置内边距
        layout.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, submitButton);

        // 将布局添加到内容区域
        contentArea.getChildren().add(layout);
    }


    private void showChangePassword() {
        contentArea.getChildren().clear();
        contentArea.getChildren().add(new Button("这是修改密码界面"));
    }

    private void showUserInfo() {
        contentArea.getChildren().clear();
        contentArea.getChildren().add(new Button("这是人员信息界面"));
    }

    private void showItemInfo() {
        contentArea.getChildren().clear();
        contentArea.getChildren().add(new Button("这是物品信息界面"));
    }

    private void showWarehouseInfo() {
        contentArea.getChildren().clear();
        contentArea.getChildren().add(new Button("这是仓库信息界面"));
    }

    private void showItemIn() {
        contentArea.getChildren().clear();
        contentArea.getChildren().add(new Button("这是物品入库界面"));
    }

    private void showItemOut() {
        contentArea.getChildren().clear();
        contentArea.getChildren().add(new Button("这是物品出库界面"));
    }

    private void showInRequest() {
        contentArea.getChildren().clear();
        contentArea.getChildren().add(new Button("这是入库请求界面"));
    }

    private void showOutRequest() {
        contentArea.getChildren().clear();
        contentArea.getChildren().add(new Button("这是出库请求界面"));
    }

    private void showPrintReport() {
        contentArea.getChildren().clear();
        contentArea.getChildren().add(new Button("这是报表打印界面"));
    }

    private void showInOutRecords() {
        contentArea.getChildren().clear();
        contentArea.getChildren().add(new Button("这是进出仓记录界面"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
