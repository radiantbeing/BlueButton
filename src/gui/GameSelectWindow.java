package gui;

import boardgamecafe.BoardGameCafe;
import boardgamecafe.Game;
import gui.template.Template;
import mgr.Manageable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GameSelectWindow extends Template {
    ArrayList<String> fileNameList;
    JLabel imageLabel;
    JTextField[] fields;
    JScrollPane scrolledTable;
    JTable table;

    @Override
    public void addComponents() {
        fields = new JTextField[6];
        imageLabel = new JLabel();
        setLayout(null);
        addFileName();
        setTable();
        setImageLabel(imageLabel);
        add(scrolledTable);
        add(imageLabel);
    }

    void setTable() {
        String header[] = {"이름", "난이도", "종류", "남은 개수"};
        DefaultTableModel model = new DefaultTableModel(header, 0);    //header추가, 행은 0개 지정

        table = new JTable(model);

        // Table sorter
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        // Column Dragging 비허용
        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);

        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        addRow(model);

        scrolledTable = new JScrollPane(table);    //스크롤 될 수 있도록 JScrollPane 적용
        scrolledTable.setBounds(20, 10, 700, 650);
        scrolledTable.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));//너무 붙어있어서 가장자리 띄움(padding)
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                imageLabel.setOpaque(true);
                int selectedRow = table.getSelectedRow();
                String cell = (String) table.getValueAt(selectedRow,0);
                Game game = (Game)BoardGameCafe.gameMgr.find(cell);
                ImageIcon imageIcon = getMatchedImage(game.name, 300);
                imageLabel.setIcon(imageIcon);
            }
        });
    }

    void addRow(DefaultTableModel model) {
        Object[] row;
        for (int i = 0; i < BoardGameCafe.gameMgr.getList().size(); i += 3) {
            Game g = (Game) BoardGameCafe.gameMgr.getList().get(i);

            row = new Object[]{new String(g.name), new String(g.difficulty),
                    new String(g.genre), new String("3")};
            model.addRow(row);
        }
    }

    void addFileName() {
        fileNameList = new ArrayList<>();
        File folder = new File("imgs/games/");
        String[] filenames = folder.list();
        for (String str : filenames) {
            fileNameList.add(str);
        }
    }

    void setImageLabel(JLabel imageLabel) {
        imageLabel.setBounds(new Rectangle(800, 200, 300, 300));
        imageLabel.setOpaque(false);
    }

    //이름고 매치되는 이미지 반환
    ImageIcon getMatchedImage(String gameName, int size){
        return MainGUI.scaleImageIcon("imgs/games/"+
                fileNameList.get(fileNameList.indexOf(gameName+".png")), size, size);
    }
}
