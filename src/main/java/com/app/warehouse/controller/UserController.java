package com.app.warehouse.controller;

import com.app.warehouse.WarehouseApplication;
import com.app.warehouse.dao.UserMapper;
import com.app.warehouse.model.User;
import com.app.warehouse.service.impl.UserDetailServiceImpl;
import de.felixroske.jfxsupport.FXMLController;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 魏陈露
 * @since 2024-09-24
 */


@FXMLController
public class UserController implements Initializable {

    private UserMapper userMapper;
    @FXML
    private VBox mainVBox1; // VBox 控件


    private UserDetailServiceImpl userDetailService;

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
        userDetailService = context.getBean(UserDetailServiceImpl.class);
        System.out.println(context);


        }
    private void AdminButtonOnClick() {
        try {
            // 加载demoApplication的FXML文件
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/test.fxml"));
            Parent root = loader.load();

            // 创建一个新的Stage
            Stage demoStage = new Stage();
            demoStage.setTitle("管理员专属功能");
            demoStage.setScene(new Scene(root));
            demoStage.initModality(Modality.APPLICATION_MODAL); // 如果需要，可以设置为模态窗口
            demoStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void LoginButtonOnClick() {
        String UsernameValue = Username.getText().trim();
        String PasswordValue = Password.getText().trim();

        // 创建后台任务
        Task<Void> loginTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                User tmp = new User();
                tmp.set人员代码(UsernameValue);
                tmp.set密码(PasswordValue);
                try {
                    User user1 = userMapper.login(tmp.get人员代码(), tmp.get密码());
                    if (user1 != null) {
                        // 登录成功
                        UserDetails user = userDetailService.loadUserByUsername(tmp.get人员代码());
                        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);

                        // 在UI线程上执行跳转
                        Platform.runLater(() -> {
                            try {
                                loadAdminController();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    } else {
                        Platform.runLater(() -> viewDialog(2));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Platform.runLater(() -> viewDialog(2)); // 登录失败提示
                }
                return null;
            }
        };

        // 启动任务
        new Thread(loginTask).start();
    }

    private void loadAdminController() throws IOException {
        // 加载 AdminController 的 FXML 文件
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin.fxml"));
        Parent adminRoot = loader.load();

        // 获取当前窗口并切换场景
        Stage stage = (Stage) Username.getScene().getWindow();

        Rectangle2D screenRectangle = Screen.getPrimary().getBounds();
        double width = screenRectangle.getWidth();
        double height = screenRectangle.getHeight();
        // 创建一个新的场景，并设置为当前舞台的场景
        Scene scene = new Scene(adminRoot, (15.0/25)*width, (17.0/25)*height); // 保持当前窗口大小
        stage.setScene(scene);
        stage.setTitle("仓库管理系统");
        stage.show();
    }

    private boolean hasAdminPermission() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        boolean flag = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("AUTHORITY"));
        // 检查用户是否具有ROLE_ADMIN权限
        return flag;

    }
    public void viewDialog(Integer x){
        DialogPane dialog = new DialogPane();
        if(x==1){
            dialog.setContentText("登录成功~~");
//            try {
//                // 创建一个新的 Stage
//                Stage registerStage = new Stage();
//
//                // 创建根布局
//                VBox registerRoot = new VBox();
//                registerRoot.setSpacing(20);
//                registerRoot.setPadding(new Insets(20));
//
//                Button adminButton = new Button("管理员专属功能");
//                adminButton.setOnAction(event -> {
//                    if (!hasAdminPermission()) {
//                        Alert alert = new Alert(Alert.AlertType.WARNING, "您没有权限访问这个功能！");
//                        alert.showAndWait();
//                    } else {
//                        // 管理员专属功能的逻辑
//                        AdminButtonOnClick();
//                    }
//                });
//
//                // 将控件添加到布局中
//                registerRoot.getChildren().addAll(adminButton);
//
//                // 设置场景并显示窗口
//                Scene registerScene = new Scene(registerRoot, 300, 200);
//                registerStage.setScene(registerScene);
//                registerStage.setTitle("注册");
//                registerStage.show();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
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
    @FXML
    private javafx.scene.control.Hyperlink registerLink;
    @FXML
    public void handleRegister(ActionEvent event) throws IOException {

        // 获取当前的舞台 (窗口)
        Stage stage = (Stage) registerLink.getScene().getWindow();

        // 加载注册页面的FXML文件
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
        Rectangle2D screenRectangle = Screen.getPrimary().getBounds();
        double width = screenRectangle.getWidth();
        double height = screenRectangle.getHeight();
        // 创建一个新的场景，并设置为当前舞台的场景
        Scene scene = new Scene(root, (15.0/25)*width, (17.0/25)*height); // 保持当前窗口大小
        stage.setScene(scene);
    }


}
