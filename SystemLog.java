package model;

import view.GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Stores all log messages contained within this system.
 */
public class SystemLog {
    /**
     * The GUI that modifies this system log
     */
    private GUI gui;
    /**
     * The list of log messages stored on this system log, size capped at 20
     */
    private List<LogMessage> log;
    /**
     * If this system log contains 20 log messages, this is true
     */
    private boolean full;

    /**
     * Constructs a new SystemLog object.
     * @param g The GUI that modifies this system log
     */
    public SystemLog(GUI g) {
        full = false;
        gui = g;
        log = new ArrayList<LogMessage>();

        startup(new LogMessage("CLIENT:Login failure"), new LogMessage("CLIENT:Automatically signed out"),
                new LogMessage("SERVER:Connection successful"), new LogMessage("SERVER:Disconnected"),
                new LogMessage("SYSTEM:Error detected"), new LogMessage("SYSTEM:Crashed, system will restart"),
                new LogMessage("SYSTEM:Download successful"), new LogMessage("CLOUD:Upload successful"),
                new LogMessage("CLOUD:Upload failed, no Internet connection"),
                new LogMessage("SYSTEM:External drive detected"));
    }

    /**
     * Creates the initial list by adding log messages to the system log.
     * @param logMessages The initial log messages to be added to the list
     */
    private void startup(LogMessage... logMessages) {
        for (int i = 0; i < logMessages.length; i++) {
            log.add(logMessages[i]);
        }
    }

    /**
     * Getter for the full boolean.
     * @return true if the system log is full, false if not
     */
    public boolean isFull() {return full;}

    /**
     * Adds a log message to the system log if the system log is not full.
     * @param message The log message to be added to the system log.
     * @return true if the log message was successfully added, false if not.
     */
    public boolean addMessage(LogMessage message) {
        if (!full) {
            log.add(message);
            if(log.size() == 20) {
                full = true;
            }
            return true;
        }
        return false;
    }

    /**
     * Getter for the list of log messages.
     * @return The list of log messages stored on this system log
     */
    public List<LogMessage> viewAll() {
        return log;
    }

    /**
     * Searches the list for all log messages with a certain machine ID and returns a list of log messages stored on
     * the system log which contain the specified machine ID.
     * @param m All log messages with this machine ID will be returned
     * @return A list of log messages containing the specified machine ID
     */
    public List<LogMessage> searchByMachineID(String m) {
        List<LogMessage> temp = new ArrayList<LogMessage>();
        for(int i = 0; i < log.size(); i++) {
            if (log.get(i).getMachineId().equalsIgnoreCase(m)) {
                temp.add(log.get(i));
            }
        }
        return temp;
    }

    /**
     * Searches the list for all log messages with a certain machine ID and removes all log messages stored on
     * the system log which contain the specified machine ID.
     * @param m All log messages with this machine ID will be removed
     * @return A list of removed log messages
     */
    public List<LogMessage> removeByMachineID(String m) {
        List<LogMessage> temp = new ArrayList<LogMessage>();
        for(int i = 0; i < log.size(); i++) {
            if (log.get(i).getMachineId().equalsIgnoreCase(m)) {
                temp.add(log.remove(i));
                i--;
            }
        }
        if (log.size() < 20) {
            full = false;
        }
        return temp;
    }

    /**
     * Searches the list for all log messages whose descriptions contain a keyword and returns these log messages in
     * a list.
     * @param keyword All log messages which have descriptions that contain this keyword will be returned
     * @return A list of log messages which have descriptions that contain the keyword
     */
    public List<LogMessage> searchByDescription(String keyword) {
        List<LogMessage> temp = new ArrayList<LogMessage>();
        for (int i = 0; i < log.size(); i++)
        {
            if (log.get(i).containsWord(keyword))
            {
                temp.add(log.get(i));
            }
        }
        return temp;
    }

    /**
     * Searches the list for all log messages whose descriptions contain a keyword and removes these log messages.
     * @param keyword All log messages which have descriptions that contain this keyword will be removed
     * @return A list of log messages which have been removed
     */
    public List<LogMessage> removeByDescription(String keyword) {
        List<LogMessage> temp = new ArrayList<LogMessage>();
        for (int i = 0; i < log.size(); i++)
        {
            if (log.get(i).containsWord(keyword))
            {
                temp.add(log.remove(i));
                i--;
            }
        }
        if(log.size() < 20) {
            full = false;
        }
        return temp;
    }

    public String generate() {
        Random r = new Random();
        int x = r.nextInt(log.size());
        String temp = "";
        for (int i = 0; i < log.size(); i++) {
            if (i == x) {
                temp = log.get(i).getDescription();
            }
        }
        return temp;
    }

    /**
     * Counts the number of descriptions for a certain machine ID
     * @return The number of descriptions for a certain machine ID
     */
    public int count() {
        List<String> list = new ArrayList<String>();
        int cnt = 0;
        for (LogMessage l : log) {
           String temp = l.getMachineId();
           list.add(l.getDescription());
           for (int i = 0; i < log.size(); i++) {
               String temp2 = log.get(i).getMachineId();
               String temp3 = log.get(i).getDescription();
               if (temp == temp2 && !(list.contains(temp3))) {
                   cnt++;
                   list.add(temp3);
               }
           }
        }
        return cnt;
    }
}
