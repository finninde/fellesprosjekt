package helperclasses;

import org.joda.time.DateTime;
import org.joda.time.Duration;

/**
 * Created by kradalby on 05/03/14.
 */
public class TimeFrame {

    private Integer id;
    private DateTime startDate;
    private DateTime endDate;

    public TimeFrame(DateTime startDate, DateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Duration getDuration() {
        Duration duration = new Duration(this.startDate, this.endDate);
        return duration;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }
}
