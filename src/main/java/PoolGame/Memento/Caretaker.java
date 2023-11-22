package PoolGame.Memento;

/**
 * The caretaker to keep mementos
 */
public class Caretaker {

    private Memento memento;

    /**
     * Get last saved memento
     * @return Last saved memento
     */
    public Memento getMemento() {
        return memento;
    }

    /**
     * Saved a memento
     */
    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
