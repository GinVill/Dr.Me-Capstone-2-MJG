package GUI;

public class FakeMain {
    public static void main(String[] args) {
        try {
            GUIMain.main(args);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
