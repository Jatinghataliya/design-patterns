package relationship.composition;

/**
 * Room is a part of a House and has no meaning outside of it.
 */
public class Room {

    private final String name;

    public Room(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
