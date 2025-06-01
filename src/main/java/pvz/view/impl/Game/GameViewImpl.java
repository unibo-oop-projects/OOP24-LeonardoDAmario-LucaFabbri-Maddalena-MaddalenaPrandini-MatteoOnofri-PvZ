package pvz.view.impl.Game;

import pvz.controller.api.ViewListener;
import pvz.utilities.GameEntity;
import pvz.view.api.GameView;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class GameViewImpl extends JPanel implements GameView {

    private final GameToolBar toolBar = new GameToolBar();
    private final DrawPanel drawPanel;
    private final GridPanel gridPanel;
    private final JLayeredPane layeredPane = new JLayeredPane();

    private ViewListener listener;


    public GameViewImpl(int width, int height) {
        double scaling;
        //Formula: scaling = 0.8 * (width / 640.0)
        switch (width) {
            case 640 -> scaling = 0.8;         // 640×480 (VGA)
            case 800 -> scaling = 1.0;         // 800×600 (SVGA)
            case 1024 -> scaling = 1.28;       // 1024×768 (XGA)
            case 1152 -> scaling = 1.44;       // 1152×864 (SXGA-)
            case 1600 -> scaling = 2.0;        // 1600×1200 (UXGA)
            case 2048 -> scaling = 2.56;       // 2048×1536 (QXGA)
            default -> scaling = 1.5;
        }





        this.drawPanel = new DrawPanel(scaling);
        this.gridPanel = new GridPanel(scaling);
        layeredPane.setLayout(new OverlayLayout(layeredPane));

        layeredPane.setPreferredSize(new Dimension(width, height));
        drawPanel.setBounds(0, 0, width, height);
        gridPanel.setBounds(0, 0, width + 1, height + 1);



        layeredPane.add(gridPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(drawPanel, JLayeredPane.PALETTE_LAYER);

        this.add(toolBar, BorderLayout.NORTH);
        this.add(layeredPane, BorderLayout.CENTER);
    }


    @Override
    public void close() {

    }

    @Override
    public boolean isVisible() {
        return super.isVisible();
    }


    @Override
    public void render(Set<GameEntity> entities, int suns, int kills) {
        System.out.println("[DEBUG] render chiamato con " + entities.size() + " entità");
        drawPanel.updateMovingEntities(Set.copyOf(entities));
        toolBar.updateStats(suns, kills);

        SwingUtilities.invokeLater(() -> {
            drawPanel.repaint();
            gridPanel.repaint();
            this.revalidate();
            this.repaint();
        });
    }


    @Override
    public void setViewListener(ViewListener listener) {
        this.listener = listener;
        toolBar.setViewListener(listener);
        gridPanel.setViewListener(listener);
    }




}
