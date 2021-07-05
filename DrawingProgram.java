import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;



public class DrawingProgram extends JFrame {
    private List<PolyLine> lines = new ArrayList<PolyLine>();
    private PolyLine currentLine;  // the current line (for capturing)
    Color lineColor = new Color(0,0,0); 
	JPanel toolbar;
    Component horizontalStrut = Box.createHorizontalStrut(40);
    JColorChooser colorChooser = new JColorChooser();
	
	TestPane canvas = new TestPane();
	
	public DrawingProgram(){
		super("Painter");
		toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		toolbar.add(new Label("Drag mouse to draw"));
        this.add(toolbar,BorderLayout.SOUTH);
		
		colorChooser.setColor(Color.black);
		
        JButton showColorChooser = new JButton("Show Color Chooser");
        toolbar.add(showColorChooser);

        // Component horizontalStrut = Box.createHorizontalStrut(40);
        toolbar.add(horizontalStrut);
                
        Label fontLabel = new Label("Font Size");
        toolbar.add(fontLabel);
		
        

        JSlider fontSlider = new JSlider(0,20,3);
        fontSlider.setPaintTrack(true); 
        fontSlider.setPaintTicks(true); 
        fontSlider.setPaintLabels(true);
        fontSlider.setMajorTickSpacing(2); 
        fontSlider.setMinorTickSpacing(1);
        toolbar.add(fontSlider);

        toolbar.add(horizontalStrut);
        
        Label undoNote = new Label("use right click to undo");
        toolbar.add(undoNote);


        
        showColorChooser.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                JOptionPane.showMessageDialog(null,colorChooser);
            }
        });
        
        
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                if(evt.getModifiersEx() == MouseEvent.BUTTON3_DOWN_MASK && lines.size() > 0){ // getModifiers() Deprecated // right click to undo
                    lines.remove(lines.size() - 1);
                    canvas.repaint();
                } 
               else{
                    currentLine = new PolyLine();
                    lines.add(currentLine);
                    currentLine.addPoint(evt.getX(), evt.getY(),colorChooser.getColor(),fontSlider.getValue());
               }
            }
         });
         canvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                currentLine.addPoint(evt.getX(), evt.getY(),colorChooser.getColor(),fontSlider.getValue());
                repaint();  
            }
         });

        this.add(canvas);
		setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
	}
    
    private class TestPane extends JPanel  {
        public void paintComponent(Graphics g) {
            lineColor = colorChooser.getColor();
            super.paintComponent(g);
            g.setColor(lineColor);
            for (PolyLine line: lines) {
               line.draw(g);
            }
        }
    }

	public static void main(String[] a){
		new DrawingProgram();
	}
}