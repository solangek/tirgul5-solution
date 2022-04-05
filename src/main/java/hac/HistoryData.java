package hac;

import java.util.ArrayList;

/**
 * This class represents the history data of a single user
 * Note that it is not thread safe.
 */
public class HistoryData {
    private ArrayList<String> history;
    private int lastResult;

    public HistoryData() {
        history = new ArrayList<String>();
        lastResult = 0;
    }

    public void  addHistory(String s) {
        history.add(s);
    }

    public void setLastResult(int r) {
        lastResult = r;
    }

    public ArrayList<String> getHistory() {
        // return a copy of history
        return history;
    }

    public int getLastResult() {
        return lastResult;
    }
}
