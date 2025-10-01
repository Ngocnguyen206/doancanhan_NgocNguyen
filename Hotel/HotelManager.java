package Hotel;

import java.util.ArrayList;

public class HotelManager {

    private final ArrayList<Room> rooms = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public boolean removeRoom(String id) {
        for (Room r : rooms) {
            if (r.getId().equals(id)) {
                rooms.remove(r);
                return true;
            }
        }
        return false;
    }

    public Room findRoom(String id) {
        for (Room r : rooms) {
            if (r.getId().equals(id)) {
                return r;
            }
        }
        return null;
    }

    public String listRooms() {
        StringBuilder sb = new StringBuilder();
        for (Room r : rooms) {
            sb.append(r.toString()).append("\n");
        }
        return sb.toString();
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }
}
