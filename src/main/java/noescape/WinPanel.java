package noescape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WinPanel extends BasePanel {
    private final Player player;
    private final int secondsRemaining;
    private final String controllerMessage;
    private final ActionListener onPlayAgain;

    public WinPanel(Player player, int secondsRemaining, String controllerMessage, ActionListener onPlayAgain) {
        this.player = player;
        this.secondsRemaining = secondsRemaining;
        this.controllerMessage = controllerMessage;
        this.onPlayAgain = onPlayAgain;
        initializeContent();
    }

    @Override
    protected void initializeContent() {
        setLayout(new GridBagLayout());

        JPanel inner = new JPanel();
        inner.setOpaque(false);
        inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));

        int w = getResponsiveWidth();
        inner.setPreferredSize(new Dimension(w, 520));
        inner.setMaximumSize(new Dimension(w, 520));

        inner.add(createTitleLabel("🎓  YOU ESCAPED!", GameWindow.COLOR_GREEN, 30));
        inner.add(Box.createVerticalStrut(20));
        inner.add(createCenteredLabel("Congratulations,  " + player.getName() + "!", GameWindow.COLOR_YELLOW, 18, Font.BOLD));
        inner.add(Box.createVerticalStrut(8));
        inner.add(createCenteredLabel(
                "The clock stopped. The loop dissolved. You found the way out.", GameWindow.COLOR_TEXT, 15, Font.PLAIN));
        inner.add(Box.createVerticalStrut(28));
        inner.add(createHorizontalDivider());
        inner.add(Box.createVerticalStrut(16));

        inner.add(createCenteredLabel("Course    :  " + player.getCourse(), GameWindow.COLOR_CYAN, 15, Font.PLAIN));
        inner.add(Box.createVerticalStrut(8));
        inner.add(createCenteredLabel("Time left :  " + secondsRemaining + " seconds", GameWindow.COLOR_GREEN, 15, Font.PLAIN));
        inner.add(Box.createVerticalStrut(16));
        inner.add(createHorizontalDivider());
        inner.add(Box.createVerticalStrut(16));

        if (controllerMessage != null && !controllerMessage.isBlank()) {
            inner.add(createCenteredLabel("[ " + controllerMessage + " ]", GameWindow.COLOR_DIMMED, 13, Font.ITALIC));
            inner.add(Box.createVerticalStrut(22));
        }

        JButton playAgainButton = createLargeCourseButton(
                "🔄   PLAY AGAIN", "Start a new game", GameWindow.COLOR_GREEN, onPlayAgain);
        playAgainButton.setMaximumSize(new Dimension(360, 80));
        inner.add(playAgainButton);
        inner.add(Box.createVerticalStrut(14));

        JButton exitButton = createLargeCourseButton(
                "✖   EXIT GAME", "Close the application", GameWindow.COLOR_RED, event -> System.exit(0));
        exitButton.setMaximumSize(new Dimension(360, 80));
        inner.add(exitButton);

        add(inner);
    }
}