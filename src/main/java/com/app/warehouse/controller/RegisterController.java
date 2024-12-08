package com.app.warehouse.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegisterController {
    @FXML
    private VBox mainVbox;
    @FXML
    private TextField Name;
    @FXML
    private ComboBox<String> Gender;
    @FXML
    private DatePicker BirthDate;
    @FXML
    private TextField Hometown;
    @FXML
    private TextField Address;
    @FXML
    private TextField PhoneNumber;
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @FXML
    public void RegistButtonOnClick() {
        // 获取用户输入的信息
        String name = Name.getText();
        String gender = Gender.getValue();
        LocalDate birthDate = BirthDate.getValue();
        String hometown = Hometown.getText();
        String address = Address.getText();
        String phoneNumber = PhoneNumber.getText();
        String username = Username.getText();
        String password = Password.getText();

        // 输入校验
        if (name.isEmpty() || gender == null || birthDate == null || hometown.isEmpty() ||
                address.isEmpty() || phoneNumber.isEmpty() || username.isEmpty() || password.isEmpty()) {
            showAlert("错误", "请填写所有字段！");
            return;
        }

        // 使用线程池处理注册请求
        executorService.submit(() -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL("http://localhost:3306/warehouse?serverTimezone=UTC"); // 确保使用合适的HTTP URL
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                // 构建JSON格式的请求体
                String jsonInputString = String.format(
                        "{\"name\":\"%s\", \"gender\":\"%s\", \"birthDate\":\"%s\", \"hometown\":\"%s\", \"address\":\"%s\", \"phoneNumber\":\"%s\", \"username\":\"%s\", \"password\":\"%s\"}",
                        name, gender, birthDate.toString(), hometown, address, phoneNumber, username, password
                );

                // 将数据发送到服务器
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                // 获取服务器响应
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_CREATED) {
                    Platform.runLater(() -> showAlert("成功", "注册成功，正在自动登录..."));
                    loginAfterRegistration(username, password);
                } else {
                    handleErrorResponse(connection); // 处理错误响应
                }
            } catch (IOException e) {
                Platform.runLater(() -> showAlert("错误", "网络错误，请稍后再试。错误信息：" + e.getMessage()));
            } finally {
                if (connection != null) {
                    connection.disconnect(); // 关闭连接
                }
            }
        });
    }


    private void loginAfterRegistration(String username, String password) {
        executorService.submit(() -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL("http://localhost:3306/warehouse?serverTimezone=UTC"); // 确保使用正确的URL
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                // 构建登录请求的JSON
                String jsonInputString = String.format(
                        "{\"username\":\"%s\", \"password\":\"%s\"}",
                        username, password
                );

                // 发送登录数据
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    Platform.runLater(() -> {
                        showAlert("成功", "注册成功并已自动登录！");
                        returnToMainPage(); // 返回到主页面
                    });
                } else {
                    handleErrorResponse(connection); // 处理错误响应
                }
            } catch (IOException e) {
                Platform.runLater(() -> showAlert("错误", "网络错误，请稍后再试。错误信息：" + e.getMessage()));
            } finally {
                if (connection != null) {
                    connection.disconnect(); // 关闭连接
                }
            }
        });
    }


    private void handleErrorResponse(HttpURLConnection connection) {
        Platform.runLater(() -> {
            try {
                int responseCode = connection.getResponseCode();
                StringBuilder responseMessage = new StringBuilder();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
                    String line;
                    while ((line = in.readLine()) != null) {
                        responseMessage.append(line);
                    }
                }
                switch (responseCode) {
                    case HttpURLConnection.HTTP_UNAUTHORIZED:
                        showAlert("错误", "用户名或密码不正确。");
                        break;
                    case HttpURLConnection.HTTP_NOT_FOUND:
                        showAlert("错误", "用户未注册，请先注册。");
                        break;
                    case HttpURLConnection.HTTP_BAD_REQUEST:
                        showAlert("错误", "请求格式不正确，请检查各个字段的填写。");
                        break;
                    default:
                        showAlert("错误", "发生未知错误，错误代码：" + responseCode + "，错误信息：" + responseMessage.toString());
                        break;
                }
            } catch (IOException e) {
                showAlert("错误", "无法读取错误响应。具体错误：" + e.getMessage());
            }
        });
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void returnToMainPage() {
        // 实现返回主页面的逻辑
        try {
            Stage stage = (Stage) mainVbox.getScene().getWindow();
            Parent mainPage = FXMLLoader.load(getClass().getResource("/fxml/admin.fxml"));
            Scene scene = new Scene(mainPage);
            stage.setScene(scene);
            stage.setTitle("主页面");
            stage.show();
        } catch (IOException e) {
            showAlert("错误", "无法加载主页面。具体错误：" + e.getMessage());
        }
    }
}