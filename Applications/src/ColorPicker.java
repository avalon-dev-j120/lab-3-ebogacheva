import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Dictionary;


public class ColorPicker extends JFrame implements ChangeListener {

    private JPanel color;

    private JSlider sliderRed;
    private JSlider sliderGreen;
    private JSlider sliderBlue;

    public ColorPicker()  {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);
        setSize(600, 250);
        setTitle("Color Picker");
        Font labelFont = new Font("Arial", Font.PLAIN, 12);

        Container container = getContentPane();
        GridBagLayout layout = new GridBagLayout();
        container.setLayout(layout);
        ToolTipManager.sharedInstance().setInitialDelay(100);

        // Initialization of the ColorPanel
        {
            color = new JPanel();
            color.setPreferredSize(new Dimension(100, 100));
            color.setBackground(new Color(125, 125, 125));
            toolTipColorText();
            Insets insets = new Insets(2,2,2,2);
            addComponentToContainer(color, container, 0,0,3,2, 1, 1, insets);
        }

        // Initialization of the LabelSet
        {
            JLabel labelRed = new JLabel("Red:");
            labelRed.setFont(labelFont);
            JLabel labelGreen = new JLabel("Green:");
            labelGreen.setFont(labelFont);
            JLabel labelBlue = new JLabel("Blue:");
            labelBlue.setFont(labelFont);

            JPanel labelSet = new JPanel();
            GridLayout labelSetLayout = new GridLayout(3, 1);
            labelSet.setLayout(labelSetLayout);
            labelSet.add(labelRed);
            labelSet.add(labelGreen);
            labelSet.add(labelBlue);

            Insets insets = new Insets(0, 5, 25, 0);
            addComponentToContainer(labelSet, container, 4,0,1,2, 0.2, 1, insets);
        }

        // Initialization of the Slider Set
        {
            sliderRed = new JSlider(0, 255,125);
            sliderRed.setMajorTickSpacing(17);
            sliderRed.setPaintLabels(true);
            sliderRed.setFont(labelFont);
            setSliderDictionary(sliderRed.getLabelTable());
            sliderRed.addChangeListener(this);
            sliderRed.setPaintTicks(true);

            sliderGreen = new JSlider(0, 255, 125);
            sliderGreen.setMajorTickSpacing(17);
            sliderGreen.setPaintLabels(true);
            sliderGreen.setFont(labelFont);
            setSliderDictionary(sliderGreen.getLabelTable());
            sliderGreen.addChangeListener(this);
            sliderGreen.setPaintTicks(true);

            sliderBlue = new JSlider(0, 255, 125);
            sliderBlue.setMajorTickSpacing(17);
            sliderBlue.setFont(labelFont);
            sliderBlue.setPaintLabels(true);
            setSliderDictionary(sliderBlue.getLabelTable());
            sliderBlue.setPaintTicks(true);
            sliderBlue.addChangeListener(this);

            JPanel sliderSet = new JPanel();
            GridLayout sliderSetLayout = new GridLayout(3, 1);
            sliderSet.setLayout(sliderSetLayout);
            sliderSet.add(sliderRed);
            sliderSet.add(sliderGreen);
            sliderSet.add(sliderBlue);

            Insets insets = new Insets(10,0,5,5);
            addComponentToContainer(sliderSet, container, 5,0,2,4,1,1,insets);
        }

        setVisible(true);
    }

    private void addComponentToContainer(JComponent component, Container container, int x, int y,
                                         int dx, int dy, double wx, double wy, Insets insets){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = dx;
        constraints.gridheight = dy;
        constraints.weightx = wx;
        constraints.weighty = wy;
        constraints.insets = insets;
        constraints.fill = GridBagConstraints.BOTH;

        container.add(component, constraints);
    }

    private void setSliderDictionary(Dictionary dictionary){
        int i = 17;
        while (i < 255) {
            dictionary.remove(i);
            i=i + 17;
        }
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
        return "#" + Integer.toHexString(curColor.getRed()) +
                Integer.toHexString(curColor.getGreen()) +
                Integer.toHexString(curColor.getBlue());

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