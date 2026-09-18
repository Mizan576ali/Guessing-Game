package project_1;

import java.util.*;


class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            Play player = new Play(sc);
            player.intro();
            player.difficultySelection();
            boolean isPredicted = player.gameOn();
            player.timeTaken();
            player.scoreTracking();
            if (!isPredicted) {
                while (true) {
                    System.out.println("\t\t---Do you want to know WHAT COMPUTER SELECTED?1(yes)/0(No)---");
                    int choice = sc.nextInt();
                    if (choice == 1) {
                        player.computerSelection();
                    }
                    if (choice == 1 || choice == 0) {
                        break;
                    }
                    System.out.println("Invalid choice!");
                }


            }
            System.out.println("Do you want to PLAY next ROUND? 1(yes)/0(No)");
            int playAgain = sc.nextInt();
            if (playAgain != 1) {
                System.out.println("Hey! ,looking for you to flex my brain.......");
                break;

            }
        }


    }

}

class Play {
    private long start;
    private long end;
    static int bestScore = Integer.MAX_VALUE;
    private final Scanner sc;
    private final int randomNo;
    Difficulty difficulty;

    Play(Scanner sc) {
        this.sc = sc;
        Random rn = new Random();
        randomNo = rn.nextInt(100);
    }

    void intro() {
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I am thinking of a number between 0(inclusive) to 100(exclusive)");
        System.out.println("You will have chances according to your difficulty");
        System.out.println("Alert!,we are tracking your time");
        System.out.println("\t\t----YOUR TIME STARTS NOW----");
        start = System.nanoTime();

    }

    private int turns = 0;

    void difficultySelection() {
        System.out.println("Plz select the difficulty level:");
        System.out.println("1.Easy(10 chances)\n2.Medium(5 chances)\n3.hard(3 chances)");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Great you have selected the Easy difficulty level");
                turns = Difficulty.Easy.getChoices();
                break;
            case 2:
                System.out.println("Great u have selected the Medium difficulty level");
                turns = Difficulty.Medium.getChoices();
                break;
            case 3:
                System.out.println("Great u have selected the Hard difficulty level");
                turns = Difficulty.Hard.getChoices();
            default:
                System.out.println("Invalid choice have been selected!");
        }

    }


    private int attempts = 0;

    boolean isGuessedCorrectly = false;

    boolean gameOn() {
        for (int i = 0; i < turns; i++) {
            attempts += 1;
            System.out.println("Enter your number");
            int playerNo = sc.nextInt();
            if (playerNo == randomNo) {
                System.out.println("Congratulation! You have guessed the correct number in " + attempts + " attempt(s)");
                isGuessedCorrectly = true;
                break;
            } else if (playerNo > randomNo) {
                System.out.println("Incorrect! The number is smaller than " + playerNo);
            } else {
                System.out.println("Incorrect! The number is greater than " + playerNo);
            }

            System.out.println("no Of Attempts LEFT: " + (turns - attempts));
        }
        if (!isGuessedCorrectly) {
            System.out.println("Oops!, You have used all the chances,Better luck next time");
        }
        return isGuessedCorrectly;
    }

    void scoreTracking() {
        if (bestScore > attempts && isGuessedCorrectly) {
            bestScore = attempts;

            System.out.println("Congratulation!, Your new HIGHEST SCORE is " + bestScore);
            return;

        }
        if (bestScore == Integer.MAX_VALUE) {
            bestScore = 0;
        }
        System.out.println("Your HIGHEST SCORE is " + bestScore);


    }

    void computerSelection() {
        System.out.println("\t\t###Computer selected number: " + randomNo);
    }

    void timeTaken() {
        end = System.nanoTime();
        long elapsed = end - start;

        System.out.println("\nYou have taken " + elapsed / 1_000_000_000 + " seconds in this turn");
    }


}

enum Difficulty {
    Easy(10),
    Medium(5),
    Hard(3);
    private final int choices;

    Difficulty(int choices) {
        this.choices = choices;
    }

    int getChoices() {
        return choices;
    }
}


