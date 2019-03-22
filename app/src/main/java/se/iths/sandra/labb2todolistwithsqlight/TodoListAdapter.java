package se.iths.sandra.labb2todolistwithsqlight;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.WordViewHolder> {

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView wordItemView;
        private final ImageView favouriteMark;
        private Context context;
        private Todo current;


        private WordViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            favouriteMark = itemView.findViewById(R.id.imageView);
            wordItemView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            showActionDialog(v);
        }

        private void showActionDialog(final View view){
            AlertDialog.Builder alertdialog = new AlertDialog.Builder(view.getContext());
            alertdialog.setTitle(R.string.update);
            alertdialog.setMessage(R.string.update_this_todo);

            alertdialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(context, EditTodoActivity.class);
                    intent.putExtra("position", current.getTodoId());
                    intent.putExtra("todoname", current.getOneTodo());
                    intent.putExtra("important", current.getImportant());
                    ((Activity)context).startActivityForResult(intent, 911);

                }
            });

            alertdialog.setNeutralButton(R.string.go_back, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            //displays the alerDialog
            alertdialog.show();
        }
    }

    private final LayoutInflater mInflater;
    private List<Todo> mTodos; // Cached copy of words
    private Context context;


    TodoListAdapter(Context context) {
        this.context =context;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (mTodos != null) {
            Todo current = mTodos.get(position);
            holder.current = current;
            holder.wordItemView.setText(current.getOneTodo());

               if(current.getImportant() ==1){
                   holder.favouriteMark.setVisibility(View.VISIBLE);
               }else{
                   holder.favouriteMark.setVisibility(View.INVISIBLE);
               }
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText(R.string.no_todo);
        }
    }

    void setTodos(List<Todo> todos){
        mTodos = todos;
        notifyDataSetChanged();
    }

    public Todo getTodoAtPosition (int position){
        return mTodos.get(position);
    }

    // getItemCount() is called many times, and when it is first called,
    // mTodos has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mTodos != null)
            return mTodos.size();
        else return 0;
    }
}


