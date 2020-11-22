package game.settings;

public class Setting<T> {

    private T value;

    /**
     * The setting class
     * @param value The value
     */
    public Setting(T value) {
        this.value = value;
    }

    /**
     * Get the value
     */
    public T getValue() {
        return value;
    }

    /**
     * Set the value
     * @param value The new value
     */
    public void setValue(T value) {
        this.value = value;
    }
}
