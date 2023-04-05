package model;

import lombok.NonNull;

import java.util.TreeSet;

/**
 * Created by master on 10/25/17.
 */

public class ElevatorController implements Runnable {
    @NonNull
    private Elevator elevator;

    private TreeSet<Request> downRequest;
    private TreeSet<Request> upRequest;
    private static final int MAX_QUEUE_SIZE = 10;

    public ElevatorController(@NonNull Elevator elevator) {
        this.elevator = elevator;
        downRequest = new TreeSet<>((r1,r2) -> r2.getFloor()-r1.getFloor());
        upRequest = new TreeSet<>();
        elevator.setStatus(Status.UP);
    }


    public void addFloorRequest(Request request) {
        if (elevator.getCurrentFloor() == request.getFloor()) {
            return;
        }
        if (elevator.getAvailableFloor().contains(request.getFloor())) {
            if (request.getFloor() > elevator.getCurrentFloor()) {
                if (upRequest.size() < MAX_QUEUE_SIZE) {
                    upRequest.add(request);
                }
            } else {
                if (downRequest.size() < MAX_QUEUE_SIZE) {
                    downRequest.add(request);
                }
            }
        } else {
            System.out.println("Floor not available on this lift");
        }
    }

    public void processRequest() throws InterruptedException {
        Thread.sleep(1000);
        Integer floorToProceed = null;
        if(elevator.getStatus() == Status.UP){
            if(upRequest.size() > 0){
                floorToProceed  = upRequest.first().getFloor();
                upRequest.remove(floorToProceed);
            }
        } else if(elevator.getStatus() == Status.DOWN ){
            if(downRequest.size() > 0){
                floorToProceed  = downRequest.last().getFloor();
                downRequest.remove(floorToProceed);
            }
        }
        if(floorToProceed !=null){
            elevator.goToFloor(floorToProceed);
        }

        if(elevator.getStatus() == Status.DOWN && downRequest.size()==0 && upRequest.size() != 0){
            elevator.setStatus(Status.UP);
        } else if(elevator.getStatus() == Status.UP && upRequest.size()==0 && downRequest.size() !=0 ){
            elevator.setStatus(Status.DOWN);
        }

    }


    public void run() {

    }


}
