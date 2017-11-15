package dt.build.impl.tsh;

import dt.build.interfaces.Head;
import org.springframework.stereotype.Component;

@Component

public class TshHead implements Head{

    @Override
    public void think() {
        System.out.println("tsh think");
    }
}
