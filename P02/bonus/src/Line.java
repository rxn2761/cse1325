public class Line
{
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private Color color;
    Line(double x1, double y1, double x2, double y2, Color color)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }
    double length()
    {
        double x_length = x2-x1;
        double y_length = y2-y1;
        double length = Math.sqrt(Math.pow(x_length,2.0) + Math.pow(y_length,2.0));
        return length;
    }
    public String toString()
    {
        String LineInfo = "";
        LineInfo = color + " (" + x1 + "," + y1 + ")-(" + x2 + "," + y2 + ") ";
        return LineInfo;
    }
}
