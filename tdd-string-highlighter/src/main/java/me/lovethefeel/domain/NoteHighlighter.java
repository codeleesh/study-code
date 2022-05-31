package me.lovethefeel.domain;

public class NoteHighlighter {
    public static final String STRING_NOTE = "note";
    public static final String STRING_CORRECT_NOTE = "{note}";
    public static final String SPACE = " ";
    private final String name;

    public NoteHighlighter(final String name) {
        this.name = name;
    }

    public String print() {

        final String result = noteReplace(this.name.split(SPACE));
        return lastSubString(result);
    }

    private String noteReplace(final String[] names) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String str : names) {
            stringBuilder.append(equalsNoteReplace(str));
            stringBuilder.append(SPACE);
        }
        return stringBuilder.toString();
    }

    private String lastSubString(final String str) {
        return str.substring(0, str.length() - 1);
    }

    private String equalsNoteReplace(final String str) {
        if (str.equals(STRING_NOTE)) {
            return str.replace(STRING_NOTE, STRING_CORRECT_NOTE);
        }
        return str;
    }
}
