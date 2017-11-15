package dt.build.impl.tsh;

import dt.build.interfaces.Hand;
import org.springframework.stereotype.Component;

@Component

public class TsbHand implements Hand{

    @Override
    public void take() {
        System.out.println("tsb take");
    }
}
