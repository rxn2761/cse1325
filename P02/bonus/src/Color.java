public enum Color {
    MAGENTA(1), CYAN(2), BRIGHTRED(3), WHITE(4);

    private int rgb;

    private Color(int rgb)
    {
        this.rgb = rgb;
    }

    public String toString()
    {
        if (rgb == 1)
        {
            return "MAGENTA (0xFF00FF)";
        }
        else if (rgb == 2)
        {
            return "CYAN (0x00FFFF)";
        }
        else if (rgb == 3)
        {
            return "BRIGHTRED (0xEE4B2B)";
        }
        else if (rgb == 4)
        {
            return "WHITE (0xFFFFFF)";
        }
        else
        {
            return "No color code";
        }
    }
}
