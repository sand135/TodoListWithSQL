package se.iths.sandra.labb2todolistwithsqlight;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import java.util.List;

@Dao
public interface TodoDao {

    @Insert
    public void insert (Todo todo);

    @Delete
    public void deleteTodo(Todo todo);


    @Query("UPDATE todo_table SET todo = :todo, is_set_important= :importance WHERE todo_id =:id")
    void update(int id, String todo, int importance);

    @Query("DELETE FROM todo_table")
    public void deleteAll();


    @Query("SELECT * from todo_table ORDER BY is_set_important DESC")
    LiveData<List<Todo>> getAllTodos();

}
