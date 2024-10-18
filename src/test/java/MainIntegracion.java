import java.io.FileNotFoundException;

import UI.SourceUI;
import org.domingus.app.Domingus;
import org.domingus.init.DomingusFactory;
import org.domingus.interfaces.Source;
import org.domingus.logger.MessageListener;
import org.domingus.init.MessageListenerFactory;
import org.domingus.ui.DomingusView;

public class MainIntegracion {

    private static String EXTENSIONS_PATH = "src/test/resources/extensions/";
    private static String PATH_MEMORY = "src/test/resources/memory.json";
    private static Integer TIME_INTERVAL = 2000;

    public static void main(String[] args) throws FileNotFoundException {
        // Inicializar logger
        MessageListenerFactory messageListenerFactory = new MessageListenerFactory();
        MessageListener messageListener = messageListenerFactory.create(PATH_MEMORY);

        // Inicializar Domingus (core)
        DomingusFactory domingusFactory = new DomingusFactory();
        Source source = new SourceUI(TIME_INTERVAL);  // source para el logger
        Domingus domingus = domingusFactory.create(source, EXTENSIONS_PATH);

        // Agregar el logger como observer
        domingus.addObserver(messageListener);
        domingus.addCurrentObserver(messageListener);

        // Inicializar UI
        DomingusView domingusView = new DomingusView(domingus);
        domingusView.init();

        // Ahora la UI y el logger están funcionando juntos
    }
}

