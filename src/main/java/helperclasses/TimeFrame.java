package helperclasses;

import org.joda.time.DateTime;
import org.joda.time.Duration;

/**
 * Created by kradalby on 05/03/14.
 */
public class TimeFrame {

    DateTime startDate;
    DateTime endDate;

    public TimeFrame(DateTime startDate, DateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Duration getDuration() {
        Duration duration = new Duration(this.startDate, this.endDate);
        return duration;
    }
}
