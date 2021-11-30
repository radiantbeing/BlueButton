package gui;

import boardgamecafe.BoardGameCafe;
import boardgamecafe.Game;
import gui.template.BasicButton;
import gui.template.BasicLabel;
import gui.template.BasicPanel;
import gui.template.Template;
import mgr.Manageable;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameSelectWindow extends Template {
    ArrayList<String> fileNameList;
    JLabel imageLabel;
    JScrollPane scrolledTable;
    JTable table;
    BasicLabel locationLabel;
    BasicLabel codeLabel;
    BasicLabel conditionLabel;
    Game selectedGame;
    BasicPanel viewRecommendGamePanel;
    BasicButton decideButton;

    @Override
    public void addComponents() {
        // Initialize
        setLayout(null);

        // About Table
        imageLabel = new JLabel();
        addFileName();
        setTable();
        setImageLabel(imageLabel);
        add(scrolledTable);

        setRightTopPanel();
        setRightBottomPanel();

        // About Prev Button
        BasicButton prevButton = new BasicButton("메뉴");
        prevButton.setBounds(160, 580, 320, 40);
        add(prevButton);
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.sampleOptionWindow);
            }
        });

        // About Decide Button
        decideButton = new BasicButton("결정");
        decideButton.setBounds(800, 580, 320, 40);
        decideButton.setBackground(new Color(121, 117, 117));
        decideButton.setEnabled(false);
        add(decideButton);
        decideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedGame == null) {
                    JOptionPane.showMessageDialog(null, "게임을 선택해주세요");
                    return;
                }
                // Timer가 끝까지 흐르면 gameMgr.mList에 돌려놓는 코드 추가 예정
                MainGUI.changeWindow(MainGUI.sampleOptionWindow);
                MainGUI.sampleOptionWindow.grayScaleButton(MainGUI.sampleOptionWindow.gameButton);
                MainGUI.sampleOptionWindow.decideEnablePayButton();
            }
        });
    }


    private void setRightTopPanel() {
        // === RightTop Panel ===
        // About RightTop Panel
        BasicPanel rightTopPanel = new BasicPanel();
        rightTopPanel.setLayout(null);
        rightTopPanel.setBounds(800, 90, 320, 120);
        add(rightTopPanel);
        rightTopPanel.add(imageLabel);

        // About underImg Panel
        BasicPanel underImgPanel = new BasicPanel();
        underImgPanel.setLayout(null);
        underImgPanel.setBackground(new Color(30, 31, 33));
        underImgPanel.setBounds(10, 10, 100, 100);
        rightTopPanel.add(underImgPanel);

        // About underImg Label
        BasicLabel underImgLabel = new BasicLabel("IMG", SwingConstants.CENTER);
        underImgLabel.setBounds(25, 40, 50, 20);
        underImgPanel.add(underImgLabel);

        // About Code Labels
        BasicLabel codeTitleLabel = new BasicLabel("고유번호", SwingConstants.RIGHT);
        codeTitleLabel.setBounds(130, 10, 60, 20);
        rightTopPanel.add(codeTitleLabel);

        codeLabel = new BasicLabel();
        codeLabel.setBounds(200, 10, 60, 20);
        codeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        rightTopPanel.add(codeLabel);

        // About Location Labels
        BasicLabel locationTitleLabel = new BasicLabel("위치", SwingConstants.RIGHT);
        locationTitleLabel.setBounds(130, 50, 60, 20);
        rightTopPanel.add(locationTitleLabel);

        locationLabel = new BasicLabel();
        locationLabel.setBounds(200, 50, 60, 20);
        locationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        rightTopPanel.add(locationLabel);

        // About Condition Labels
        BasicLabel conditionTitleLabel = new BasicLabel("상태", SwingConstants.RIGHT);
        conditionTitleLabel.setBounds(130, 90, 60, 20);
        rightTopPanel.add(conditionTitleLabel);

        conditionLabel = new BasicLabel();
        conditionLabel.setBounds(200, 90, 60, 20);
        conditionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        rightTopPanel.add(conditionLabel);
        //=======================
    }

    private void setRightBottomPanel() {
        // === RightBottom Panel ===
        // About RightBottom Panel
        BasicPanel rightBottomPanel = new BasicPanel();
        rightBottomPanel.setLayout(null);
        rightBottomPanel.setBounds(800, 220, 320, 250);
        add(rightBottomPanel);

        // About recommendGameTitle Label
        BasicLabel recommendGameTitleLabel = new BasicLabel("추천 게임", SwingConstants.CENTER);
        recommendGameTitleLabel.setOpaque(true);
        recommendGameTitleLabel.setBackground(new Color(0, 120, 242));
        recommendGameTitleLabel.setFontAttribute(15);
        recommendGameTitleLabel.setBounds(0, 0, 320, 40);
        rightBottomPanel.add(recommendGameTitleLabel);

        // About viewRecommendGame Panel
        viewRecommendGamePanel = new BasicPanel();
        viewRecommendGamePanel.setLayout(new GridLayout(2, 2));
        viewRecommendGamePanel.setBackground(new Color(30, 31, 33));
        viewRecommendGamePanel.setBounds(10, 50, 300, 190);
        rightBottomPanel.add(viewRecommendGamePanel);

    }


    void setTable() {
        String header[] = {"", "이름", "난이도", "종류"};
        DefaultTableModel model = new DefaultTableModel(header, 0);    //header추가, 행은 0개 지정

        table = new JTable(model);

        // Design table
        table.setFont(new Font("NanumGothic", Font.PLAIN, 12));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(61, 62, 65));
        table.setDefaultEditor(Object.class, null); // Not Editable

        // Table sorter
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        // Column Dragging 비허용
        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);

        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        addRow(model);
        resizeColumnWidth(table);

        scrolledTable = new JScrollPane(table);    // 스크롤 될 수 있도록 JScrollPane 적용
        scrolledTable.setBackground(new Color(41, 42, 45));
        scrolledTable.setBounds(160, 70, 320, 400);
        scrolledTable.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //너무 붙어있어서 가장자리 띄움(padding)
        MyMouseAdapter listener = new MyMouseAdapter();
        table.addMouseListener(listener);
    }

    class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            imageLabel.setOpaque(true);
            int selectedRow = table.getSelectedRow();
            String cell = (String) table.getValueAt(selectedRow, 0);
            selectedGame = (Game) BoardGameCafe.gameMgr.find(cell);
            // 현재 로그인 중인 사용자에게 게임 넘겨줌
            LogInWindow.getNowLoginMember().setPlayingGame(selectedGame);
            // 결정 버튼 활성화
            decideButton.setBackground(new Color(0, 120, 242));
            decideButton.setEnabled(true);

            imageLabel.setIcon(getMatchedImage(selectedGame.name, 100));

            // 고유번호, 위치, 상태 라벨 텍스트 설정
            codeLabel.setText(String.format("%s", selectedGame.code));
            locationLabel.setText(String.format("%s", selectedGame.location));
            conditionLabel.setText(String.format("%s", selectedGame.condition));

            viewRecommendGamePanel.removeAll();
            ArrayList<Game> recommendGames = getRecommendGames(selectedGame);
            for (Game g : recommendGames) {
                JButton recommendGameButton = new JButton(g.name, getMatchedImage(g.name, 50));
                recommendGameButton.setBorderPainted(false);
                recommendGameButton.setContentAreaFilled(false);
                recommendGameButton.setFocusPainted(false);
                recommendGameButton.setOpaque(false);
                recommendGameButton.setVerticalTextPosition(JButton.CENTER);
                recommendGameButton.setForeground(Color.WHITE);
                recommendGameButton.setFont(new Font("NanumGothic", Font.PLAIN, 12));
                viewRecommendGamePanel.add(recommendGameButton);
                recommendGameButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        table.setRowSelectionInterval(g.code, g.code);
                        // 선택한 Row로 스크롤 이동
                        table.scrollRectToVisible(table.getCellRect(g.code, 0, true));
                        MyMouseAdapter.this.mouseClicked(e);
                    }
                });
            }
        }
    }

    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 50; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    void addRow(DefaultTableModel model) {
        Object[] row;
        for (int i = 0; i < BoardGameCafe.gameMgr.getList().size(); i++) {
            Game g = (Game) BoardGameCafe.gameMgr.getList().get(i);
            row = new Object[]{new String("" + g.code), new String(g.name), new String(g.difficulty),
                    new String(g.genre)};
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
        imageLabel.setBounds(10, 10, 100, 100);
        imageLabel.setOpaque(false);
    }

    // 이름고 매치되는 이미지 반환
    ImageIcon getMatchedImage(String gameName, int size) {
        return MainGUI.scaleImageIcon("imgs/games/" +
                fileNameList.get(fileNameList.indexOf(gameName + ".png")), size, size);
    }

    ArrayList<Game> getRecommendGames(Game game) {
        ArrayList<Game> recommendList = new ArrayList<>();
        ArrayList<Game> returnList = new ArrayList<>();
        for (Manageable m : BoardGameCafe.gameMgr.getList()) {
            Game g = (Game) m;
            if (!game.name.equals(g.name) && game.genre.equals(g.genre) && !isExistGame(g, recommendList)) {
                recommendList.add(g);
            }
        }
        Collections.shuffle(recommendList);
        for (Game g : recommendList) {
            if (returnList.size() < 4)
                returnList.add(g);
        }
        return returnList;
    }

    boolean isExistGame(Game game, ArrayList<Game> gList) {
        for (Game o : gList) {
            if (game.name.equals(o.name))
                return true;
        }
        return false;
    }

    public void removeSelectedRows(JTable table){
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        int[] rows = table.getSelectedRows();
        for(int i=0;i<rows.length;i++){
            model.removeRow(rows[i]-i);
        }
    }
}
