package gui;

import boardgamecafe.BoardGameCafe;
import boardgamecafe.Game;
import gui.template.Template;
import mgr.Manageable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GameSelectWindow extends Template {
    private JLabel imageLabel = new JLabel();
    private JTextField[] fields = new JTextField[6];
    private JScrollPane scrolledTable;
    private JTable table;
    List<String> fileList = new ArrayList<>();
    @Override
    public void addComponents() {
//        setLayout(null);
//        addFileName();
//        setTable();
//        setImageLabel(imageLabel);
//        add(scrolledTable);
//        add(imageLabel);
        File folder = new File("imgs/games");
        String[]filenames=folder.list();
        for (String str : filenames) {
            fileList.add(str);
        }
    }

    void setTable() {
        String header[] = {"이름", "난이도", "종류", "갯수"};
        DefaultTableModel model = new DefaultTableModel(header, 0);    //header추가, 행은 0개 지정

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        addRow(model);

        scrolledTable = new JScrollPane(table);    //스크롤 될 수 있도록 JScrollPane 적용
        scrolledTable.setBounds(20,20,1200,300);
        scrolledTable.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));//너무 붙어있어서 가장자리 띄움(padding)
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                imageLabel.setIcon(MainGUI.scaleImageIcon(fileList.get(selectedRow),300,300));
            }
        });
    }

    void addRow(DefaultTableModel model){
        Object [] row;
        for(int i=0;i<BoardGameCafe.gameMgr.getList().size();i+=3){
            Game g = (Game)BoardGameCafe.gameMgr.getList().get(i);
            row = new Object[] {new String(g.name), new String(g.difficulty),
                    new String(g.genre),new String("3")};
            model.addRow(row);
        }
    }

    void addFileName(){
        File folder = new File("imgs/games/");
        String[]filenames=folder.list();
        for (String str : filenames) {
            fileList.add(str);
        }
    }

    void setImageLabel(JLabel imageLabel){
        imageLabel.setBounds(new Rectangle(300,300,300,300));
        imageLabel.setOpaque(true);
    }
}
