import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Dictionary;


public class ColorPicker extends JFrame implements ChangeListener {

    private JPanel color;

    private JLabel labelRed;
    private JLabel labelGreen;
    private JLabel labelBlue;
    private JPanel labelSet;

    private JSlider sliderRed;
    private JSlider sliderGreen;
    private JSlider sliderBlue;
    private JPanel sliderSet;

    private final Font labelFont = new Font("Arial", Font.PLAIN, 12);


    public ColorPicker()  {


        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);
        setSize(600, 200);
        setTitle("Color Picker");

        Container container = getContentPane();
        GridBagLayout layout = new GridBagLayout();
        container.setLayout(layout);
        ToolTipManager.sharedInstance().setInitialDelay(100);

        // Initialization of the ColorPanel
        {
            GridBagConstraints constraintsColor = new GridBagConstraints();
            constraintsColor.gridx = 0;
            constraintsColor.gridy = 0;
            constraintsColor.gridwidth = 3;
            constraintsColor.gridheight = 2;
            constraintsColor.weightx = 1;
            constraintsColor.weighty = 1;
            constraintsColor.insets = new Insets(5,5,5,5);
            constraintsColor.fill = GridBagConstraints.BOTH;

            color = new JPanel();
            color.setPreferredSize(new Dimension(100, 100));
            color.setBackground(new Color(125, 125, 125));

            toolTipColorText();

            container.add(color, constraintsColor);
        }

        // Initialization of the LabelSet
        {

            labelRed = new JLabel("Red:");
            labelRed.setFont(labelFont);
            labelGreen = new JLabel("Green:");
            labelGreen.setFont(labelFont);
            labelBlue = new JLabel("Blue:");
            labelBlue.setFont(labelFont);

            labelSet = new JPanel();
            GridLayout labelSetLayout = new GridLayout(3, 1);
            labelSet.setLayout(labelSetLayout);
            labelSet.add(labelRed);
            labelSet.add(labelGreen);
            labelSet.add(labelBlue);

            GridBagConstraints constraintsLabelSet = new GridBagConstraints();
            constraintsLabelSet.gridx = 4;
            constraintsLabelSet.gridy = 0;
            constraintsLabelSet.gridwidth = 1;
            constraintsLabelSet.gridheight = 2;
            constraintsLabelSet.weightx = 0.2;
            constraintsLabelSet.weighty = 1;
            constraintsLabelSet.insets = new Insets(0, 5, 25, 0);
            constraintsLabelSet.anchor = GridBagConstraints.LINE_START;
            constraintsLabelSet.fill = GridBagConstraints.BOTH;

            container.add(labelSet, constraintsLabelSet);
        }

        // Initialization of the Slider Set
        {
            sliderRed = new JSlider(0, 255,125);
            sliderRed.setMajorTickSpacing(17);
            sliderRed.setPaintLabels(true);
            sliderRed.setFont(labelFont);
            Dictionary dictionary = sliderRed.getLabelTable();
            int i = 17;
            while (i < 255){
                dictionary.remove(i);
                i = i + 17;
            }
            sliderRed.addChangeListener(this);
            sliderRed.setPaintTicks(true);
            sliderGreen = new JSlider(0, 255, 125);
            sliderGreen.setMajorTickSpacing(17);
            sliderGreen.setPaintLabels(true);
            sliderGreen.setFont(labelFont);
            Dictionary dictionary2 = sliderGreen.getLabelTable();
            int i2 = 17;
            while (i2 < 255){
                dictionary2.remove(i2);
                i2 = i2 + 17;
            }
            sliderGreen.addChangeListener(this);
            sliderGreen.setPaintTicks(true);
            sliderBlue = new JSlider(0, 255, 125);
            sliderBlue.setMajorTickSpacing(17);
            sliderBlue.setFont(labelFont);
            sliderBlue.setPaintLabels(true);
            Dictionary dictionary3 = sliderBlue.getLabelTable();
            int i3 = 17;
            while (i3 < 255){
                dictionary3.remove(i3);
                i3 = i3 + 17;
            }
            sliderBlue.setPaintTicks(true);
            sliderBlue.addChangeListener(this);

            sliderSet = new JPanel();
            GridLayout sliderSetLayout = new GridLayout(3, 1);
            sliderSet.setLayout(sliderSetLayout);
            sliderSet.add(sliderRed);
            sliderSet.add(sliderGreen);
            sliderSet.add(sliderBlue);

            GridBagConstraints constraintsSliderSet = new GridBagConstraints();
            constraintsSliderSet.gridx = 5;
            constraintsSliderSet.gridy = 0;
            constraintsSliderSet.gridheight = 2;
            constraintsSliderSet.gridwidth = 4;
            constraintsSliderSet.weightx = 1;
            constraintsSliderSet.weighty = 1;
            constraintsSliderSet.insets = new Insets(10,0,5,5);
            constraintsSliderSet.fill = GridBagConstraints.BOTH;

            container.add(sliderSet, constraintsSliderSet);


        }

        setVisible(true);

    }
    @Override
    public void stateChanged(ChangeEvent event){
        if(event.getSource() == sliderRed || event.getSource() == sliderBlue || event.getSource() == sliderGreen){
            color.setBackground(new Color(sliderRed.getValue(), sliderGreen.getValue(), sliderBlue.getValue()));
        }
        copyHexColorToClipboard(getCurrentHexColor());

        toolTipColorText();

    }

    private String getCurrentHexColor(){
        Color curColor = color.getBackground();
        return "#" + Integer.toHexString(curColor.getRed()) + Integer.toHexString(curColor.getGreen()) + Integer.toHexString(curColor.getBlue());

    }

    private void toolTipColorText(){

        color.setToolTipText(getCurrentHexColor());
    }

    private void copyHexColorToClipboard(String hexColorText){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        StringSelection selection = new StringSelection(hexColorText);
        clipboard.setContents(selection, selection);
    }

}