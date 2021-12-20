package com.danny.dictionary;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/words")
public class WordController {
    private final WordService service;

    public WordController(WordService service) {
        this.service = service;
    }

    @GetMapping
    public List<Word> getWords() {
        return service.getWords();
    }

    @PostMapping()
    public Word addWord(@RequestBody Word word) {
        return service.addWord(word);
    }

    @GetMapping(path ="/{id}")
    public Word getWord(@PathVariable Long id) {
        return service.getWord(id);
    }

    @PutMapping(path = "/{id}")
    public Word replaceWord(@RequestBody Word word, @PathVariable Long id) {
        return service.replaceWord(word, id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteWord(@PathVariable Long id) {
        service.deleteWord(id);
    }
}
