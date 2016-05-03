package de.htwg;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Enigma {

    public static Rotor firstRotor;
    public static Rotor secondRotor;
    public static Rotor thirdRotor;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to decrypt or encrypt?");
        boolean encrypt = false;
        if(scanner.nextLine().equals("encrypt")) {encrypt = true;}
        System.out.println("What is the key?");
        String key = scanner.nextLine();
        System.out.println("What is the word you would like to encode?");
        String code = scanner.nextLine();

        Rotor rotor50 = new Rotor("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Rotor rotor51 = new Rotor("ADCBEHFGILJKMPNOQTRSUXVWZY");
        Rotor rotor60 = new Rotor("ACEDFHGIKJLNMOQPRTSUWVXZYB");
        Rotor rotor61 = new Rotor("AZXVTRPNDJHFLBYWUSQOMKIGEC");
        Rotor rotor70 = new Rotor("AZYXWVUTSRQPONMLKJIHGFEDCB");
        Rotor rotor71 = new Rotor("AEBCDFJGHIKOLMNPTQRSUYVWXZ");

        Map<String,Rotor> rotorMap = new HashMap<>();
        rotorMap.put("50",rotor50);
        rotorMap.put("51",rotor51);
        rotorMap.put("60",rotor60);
        rotorMap.put("61",rotor61);
        rotorMap.put("70",rotor70);
        rotorMap.put("71",rotor71);

        System.out.println("Which rotors do you want to use?");
        try {
            String[] rotors = scanner.nextLine().split(" ");
            firstRotor = rotorMap.get(rotors[0]);
            secondRotor = rotorMap.get(rotors[1]);
            thirdRotor = rotorMap.get(rotors[2]);
        } catch (Exception e) {
            System.out.println("Invalid Input, setting rotors to 61 50 70");
            firstRotor = rotor61;
            secondRotor = rotor50;
            thirdRotor = rotor70;
        }
        setKey(key);
        if(encrypt) {
            String result = encrypt(code);
            System.out.println("Encryption Result: " + result);
        } else {
            String result = decrypt(code);
            System.out.println("Decryption Result " + result);
        }
    }

    private static String decrypt(String code) {
        StringBuilder sb = new StringBuilder();
        boolean readFirst = true;
        for(char character : code.toCharArray()) {
            if (readFirst) {
                turnThirdRotorTo(character);
                sb.append(firstRotor.getCurrent());
            } else {
                turnThirdRotorTo(character);
                sb.append(secondRotor.getCurrent());
            }
            readFirst = !readFirst;
        }
        return sb.toString();
    }

    private static String encrypt(String code) {
        StringBuilder sb = new StringBuilder();
        boolean turnFirst = true;
        for(char character : code.toCharArray()) {
            if(turnFirst) {
                turnFirstRotorTo(character);
            } else {
                turnSecondRotorTo(character);
            }
            sb.append(thirdRotor.getCurrent());
            turnFirst = !turnFirst;
        }
        return sb.toString();
    }

    private static void setKey(String key) {
        setFirstRotor(key.charAt(0));
        setSecondRotor(key.charAt(1));
        setThirdRotor(key.charAt(2));
    }

    private static void setFirstRotor(char c) {
        while (!firstRotor.getCurrent().equals(c)) {
            firstRotor.turnClockWise();
        }
    }
    private static void setSecondRotor(char c) {
        while (!secondRotor.getCurrent().equals(c)) {
            secondRotor.turnClockWise();
        }
    }

    private static void setThirdRotor(char c) {
        while (!thirdRotor.getCurrent().equals(c)) {
            thirdRotor.turnClockWise();
        }
    }

    private static void turnThirdRotorTo(char c) {
        while (!thirdRotor.getCurrent().equals(c)) {
            firstRotor.turnClockWise();
            secondRotor.turnCounterClockWise();
            thirdRotor.turnClockWise();
        }
    }

    private static void turnSecondRotorTo(char c) {
        while (!secondRotor.getCurrent().equals(c)) {
            firstRotor.turnCounterClockWise();
            secondRotor.turnClockWise();
            thirdRotor.turnCounterClockWise();
        }
    }

    private static void turnFirstRotorTo(char c) {
        while (!firstRotor.getCurrent().equals(c)) {
            firstRotor.turnClockWise();
            secondRotor.turnCounterClockWise();
            thirdRotor.turnClockWise();
        }
    }
}
