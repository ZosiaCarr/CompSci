package edu.ib.compsciia.businesslogic;

import android.graphics.Color;

import edu.ib.compsciia.R;

public class Plant extends LifeForm {

    @Override
    public boolean hasBirthDay() {
        return false;
    }


    @Override
    public int getColor() {
        return Color.rgb(34,139,34);
    }

    @Override
    public int getIcon() {
        return R.drawable.icons8_palm_tree_48;
    }
}
