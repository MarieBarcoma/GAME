package noescape;

import javax.swing.*;
import java.awt.*;

public class ReadyPanel extends BasePanel {
    private final Player player;
    private final JTextField inputField;
    private final JButton submitButton;

    public ReadyPanel(Player player, JTextField inputField, JButton submitButton) {
        this.player = player;
        this.inputField = inputField;
        this.submitButton = submitButton;
        initializeContent();
    }

    @Override
    protected void initializeContent() {
        setLayout(new GridBagLayout());

        JPanel inner = new JPanel();
        inner.setOpaque(false);
        inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));

        int w = getResponsiveWidth();
        inner.setPreferredSize(new Dimension(w, 460));
        inner.setMaximumSize(new Dimension(w, 460));

        inner.add(createTitleLabel("NO ESCAPE", GameWindow.COLOR_PURPLE, 36));
        inner.add(Box.createVerticalStrut(28));
        inner.add(createHorizontalDivider());
        inner.add(Box.createVerticalStrut(22));

        inner.add(createCenteredLabel("Player  : " + player.getName(), GameWindow.COLOR_TEXT, 16, Font.PLAIN));
        inner.add(Box.createVerticalStrut(8));
        inner.add(createCenteredLabel("Course  :  " + player.getCourse(), GameWindow.COLOR_CYAN, 16, Font.PLAIN));
        inner.add(Box.createVerticalStrut(8));
        inner.add(createCenteredLabel("Attempts:  " + player.getMaxAttempts() + " per room", GameWindow.COLOR_TEXT, 15, Font.PLAIN));
        inner.add(Box.createVerticalStrut(8));
        inner.add(createCenteredLabel("Bonus   :  +" + player.getBonusSeconds() + " seconds", GameWindow.COLOR_GREEN, 15, Font.PLAIN));
        inner.add(Box.createVerticalStrut(30));
        inner.add(createHorizontalDivider());
        inner.add(Box.createVerticalStrut(26));

        JButton startButton = createLargeCourseButton(
                "▶   START GAME",
                "Begin the time loop", GameWindow.COLOR_GREEN,
                event -> {
                    inputField.setText("start");
                    submitButton.doClick();
                }
        );
        startButton.setMaximumSize(new Dimension(360, 80));
        inner.add(startButton);

        add(inner);
    }
}