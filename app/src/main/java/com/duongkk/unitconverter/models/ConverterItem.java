package com.duongkk.unitconverter.models;

import com.duongkk.unitconverter.libs.Unit;

/**
 * Created by MyPC on 7/12/2016.
 */
public class ConverterItem {
    int id;
    Unit[] units;
    int posFrom;
    int posTo;
    double fromValue;
    double toValue;
    String nameConverter;
    int color;
    int drawableIcon;
    int color2;

    public int getColor2() {
        return color2;
    }

    public void setColor2(int color2) {
        this.color2 = color2;
    }

    public int getDrawableIcon() {
        return drawableIcon;
    }

    public void setDrawableIcon(int drawableIcon) {
        this.drawableIcon = drawableIcon;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public double getFromValue() {
        return fromValue;
    }

    public void setFromValue(double fromValue) {
        this.fromValue = fromValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameConverter() {
        return nameConverter;
    }

    public void setNameConverter(String nameConverter) {
        this.nameConverter = nameConverter;
    }

    public int getPosFrom() {
        return posFrom;
    }

    public void setPosFrom(int posFrom) {
        this.posFrom = posFrom;
    }

    public int getPosTo() {
        return posTo;
    }

    public void setPosTo(int posTo) {
        this.posTo = posTo;
    }

    public double getToValue() {
        return toValue;
    }

    public void setToValue(double toValue) {
        this.toValue = toValue;
    }

    public Unit[] getUnits() {
        return units;
    }

    public void setUnits(Unit[] units) {
        this.units = units;
    }
}
