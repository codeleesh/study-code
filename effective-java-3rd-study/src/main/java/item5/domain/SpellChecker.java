package item5.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class SpellChecker {

    private final Lexicon dictionary;

    public static SpellChecker create(Supplier<? extends Lexicon> generator) {
        return new SpellChecker(generator.get());
    }

    public SpellChecker(Lexicon dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
    }

    public boolean isValid(final String word) {
        throw new UnsupportedOperationException();
    }

    public List<String> suggestions(final String tyop) {
        throw new UnsupportedOperationException();
    }
}

interface Lexicon {}

class KoreanDictionary implements Lexicon {
}

class TestDictionary implements Lexicon {
}
