package helperclasses;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kradalby on 05/03/14.
 */
public class TimeFrame implements Serializable {

    private Integer id;
    private DateTime startDate;
    private DateTime endDate;
    private static List instances = new ArrayList();

    public TimeFrame(DateTime startDate, DateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        instances.add(new WeakReference(this));
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

    public static List getInstances() {
        return instances;
    }
}
