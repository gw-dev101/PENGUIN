package penguin.graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import penguin.core.Environment;
import penguin.core.Penguin;
import penguin.core.TerrainType;

public class Renderer extends JPanel {
    private final Penguin[] penguins;
    private final Environment environment;
    private final Image penguinSprite;
    private final BufferedImage terrainImage;

    public Renderer(Penguin[] penguins, Environment environment) {
        this.penguins = penguins;
        this.environment = environment;
        setPreferredSize(new Dimension(environment.getWidth(), environment.getHeight()));
        setBackground(Color.CYAN);

        // Load and scale penguin sprite once
        ImageIcon penguinIcon = new ImageIcon(getClass().getResource("/assets/penguin.png"));
        penguinSprite = penguinIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

        // Pre-render terrain to a BufferedImage for fast drawing
        terrainImage = new BufferedImage(environment.getWidth(), environment.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < environment.getWidth(); x++) {
            for (int y = 0; y < environment.getHeight(); y++) {
                TerrainType type = environment.getTerrainType(x, y);
                int rgb = (type == TerrainType.ICE) ? Color.WHITE.getRGB() : Color.BLUE.getRGB();
                terrainImage.setRGB(x, y, rgb);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw pre-rendered terrain
        g.drawImage(terrainImage, 0, 0, null);

        // Draw penguins
        for (Penguin p : penguins) {
            int px = (int) p.x;
            int py = (int) p.y;
            int spriteWidth = penguinSprite.getWidth(null);
            int spriteHeight = penguinSprite.getHeight(null);

            // Draw the penguin sprite centered at (px, py)
            g.drawImage(penguinSprite, px - spriteWidth / 2, py - spriteHeight / 2, null);

            // Draw direction line
            //int dx = (int) (10 * Math.cos(p.angle));
            //int dy = (int) (10 * Math.sin(p.angle));
            //g.setColor(Color.BLACK);
            //g.drawLine(px, py, px + dx, py + dy);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PENGUIN");
        Environment env = new Environment(800, 600);
        Penguin[] penguins = new Penguin[50];
        for (int i = 0; i < penguins.length; i++) {
            penguins[i] = new Penguin(Math.random() * env.getWidth(), Math.random() * env.getHeight());
        }
        Renderer renderer = new Renderer(penguins, env);
        frame.add(renderer);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Simulation loop
        new Timer(8, e -> { // ~120 FPS
            for (Penguin p : penguins) {
                p.flock(penguins);
                p.update();
            }
            renderer.repaint();
        }).start();
    }
}