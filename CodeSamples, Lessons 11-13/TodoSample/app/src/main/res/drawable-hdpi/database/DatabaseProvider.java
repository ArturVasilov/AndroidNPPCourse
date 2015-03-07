package ru.sportics.googleanalytics.database;

import java.util.List;

import ru.sportics.googleanalytics.TodoItem;

public interface DatabaseProvider {

    public void close();

    public void addTodoItem(TodoItem todoItem);

    public List<TodoItem> getAll();

    public void removeItem(TodoItem todoItem);

}
