package main.java;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class GestionContactosGUI extends JFrame {

    private ResourceBundle messages;
    private Locale currentLocale;

    // Componentes de la interfaz
    private JLabel lblTitle, lblName, lblPhone, lblEmail, lblLanguage;
    private JTextField txtName, txtPhone, txtEmail;
    private JButton btnAdd, btnDelete, btnClear;
    private JComboBox<String> cbLanguage;
    private JTable table;
    private DefaultTableModel tableModel;

    public GestionContactosGUI() {
        // Idioma por defecto: Español
        currentLocale = Locale.forLanguageTag("es");
        loadBundle();

        initUI();
        updateTexts();
    }

    private void loadBundle() {
        // Carga robusta compatible con ejecuciones desde IDEs y consola
        try {
            messages = ResourceBundle.getBundle("messages", currentLocale, Thread.currentThread().getContextClassLoader());
        } catch (Exception e1) {
            try {
                messages = ResourceBundle.getBundle("messages", currentLocale);
            } catch (Exception e2) {
                messages = ResourceBundle.getBundle("main.resources.messages", currentLocale);
            }
        }
    }

    private void initUI() {
        setTitle("Gestión de Contactos");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        getContentPane().setBackground(new Color(248, 249, 250));

        // --- PANEL SUPERIOR ---
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(44, 62, 80));

        lblTitle = new JLabel();
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 10));

        JPanel langPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        langPanel.setOpaque(false);

        lblLanguage = new JLabel();
        lblLanguage.setForeground(Color.WHITE);
        lblLanguage.setFont(new Font("Segoe UI", Font.BOLD, 12));

        String[] languages = {"Español", "English", "Français"};
        cbLanguage = new JComboBox<>(languages);
        cbLanguage.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cbLanguage.addActionListener(e -> changeLanguage());

        langPanel.add(lblLanguage);
        langPanel.add(cbLanguage);

        topPanel.add(lblTitle, BorderLayout.WEST);
        topPanel.add(langPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // --- PANEL CENTRAL ---
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // Formulario de entrada
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 230), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lblName = new JLabel();
        txtName = new JTextField(15);
        lblPhone = new JLabel();
        txtPhone = new JTextField(15);
        lblEmail = new JLabel();
        txtEmail = new JTextField(15);

        Font fontInput = new Font("Segoe UI", Font.PLAIN, 12);
        txtName.setFont(fontInput);
        txtPhone.setFont(fontInput);
        txtEmail.setFont(fontInput);

        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(lblName, gbc);
        gbc.gridx = 1; gbc.gridy = 0; formPanel.add(txtName, gbc);

        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(lblPhone, gbc);
        gbc.gridx = 1; gbc.gridy = 1; formPanel.add(txtPhone, gbc);

        gbc.gridx = 0; gbc.gridy = 2; formPanel.add(lblEmail, gbc);
        gbc.gridx = 1; gbc.gridy = 2; formPanel.add(txtEmail, gbc);

        // Botones de acción
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 5));
        btnPanel.setOpaque(false);

        btnAdd = new JButton();
        btnDelete = new JButton();
        btnClear = new JButton();

        styleButton(btnAdd, new Color(39, 174, 96));
        styleButton(btnDelete, new Color(192, 57, 43));
        styleButton(btnClear, new Color(127, 140, 141));

        btnPanel.add(btnAdd);
        btnPanel.add(btnDelete);
        btnPanel.add(btnClear);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        formPanel.add(btnPanel, gbc);

        centerPanel.add(formPanel, BorderLayout.WEST);

        // Tabla de contactos
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(236, 240, 241));
        table.setRowHeight(24);

        JScrollPane scrollPane = new JScrollPane(table);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // Eventos
        btnAdd.addActionListener(e -> addContact());
        btnDelete.addActionListener(e -> deleteContact());
        btnClear.addActionListener(e -> clearFields());
    }

    private void styleButton(JButton btn, Color bgColor) {
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void changeLanguage() {
        int index = cbLanguage.getSelectedIndex();
        switch (index) {
            case 0 -> currentLocale = Locale.forLanguageTag("es");
            case 1 -> currentLocale = Locale.forLanguageTag("en");
            case 2 -> currentLocale = Locale.forLanguageTag("fr");
        }
        loadBundle();
        updateTexts();
    }

    private void updateTexts() {
        setTitle(messages.getString("app.title"));
        lblTitle.setText(messages.getString("app.title"));
        lblName.setText(messages.getString("label.name"));
        lblPhone.setText(messages.getString("label.phone"));
        lblEmail.setText(messages.getString("label.email"));
        lblLanguage.setText(messages.getString("label.language"));

        btnAdd.setText(messages.getString("btn.add"));
        btnDelete.setText(messages.getString("btn.delete"));
        btnClear.setText(messages.getString("btn.clear"));

        String[] columnNames = {
            messages.getString("col.name"),
            messages.getString("col.phone"),
            messages.getString("col.email"),
            messages.getString("col.birthdate")
        };
        tableModel.setColumnIdentifiers(columnNames);
    }

    private void addContact() {
        if (!txtName.getText().trim().isEmpty()) {
            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, currentLocale);
            String formattedDate = df.format(new Date());

            tableModel.addRow(new Object[]{
                txtName.getText().trim(),
                txtPhone.getText().trim(),
                txtEmail.getText().trim(),
                formattedDate
            });
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, 
                currentLocale.getLanguage().equals("es") ? "Ingrese al menos el nombre" : "Please enter a name",
                "Info", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteContact() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
        }
    }

    private void clearFields() {
        txtName.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtName.requestFocus();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> new GestionContactosGUI().setVisible(true));
    }
}