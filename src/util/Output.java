package util;
/**
 * legacy code for console/terminal IO.
 * */

public class Output {

    public static void printLoading(int timeToLoad) throws InterruptedException {
        printNewLines(2);
        // Print a period every second for the timeToLoad value
        for(int i = 0; i < timeToLoad; i++){
            printColor(".", Colors.ANSI_YELLOW, false);
            Thread.sleep(1000);
        }
        printNewLines(2);

    }

    public static void printColor(String text, String color, boolean newLine){
        if(newLine){
            System.out.println(color + text + color);
        } else {
            System.out.print(color + text + color);
        }
    }

    public static void printNewLines(int numLines){
        for(int i = 0; i < numLines; i ++){
            System.out.println();
        }
    }
}
