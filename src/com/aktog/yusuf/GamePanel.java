package com.aktog.yusuf;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class GamePanel extends JPanel {
    final static int PANEL_WIDTH = 1800;
    final static int PANEL_HEIGHT = 800;
    static int panelCount = 0;

    JPanel secondsFirstDigitOuterPanel;
    JPanel secondsSecondDigitOuterPanel;
    JPanel minutesFirstDigitOuterPanel;
    JPanel minutesSecondDigitOuterPanel;
    JPanel hoursFirstDigitOuterPanel;
    JPanel hoursSecondDigitOuterPanel;

    ArrayList<JPanel> secondsFirstDigitInnerPanels;
    ArrayList<JPanel> secondsSecondDigitInnerPanels;
    ArrayList<JPanel> minutesFirstDigitInnerPanels;
    ArrayList<JPanel> minutesSecondDigitInnerPanels;
    ArrayList<JPanel> hoursFirstDigitInnerPanels;
    ArrayList<JPanel> hoursSecondDigitInnerPanels;


    final String zero = "FTTTF" + "FTFTF" + "FTFTF" + "FTFTF" + "FTTTF";
    final String one = "FFTFF" + "FTTFF" + "FFTFF" + "FFTFF" + "FTTTF";
    final String two = "FTTTF" + "FFFTF" + "FTTTF" + "FTFFF" + "FTTTF";
    final String three = "FTTTF" + "FFFTF" + "FTTTF" + " FFTFF" + "TTTFF";
    final String four = "FTFTF" + "FTFTF" + "FTTTF" + "FFFTF" + "FFFTF";
    final String five = takeMirrorOfClockStr(two);
    final String six = "FTTTF" + "FTFFF" + "FTTTF" + "FTFTF" + "FTTTF";
    final String seven = "FTTTF" + "FFFTF" + "FFFTF" + "FFFTF" + "FFFTF";
    final String eight = "FTTTF" + "FTFTF" + "FTTTF" + "FTFTF" + "FTTTF";
    final String nine = "FTTTF" + "FTFTF" + "FTTTF" + "FFFTF" + "FTTTF";

    final ArrayList<String> numberRepresentations = new ArrayList<>(Arrays.asList(zero, one, two, three, four, five, six, seven, eight, nine));


    final static int GRID_SIZE = 25;

    final void loadPreferences() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.setLayout(null);

        hoursFirstDigitOuterPanel = new JPanel();
        hoursFirstDigitInnerPanels = new ArrayList<>();

        hoursSecondDigitOuterPanel = new JPanel();
        hoursSecondDigitInnerPanels = new ArrayList<>();

        minutesFirstDigitOuterPanel = new JPanel();
        minutesFirstDigitInnerPanels = new ArrayList<>();

        minutesSecondDigitOuterPanel = new JPanel();
        minutesSecondDigitInnerPanels = new ArrayList<>();

        secondsFirstDigitOuterPanel = new JPanel();
        secondsFirstDigitInnerPanels = new ArrayList<>();

        secondsSecondDigitOuterPanel = new JPanel();
        secondsSecondDigitInnerPanels = new ArrayList<>();

        initPanel(hoursFirstDigitOuterPanel, hoursFirstDigitInnerPanels,25);
        startThread(360, 2, hoursFirstDigitInnerPanels);

        initPanel(hoursSecondDigitOuterPanel, hoursSecondDigitInnerPanels,275);
        startThread(36, 10, hoursSecondDigitInnerPanels);

        initPanel(minutesFirstDigitOuterPanel, minutesFirstDigitInnerPanels,625);
        startThread(6, 6, minutesFirstDigitInnerPanels);

        initPanel(minutesSecondDigitOuterPanel, minutesSecondDigitInnerPanels,875);
        startThread(0.6, 10, minutesSecondDigitInnerPanels);

        initPanel(secondsFirstDigitOuterPanel, secondsFirstDigitInnerPanels,1225);
        startThread(0.1, 6, secondsFirstDigitInnerPanels);

        initPanel(secondsSecondDigitOuterPanel, secondsSecondDigitInnerPanels,1475);
        startThread(0.01, 10, secondsSecondDigitInnerPanels);


        this.add(hoursFirstDigitOuterPanel);
        this.add(hoursSecondDigitOuterPanel);
        this.add(minutesFirstDigitOuterPanel);
        this.add(minutesSecondDigitOuterPanel);
        this.add(secondsFirstDigitOuterPanel);
        this.add(secondsSecondDigitOuterPanel);


    }

    public void initPanel(JPanel panel, ArrayList<JPanel> panels, int x) {
        int dimension = (int) Math.sqrt(GRID_SIZE);

        panel.setLocation(x, 3*PANEL_HEIGHT/8 );
        panel.setSize(PANEL_WIDTH / 6, PANEL_HEIGHT / 4);
        panel.setBackground(Color.gray);
        panel.setLayout(new GridLayout(dimension, dimension, 10, 10));


        for (int i = 0; i < GRID_SIZE; i++) {

            JPanel innerPanel = new JPanel();
            innerPanel.setBackground(Color.gray);
            panels.add(innerPanel);
            panel.add(innerPanel);

        }
        panelCount++;
    }

    public void startThread(double delay, int upperLimit, ArrayList<JPanel> innerPanels) {
        new Thread(() -> {
            int i = 0;
            while (true) {
                changeClock(numberRepresentations.get(i), innerPanels);

                try {
                    Thread.sleep((long) (delay * 1000L));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resetInnerPanels(innerPanels);
                i++;
                if (i == upperLimit) {
                    i = 0;
                }

            }

        }).start();
    }

    public void resetInnerPanels(ArrayList<JPanel> panels) {
        for (int i = 0; i < GRID_SIZE; i++) {
            panels.get(i).setBackground(Color.gray);


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
            //System.out.println("begin: " + begin + " end: " + end);
            String part = clockStr.substring(begin, end);
            //System.out.println(part);
            str.append(part);
        }
        return str.toString();
    }

    public GamePanel() {
        loadPreferences();
    }
}