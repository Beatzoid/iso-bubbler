package input.mouse;

import state.State;

public interface MouseConsumer {

    void onClick(State state);
    void onDrag(State state);
}
