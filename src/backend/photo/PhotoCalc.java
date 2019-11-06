package backend.photo;

import java.util.List;

public class PhotoCalc {
    public int getDesiredWidth() {
        return desiredWidth;
    }

    public void setDesiredWidth(int desiredWidth) {
        this.desiredWidth = desiredWidth;
    }

    public int getMaxHDeviation() {
        return maxHDeviation;
    }

    public void setMaxHDeviation(int maxHDeviation) {
        this.maxHDeviation = maxHDeviation;
    }

    public int getMaxWDeviation() {
        return maxWDeviation;
    }

    public void setMaxWDeviation(int maxWDeviation) {
        this.maxWDeviation = maxWDeviation;
    }

    public int desiredWidth, maxHDeviation, maxWDeviation;

    public PhotoCalc(int desiredWidth, int hDeviation, int wDeviation){
        this.desiredWidth = desiredWidth;
        this.maxHDeviation = hDeviation;
        this.maxWDeviation = wDeviation;
    }

    private boolean setMeetsRequirments(List<Photo> photos){

        return false;
    }


}
