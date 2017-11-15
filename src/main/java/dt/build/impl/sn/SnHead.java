package dt.build.impl.sn;

import dt.build.interfaces.Head;
import org.springframework.stereotype.Component;

@Component
public class SnHead implements Head {
    @Override
    public void think() {
        System.out.println("sn think");
    }
}
