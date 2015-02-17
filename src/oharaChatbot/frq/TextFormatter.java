package oharaChatbot.frq;

public class TextFormatter {
    private String line;
    
    public TextFormatter(String lineToFormat) {
        line = lineToFormat;
    }
    
    public int findString(String str, int start) {
    	if (start >= line.length())
    		return -1;
        int pos = start;
        int next;
        do {
            pos = line.indexOf(str, pos);
            next = line.indexOf(str, pos + 1);
            if (pos != -1 && next != pos + 1)
                return pos;
            pos = pos == -1 ? pos : pos + 2;
        } while (pos != -1 && pos < line.length());
        return pos;
    }
    
    public int countStrings(String str) {
        int pos =  -1;
        int found = 0;
        do {
            pos = this.findString(str, pos + 1);
            found++;
        } while (pos != -1);
        found--;
        return found;
    }
    
    public String convertItalics() {
        int pos1 = -1;
        int pos2 = -1;
        do {
            pos1 = findString("_", pos2 + 1);
            pos2 = findString("_", pos1 + 1);
            if (pos1 != -1 && pos2 != -1) {
                line = line.substring(0, pos1) + "<I>" + line.substring(pos1 + 1);
                pos2 += 2;
                line = line.substring(0, pos2) + "</I>" + line.substring(pos2 + 1);
                pos2 += 4;
            }
        } while (pos1 != -1 && pos2 != -1);
        return line;
    }
}