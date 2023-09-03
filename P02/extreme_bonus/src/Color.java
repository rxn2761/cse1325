public enum Color {
    MAGENTA(1), CYAN(2), BRIGHTRED(3), WHITE(4);
    static String Magenta = "\u001b[35m";
    static String Cyan = "\u001b[36m";
    static String BrightRed = "\u001b[31;1m";
    static String White = "\u001b[37m";
    static String Reset = "\u001b[0m";
    private int rgb;

    private Color(int rgb)
    {
        this.rgb = rgb;
    }

    public String toString()
    {
        if (rgb == 1)
        {
            return "MAGENTA " + "(" + Magenta + "0xFF00FF" + Reset + ")";
        }
        else if (rgb == 2)
        {
            return "CYAN " + "(" + Cyan + "0x00FFFF" + Reset + ")";
        }
        else if (rgb == 3)
        {
            return "BRIGHTRED " + "(" + BrightRed + "0xEE4B2B" + Reset + ")";
        }
        else if (rgb == 4)
        {
            return "WHITE " + "(" + White + "0xFFFFFF" + Reset + ")";
        }
        else
        {
            return "No color code";
        }
    }
}
