package de.htwg;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tabuechn on 29.04.2016. Be grateful for this code! ^(°.°)^
 */
public class Rotor {
    private List<Character> stringList;

    public static final int STRING_LENGTH = 26;

    public Rotor(String firstSide) {
        assert firstSide.length() == STRING_LENGTH;
        stringList = new LinkedList<>();
        for(int i = 0; i < firstSide.length(); ++i) {
            stringList.add(firstSide.charAt(i));
        }
    }

    public void turnClockWise() {
        Character first =stringList.remove(0);
        stringList.add(first);
    }
    public void turnCounterClockWise() {
        Character last = stringList.remove(stringList.size()-1);
        stringList.add(0,last);
    }

    public Character getCurrent() {
        return stringList.get(0);
    }
}
