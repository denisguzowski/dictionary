package com.danny.dictionary;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WordService {
    private final WordRepository repository;

    public WordService(WordRepository repository) {
        this.repository = repository;
    }

    public List<Word> getWords() {
        return repository.findAll();
    }

    public Word addWord(Word word) {
        return repository.save(word);
    }

    public Word getWord(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new WordDoesNotExistException(id));
    }

    public Word replaceWord(Word word, Long id) {
        return repository.findById(id)
                .map(w -> {
                    w.setWord(word.getWord());
                    w.setPos(word.getPos());
                    w.setPronunciation(word.getPronunciation());
                    w.setDefinition(word.getDefinition());
                    w.setExample(word.getExample());
                    return repository.save(w);
                })
                .orElseGet(() -> {
                    word.setId(id);
                    return repository.save(word);
                });
    }


    public void deleteWord(Long id) {
        repository.delete(
                repository.findById(id)
                        .orElseThrow(() -> new WordDoesNotExistException(id))
        );
    }
}
