package penguin.graphics;
import javax.swing.*;
import java.awt.*;
import penguin.core.Environment;
import penguin.core.Penguin;
import penguin.core.TerrainType;


public class Renderer extends JPanel {
    private final Penguin[] penguins;
    private final Environment environment;

    public Renderer(Penguin[] penguins, Environment environment) {
        this.penguins = penguins;
        this.environment = environment;
        setPreferredSize(new Dimension(environment.getWidth(), environment.getHeight()));
        setBackground(Color.CYAN);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw terrain
        for (int x = 0; x < environment.getWidth(); x++) {
            for (int y = 0; y < environment.getHeight(); y++) {
                TerrainType type = environment.getTerrainType(x, y);
                if (type == TerrainType.ICE) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLUE);
                }
                g.fillRect(x, y, 1, 1);
            }
        }
        // Draw penguins
        for (Penguin p : penguins) {
            int px = (int) p.x;
            int py = (int) p.y;
            g.setColor(Color.BLACK);
            g.fillOval(px - 5, py - 5, 10, 10);
            // Draw direction line
            int dx = (int) (10 * Math.cos(p.angle));
            int dy = (int) (10 * Math.sin(p.angle));
            g.drawLine(px, py, px + dx, py + dy);
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Penguin Simulation");
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
        new Timer(16, e -> {
            for (Penguin p : penguins) {
                p.flock(penguins);
                p.update();
            }
            renderer.repaint();
        }).start();
    }
}