package item5.domain;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static item5.domain.SpellChecker.*;

class SpellCheckerTest {

    @Test
    void supplier_통해_의존주입_한다() {

        final Lexicon lexicon = new KoreanDictionary();
        SpellChecker spellChecker = SpellChecker.create(new Supplier<Lexicon>() {
            @Override
            public Lexicon get() {
                return lexicon;
            }
        });

        final Lexicon testLexicon = new TestDictionary();
        SpellChecker testChecker = SpellChecker.create(() -> testLexicon); // 람다 표현식
    }
}