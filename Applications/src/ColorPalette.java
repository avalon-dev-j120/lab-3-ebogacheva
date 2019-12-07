import javax.swing.*;
import java.awt.*;

public class ColorPalette extends JFrame {

    private JPanel color;

    private JLabel labelRed;
    private JLabel labelGreen;
    private JLabel labelBlue;
    private JPanel labelSet;

    private JSlider sliderRed;
    private JSlider sliderGreen;
    private JSlider sliderBlue;
    private JPanel sliderSet;


    public ColorPalette() {


        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);
        setSize(600, 200);
        setTitle("Color Picker");

        Container container = getContentPane();
        GridBagLayout layout = new GridBagLayout();
        container.setLayout(layout);

        // Initialization of the ColorPanel
        {
            GridBagConstraints constraintsColor = new GridBagConstraints();
            constraintsColor.gridx = 0;
            constraintsColor.gridy = 0;
            constraintsColor.weightx = 3;
            constraintsColor.weighty = 2;
            constraintsColor.insets = new Insets(5,5,5,5);
            constraintsColor.fill = GridBagConstraints.BOTH;

            color = new JPanel();
            color.setPreferredSize(new Dimension(100, 100));
            color.setBackground(new Color(23, 57, 88));

            container.add(color, constraintsColor);
        }

        // Initialization of the LabelSet
        {
            labelRed = new JLabel("Red:");
            labelGreen = new JLabel("Green:");
            labelBlue = new JLabel("Blue:");

            labelSet = new JPanel();
            GridLayout labelSetLayout = new GridLayout(3, 1);
            labelSet.setLayout(labelSetLayout);
            labelSet.add(labelRed);
            labelSet.add(labelGreen);
            labelSet.add(labelBlue);

            GridBagConstraints constraintsLabelSet = new GridBagConstraints();
            constraintsLabelSet.gridx = 4;
            constraintsLabelSet.gridy = 0;
            constraintsLabelSet.weightx = 0.5;
            constraintsLabelSet.weighty = 2;
            constraintsLabelSet.insets = new Insets(5, 5, 5, 5);
            constraintsLabelSet.anchor = GridBagConstraints.LINE_START;
            constraintsLabelSet.fill = GridBagConstraints.BOTH;

            container.add(labelSet, constraintsLabelSet);
        }

        // Initialization of the Slider Set
        {
            sliderRed = new JSlider(0, 255,125);
            sliderRed.setMajorTickSpacing(18);
            sliderRed.setPaintTicks(true);
            sliderGreen = new JSlider(0, 255, 125);
            sliderGreen.setMajorTickSpacing(18);
            sliderGreen.setPaintTicks(true);
            sliderBlue = new JSlider(0, 255, 125);
            sliderBlue.setMajorTickSpacing(18);
            sliderBlue.setPaintTicks(true);

            sliderSet = new JPanel();
            GridLayout sliderSetLayout = new GridLayout(3, 1);
            sliderSet.setLayout(sliderSetLayout);
            sliderSet.add(sliderRed);
            sliderSet.add(sliderGreen);
            sliderSet.add(sliderBlue);

            GridBagConstraints constraintsSliderSet = new GridBagConstraints();
            constraintsSliderSet.gridx = 5;
            constraintsSliderSet.gridy = 0;
            constraintsSliderSet.weightx = 4;
            constraintsSliderSet.weighty = 2;
            constraintsSliderSet.insets = new Insets(10,5,5,5);
            constraintsSliderSet.fill = GridBagConstraints.BOTH;

            container.add(sliderSet, constraintsSliderSet);


        }

        setVisible(true);


    }
}
