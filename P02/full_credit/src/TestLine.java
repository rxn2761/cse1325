public class TestLine {
    public static void main(String[] args)
    {
        Line l1 = new Line(2, 2, 5, 6, Color.MAGENTO);
        Line l2 = new Line(24.521, 12.422, 12.126, 5.245, Color.CYAN);
        Line l3 = new Line(5.678, 3.145, 12.245, 8.543, Color.BRIGHTRED);
        Line l4 = new Line(1.234, 2.234, 3.456, 4.567, Color.WHITE);

        System.out.println(l1.toString() + "has length " + l1.length());
        System.out.println(l2.toString() + "has length " + l2.length());
        System.out.println(l3.toString() + "has length " + l3.length());
        System.out.println(l4.toString() + "has length " + l4.length());
    }
}

