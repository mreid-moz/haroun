import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Exceptions {

    public static Throwable getRootCause(final Throwable throwable) {
        List<Throwable> list = new ArrayList<Throwable>();
        list.add(throwable);
        
        Throwable cause = throwable.getCause();
        while (cause != null && !list.contains(cause)) {
            list.add(cause);
            cause = cause.getCause();
        }
        
        Collections.reverse(list);
        
        return list.get(0);
    }

}
