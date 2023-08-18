package cl.rivendel.csv.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class StageListener implements ApplicationListener<JavaFXAppSupport.StageReadyEvent> {
    private final String appTitle;
    private final Resource fxml;
    private final ApplicationContext applicationContext;


    StageListener(@Value("${spring.application.ui.title}")String springApptitle ,
                  @Value("classpath:/ui.fxml")Resource fxmlResource, ApplicationContext applicationContext){
        this.appTitle = springApptitle;
        this.fxml = fxmlResource;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(JavaFXAppSupport.StageReadyEvent event) {
        try {
            Stage stage = event.getStage();
            URL url = this.fxml.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            Parent root =  fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(this.appTitle);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
