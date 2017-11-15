package dt.aop.objects;

import org.springframework.stereotype.Component;

@Component
public class SomeService {
    public static int getItValue() {
        return 5;
    }

    public double getDoubleValue() {
        return 5.5;
    }

}
