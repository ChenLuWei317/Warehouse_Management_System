package com.app.warehouse_management_system;

import com.app.warehouse_management_system.view.PrimaryStageView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WarehouseManagementSystemApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
        launchApp(WarehouseManagementSystemApplication.class, PrimaryStageView.class, args);
        SpringApplication.run(WarehouseManagementSystemApplication.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
    }

}
