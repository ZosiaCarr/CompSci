package edu.ib.compsciia.businesslogic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AppViewModel extends ViewModel {
    private final MutableLiveData<LifeForm> selectedLifeForm = new MutableLiveData<LifeForm>();
    private final MutableLiveData<Schedule> selectedSchedule = new MutableLiveData<Schedule>();
    private final MutableLiveData<Activity> selectedActivity = new MutableLiveData<Activity>();

    public void selectLifeform(LifeForm item) {
        selectedLifeForm.setValue(item);
    }
    public LiveData<LifeForm> getSelectedLifeForm() {
        return selectedLifeForm;
    }

    public void setSelectedSchedule(Schedule item) {
        selectedSchedule.setValue(item);
    }
    public LiveData<Schedule> getSelectedSchedule() {
        return selectedSchedule;
    }

    public void setSelectedActivity(Activity item) {
        selectedActivity.setValue(item);
    }
    public LiveData<Activity> getSelectedActivity() {
        return selectedActivity;
    }
}