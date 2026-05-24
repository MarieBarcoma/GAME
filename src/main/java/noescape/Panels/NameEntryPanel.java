package noescape.Panels;

import javax.swing.*;
import javax.swing.border.*;

import noescape.Base.BasePanel;
import noescape.Core.GameWindow;

import java.awt.*;

public class NameEntryPanel extends BasePanel {
    public NameEntryPanel() {
        initializeContent();
    }

    @Override
    protected void initializeContent() {
        setLayout(new BorderLayout());

        JPanel topSection = new JPanel();
        topSection.setOpaque(false);
        topSection.setLayout(new BoxLayout(topSection, BoxLayout.Y_AXIS));
        topSection.setBorder(new EmptyBorder(44, 60, 20, 60));

        JLabel titleLabel = new JLabel("NO ESCAPE", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Consolas", Font.BOLD, 72));
        titleLabel.setForeground(GameWindow.COLOR_PURPLE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("An endless campus time-loop", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Consolas", Font.ITALIC, 18));
        subtitleLabel.setForeground(GameWindow.COLOR_DIMMED);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        topSection.add(titleLabel);
        topSection.add(Box.createVerticalStrut(10));
        topSection.add(subtitleLabel);

        JPanel midSection = new JPanel(new GridBagLayout());
        midSection.setOpaque(false);
        midSection.setBorder(new EmptyBorder(0, 80, 0, 80));

        JPanel midContent = new JPanel();
        midContent.setOpaque(false);
        midContent.setLayout(new BoxLayout(midContent, BoxLayout.Y_AXIS));

        midContent.add(createHorizontalDivider());
        midContent.add(Box.createVerticalStrut(36));

        String[] story = {
            "The campus clock tower rings midnight.",
            "The halls are empty. The doors are locked.",
            "You are a student trapped in a time loop.",
            "The only way out  —  solve every room before time runs out."
        };
        for (String line : story) {
            JLabel l = new JLabel(line, SwingConstants.CENTER);
            l.setFont(new Font("Calibri", Font.PLAIN, 18));
            l.setForeground(GameWindow.COLOR_TEXT);
            l.setAlignmentX(Component.CENTER_ALIGNMENT);
            midContent.add(l);
            midContent.add(Box.createVerticalStrut(8));
        }

        midContent.add(Box.createVerticalStrut(40));
        midContent.add(createHorizontalDivider());
        midContent.add(Box.createVerticalStrut(36));

        JLabel promptLabel = new JLabel("What is your name?", SwingConstants.CENTER);
        promptLabel.setFont(new Font("Consolas", Font.BOLD, 26));
        promptLabel.setForeground(GameWindow.COLOR_YELLOW);
        promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel hintLabel = new JLabel("Type your name in the field below and press  Submit.", SwingConstants.CENTER);
        hintLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
        hintLabel.setForeground(GameWindow.COLOR_DIMMED);
        hintLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        midContent.add(promptLabel);
        midContent.add(Box.createVerticalStrut(12));
        midContent.add(hintLabel);
        midContent.add(Box.createVerticalStrut(36));
        midContent.add(createHorizontalDivider());

        midSection.add(midContent);

        add(topSection, BorderLayout.NORTH);
        add(midSection, BorderLayout.CENTER);
    }
}