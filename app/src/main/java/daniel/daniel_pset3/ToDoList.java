package daniel.daniel_pset3;
/*
 * Student name: Daniel Oliemans
 * Student number: 11188669
 * University of Amsterdam
 * Source used for code: https://www.youtube.com/watch?v=q3rhteIierY
 */
public class ToDoList {

    private int _id;
    private String _todo;

    public ToDoList () {

    }

    public ToDoList(String _todo) {
        this._todo = _todo;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_todo(String _todo) {
        this._todo = _todo;
    }

    public int get_id() {
        return _id;
    }

    public String get_todo() {
        return _todo;
    }

    public String toString() {
        return _todo;
    }
}
