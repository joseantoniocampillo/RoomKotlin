package es.jacampillo.roomkotlin

import androidx.lifecycle.LiveData

// Declara el DAO como una propiedad privada in el constructor. P
class WordRepository(private val wordDao: WordDao) {

    //Room ejecuta todas las consultas en un separado thread.
    // Observed LiveData será notificado cuando el dato haya cambiado.
    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

}