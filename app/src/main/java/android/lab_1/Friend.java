package android.lab_1;

import java.io.Serializable;

public class Friend implements Serializable {
    private String name;

    public Friend(String name) {

        this.name = name;
    }

    // copy constructor
    public Friend(Friend original) {

        this.name = original.name;
    }
    // check if 1 friend object is equal to this
    @Override
    public boolean equals(Object object){
        boolean equal = false;

        if (object != null && object instanceof Friend)
        {
            equal = this.name == ((Friend) object).name;
        }

        return equal;
    }

    @Override
    public String toString() {

        return name;
    }
}
