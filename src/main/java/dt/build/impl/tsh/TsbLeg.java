package dt.build.impl.tsh;

import dt.build.interfaces.Leg;

public class TsbLeg implements Leg {
    @Override
    public void go() {
        System.out.println("tsb go");
    }
}
