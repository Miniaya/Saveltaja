package saveltaja.ui;

import saveltaja.io.*;

public class Main {
    
    public static void main(String[] args) {
        IO io = new ConsoleIO();
        CLUI ui = new CLUI(io);
        ui.init();
    }
    
}
