package edu.ib.compsciia.businesslogic;

import android.graphics.Color;

import edu.ib.compsciia.R;

public class Pet extends LifeForm {

    @Override
    public boolean hasBirthDay() {
        return true;
    }

    @Override
    public int getColor() {
        return Color.BLUE;
    }

    @Override
    public int getIcon() {
        return R.drawable.icons8_pets_50;
    }
}
