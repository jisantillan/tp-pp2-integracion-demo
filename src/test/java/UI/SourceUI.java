package UI;

import org.domingus.app.ClassroomAssignment;
import org.domingus.interfaces.Observer;
import org.domingus.interfaces.Source;

import java.util.HashSet;
import java.util.Set;

public class SourceUI implements Source, Runnable {

    private Set<Observer> observers;
    private int version;

    public SourceUI() {
        observers = new HashSet<>();
    }

    @Override
    public void suscribe(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void send(ClassroomAssignment classroomAssignment) {
        observers.forEach((observer -> observer.update(classroomAssignment)));
    }

    @Override
    public void run() {
        send(new ClassroomAssignment(version++));
    }

}