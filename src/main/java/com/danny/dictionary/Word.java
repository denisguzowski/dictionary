package com.danny.dictionary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Word {
    private @Id @GeneratedValue Long id;
    private String word;
    private String pos;
    private String pronunciation;
    private String definition;
    private String example;

    public Word(Long id, String word, String pos, String pronunciation, String definition, String example) {
        this.id = id;
        this.word = word;
        this.pos = pos;
        this.pronunciation = pronunciation;
        this.definition = definition;
        this.example = example;
    }

    public Word() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return  Objects.equals(id, word1.id) &&
                Objects.equals(word, word1.word) &&
                Objects.equals(pos, word1.pos) &&
                Objects.equals(pronunciation, word1.pronunciation) &&
                Objects.equals(definition, word1.definition) &&
                Objects.equals(example, word1.example);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, pos, pronunciation, definition, example);
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", pos='" + pos + '\'' +
                ", pronunciation='" + pronunciation + '\'' +
                ", definition='" + definition + '\'' +
                ", example='" + example + '\'' +
                '}';
    }
}
