package helperclasses;

import org.joda.time.DateTime;
import org.joda.time.Duration;

/**
 * Created by kradalby on 05/03/14.
 */
public class TimeFrame {

    DateTime startDate;
    DateTime endDate;

    public Duration getDuration() {
        Duration duration = new Duration(this.startDate, this.endDate);
        return duration;
    }
}
