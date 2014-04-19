package phss.playground;

public class Thingy {

    private String stuff;

    public Thingy(String stuff) {
        this.stuff = stuff;
    }

    public String saidHelloToTheStuff() {
        return "Hello to you, " + this.stuff;
    }
}
