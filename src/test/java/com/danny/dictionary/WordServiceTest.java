package com.danny.dictionary;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class WordServiceTest {

    @Mock
    WordRepository wordRepositoryMock;

    @InjectMocks
    WordService wordService;

    @Test
    void testInitialization() {
        assertNotNull(wordRepositoryMock);
        assertNotNull(wordService);
    }

    @Test
    void getWords() {
        Mockito.when(wordRepositoryMock.findAll()).thenReturn(
                List.of(
                        new Word(1L, "word1", "", "", "", ""),
                        new Word(2L, "word2", "", "", "", ""))
        );

        assertEquals(2, wordService.getWords().size());
        Mockito.verify(wordRepositoryMock).findAll();
    }

    @Test
    void addWord() {
        Word w1 = new Word(1L, "word1", "", "", "", "");
        Mockito.when(wordRepositoryMock.save(Mockito.any())).thenReturn(w1);

        assertEquals(w1, wordService.addWord(w1));

        Mockito.verify(wordRepositoryMock).save(ArgumentMatchers.eq(new Word(1L, "word1", "", "", "", "")));
    }

    @Test
    void getWord() {
        Word w1 = new Word(1L, "word1", "", "", "", "");
        Mockito.when(wordRepositoryMock.findById(1L)).thenReturn(java.util.Optional.of(w1));

        assertEquals(w1, wordService.getWord(1L));
    }

    @Test
    void getWordShouldThrowException() {
        Mockito.when(wordRepositoryMock.findById(2L)).thenThrow(new WordDoesNotExistException(2L));

        Exception exception = assertThrows(WordDoesNotExistException.class, () -> wordService.getWord(2L));
        assertEquals("No word with id = 2 exists", exception.getMessage());
    }

    @Test
    void deleteWordShouldThrowException() {
        Exception exception = assertThrows(WordDoesNotExistException.class, () -> wordService.deleteWord(1L));
        assertEquals("No word with id = 1 exists", exception.getMessage());
    }
}