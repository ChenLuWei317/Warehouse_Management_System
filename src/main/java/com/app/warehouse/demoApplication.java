package com.app.warehouse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;

public class demoApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 主窗口布局
        BorderPane root = new BorderPane();

        // 创建主窗口中的按钮，用于触发弹窗
        Button showPopupButton = new Button("打开弹窗");
        showPopupButton.setOnAction(e -> showPopup());  // 点击按钮时调用弹窗方法
        root.setCenter(showPopupButton);  // 将按钮放在主窗口的中心

        // 创建并设置主窗口场景
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("管理员");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // 弹窗方法
    public void showPopup() {
        // 创建一个新的窗口 (Stage)
        Stage popupStage = new Stage();
        popupStage.setTitle("弹窗");

        // 设置为模态窗口，阻止用户与主窗口交互，直到弹窗关闭
        popupStage.initModality(Modality.APPLICATION_MODAL);

        // 弹窗内容布局 (VBox)
        VBox popupLayout = new VBox(10);
        popupLayout.setAlignment(Pos.CENTER);

        // 创建弹窗中的标签和按钮
        Label label = new Label("这是一个弹窗！");
        Button closeButton = new Button("关闭");
        closeButton.setOnAction(e -> popupStage.close());  // 点击按钮时关闭弹窗

        // 将标签和按钮添加到布局中
        popupLayout.getChildren().addAll(label, closeButton);


    }
}