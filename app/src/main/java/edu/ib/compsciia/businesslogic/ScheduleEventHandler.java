package edu.ib.compsciia.businesslogic;

public interface ScheduleEventHandler {
    void ScheduleRemoved(int id);
    void ScheduleNextEvent(ScheduleRunDate rd);
}
