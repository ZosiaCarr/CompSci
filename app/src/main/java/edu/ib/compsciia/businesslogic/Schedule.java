package edu.ib.compsciia.businesslogic;

public class Schedule  extends BaseObject  implements java.io.Serializable{
    public Schedule(LifeFormManager lfm) {
        super(lfm);
    }

    private int time;
    private int alertTimeBefore;


}
