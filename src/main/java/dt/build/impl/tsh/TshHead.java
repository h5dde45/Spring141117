package dt.build.impl.tsh;

import dt.build.interfaces.Head;

public class TshHead implements Head{

    @Override
    public void think() {
        System.out.println("tsh think");
    }
}
