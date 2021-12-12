
package comp2502project1;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.geom.Rectangle2D;

public class DrawShapes extends JFrame {
    private Rectangle2D.Float myRect = new Rectangle2D.Float(100, 100, 90, 90);
 
    public static void main(String[] args) {
        
        new DrawShapes();
        
    }

     public DrawShapes() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                
 
                JFrame frame = new JFrame("Testing");
            
   
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              
 
  
 
                frame.add(new ControlPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                
              
            }
        });
    }


    public class ControlPane extends JPanel {

        private JRadioButton circle;
        private JRadioButton square;
        private JRadioButton rectangle;
        private JRadioButton delete;
        private JRadioButton line;

        private DrawPane drawPane;

        public ControlPane() {
            setLayout(new GridBagLayout());

            ButtonGroup bg = new ButtonGroup();
            circle = new JRadioButton("Circle");
            square = new JRadioButton("Square");
            rectangle = new JRadioButton("Rectangle");
            delete = new JRadioButton("Delete");
            line = new JRadioButton("Line");

            bg.add(circle);
            bg.add(square);
            bg.add(rectangle);
            bg.add(line);
            bg.add(delete);
            

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;

            JPanel shape = new JPanel();
            shape.add(circle);
            shape.add(square);
            shape.add(rectangle);
            shape.add(line);
            shape.add(delete);
            
            add(shape, gbc);

            JButton draw = new JButton("Draw");
            draw.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (circle.isSelected()) {
                        drawPane.setDrawableShape(DrawableShape.CIRCLE);
                    } else if (square.isSelected()) {
                        drawPane.setDrawableShape(DrawableShape.SQUARE);
                    }else if (rectangle.isSelected()) {
                        drawPane.setDrawableShape(DrawableShape.RECTANGLE);
                    }
                    else if(line.isSelected()){
                    drawPane.setDrawableShape(DrawableShape.LINE);
                    }else if (delete.isSelected()) {
                        drawPane.setDrawableShape(DrawableShape.DELETE);
                    }
                }
            });

            gbc.gridwidth = GridBagConstraints.REMAINDER;
            add(draw, gbc);

            drawPane = new DrawPane();

            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.fill = gbc.BOTH;
            add(drawPane, gbc);
        }

    }

    public enum DrawableShape {
        CIRCLE,
        LINE,
        SQUARE,
        RECTANGLE,
        DELETE
        
    }

    public class DrawPane extends JPanel {

        private DrawableShape drawableShape;

        public DrawPane() {
        

        } public void setDrawableShape(DrawableShape drawableShape) {
            this.drawableShape = drawableShape;
            repaint();
        }

        public DrawableShape getDrawableShape() {
            return drawableShape;
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(400, 400);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            DrawableShape shape = getDrawableShape();
           
              
                if (shape == DrawableShape.CIRCLE) {
                    g2d.fillOval(100, 100, 200, 200);
                } else if (shape == DrawableShape.SQUARE) {
                    g2d.fillRect(100, 100, 200, 200);
                }else if (shape == DrawableShape.RECTANGLE) {
                    g2d.fillRect(100, 100, 200, 100);
                }else if (shape == DrawableShape.LINE) {
                    g2d.drawLine(300, 300, 150, 75);
                }
            
            g2d.dispose();
        }
    }
   
    
          
  
    }
    


