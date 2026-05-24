package noescape;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class RoomPanel extends BasePanel {
    private final Escapable room;
    private final int roomIndex;
    private final int totalRooms;
    private final Player player;
    private final String controllerMessage;
    private final Escapable[] allRooms;
    private final int activeRoomIndex;

    private JLabel attemptsLabel;

    public RoomPanel(Escapable room, int roomIndex, int totalRooms, Player player, String controllerMessage, Escapable[] allRooms, int activeRoomIndex) {
        this.room = room;
        this.roomIndex = roomIndex;
        this.totalRooms = totalRooms;
        this.player = player;
        this.controllerMessage = controllerMessage;
        this.allRooms = allRooms;
        this.activeRoomIndex = activeRoomIndex;
        initializeContent();
    }

    public void refreshAttempts() {
        if (attemptsLabel == null) return;
        int used = room.getAttempts();
        int max = player.getMaxAttempts();
        attemptsLabel.setText("Attempts used:  " + used + " / " + max);
        attemptsLabel.setForeground(
            used == 0 ? GameWindow.COLOR_GREEN
          : used >= max - 1 ? GameWindow.COLOR_RED
          : GameWindow.COLOR_YELLOW
        );
    }

    @Override
    protected void initializeContent() {
        setLayout(new BorderLayout());

        JPanel topSection = new JPanel();
        topSection.setOpaque(false);
        topSection.setLayout(new BoxLayout(topSection, BoxLayout.Y_AXIS));
        topSection.setBorder(new EmptyBorder(30, 60, 16, 60));

        JLabel roomNameLabel = new JLabel(room.getName(), SwingConstants.CENTER);
        roomNameLabel.setFont(new Font("Consolas", Font.BOLD, 36));
        roomNameLabel.setForeground(GameWindow.COLOR_CYAN);
        roomNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel roomTypeLabel = new JLabel(
            "Room " + (roomIndex + 1) + " of " + totalRooms
            + "   ·   " + room.getRoomType()
            + "   ·   " + player.getMaxAttempts() + " attempts allowed",
            SwingConstants.CENTER);
        roomTypeLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
        roomTypeLabel.setForeground(GameWindow.COLOR_DIMMED);
        roomTypeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        topSection.add(roomNameLabel);
        topSection.add(Box.createVerticalStrut(8));
        topSection.add(roomTypeLabel);
        topSection.add(Box.createVerticalStrut(16));
        topSection.add(createHorizontalDivider());

        JPanel midSection = new JPanel(new GridBagLayout());
        midSection.setOpaque(false);
        midSection.setBorder(new EmptyBorder(0, 80, 0, 80));

        JPanel midContent = new JPanel();
        midContent.setOpaque(false);
        midContent.setLayout(new BoxLayout(midContent, BoxLayout.Y_AXIS));

        midContent.add(Box.createVerticalStrut(30));

        JPanel puzzleBox = new JPanel();
        puzzleBox.setLayout(new BoxLayout(puzzleBox, BoxLayout.Y_AXIS));
        puzzleBox.setBackground(new Color(30, 20, 50, 220));
        puzzleBox.setOpaque(true);
        puzzleBox.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(GameWindow.COLOR_PURPLE, 1, true),
            new EmptyBorder(20, 30, 20, 30)
        ));
        puzzleBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        JLabel puzzleTag = new JLabel("📝  PUZZLE", SwingConstants.CENTER);
        puzzleTag.setFont(new Font("Consolas", Font.BOLD, 13));
        puzzleTag.setForeground(GameWindow.COLOR_YELLOW);
        puzzleTag.setAlignmentX(Component.CENTER_ALIGNMENT);
        puzzleBox.add(puzzleTag);
        puzzleBox.add(Box.createVerticalStrut(12));

        for (String line : wrapTextToLines(room.getPuzzle(), 65)) {
            JLabel l = new JLabel(line, SwingConstants.CENTER);
            l.setFont(new Font("Calibri", Font.PLAIN, 18));
            l.setForeground(GameWindow.COLOR_TEXT);
            l.setAlignmentX(Component.CENTER_ALIGNMENT);
            puzzleBox.add(l);
            puzzleBox.add(Box.createVerticalStrut(4));
        }
        midContent.add(puzzleBox);
        midContent.add(Box.createVerticalStrut(22));

        int used = room.getAttempts();
        int max  = player.getMaxAttempts();
        attemptsLabel = new JLabel("Attempts used:  " + used + " / " + max, SwingConstants.CENTER);
        attemptsLabel.setName("attemptsLabel");   // tag for GameWindow lookup
        attemptsLabel.setFont(new Font("Consolas", Font.BOLD, 15));
        attemptsLabel.setForeground(
            used == 0 ? GameWindow.COLOR_GREEN
          : used >= max - 1 ? GameWindow.COLOR_RED
          : GameWindow.COLOR_YELLOW
        );
        attemptsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        midContent.add(attemptsLabel);
        midContent.add(Box.createVerticalStrut(14));

        JLabel instrLabel = new JLabel("Type your answer below and press  Submit.", SwingConstants.CENTER);
        instrLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
        instrLabel.setForeground(GameWindow.COLOR_DIMMED);
        instrLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        midContent.add(instrLabel);
        midContent.add(Box.createVerticalStrut(6));

        JLabel clueHint = new JLabel("Use the  Clue  or  Hint  buttons if stuck.", SwingConstants.CENTER);
        clueHint.setFont(new Font("Calibri", Font.PLAIN, 14));
        clueHint.setForeground(GameWindow.COLOR_DIMMED);
        clueHint.setAlignmentX(Component.CENTER_ALIGNMENT);
        midContent.add(clueHint);

        if (controllerMessage != null && !controllerMessage.isBlank()) {
            midContent.add(Box.createVerticalStrut(20));
            midContent.add(createHorizontalDivider());
            midContent.add(Box.createVerticalStrut(14));
            Color msgColor = controllerMessage.contains("Correct") ? GameWindow.COLOR_GREEN
                           : controllerMessage.contains("CLUE") || controllerMessage.contains("HINT") ? GameWindow.COLOR_CYAN
                           : controllerMessage.contains("locked") ? GameWindow.COLOR_RED
                           : GameWindow.COLOR_ORANGE;
            for (String line : wrapTextToLines(controllerMessage, 70)) {
                JLabel ml = new JLabel("[ " + line + " ]", SwingConstants.CENTER);
                ml.setFont(new Font("Calibri", Font.ITALIC, 15));
                ml.setForeground(msgColor);
                ml.setAlignmentX(Component.CENTER_ALIGNMENT);
                midContent.add(ml);
                midContent.add(Box.createVerticalStrut(4));
            }
        }

        midSection.add(midContent);

        JPanel bottomSection = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        bottomSection.setOpaque(false);
        bottomSection.setBorder(new EmptyBorder(10, 0, 24, 0));

        if (allRooms != null) {
            for (int i = 0; i < allRooms.length; i++) {
                Escapable r = allRooms[i];
                boolean active = (i == activeRoomIndex);
                boolean solved = r.isSolved();
                boolean locked = r.isLocked();

                String icon = solved ? "✓ " : locked ? "🔒 " : active ? "▶ " : "○ ";
                Color  col  = solved ? GameWindow.COLOR_GREEN : active ? GameWindow.COLOR_CYAN : locked ? GameWindow.COLOR_DIMMED : GameWindow.COLOR_TEXT;

                JLabel nav = new JLabel(icon + r.getName(), SwingConstants.CENTER);
                nav.setFont(new Font("Consolas", Font.PLAIN, 13));
                nav.setForeground(col);
                nav.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(col, 1, true),
                    new EmptyBorder(6, 14, 6, 14)
                ));
                bottomSection.add(nav);
            }
        }

        add(topSection, BorderLayout.NORTH);
        add(midSection, BorderLayout.CENTER);
        add(bottomSection, BorderLayout.SOUTH);
    }
}