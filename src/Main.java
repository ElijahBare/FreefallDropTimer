import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * @author Elijah Bare
 */

class Panel extends JFrame {


    private static final JPanel panel = new JPanel();
    private static final JButton button = new JButton("Start");
    public static final JTextArea result = new JTextArea();


    public Panel() {

        button.addActionListener(new TimerActionListener());

        result.setText("Waiting....");

        panel.add(button);
        panel.add(result);

        add(panel);

        // Set the size of the frame and make it visible
        setSize(200, 100);

        setVisible(true);
    }

}

public class Main {
    public static SharedVars sharedVars = new SharedVars();

    public static void main(String[] args) {
        new Panel();
    }
}


class TimerActionListener implements ActionListener {

    public static double getDistance(double start, double stop) {
        double hangtime = (stop - start) / 1000;

        //distance = (vi + vf)t
        //         -------------
        //               2

        double finalVelocity = (hangtime * -9.8);

        return (finalVelocity * hangtime) / 2;
    }

    //https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (Objects.equals(button.getText(), "Start")) {
                button.setText("Stop");
                Main.sharedVars.setStop(System.currentTimeMillis());

            } else {
                button.setText("Start");
                Main.sharedVars.setStart(System.currentTimeMillis());
                System.out.println("Fell " + round(Math.abs(getDistance(Main.sharedVars.getStart(), Main.sharedVars.getStop())), 3) + " meters");
                Panel.result.setText("Fell " + round(Math.abs(getDistance(Main.sharedVars.getStart(), Main.sharedVars.getStop())), 3) + " meters");
            }
        }
    }
}


class SharedVars {
    private long start;
    private long stop;

    public long getStart() {
        return start;
    }

    public long getStop() {
        return stop;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public void setStop(long stop) {
        this.stop = stop;
    }
}

