package noescape;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class GameWindow extends JFrame {

    public static final Color COLOR_BACKGROUND_DARK = new Color(13, 13, 23);
    public static final Color COLOR_BACKGROUND_CARD = new Color(22, 22, 40);
    public static final Color COLOR_BACKGROUND_INPUT = new Color(18, 18, 32);
    public static final Color COLOR_PURPLE = new Color(160, 80, 220);
    public static final Color COLOR_CYAN = new Color(60, 200, 220);
    public static final Color COLOR_GREEN = new Color(60, 200, 100);
    public static final Color COLOR_YELLOW = new Color(240, 200, 60);
    public static final Color COLOR_RED = new Color(220, 70, 70);
    public static final Color COLOR_ORANGE = new Color(230, 140, 50);
    public static final Color COLOR_TEXT = new Color(210, 210, 230);
    public static final Color COLOR_DIMMED = new Color(130, 120, 155);
    public static final Color COLOR_BORDER = new Color(55, 45, 85);

    private JLabel timerLabel;
    private JLabel roomInfoLabel;
    private JPanel contentCardPanel;
    private JTextField inputField;
    private JButton submitButton;
    private JButton clueButton;
    private JButton hintButton;

    private JLabel feedbackLabel;
    private JPanel feedbackRow;

    public GameWindow() {
        buildWindow();
    }

    private void buildWindow() {
        setTitle("NO ESCAPE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(COLOR_BACKGROUND_DARK);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(900, 600));

        add(buildHeaderPanel(), BorderLayout.NORTH);
        add(initializeContentArea(), BorderLayout.CENTER);
        add(buildFooterPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel buildHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(8, 8, 18));
        header.setBorder(new MatteBorder(0, 0, 2, 0, COLOR_PURPLE));
        header.setPreferredSize(new Dimension(0, 56));

        roomInfoLabel = new JLabel("", SwingConstants.CENTER);
        roomInfoLabel.setFont(new Font("Consolas", Font.PLAIN, 15));
        roomInfoLabel.setForeground(COLOR_DIMMED);

        timerLabel = new JLabel("", SwingConstants.RIGHT);
        timerLabel.setFont(new Font("Consolas", Font.BOLD, 22));
        timerLabel.setForeground(COLOR_YELLOW);
        timerLabel.setBorder(new EmptyBorder(0, 0, 0, 22));

        header.add(roomInfoLabel, BorderLayout.CENTER);
        header.add(timerLabel, BorderLayout.EAST);
        return header;
    }

    private JPanel initializeContentArea() {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(COLOR_BACKGROUND_DARK);
        wrapper.setBorder(new EmptyBorder(8, 16, 6, 16));

        contentCardPanel = new JPanel(new BorderLayout());
        contentCardPanel.setBackground(COLOR_BACKGROUND_DARK);

        feedbackLabel = new JLabel("", SwingConstants.CENTER);
        feedbackLabel.setFont(new Font("Consolas", Font.BOLD, 15));
        feedbackLabel.setForeground(COLOR_RED);

        feedbackRow = new JPanel(new BorderLayout());
        feedbackRow.setBackground(new Color(40, 10, 10));
        feedbackRow.setBorder(new EmptyBorder(10, 24, 10, 24));
        feedbackRow.add(feedbackLabel, BorderLayout.CENTER);
        feedbackRow.setVisible(false);

        wrapper.add(contentCardPanel, BorderLayout.CENTER);
        wrapper.add(feedbackRow, BorderLayout.SOUTH);
        return wrapper;
    }

    private JPanel buildFooterPanel() {
        JPanel footer = new JPanel(new BorderLayout(8, 0));
        footer.setBackground(COLOR_BACKGROUND_DARK);
        footer.setBorder(new CompoundBorder(
            new MatteBorder(2, 0, 0, 0, COLOR_BORDER),
            new EmptyBorder(10, 24, 14, 24)
        ));

        inputField = new JTextField();
        inputField.setFont(new Font("Consolas", Font.PLAIN, 16));
        inputField.setBackground(COLOR_BACKGROUND_INPUT);
        inputField.setForeground(COLOR_TEXT);
        inputField.setCaretColor(COLOR_PURPLE);
        inputField.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(COLOR_PURPLE, 1, true),
            new EmptyBorder(8, 16, 8, 16)
        ));

        submitButton = createFooterButton("Submit", COLOR_GREEN);
        clueButton = createFooterButton("Clue", COLOR_CYAN);
        hintButton = createFooterButton("Hint", COLOR_YELLOW);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        btnPanel.setBackground(COLOR_BACKGROUND_DARK);
        btnPanel.add(clueButton);
        btnPanel.add(hintButton);
        btnPanel.add(submitButton);

        footer.add(inputField, BorderLayout.CENTER);
        footer.add(btnPanel, BorderLayout.EAST);
        return footer;
    }

    private void swapContentPanel(JPanel newPanel) {
        feedbackRow.setVisible(false);
        contentCardPanel.removeAll();
        contentCardPanel.add(newPanel, BorderLayout.CENTER);
        contentCardPanel.revalidate();
        contentCardPanel.repaint();
    }

    public void showEnterNameScreen() {
        roomInfoLabel.setText("");
        submitButton.setVisible(true);
        clueButton.setVisible(false);
        hintButton.setVisible(false);
        inputField.setEnabled(true);
        swapContentPanel(new NameEntryPanel());
    }

    public void showChooseCourseScreen(String playerName, ActionListener onCS, ActionListener onNursing) {
        roomInfoLabel.setText("Choose Your Course");
       
        submitButton.setVisible(false);
        clueButton.setVisible(false);
        hintButton.setVisible(false);
        inputField.setVisible(false);
        swapContentPanel(new CourseSelectPanel(playerName, onCS, onNursing));
    }

    public void showSplashScreen(Player player) {
        roomInfoLabel.setText("Ready to Play");
        submitButton.setVisible(false);
        clueButton.setVisible(false);
        hintButton.setVisible(false);
        inputField.setVisible(false);
        swapContentPanel(new ReadyPanel(player, inputField, submitButton));
    }

    public void showRoomScreen(Escapable room, int roomIndex, int totalRooms, Player player, String controllerMessage, Escapable[] allRooms, int activeRoomIndex) {
        roomInfoLabel.setText(
            "Room " + (roomIndex + 1) + " of " + totalRooms
            + "  —  " + room.getName());
        submitButton.setVisible(true);
        clueButton.setVisible(true);
        hintButton.setVisible(true);
        inputField.setVisible(true);
        inputField.setEnabled(true);
        swapContentPanel(new RoomPanel(room, roomIndex, totalRooms, player, controllerMessage, allRooms, activeRoomIndex));
    }

    public void showWinScreen(Player player, int secondsRemaining, String controllerMessage) {
        roomInfoLabel.setText("YOU ESCAPED!");
        submitButton.setVisible(false);
        clueButton.setVisible(false);
        hintButton.setVisible(false);
        inputField.setVisible(false);
        swapContentPanel(new WinPanel(player, secondsRemaining, controllerMessage,
            e -> {
                inputField.setVisible(true);
                submitButton.setVisible(true);
                inputField.setText("restart");
                submitButton.doClick();
            }));
    }

    public void showLoopScreen(Player player, String controllerMessage) {
        roomInfoLabel.setText("You Failed!");
        submitButton.setVisible(false);
        clueButton.setVisible(false);
        hintButton.setVisible(false);
        inputField.setVisible(false);
        swapContentPanel(new LoopPanel(player, controllerMessage,
            e -> {
                inputField.setVisible(true);
                submitButton.setVisible(true);
                inputField.setText("restart");
                submitButton.doClick();
            }));
    }

    public void showFeedback(String message, Color textColor) {
        if (message == null || message.isBlank()) {
            feedbackRow.setVisible(false);
            return;
        }

        // ── Update the attempt counter inside RoomPanel live ──
        if (contentCardPanel.getComponentCount() > 0) {
            java.awt.Component c = contentCardPanel.getComponent(0);
            if (c instanceof RoomPanel) {
                ((RoomPanel) c).refreshAttempts();
            }
        }

        
        feedbackLabel.setText(message);
        feedbackLabel.setForeground(textColor);

        int r = Math.min(255, 10 + textColor.getRed()   / 10);
        int g = Math.min(255, 10 + textColor.getGreen() / 10);
        int b = Math.min(255, 10 + textColor.getBlue()  / 10);
        feedbackRow.setBackground(new Color(r, g, b));
        feedbackRow.setVisible(true);
        feedbackRow.revalidate();
        feedbackRow.repaint();
    }
 
    private JButton createFooterButton(String text, Color accent) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(accent.getRed(), accent.getGreen(), accent.getBlue(), getModel().isRollover() ? 60 : 25));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Consolas", Font.BOLD, 14));
        btn.setForeground(accent);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(accent, 1, true),
            new EmptyBorder(8, 20, 8, 20)
        ));
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public void attachListeners(ActionListener onSubmit, ActionListener onClue, ActionListener onHint) {
        submitButton.addActionListener(onSubmit);
        inputField.addActionListener(onSubmit);
        clueButton.addActionListener(onClue);
        hintButton.addActionListener(onHint);
    }

    public void setInputEnabled(boolean enabled) {
        submitButton.setEnabled(enabled);
        clueButton.setEnabled(enabled);
        hintButton.setEnabled(enabled);
        inputField.setEnabled(enabled);
    }

    public void setClueHintVisible(boolean visible) {
        clueButton.setVisible(visible);
        hintButton.setVisible(visible);
    }

    public void setRoomInfoLabel(String text) { roomInfoLabel.setText(text); }

    public JTextField getInputField() { return inputField; }
    public JLabel getTimerLabel() { return timerLabel; }
    public JButton getSubmitButton() { return submitButton; }
    public JButton getClueButton(){ return clueButton; }
    public JButton getHintButton() { return hintButton; }
}