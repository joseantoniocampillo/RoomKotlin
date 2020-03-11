package es.jacampillo.roomkotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

//Esta clase extiende de AndroidViewModel y requiere application como parámetro.
class WordViewModel(application: Application): AndroidViewModel(application){

    // El viewModel mantiene una referencia al repositorio para conseguir los datos
    private val repository: WordRepository
    // LiveData nos da actualizaciones sobre words cuando cambian
    val allWords: LiveData<List<Word>>

    init {
        val wordsDao = WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repository = WordRepository(wordsDao)
        allWords = repository.allWords
    }

    /*La implementación de insert() in la base de datos está completamente oculta para la UI,
      Room se asegura de que no suceden las operaciones en el hilo principal, bloqueando la UI.
      No necesitamos manejar cambios en Dispatchers por que viewModel tiene una coroutine scope en su
      propio ciclo de vida llamado vewModelScope que puede usarse aquí.
    */

    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }
}