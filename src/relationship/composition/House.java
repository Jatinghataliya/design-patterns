package relationship.composition;

import java.util.ArrayList;
import java.util.List;

/**
 * House OWNS its Rooms. Rooms are created inside the House and are never
 * exposed for outside creation, so they live and die with the House.
 */
public class House {

    private final String address;
    private final List<Room> rooms = new ArrayList<>();

    public House(String address, String... roomNames) {
        this.address = address;
        for (String roomName : roomNames) {
            rooms.add(new Room(roomName)); // House creates and owns its parts
        }
    }

    public void printRooms() {
        System.out.println("House at " + address + " has rooms:");
        for (Room room : rooms) {
            System.out.println("  - " + room.getName());
        }
    }
}
