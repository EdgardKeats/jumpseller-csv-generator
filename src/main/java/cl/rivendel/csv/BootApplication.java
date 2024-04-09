package cl.rivendel.csv;

import cl.rivendel.csv.ui.JavaFXAppSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplication {
    public static void main(String[] args) {
        javafx.application.Application.launch(JavaFXAppSupport.class, args);
    }
}
