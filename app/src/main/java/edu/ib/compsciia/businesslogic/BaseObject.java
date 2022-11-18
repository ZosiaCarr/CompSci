package edu.ib.compsciia.businesslogic;

public class BaseObject implements java.io.Serializable   {
    private LifeFormManager lifeFormManager;
    public BaseObject(LifeFormManager lfm)
    {
        this.lifeFormManager = lfm;
    }
    public LifeFormManager getLifeFormManager()
    {
        return this.lifeFormManager;
    }
}
