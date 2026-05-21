package noescape;

/**
 * LOCATION: A campus area that contains a Room.
 *
 * OOP: Encapsulation - wraps a room with campus metadata.
 */
public class Location {

    private final String       buildingName;
    private final RoomBehavior room;

    public Location(String buildingName, RoomBehavior room) {
        this.buildingName = buildingName;
        this.room         = room;
    }

    public String       getBuildingName() { return buildingName; }
    public RoomBehavior getRoom()         { return room;         }

    @Override
    public String toString() {
        return buildingName + " - " + room.getName();
    }
}