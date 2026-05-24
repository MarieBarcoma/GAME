package noescape;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CourseSelectPanel extends BasePanel {
    private final String playerName;
    private final ActionListener onComputerScienceSelected;
    private final ActionListener onNursingSelected;

    public CourseSelectPanel(String playerName, ActionListener onComputerScienceSelected, ActionListener onNursingSelected) {
        this.playerName = playerName;
        this.onComputerScienceSelected = onComputerScienceSelected;
        this.onNursingSelected = onNursingSelected;
        initializeContent();
    }

    @Override
    protected void initializeContent() {
        setLayout(new BorderLayout());

        JPanel topSection = new JPanel();
        topSection.setOpaque(false);
        topSection.setLayout(new BoxLayout(topSection, BoxLayout.Y_AXIS));
        topSection.setBorder(new EmptyBorder(44, 60, 20, 60));

        JLabel welcomeLabel = new JLabel("Welcome,  " + playerName + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Consolas", Font.BOLD, 40));
        welcomeLabel.setForeground(GameWindow.COLOR_YELLOW);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Choose your course to begin the time loop.", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Calibri", Font.ITALIC, 17));
        subtitleLabel.setForeground(GameWindow.COLOR_DIMMED);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        topSection.add(welcomeLabel);
        topSection.add(Box.createVerticalStrut(10));
        topSection.add(subtitleLabel);

        JPanel midSection = new JPanel(new GridBagLayout());
        midSection.setOpaque(false);
        midSection.setBorder(new EmptyBorder(0, 80, 0, 80));

        JPanel cards = new JPanel();
        cards.setOpaque(false);
        cards.setLayout(new BoxLayout(cards, BoxLayout.Y_AXIS));

        cards.add(createHorizontalDivider());
        cards.add(Box.createVerticalStrut(32));
        cards.add(createCenteredLabel("Each course has unique puzzles, scenes, and outcomes.", GameWindow.COLOR_DIMMED, 15, Font.PLAIN));
        cards.add(Box.createVerticalStrut(32));

        cards.add(createCourseCard(
            "💻  Computer Science",
            new String[]{
                "+20 bonus seconds added to your timer",
                "3 attempts per room before the loop tightens",
                "Case-insensitive answers in most rooms"
            },
            GameWindow.COLOR_CYAN,
            onComputerScienceSelected
        ));
        cards.add(Box.createVerticalStrut(20));

        cards.add(createCourseCard(
            "🏥  Nursing",
            new String[]{
                "No time bonus — rely on skill alone",
                "5 attempts per room — more chances to escape",
                "Medical-themed puzzles across all 4 rooms"
            },
            new Color(255, 130, 180),
            onNursingSelected
        ));
        cards.add(Box.createVerticalStrut(32));
        cards.add(createHorizontalDivider());

        midSection.add(cards);

        JPanel bottomSection = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomSection.setOpaque(false);
        bottomSection.setBorder(new EmptyBorder(14, 0, 28, 0));

        JLabel noteLabel = new JLabel("Click a course button above to begin — no Submit needed.");
        noteLabel.setFont(new Font("Calibri", Font.ITALIC, 14));
        noteLabel.setForeground(GameWindow.COLOR_DIMMED);
        bottomSection.add(noteLabel);

        add(topSection, BorderLayout.NORTH);
        add(midSection, BorderLayout.CENTER);
        add(bottomSection, BorderLayout.SOUTH);
    }

    private JPanel createCourseCard(String title, String[] details, Color accent, ActionListener onClick) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(accent.getRed(), accent.getGreen(), accent.getBlue(), 18));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        card.setOpaque(false);
        card.setLayout(new BorderLayout(20, 0));
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(accent, 1, true),
            new EmptyBorder(20, 28, 20, 28)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130));
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Consolas", Font.BOLD, 20));
        titleLabel.setForeground(accent);

        JPanel detailPanel = new JPanel();
        detailPanel.setOpaque(false);
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
        for (String detail : details) {
            JLabel d = new JLabel("→  " + detail);
            d.setFont(new Font("Calibri", Font.PLAIN, 14));
            d.setForeground(GameWindow.COLOR_TEXT);
            detailPanel.add(d);
            detailPanel.add(Box.createVerticalStrut(3));
        }

        card.add(titleLabel, BorderLayout.WEST);
        card.add(detailPanel, BorderLayout.CENTER);

        card.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent e) {
                onClick.actionPerformed(null);
            }
            @Override public void mouseEntered(java.awt.event.MouseEvent e) {
                card.repaint();
            }
        });

        return card;
    }
}