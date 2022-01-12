import java.util.*;
import java.lang.*;

class BadUpdateTimeException extends Exception{
    public BadUpdateTimeException(Calendar dateTime, boolean bool){
        super(bool?"Update time is earlier than the last update:":"Update time is in the future: "+ dateTime); // dateTime object may not be in right format
    }
}

class UpdateTimeBeforeLastUpdateException extends BadUpdateTimeException{
    public UpdateTimeBeforeLastUpdateException(Calendar dateTime){
        super(dateTime,true);
    }
}

class UpdateTimeInTheFutureException extends BadUpdateTimeException{
    public UpdateTimeInTheFutureException(Calendar dateTime){
        super(dateTime,false);
    }
}

public class TimeStamp {
    private Calendar lastUpdate;

    public TimeStamp(){
        update();
    }
    public void update(){
        lastUpdate = Calendar.getInstance();
    }

    public void update(Calendar dateTime){
        assert (!dateTime.before(lastUpdate));
        lastUpdate = dateTime;
    }

    public Calendar getTimeStamp(){
        return lastUpdate;
    }

    public void updateWithExc1() throws Exception{
        throw new UpdateTimeInTheFutureException(getTimeStamp());
    }

    public void updateWithExc2() throws Exception{
        throw new UpdateTimeBeforeLastUpdateException(getTimeStamp());
    }

    public void updateWithExc3() throws Exception{
        throw new UpdateTimeInTheFutureException(getTimeStamp());
    }
}
