package edu.ib.compsciia.businesslogic;

// Used to alert when notifications need to change
public interface ScheduleEventHandler {
    void ScheduleRemoved(int id);
    void ScheduleNextEvent(ScheduleRunDate rd);
}
