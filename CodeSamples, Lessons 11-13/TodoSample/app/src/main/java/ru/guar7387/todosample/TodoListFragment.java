package ru.guar7387.todosample;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import ru.guar7387.todosample.fab.FabGenerator;
import ru.guar7387.todosample.fab.FloatingActionButton;

public class TodoListFragment extends Fragment {

    public static final String KEY = "items_key";

    private ListView listView;

    private TodoListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.todo_list_fragment, container, false);
        ArrayList<TodoItem> items = getArguments().getParcelableArrayList(KEY);
        adapter = new TodoListAdapter(getActivity(), 0, items);
        initListView(root);
        initFab(root);
        return root;
    }

    private void initListView(View root) {
        listView = (ListView) root.findViewById(R.id.todosList);

        listView.setAdapter(adapter);
        listView.setEmptyView(root.findViewById(android.R.id.empty));
        SwipeToDismissTouchListener swipeTouch = new SwipeToDismissTouchListener(listView,
                new SwipeToDismissTouchListener.DismissCallbacks() {
                    @Override
                    public boolean canDismiss(int position) {
                        return true;
                    }

                    @Override
                    public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {
                            TodoItem todoItem = adapter.getItem(position);
                            ((OnTodoRemoved) getActivity()).onTodoRemove(todoItem);
                            adapter.remove(todoItem);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
        listView.setOnTouchListener(swipeTouch);
        listView.setOnScrollListener(swipeTouch.makeScrollListener());
    }

    private void initFab(View root) {
        FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.fab);
        FabGenerator.createFab(getActivity().getApplicationContext(), fab, R.drawable.fab_plus, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((OnCreateTodo) getActivity()).onCreateTodo();
            }
        });
        fab.attachToListView(listView);
    }
}
