package entity;

import controller.Controller;
import core.Movement;

public abstract class MovingEntity extends GameObject {

    private Controller controller;
    private Movement movement;

    public MovingEntity(Controller controller) {
        super();
        this.controller = controller;
        this.movement = new Movement(2);
    }

    @Override
    public void update() {
        movement.update(controller);
        position.apply(movement);
    }
}
