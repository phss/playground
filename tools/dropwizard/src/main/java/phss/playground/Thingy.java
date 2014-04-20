package phss.playground;

import java.util.Arrays;

import static java.util.Arrays.sort;

public class Thingy {

    private String stuff;

    public Thingy(String stuff) {
        this.stuff = stuff;
    }

    public String saidHelloToTheStuff() {
        return "Hello to you, " + this.stuff;
    }

    public String[] sortAnArray() {
        String[] things = { "aaa", "bbbb", "cc" };

        sort(things, (s1, s2) -> s1.length() - s2.length());

        return things;
    }
}
