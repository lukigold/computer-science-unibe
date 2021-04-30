package covidgame1;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Creates interactive tool for COVID game.
 * 
 * @author Kevin Stöckli
 * @author Lukas Ingold
 *
 */
public class CovidRenderer extends JPanel implements DocumentListener {
    private static final int TEXT_AREA_WIDTH = 160;

    private static final Dimension FRAME_SIZE = new Dimension(950 + TEXT_AREA_WIDTH, 910);
    private static final Dimension PREFERRED_IMAGE_SIZE = new Dimension(900, 900);

    // Initialize an empty environment
    private Environment environment = Environment.empty();

    /**
     * Create a text area using the given listener for any document changes.
     * The text area will be wrapped in a vertically scrollable pane.
     *
     * @param listener A document listener for any changes. Should not be {@code null}.
     * @return A scrollable text area
     */
    private static JScrollPane createTextArea(DocumentListener listener) {
        assert listener != null;

        var textArea = new JTextArea();
        textArea.getDocument().addDocumentListener(listener);
        textArea.setVisible(true);

        var scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));

        return scrollPane;
    }

    /**
     * Creates the right panel, featuring a small label and the given text area for the input program.
     *
     * @param textArea The text area for the input program
     * @return The right side panel
     */
    private static JPanel createRightPanel(JComponent textArea) {
        assert textArea != null;

        var right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

        var label = new JLabel("Your program:");
        label.setPreferredSize(new Dimension(TEXT_AREA_WIDTH, 20));
        right.add(label);

        textArea.setPreferredSize(new Dimension(TEXT_AREA_WIDTH, PREFERRED_IMAGE_SIZE.height));
        right.add(textArea);

        return right;
    }

    public static void main(String[] args) {
        // main window frame
        var frame = new JFrame("Covid-21");
        frame.setSize(FRAME_SIZE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        // left image
        var image = new CovidRenderer();
        image.setPreferredSize(PREFERRED_IMAGE_SIZE);
        panel.add(image);

        // right text area
        var textArea = createTextArea(image);
        var rightPanel = createRightPanel(textArea);
        panel.add(rightPanel);

        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        var size = getSize();
        var scale = new Dimension(size.width / Environment.SIZE, size.height / Environment.SIZE);
        var width = scale.width - 1;
        var height = scale.height - 1;

        g.setColor(new Color(0.95f, 0.95f, 0.95f));
        g.fillRect(0, 0, size.width, size.height);

        for (var i = 0; i < Environment.SIZE; i++) {
            for (var j = 0; j < Environment.SIZE; j++) {
                var area = environment.areas[i][j];

                var x = scale.width * i + 1;
                var y = scale.height * j + 1;

                g.setColor(area.toColor());
                g.fillRect(x, y, width, height);
            }
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        onChange(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        onChange(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        onChange(e);
    }

    /**
     * This method gets invoked on any document change. This means, that the input program changed and
     * we need to update our environment.
     *
     * @param e A document event. Should not be null.
     */
    private void onChange(DocumentEvent e) {
        assert e != null;

        var doc = e.getDocument();
        try {
            var program = doc.getText(0, doc.getLength());
            environment = Environment.createFrom(program);
            this.repaint();
        } catch (BadLocationException badLocationException) {
            // should not happen
            throw new RuntimeException(badLocationException);
        }
    }
}
