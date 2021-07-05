
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
class PolyLine {
    private List<Integer> xList;  
    private List<Integer> yList;
    Color color;
    int fontSize;
    public PolyLine() {
       xList = new ArrayList<Integer>();
       yList = new ArrayList<Integer>();
       
    }
  
    public void addPoint(int x, int y, Color color, int fontSize) {
       xList.add(x);
       yList.add(y);
       this.color = color;
       this.fontSize = fontSize;
    }
  
    public void draw(Graphics g) { 
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color); // avoid changing the color of all canvas
        g2d.setStroke(new BasicStroke(fontSize));
        for (int i = 0; i < xList.size() - 1; ++i) {
            g2d.drawLine((int)xList.get(i), (int)yList.get(i), (int)xList.get(i + 1),
                (int)yList.get(i + 1));
       }
    }
 }