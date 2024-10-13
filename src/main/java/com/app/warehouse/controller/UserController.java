package com.app.warehouse.controller;

import com.app.warehouse.WarehouseApplication;
import com.app.warehouse.dao.UserMapper;
import com.app.warehouse.model.User;
import de.felixroske.jfxsupport.FXMLController;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 *

 * 前端控制器
 *


 *
 * @author 魏陈露
 * @since 2024-09-24
 */


@FXMLController
public class UserController implements Initializable {



    private UserMapper userMapper;
    @FXML
    private VBox mainVBox1; // VBox 控件
    @FXML
    private TextField Username;

    @FXML
    private PasswordField Password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
// 创建淡入效果
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), mainVBox1);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(false);

// 创建从左侧平移效果
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(2000), mainVBox1);
        translateTransition.setFromX(-500); // 调整为更大值以确保平移效果明显
        translateTransition.setToX(0); // 移动到原始位置
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);

// 同时播放淡入和平移动画
        ParallelTransition parallelTransition = new ParallelTransition(fadeTransition, translateTransition);

// 设置初始透明度为0
        mainVBox1.setOpacity(0);

// 在界面加载完成后启动动画
        Platform.runLater(() -> parallelTransition.play());
        ApplicationContext context = WarehouseApplication.getContext();
        userMapper = context.getBean(UserMapper.class);
        System.out.println(context);

    }


    @FXML
    public void LoginButtonOnClick(){
        String UsernameValue = Username.getText().trim();
        String PasswordValue = Password.getText().trim();
        System.out.println("u+"+UsernameValue+"p+"+PasswordValue);

        User tmp = new User();
        tmp.set人员代码(UsernameValue);
        tmp.set密码(PasswordValue);
        try {
            User user1 =userMapper.login(tmp.get人员代码(), tmp.get密码());
            System.out.println(user1.toString());
            viewDialog(1);
        }catch(Exception e){
            e.printStackTrace();
            viewDialog(2);
        }
        System.out.println(tmp.toString());

    }
//    private WarehouseApplication application;
//
//    public void setApp(WarehouseApplication application){
//        this.application = application;
//    }
//
//    @FXML
//    private void OUT_M(ActionEvent event) {
//        application.useroutmain();
//    }
@FXML
private javafx.scene.control.Hyperlink registerLink;
    @FXML
    public void handleRegister(ActionEvent event) throws IOException {

            // 获取当前的舞台 (窗口)
            Stage stage = (Stage) registerLink.getScene().getWindow();

            // 加载注册页面的FXML文件
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/regist.fxml"));
        Rectangle2D screenRectangle = Screen.getPrimary().getBounds();
        double width = screenRectangle.getWidth();
        double height = screenRectangle.getHeight();
            // 创建一个新的场景，并设置为当前舞台的场景
            Scene scene = new Scene(root, (15.0/25)*width, (17.0/25)*height); // 保持当前窗口大小
            stage.setScene(scene);
        }


//
////        show_scene2();
//        try {
//// 获取当前的 Stage
////            Stage currentStage = (Stage) mainVBox.getScene().getWindow();
//
//// 创建新的 Stage
//            Stage registerStage = new Stage();
//
//// 加载 regist.fxml 文件
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/regist.fxml"));
//            Parent registerRoot = loader.load();
//
//// 获取屏幕当前分辨率
//            Rectangle2D screenRectangle = Screen.getPrimary().getBounds();
//            double width = screenRectangle.getWidth();
//            double height = screenRectangle.getHeight();
//
//// 计算登录界面的宽度和高度
//            double loginWidth = (15.0 / 25) * width;
//            double loginHeight = (17.0 / 25) * height;
//
//// 设置场景并显示窗口，大小与登录界面相同
//            Scene registerScene = new Scene(registerRoot, loginWidth, loginHeight);
//            registerStage.setScene(registerScene);
//            registerStage.setTitle("注册");
//            registerStage.show();
//
//// 关闭当前登录窗口
////            currentStage.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }



    public void viewDialog(Integer x){
        DialogPane dialog = new DialogPane();
        if(x==1){
            dialog.setContentText("登录成功~~");
        }else if(x==2){
            dialog.setContentText("登录失败~~");
        }
        dialog.getButtonTypes().add(ButtonType.OK);
        Button ok = (Button)dialog.lookupButton(ButtonType.OK);


        Stage dialogStage = new Stage();
        Scene dialogScene = new Scene(dialog);
        dialogStage.setScene(dialogScene);
        dialogStage.setTitle("提示");
        ok.setOnAction(event -> {
            dialogStage.close();
        });


        dialogStage.setAlwaysOnTop(true);
        dialogStage.setResizable(false);
        dialogStage.show();
    }



}