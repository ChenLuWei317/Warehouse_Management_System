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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class UserController implements Initializable {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @FXML
    private VBox mainVBox1;

    @FXML
    private TextField Username;

    @FXML
    private PasswordField Password;

    @FXML
    private Hyperlink registerLink;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Create fade-in and translate transitions
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), mainVBox1);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.setCycleCount(1);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(2000), mainVBox1);
        translateTransition.setFromX(-500);
        translateTransition.setToX(0);
        translateTransition.setCycleCount(1);

        ParallelTransition parallelTransition = new ParallelTransition(fadeTransition, translateTransition);
        mainVBox1.setOpacity(0);
        Platform.runLater(parallelTransition::play);

        ApplicationContext context = WarehouseApplication.getContext();
        userMapper = context.getBean(UserMapper.class);
        userDetailService = context.getBean(UserDetailServiceImpl.class);
    }

    @FXML
    public void LoginButtonOnClick() {
        String username = Username.getText().trim();
        String password = Password.getText().trim();

        Task<Void> loginTask = new Task<Void>() { // 指定 Void 类型
            @Override
            protected Void call() throws Exception {
                User user = new User();
                user.set人员代码(username);
                user.set密码(password);

                try {
                    User authenticatedUser = userMapper.login(user.get人员代码(), user.get密码());
                    if (authenticatedUser != null) {
                        UserDetails userDetails = userDetailService.loadUserByUsername(user.get人员代码());
                        Authentication authentication = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);

                        Platform.runLater(() -> {
                            try {
                                loadAdminController();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    } else {
                        Platform.runLater(() -> showDialog("Login Failed", "Invalid username or password."));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Platform.runLater(() -> showDialog("Login Error", "An error occurred during login."));
                }
                return null;
            }
        };

        new Thread(loginTask).start();
    }

    @FXML
    public void handleRegister(ActionEvent event) {
        try {
            Stage stage = (Stage) registerLink.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
            Rectangle2D screenRectangle = Screen.getPrimary().getBounds();
            double width = screenRectangle.getWidth();
            double height = screenRectangle.getHeight();
            Scene scene = new Scene(root, (15.0 / 25) * width, (17.0 / 25) * height);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            showDialog("Error", "Failed to load the registration screen.");
        }
    }

    private void loadAdminController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin.fxml"));
        Parent adminRoot = loader.load();
        Stage stage = (Stage) Username.getScene().getWindow();
        Rectangle2D screenRectangle = Screen.getPrimary().getBounds();
        double width = screenRectangle.getWidth();
        double height = screenRectangle.getHeight();
        Scene scene = new Scene(adminRoot, (15.0 / 25) * width, (17.0 / 25) * height);
        stage.setScene(scene);
        stage.setTitle("Warehouse Management System");
        stage.show();
    }

    private void showDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}