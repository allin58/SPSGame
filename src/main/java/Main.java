import exteption.ExitException;
import exteption.WrongNumberException;
import service.SHAGen;
import service.WinDefiner;
import java.util.Random;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {


    public static void main(String[] args) {

        try {
            if (!(args.length >= 3) || args.length % 2 == 0) {
                throw new WrongNumberException();
            }

            Random rand = new Random();
            int int_random = rand.nextInt(args.length);
            String compTurn = args[int_random];
            String key = SHAGen.getKey();
            String sha = SHAGen.sha3(key + compTurn);
            System.out.println("HMAC:" + sha);


            System.out.println("Available moves:");
            for (int i = 0; i < args.length; i++) {
                System.out.println(i+1 + " - " + args[i]);
            }
            System.out.println("0 - exit");

            System.out.print("Enter your move: ");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int playerTurnId = Integer.parseInt(reader.readLine());
            if(playerTurnId > args.length || playerTurnId < 0) {
                throw new WrongNumberException();
            }
            if(playerTurnId == 0) {
                throw new ExitException();
            }
            String playerTurn = args[playerTurnId-1];
            System.out.println("Your move: " + playerTurn);

            System.out.println("Computer move: " + compTurn);
            WinDefiner winDefiner = new WinDefiner(args);

            System.out.println(winDefiner.define(playerTurn,compTurn));
            System.out.println("HMAC key: " + key );


        } catch (WrongNumberException e) {
            System.out.println("Error: Wrong number of arguments");
        }  catch (IOException e) {
            System.out.println("Reading error");
        } catch (ExitException e) {
            System.out.println("Good bye");
        }


    }


}
