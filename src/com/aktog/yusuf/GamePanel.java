package com.aktog.yusuf;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class GamePanel extends JPanel {
    final static int PANEL_WIDTH = 800;
    final static int PANEL_HEIGHT = 600;
    JPanel hoursPanel;
    JPanel secondsPanel;
    ArrayList<JPanel> hourInnerPanels;
    ArrayList<JPanel> secondsInnerPanels;

    final String zero = "TTTTT" + "TFFFT" + "TFFFT" + "TFFFT" + "TTTTT";
    final String one = "FFTFF" + "FTTFF" + "FFTFF" + "FFTFF" + "FTTTF";
    final String two = "FTTTF" + "FFFTF" + "FTTTF" + "FTFFF" + "FTTTF";
    final String three = "FTTTF" + "FFFTF" + "FTTTF" + " FFTFF" + "TTTFF";
    final String four = "TFFFT" + "TFFFT" + "TTTTT" + "FFFFT" + "FFFFT";
    final String five = takeMirrorOfClockStr(two);
    final String six = "FTTTT" + "FTFFF" + "FTTTT" + "FTFFT" + "FTTTT";
    final String seven = "TTTTT" + "FFFFT" + "FFFFT" + "FFFFT" + "FFFFT";
    final String eight = "TTTTT" + "TFFFT" + "TTTTT" + "TFFFT" + "TTTTT";
    final String nine = "FTTTT" + "FTFFT"+ "FTTTT" + "FFFFT" + "FTTTT";

    final ArrayList<String> numberRepresentetations =  new ArrayList<>(Arrays.asList(zero, one, two, three, four, five, six, seven, eight, nine));


    final static int GRID_SIZE = 25;

    final void loadPreferences() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.setLayout(null);
        hoursPanel = new JPanel();
        secondsPanel = new JPanel();
        hoursPanel.setLocation(75, 50);
        secondsPanel.setLocation(PANEL_WIDTH / 2 + 50, 50);

        hoursPanel.setSize(PANEL_WIDTH / 2 - 75, PANEL_HEIGHT - 100);
        secondsPanel.setSize(PANEL_WIDTH / 2 - 100, PANEL_HEIGHT - 100);

        hoursPanel.setBackground(Color.gray);
        secondsPanel.setBackground(Color.gray);

        int dimension = (int) Math.sqrt(GRID_SIZE);

        hoursPanel.setLayout(new GridLayout(dimension, dimension, 10, 10));
        secondsPanel.setLayout(new GridLayout(dimension, dimension, 10, 10));

        hourInnerPanels = new ArrayList<>();
        secondsInnerPanels = new ArrayList<>();

        for (int i = 0; i < GRID_SIZE; i++) {

            JPanel panel = new JPanel();
            panel.setBackground(Color.gray);
            hourInnerPanels.add(panel);
            hoursPanel.add(panel);

            panel = new JPanel();
            panel.setBackground(Color.gray);
            secondsInnerPanels.add(panel);
            secondsPanel.add(panel);

        }

/*        changeClock(zero, hourInnerPanels);
        changeClock(zero, secondsInnerPanels);*/

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(true){

                    i++;
                    if(i == 10){
                        i = 0;
                    }


                    //changeClock(numberRepresentetations.get(i),hourInnerPanels);
                    changeClock(numberRepresentetations.get(i),secondsInnerPanels);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    resetClock();
                    //repaint();

                }

            }
        }).start();
        this.add(hoursPanel);
        this.add(secondsPanel);

    }

    public void resetClock(){
        for (int i = 0; i < GRID_SIZE; i++) {
            hourInnerPanels.get(i).setBackground(Color.gray);
            secondsInnerPanels.get(i).setBackground(Color.gray);


        }
    }
    public void changeClock(String clockStr, ArrayList<JPanel> panels) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (clockStr.charAt(i) == 'T') {
                panels.get(i).setBackground(Color.blue);
            }

        }

    }

    public String takeMirrorOfClockStr(String clockStr) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < Math.sqrt(GRID_SIZE); i++) {
            int begin = GRID_SIZE - (i + 1) * 5;
            int end = GRID_SIZE - (i + 1) * 5 + 5;
            System.out.println("begin: " + begin + " end: " + end);
            String part = clockStr.substring(begin, end);
            System.out.println(part);
            str.append(part);
        }
        return str.toString();
    }

    public GamePanel() {
        loadPreferences();
    }
}