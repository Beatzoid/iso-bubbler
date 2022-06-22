package game.settings;

public class Setting<T> {
    private T value;

    public Setting(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
