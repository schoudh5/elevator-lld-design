package model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Created by master on 10/25/17.
 */

@RequiredArgsConstructor
public class Elevator {

    @Getter @Setter
    private int currentFloor;
    @NonNull
    @Getter
    private final Set<Integer> availableFloor;
    @Getter
    @Setter
    private Status status;

    public void goToFloor(int floor) {
        this.currentFloor = floor;
    }
}
