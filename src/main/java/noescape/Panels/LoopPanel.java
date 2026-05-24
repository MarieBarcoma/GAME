package noescape.Panels;

import javax.swing.*;

import noescape.Base.BasePanel;
import noescape.Core.GameWindow;
import noescape.Support.Player;

import java.awt.*;
import java.awt.event.ActionListener;

public class LoopPanel extends BasePanel {
    private final Player player;
    private final String controllerMessage;
    private final ActionListener onTryAgain;

    public LoopPanel(Player player, String controllerMessage, ActionListener onTryAgain) {
        this.player = player;
        this.controllerMessage = controllerMessage;
        this.onTryAgain = onTryAgain;
        initializeContent();
    }

    @Override
    protected void initializeContent() {
        setLayout(new GridBagLayout());

        JPanel inner = new JPanel();
        inner.setOpaque(false);
        inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));

        int w = getResponsiveWidth();
        inner.setPreferredSize(new Dimension(w, 500));
        inner.setMaximumSize(new Dimension(w, 500));

        inner.add(createTitleLabel("💀  YOU FAILED!", GameWindow.COLOR_RED, 30));
        inner.add(Box.createVerticalStrut(22));
        inner.add(createCenteredLabel("Time ran out,  " + player.getName() + ".", GameWindow.COLOR_TEXT, 16, Font.PLAIN));
        inner.add(Box.createVerticalStrut(8));
        inner.add(createCenteredLabel("Better luck next time.", GameWindow.COLOR_TEXT, 15, Font.PLAIN));
        inner.add(Box.createVerticalStrut(28));
        inner.add(createHorizontalDivider());
        inner.add(Box.createVerticalStrut(16));

        if (controllerMessage != null && !controllerMessage.isBlank()) {
            inner.add(createCenteredLabel("[ " + controllerMessage + " ]", GameWindow.COLOR_DIMMED, 13, Font.ITALIC));
            inner.add(Box.createVerticalStrut(16));
        }

        inner.add(createHorizontalDivider());
        inner.add(Box.createVerticalStrut(24));

        JButton tryAgainButton = createLargeCourseButton(
                "🔄   TRY AGAIN", "Restart the loop", GameWindow.COLOR_YELLOW, onTryAgain);
        tryAgainButton.setMaximumSize(new Dimension(360, 80));
        inner.add(tryAgainButton);
        inner.add(Box.createVerticalStrut(14));

        JButton exitButton = createLargeCourseButton(
                "✖   EXIT GAME", "Close the application", GameWindow.COLOR_RED, event -> System.exit(0));
        exitButton.setMaximumSize(new Dimension(360, 80));
        inner.add(exitButton);

        add(inner);
    }
}