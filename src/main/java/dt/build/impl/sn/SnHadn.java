package dt.build.impl.sn;

import dt.build.interfaces.Hand;
import org.springframework.stereotype.Component;

@Component
public class SnHadn implements Hand {
    @Override
    public void take() {
        System.out.println("sn take");
    }
}
