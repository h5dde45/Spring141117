package dt.build.impl.rob;

import dt.build.interfaces.Hand;
import dt.build.interfaces.Head;
import dt.build.interfaces.Leg;
import dt.build.interfaces.Rob;

public class Mod1000 implements Rob {
    private Head head;
    private Hand hand;
    private Leg leg;

    private String string;
    private int year;
    private boolean enableSound;

    public Mod1000() {
    }

    public Mod1000(Head head, Hand hand, Leg leg) {
        this.head = head;
        this.hand = hand;
        this.leg = leg;
    }

    public Mod1000(String string, int year, boolean enableSound) {
        this.string = string;
        this.year = year;
        this.enableSound = enableSound;
    }

    public Mod1000(Head head, Hand hand, Leg leg, String string, int year, boolean enableSound) {
        this.head = head;
        this.hand = hand;
        this.leg = leg;
        this.string = string;
        this.year = year;
        this.enableSound = enableSound;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isEnableSound() {
        return enableSound;
    }

    public void setEnableSound(boolean enableSound) {
        this.enableSound = enableSound;
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Leg getLeg() {
        return leg;
    }

    public void setLeg(Leg leg) {
        this.leg = leg;
    }

    @Override
    public void action() {
        head.think();
        hand.take();
        leg.go();
        System.out.println("s - " + string);
        System.out.println("y - " + year);
        System.out.println("es - " + enableSound);
    }

    @Override
    public void secondAction() {
        System.out.println("secondAction");
    }
}
