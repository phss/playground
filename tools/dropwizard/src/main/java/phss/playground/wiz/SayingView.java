package phss.playground.wiz;

import io.dropwizard.views.View;

public class SayingView extends View {

    private final Saying saying;

    public SayingView(Saying saying) {
        super("saying.mustache");
        this.saying = saying;
    }

    public Saying getSaying() {
        return saying;
    }
}
