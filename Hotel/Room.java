package Hotel;

public class Room {

    private final String id;
    private final String type;    // Standard, VIP, Suite
    private final double price;
    private String status;  // Con trong / Da dat
    private String customer;
    private int days;

    public Room(String id, String type, double price) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.status = "Con trong";
        this.customer = "";
        this.days = 0;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public String getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setDays(int days) {
        this.days = days;
    }

    // Tinh tien thue
    public double calculateBill() {
        return price * days;
    }

    @Override
    public String toString() {
        return "Phong: " + id + " | Loai: " + type + " | Gia: " + price
                + " | Trang thai: " + status
                + (status.equals("Da dat") ? " | Khach: " + customer + " | Ngay: " + days : "");
    }
}
