package se.iths.sandra.labb2todolistwithsqlight;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "todo_table")
public class Todo {

 @PrimaryKey(autoGenerate = true)
  @ColumnInfo (name= "todo_id")
  private int todoId;


  @ColumnInfo(name ="todo")
  private String oneTodo;

  @ColumnInfo(name ="is_set_important")
  private int important;


   //Constructor
   public Todo(String oneTodo){
           this.oneTodo = oneTodo;
          this.important = 0;
        }


    public void setOneTodo(String oneTodo) {
        this.oneTodo = oneTodo;
    }

   public String getOneTodo() {
            return this.oneTodo;
        }

    public int getImportant() {
        return important;
    }

    public void setImportant(int important) {
        this.important = important;
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

}

