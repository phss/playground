package controllers;

import play.*;
import play.mvc.*;
import views.html.index;


public class Application extends Controller {

    public static Result index() {
        return ok("Hello World!");
    }

    public static Result tasks() {
        return TODO;
    }

    public static Result newTask() {
        return TODO;
    }

    public static Result deleteTask(Long id) {
        return TODO;
    }

}
