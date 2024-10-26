import java.io.FileNotFoundException;
import java.io.IOException;

import UI.SourceUI;
import UI.TimerUI;
import org.domingus.app.Domingus;
import org.domingus.init.DomingusFactory;
import org.domingus.init.LoggerFactory;
import org.domingus.interfaces.Source;
import org.domingus.logger.Logger;

import org.domingus.ui.DomingusView;

public class MainIntegracion {

    private static String EXTENSIONS_PATH = "src/test/resources/extensions/";
    private static String PATH_MEMORY = "src/test/resources/memory.txt";
    private static Integer TIME_INTERVAL = 1000;

    public static void main(String[] args) throws IOException {
        // Inicializar logger
        LoggerFactory loggerFactory = new LoggerFactory();
        Logger logger = loggerFactory.create(PATH_MEMORY);

        // Inicializar Domingus (core)
        DomingusFactory domingusFactory = new DomingusFactory();
        Source source = new SourceUI();  // source para el logger
        Domingus domingus = domingusFactory.create(source, EXTENSIONS_PATH);
        TimerUI timerUI = new TimerUI(TIME_INTERVAL, (Runnable) source);
        // Agregar el logger como observer
        domingus.addObserver(logger);
        domingus.addCurrentObserver(logger.getClass().getSimpleName());

        // Inicializar UI
        DomingusView domingusView = new DomingusView(domingus);
        domingusView.init();

        timerUI.run();

        // Ahora la UI y el logger están funcionando juntos
    }
}

