import config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.PatientService;

import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
    }
}
