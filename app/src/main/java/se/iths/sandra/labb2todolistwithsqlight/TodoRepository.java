package se.iths.sandra.labb2todolistwithsqlight;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TodoRepository {

    private TodoDao mTodoDao;
    private LiveData<List<Todo>> mAllTodos;


    TodoRepository(Application application) {
        TodoRoomDataBase db = TodoRoomDataBase.getDatabase(application);
        mTodoDao = db.todoDao();
        mAllTodos = mTodoDao.getAllTodos();
    }

    LiveData<List<Todo>> getAllTodos() {
        return mAllTodos;
    }

    void insert(Todo todo) {
        new insertAsyncTask(mTodoDao).execute(todo);
    }

    void delete(Todo todo){
        new deleteTodoAsyncTask(mTodoDao).execute(todo);
    }

    void update(Todo todo){
        new updateTodoAsyncTask(mTodoDao).execute(todo);
    }

    void deletAll(){
        new deleteAllWordsAsyncTask(mTodoDao).execute();
    }


    private static class deleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {
        private TodoDao mAsyncTaskDao;

        deleteAllWordsAsyncTask(TodoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class deleteTodoAsyncTask extends AsyncTask<Todo,Void, Void>{
        private TodoDao mAsyncTaskDao;

        deleteTodoAsyncTask (TodoDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Todo... todos) {
            mAsyncTaskDao.deleteTodo(todos[0]);
           return null;
        }
    }

    private static class updateTodoAsyncTask extends AsyncTask<Todo,Void, Void >{
        private TodoDao mAsyncTaskDao;

        updateTodoAsyncTask(TodoDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Todo... todos) {
            mAsyncTaskDao.update(todos[0].getTodoId(), todos[0].getOneTodo(), todos[0].getImportant());
            return null;
        }
    }

    private static class insertAsyncTask extends AsyncTask<Todo, Void, Void> {

        private TodoDao mAsyncTaskDao;

        insertAsyncTask(TodoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Todo... params) { ;
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
