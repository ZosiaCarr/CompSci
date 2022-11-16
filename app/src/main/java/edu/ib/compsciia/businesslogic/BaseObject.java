package edu.ib.compsciia.businesslogic;

public class BaseObject {
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
