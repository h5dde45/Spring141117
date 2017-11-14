package dt.build.impl.tsh;

import dt.build.interfaces.Hand;

public class TsbHand implements Hand{

    @Override
    public void take() {
        System.out.println("tsb take");
    }
}
