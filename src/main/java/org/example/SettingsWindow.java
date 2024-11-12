package org.example;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 230;
    private static final int WINDOW_WIDTH = 350;
    private static final int WINDOW_POS_X = 780;
    private static final int WINDOW_POS_Y = 360;
    private static final String SELECTED_FIELD_SIZE = "Установленный размер поля: ";
    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;
    private static final String SELECTED_WIN_LENGTH = "Установленная длина: ";
    private static final int MIN_WIN_LENGTH = 3;

    JButton btnStart = new JButton("Start new game");
    SettingsWindow(GameWindow gameWindow) {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocation(WINDOW_POS_X, WINDOW_POS_Y);

        setLayout(new GridLayout(10,1));
        add(new JLabel("Выберите режим игры"));
        ButtonGroup groupButton = new ButtonGroup();
        JRadioButton pve = new JRadioButton("Человек против компьютера", true);
        JRadioButton pvp = new JRadioButton("Человек против человека",false);
        groupButton.add(pve);
        groupButton.add(pvp);
        add(pve);
        add(pvp);

        JLabel labelFieldSize = new JLabel(SELECTED_FIELD_SIZE + MIN_FIELD_SIZE);
        JLabel labelWinLength = new JLabel(SELECTED_WIN_LENGTH + MIN_WIN_LENGTH);
        JSlider sliderFieldSize = new JSlider(MIN_FIELD_SIZE,MAX_FIELD_SIZE,MIN_FIELD_SIZE);
        JSlider sliderWinLength = new JSlider(MIN_WIN_LENGTH,MAX_FIELD_SIZE,MIN_WIN_LENGTH);

        sliderFieldSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentFieldSize = sliderFieldSize.getValue();
                labelFieldSize.setText(SELECTED_FIELD_SIZE + currentFieldSize);
                sliderWinLength.setMaximum(currentFieldSize);
            }
        });

        sliderWinLength.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelWinLength.setText(SELECTED_WIN_LENGTH + sliderWinLength.getValue());
            }
        });

        add(new JLabel("Выберите размеры поля"));
        add(labelFieldSize);
        add(sliderFieldSize);
        add(new JLabel("Выберите длину для победы"));
        add(labelWinLength);
        add(sliderWinLength);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int gameMode;
                if (pve.isSelected()) {
                    gameMode = Map.MODE_PvE;
                } else if (pvp.isSelected()) {
                    gameMode = Map.MODE_PvP;
                } else {
                    throw new RuntimeException("Unknown game mode");
                }
                gameWindow.startNewGame(gameMode, sliderFieldSize.getValue(), sliderFieldSize.getValue(),
                        sliderWinLength.getValue());
                setVisible(false);
            }
        });
        add(btnStart);
        setVisible(true);
    }
}
