package edu.ib.compsciia.businesslogic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AppViewModel extends ViewModel {
    private final MutableLiveData<LifeForm> selectedLifeForm = new MutableLiveData<LifeForm>();
    private final MutableLiveData<Schedule> selectedSchedule = new MutableLiveData<Schedule>();
    public void selectLifeform(LifeForm item) {
        selectedLifeForm.setValue(item);
    }
    public LiveData<LifeForm> getSelectedItem() {
        return selectedLifeForm;
    }


    public void setSelectedSchedule(Schedule item) {
        selectedSchedule.setValue(item);
    }
    public LiveData<Schedule> getSelectedSchedule() {
        return selectedSchedule;
    }

}