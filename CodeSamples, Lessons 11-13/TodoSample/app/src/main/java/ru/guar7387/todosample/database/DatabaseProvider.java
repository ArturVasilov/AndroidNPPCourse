package ru.guar7387.todosample.database;

import java.util.List;

import ru.guar7387.todosample.TodoItem;

/**
 * simple interface to work with database
 * it contains all operations we need
 * the only implementation is {@link DatabaseDefaultProvider}
 */
public interface DatabaseProvider {

    public void close();

    public void addTodoItem(TodoItem todoItem);

    public List<TodoItem> getAll();

    public void removeItem(TodoItem todoItem);

}
