package se.iths.sandra.labb2todolistwithsqlight;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {


    private TodoRepository mRepository;
    private LiveData<List<Todo>> mAllTodos;
    private LiveData<List<Todo>> mFilteredTodos;



    public TodoViewModel(Application application) {
        super(application);
        mRepository = new TodoRepository((application));
        mAllTodos = mRepository.getAllTodos();
    }

    public void update(Todo todo){
        mRepository.update(todo);
    }

    public void insert(Todo todo){
        mRepository.insert(todo);
    }

    public void delete(Todo todo){
        mRepository.delete(todo);
    }

    public void deleteAll(){
        mRepository.deletAll();
    }

    LiveData<List<Todo>> getAllTodos() { return mAllTodos; }



}
