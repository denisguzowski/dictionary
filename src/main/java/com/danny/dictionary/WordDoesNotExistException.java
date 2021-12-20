package com.danny.dictionary;

public class WordDoesNotExistException extends RuntimeException {
    public WordDoesNotExistException(Long id) {
        super(String.format("No word with id = %d exists", id));
    }
}
