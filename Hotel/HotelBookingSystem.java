package Hotel;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;

public class HotelBookingSystem extends JFrame {

    private JTextArea textArea;
    private JTextField txtRoomId, txtPrice, txtCustomer, txtDays;
    private final JComboBox<String> cbRoomType;
    private final JComboBox<String> cbStatus;
    private HotelManager manager;

    public HotelBookingSystem() {
        manager = new HotelManager();

        setTitle("Hotel Booking Management");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Text area hien thi
        textArea = new JTextArea();
        textArea.setBackground(new Color(240, 255, 200));
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Panel nut chuc nang
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton btnAddRoom = new JButton("Add room");
        JButton btnRemoveRoom = new JButton("Remove room");
        JButton btnShowRooms = new JButton("Show rooms");
        JButton btnBookRoom = new JButton("Book room");
        JButton btnCheckout = new JButton("Check out");
        JButton btnExportFile = new JButton("Export File");
        JButton btnFindRoom = new JButton("Find room");
        JButton btnStatistic = new JButton("Statistics");

        buttonPanel.add(btnAddRoom);
        buttonPanel.add(btnRemoveRoom);
        buttonPanel.add(btnShowRooms);
        buttonPanel.add(btnBookRoom);
        buttonPanel.add(btnCheckout);
        buttonPanel.add(btnExportFile);
        buttonPanel.add(btnFindRoom);
        buttonPanel.add(btnStatistic);

        add(buttonPanel, BorderLayout.NORTH);

        // Panel nhap du lieu
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Room information"));

        txtRoomId = new JTextField();
        cbRoomType = new JComboBox<>(new String[]{"Standard", "VIP", "Suite"});
        txtPrice = new JTextField();
        cbStatus = new JComboBox<>(new String[]{"Available", "Booked"});
        txtCustomer = new JTextField();
        txtDays = new JTextField();

        inputPanel.add(new JLabel("Room ID:"));
        inputPanel.add(txtRoomId);
        inputPanel.add(new JLabel("Room type:"));
        inputPanel.add(cbRoomType);
        inputPanel.add(new JLabel("Price/ Day:"));
        inputPanel.add(txtPrice);
        inputPanel.add(new JLabel("Status:"));
        inputPanel.add(cbStatus);
        inputPanel.add(new JLabel("Customer name:"));
        inputPanel.add(txtCustomer);
        inputPanel.add(new JLabel("Days:"));
        inputPanel.add(txtDays);

        add(inputPanel, BorderLayout.SOUTH);

        // ================== CAC SU KIEN NUT ==================
        // Them phong
        btnAddRoom.addActionListener(e -> {
            try {
                String id = txtRoomId.getText();
                String type = cbRoomType.getSelectedItem().toString();
                double price = Double.parseDouble(txtPrice.getText());

                Room room = new Room(id, type, price);
                manager.addRoom(room);

                textArea.setText("Add room successfully!\n" + manager.listRooms());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Input error!");
            }
        });

        // Xoa phong
        btnRemoveRoom.addActionListener(e -> {
            String id = txtRoomId.getText();
            if (manager.removeRoom(id)) {
                textArea.setText("Remove room successfully!\n" + manager.listRooms());
            } else {
                textArea.setText("Room not found!");
            }
        });

        // Hien thi phong
        btnShowRooms.addActionListener(e -> {
            textArea.setText(manager.listRooms());
        });

        // Dat phong
        btnBookRoom.addActionListener(e -> {
            String id = txtRoomId.getText();
            Room r = manager.findRoom(id);
            if (r != null && r.getStatus().equals("Available")) {
                r.setCustomer(txtCustomer.getText());
                r.setDays(Integer.parseInt(txtDays.getText()));
                r.setStatus("Booked");
                textArea.setText("Booking successful!\n" + r.toString());
            } else {
                textArea.setText("Room does not exist or already booked!");
            }
        });

        // Tra phong
        btnCheckout.addActionListener(e -> {
            String id = txtRoomId.getText();
            Room r = manager.findRoom(id);
            if (r != null && r.getStatus().equals("Booked")) {
                double bill = r.calculateBill();
                r.setStatus("Available");
                r.setCustomer("");
                r.setDays(0);
                textArea.setText("Customer checked out. Invoice: " + bill + " VND");
            } else {
                textArea.setText("Room does not exist or not booked yet!");
            }
        });

        // Xuat file
        btnExportFile.addActionListener(e -> {
            try (FileWriter fw = new FileWriter("hotel_rooms.txt")) {
                fw.write(manager.listRooms());
                JOptionPane.showMessageDialog(this, "Export file successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error while exporting file!");
            }
        });

        // Tim phong
        btnFindRoom.addActionListener(e -> {
            String id = txtRoomId.getText();
            Room r = manager.findRoom(id);
            if (r != null) {
                textArea.setText("Found: \n" + r.toString());
            } else {
                textArea.setText("Room not found!");
            }
        });

        // Thong ke
        btnStatistic.addActionListener(e -> {
            int available = 0, booked = 0;
            for (Room r : manager.getRooms()) {
                if (r.getStatus().equals("Available")) {
                    available++;
                } else if (r.getStatus().equals("Booked")) {
                    booked++;
                }
            }
            textArea.setText("Statistics:\nAvailable rooms: " + available + "\nBooked rooms: " + booked);
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new HotelBookingSystem();
    }
}
