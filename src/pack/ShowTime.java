package pack;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

public class ShowTime extends Thread {

    private ArrayList<Thread> _ts;
    private int _times;
    private boolean _continue;
    private Thread t;
    private JTextField _j;
    private long requests = 0;

    ShowTime(int times, JTextField j) {
        _times = times;
        _ts = new ArrayList<Thread>();
        _continue = true;
        _j = j;
    }

    @Override
    public void run() {
        while (_continue) {
            _ts.clear();
            for (int i = 0; i < _times; i++) {
                t = new Thread(new JavaGetUrl());
                _ts.add(t);
            }
            for (int i = 0; i < _ts.size(); i++) {
                _ts.get(i).start();
            }
            for (int i = 0; i < _ts.size(); i++) {
                try {
                    _ts.get(i).join();
                    _j.setText(new Long(requests++).toString());
                } catch (InterruptedException ex) {
                    Logger.getLogger(ShowTime.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void stopCicle() {
        _continue = false;

        for (int i = 0; i < _ts.size(); i++) {
            Thread elem = _ts.get(i);

            while (elem.isAlive()) {
                elem.interrupt();
            }
        }
    }
}
