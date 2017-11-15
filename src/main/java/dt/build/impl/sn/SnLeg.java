package dt.build.impl.sn;

import dt.build.interfaces.Leg;
import org.springframework.stereotype.Component;

@Component
public class SnLeg implements Leg {
    @Override
    public void go() {
        System.out.println("sn go");
    }
}
