package me.lovethefeel.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class NoteHighlighterTest {

    /**
     * abc -> abc
     * note -> {note}
     * 1 note -> 1 {note}
     * 1 note 2 -> 1 {note} 2
     * keynote -> keynote
     * ke1note -> ke1note
     * yes note1 -> yes note1
     * yes notea -> yes notea
     * no a note -> no a {note}
     * no a note note -> no a {note} {note}
     * no a note note anote -> no a {note} {note} anote
     */
    @ParameterizedTest
    @CsvSource({
            "abc, abc",
            "note, {note}",
            "1 note, 1 {note}",
            "1 note 2, 1 {note} 2",
            "keynote, keynote",
            "ke1note, ke1note",
            "yes note1, yes note1",
            "yes notea, yes notea",
            "no a note, no a {note}",
            "no a note note, no a {note} {note}",
            "no a note note anote, no a {note} {note} anote"
    })
    void noteReplaceTest(String given, String expected) {
        NoteHighlighter noteHighlighter = new NoteHighlighter(given);
        String result = noteHighlighter.print();
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("문자열 abc")
    @Test
    void str_abc() {
        String name = "abc";
        NoteHighlighter noteHighlighter = new NoteHighlighter(name);
        String result = noteHighlighter.print();
        assertThat(result).isEqualTo("abc");
    }

    @DisplayName("문자열 note")
    @Test
    void str_note() {
        String name = "note";
        NoteHighlighter noteHighlighter = new NoteHighlighter(name);
        String result = noteHighlighter.print();
        assertThat(result).isEqualTo("{note}");
    }

    @DisplayName("문자열 1 note")
    @Test
    void str_1_note() {
        String name = "1 note";
        NoteHighlighter noteHighlighter = new NoteHighlighter(name);
        String result = noteHighlighter.print();
        assertThat(result).isEqualTo("1 {note}");
    }

    @DisplayName("문자열 1 note 2")
    @Test
    void str_1_note_2() {
        String name = "1 note 2";
        NoteHighlighter noteHighlighter = new NoteHighlighter(name);
        String result = noteHighlighter.print();
        assertThat(result).isEqualTo("1 {note} 2");
    }

    @DisplayName("문자열 keynote")
    @Test
    void str_keynote() {
        String name = "keynote";
        NoteHighlighter noteHighlighter = new NoteHighlighter(name);
        String result = noteHighlighter.print();
        assertThat(result).isEqualTo("keynote");
    }

    @DisplayName("문자열 ke1note")
    @Test
    void str_ke1note() {
        String name = "ke1note";
        NoteHighlighter noteHighlighter = new NoteHighlighter(name);
        String result = noteHighlighter.print();
        assertThat(result).isEqualTo("ke1note");
    }

    @DisplayName("문자열 yes notea")
    @Test
    void str_yes_notea() {
        String name = "yes notea";
        NoteHighlighter noteHighlighter = new NoteHighlighter(name);
        String result = noteHighlighter.print();
        assertThat(result).isEqualTo("yes notea");
    }

    @DisplayName("문자열 no a note")
    @Test
    void str_no_a_note() {
        String name = "no a note";
        NoteHighlighter noteHighlighter = new NoteHighlighter(name);
        String result = noteHighlighter.print();
        assertThat(result).isEqualTo("no a {note}");
    }

    @DisplayName("문자열 no a note note")
    @Test
    void str_no_a_note_note () {
        String name = "no a note note";
        NoteHighlighter noteHighlighter = new NoteHighlighter(name);
        String result = noteHighlighter.print();
        assertThat(result).isEqualTo("no a {note} {note}");
    }

    @DisplayName("문자열 no a note note anote")
    @Test
    void str_no_a_note_note_anote () {
        String name = "no a note note anote";
        NoteHighlighter noteHighlighter = new NoteHighlighter(name);
        String result = noteHighlighter.print();
        assertThat(result).isEqualTo("no a {note} {note} anote");
    }
}
