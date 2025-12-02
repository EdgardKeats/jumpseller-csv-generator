package cl.rivendel.csv.ui;

import cl.rivendel.csv.BootApplication;
import cl.rivendel.csv.service.ScryfallClient;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class JavaFXAppSupport extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        ApplicationContextInitializer<GenericApplicationContext> initializer = applicationContext -> {
            applicationContext.registerBean(Application.class, ()->JavaFXAppSupport.this);
            applicationContext.registerBean(Parameters.class, this::getParameters);
            applicationContext.registerBean(HostServices.class, this::getHostServices);
        };
        this.context = new SpringApplicationBuilder()
            .sources(BootApplication.class)
            .initializers(initializer)
            .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void stop() throws Exception {
        this.context.close();
        Platform.exit();
    }

    @Override
    public void start(Stage stage) {
        this.context.publishEvent(new StageReadyEvent(stage));
    }

    public class StageReadyEvent extends ApplicationEvent{
        public Stage getStage(){
            return Stage.class.cast(getSource());
        }

        public StageReadyEvent(Stage source) {
            super(source);
        }
    }
}
