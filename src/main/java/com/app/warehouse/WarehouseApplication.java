package com.app.warehouse;
import com.app.warehouse.service.impl.UserDetailServiceImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

@SpringBootApplication
@MapperScan("com.app.warehouse.dao")
public class WarehouseApplication extends Application {

    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private AuthenticationManager authenticationManager;
    public static void main(String[] args) {

        launch(WarehouseApplication.class, args);

    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        //获取屏幕当前分辨率
        Rectangle2D screenRectangle = Screen.getPrimary().getBounds();
        double width = screenRectangle.getWidth();
        double height = screenRectangle.getHeight();


        // 创建一个场景，并将栈面板设置为场景的内容
        Scene scene = new Scene(root, (9.0/25)*width, (9.0/25)*height);

        // 将场景设置为舞台的内容，并显示舞台
        primaryStage.setX((width-(9.0/25)*width)/2);
        primaryStage.setY((height-(16.0/25)*height)/2);
        primaryStage.setResizable(false);
        primaryStage.setTitle("鬼知道的什么管理系统");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static ApplicationContext getContext() {
        return SpringApplication.run(WarehouseApplication.class);
    }



}