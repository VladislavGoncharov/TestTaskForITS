package com.vladislavgoncharov.testtaskforits.utilits;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.WindowEvent;

public class DialogBoxForViewingDataFromDatabase extends JPanel {
    private static JFrame frame;

    public DialogBoxForViewingDataFromDatabase() {
        super(new GridLayout(1, 0));

        // заменить данными из таблицы в бд
        final String[] columnNames = {
                "First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};

        final Object[][] data = {{
            "000First Name",
            "Last Name",
            "Sport",
            "# of Years",
            "Vegetarian"}, {
            "111First Name",
            "Last Name",
            "Sport",
            "# of Years",
            "Vegetarian"}
        };


        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 250));
        //отцентровка текста в ячейках
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.getSelectionModel()
                .addListSelectionListener(new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent e) {
                        if (e.getValueIsAdjusting()) return; // убрать дубликат сообщения
                        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                        // беру номер строчки и в этом же листе беру данные и работаю с ними
                        if (lsm.isSelectionEmpty()) {
                            System.out.println("No rows are selected.");
                        } else {
                            int selectedRow = lsm.getMinSelectionIndex();
                            System.out.println("Row " + selectedRow
                                    + " is now selected.");
                            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));


                        }
                    }
                });

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    public static void createAndShowGUI() {

        frame = new JFrame("Выберите один вариант");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(300, 200);

        DialogBoxForViewingDataFromDatabase newContentPane = new DialogBoxForViewingDataFromDatabase();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
