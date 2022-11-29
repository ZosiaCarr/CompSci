package edu.ib.compsciia.businesslogic;

import java.util.ArrayList;
import java.util.List;

public abstract class LifeForm{
    public LifeForm() {
        activities = new ArrayList<Activity>();
    }

    private List<Activity> activities;
    public List<Activity> getActivities () {
        return activities;
    }
    public void addActivity(Activity ac)
    {
        this.activities.add(ac);
    }
    public void removeActivity(Activity ac)
    {
        this.activities.remove(ac);
    }


}
