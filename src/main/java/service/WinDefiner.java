package service;

import java.util.ArrayList;
import java.util.HashMap;

public class WinDefiner {

 private String[] circle;

    public WinDefiner(String[] circle) {
        this.circle = circle;
    }


    public String define(String playerTurn, String compTurn) {
        if(playerTurn.equals(compTurn)) {
            return "Standoff";
        }

        HashMap<String,Integer> elements = new HashMap<String, Integer>();
        for (int i =0; i < circle.length; i++) {
            elements.put(circle[i],i);
        }
        int middle = circle.length / 2;
        ArrayList<Integer> winerPositions = new ArrayList<Integer>();
        for (int i = 1; i <= middle ; i++) {
          Integer elementId = elements.get(playerTurn);
          winerPositions.add((elementId + i) % circle.length);
        }

       Integer compTurnId = elements.get(compTurn);
       if(winerPositions.contains(compTurnId)) {
           return "you lost";
       }

        return "you win";
    }
}
