package Hotel;
import javax.swing.*;
import java.awt.*;

public class HotelBookingGUI extends JFrame {
    private final JTextArea textArea;
    private final JTextField txtRoomId;
    private final JTextField txtPrice;
    private final JTextField txtCustomer;
    private final JTextField txtDays;
    private final JComboBox<String> cbRoomType;
    private final JComboBox<String> cbStatus;

    public HotelBookingGUI() {
        setTitle("Hotel Booking Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Text area hiển thị kết quả
        textArea = new JTextArea();
        textArea.setBackground(new Color(255, 255, 200)); // màu vàng nhạt
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Panel nút chức năng
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        String[] buttons = {"Them phong", "Xoa phong", "Hien thi phong", "Dat phong",
                            "Tra phong", "Xuat File", "Tim phong", "Thong ke"};
        for (String name : buttons) {
            buttonPanel.add(new JButton(name));
        }
        add(buttonPanel, BorderLayout.NORTH);

        // Panel nhập dữ liệu
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thong tin phong"));

        txtRoomId = new JTextField();
        cbRoomType = new JComboBox<>(new String[]{"Standard", "VIP", "Suite"});
        txtPrice = new JTextField();
        cbStatus = new JComboBox<>(new String[]{"Con trong", "Da dat"});
        txtCustomer = new JTextField();
        txtDays = new JTextField();

        inputPanel.add(new JLabel("Ma phong:"));
        inputPanel.add(txtRoomId);
        inputPanel.add(new JLabel("Loai phong:"));
        inputPanel.add(cbRoomType);
        inputPanel.add(new JLabel("Gia/đem:"));
        inputPanel.add(txtPrice);
        inputPanel.add(new JLabel("Trang thai:"));
        inputPanel.add(cbStatus);
        inputPanel.add(new JLabel("Ten khach hang:"));
        inputPanel.add(txtCustomer);
        inputPanel.add(new JLabel("So ngay thue:"));
        inputPanel.add(txtDays);

        add(inputPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new HotelBookingGUI();
    }
}


