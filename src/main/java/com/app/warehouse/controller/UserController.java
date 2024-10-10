package com.app.warehouse.controller;

import com.app.warehouse.WarehouseApplication;
import com.app.warehouse.dao.UserMapper;
import com.app.warehouse.model.User;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

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
    private TextField Username;

    @FXML
    private PasswordField Password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
