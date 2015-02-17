package oharaChatbot.frq;

public class TextFormatter {
    private String line;
    
    public TextFormatter(String lineToFormat) {
        line = lineToFormat;
    }
    
    public int findString(String str, int start) {
        int pos = start;
        int next;
        while (pos != -1 && pos < line.length()) {
            pos = line.indexOf(str, pos);
            next = line.indexOf(str, pos + 1);
            if (pos >= 0 && next > pos + 1)
                return pos;
            pos = pos == -1 ? pos : pos + 3;
        }
        return -1;
    }
    
    public int countStrings(String str) {
        int pos = 0;
        int found = 0;
        while (pos != -1) {
            pos = line.indexOf(str, pos);
            found++;
        }
        found--;
        return found;
    }
    
    public String convertItalics() {
        int pos1 = -1;
        int pos2 = -1;
        String result = line;
        do {
            pos1 = result.indexOf("_", pos2 + 1);
            pos2 = result.indexOf("_", pos1 + 1);
            if (pos1 != -1 && pos2 != -1) {
                result = result.substring(0, pos1) + "<I>" + result.substring(pos1 + 2);
                pos2 += 2;
                result = result.substring(0, pos2) + "<//I>" + result.substring(pos2 + 2);
                pos2 += 4;
            }
        } while (pos1 != -1 && pos2 != -1);
        return result;
    }
}