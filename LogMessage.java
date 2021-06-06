package model;

/**
 * A system message which consists of a machine ID and a description. A colon separates the machine ID from the
 * description.
 */
public class LogMessage {
    /**
     * The Machine ID of this log message
     */
    private String machineId;
    /**
     * The description of this log message
     */
    private String description;

    /**
     * Constructs a new LogMessage object.
     * @param message The whole log message, with a Machine ID and a description
     */
    public LogMessage(String message) {
        int colon = message.indexOf(":");
        machineId = message.substring(0, colon);
        description = message.substring(colon + 1);
    }

    /**
     * Checks if the description contains a certain keyword.
     * @param keyword The keyword to check for
     * @return true if the keyword is found within the description, false if not
     */
    public boolean containsWord(String keyword) {
        if (description.equals(keyword)) {
            return true;
        }
        else if(description.indexOf(keyword + "") == 0) {
            return true;
        }
        else if(description.indexOf(" " + keyword + " ") != -1) {
            return true;
        }
        else if(description.length() > keyword.length()) {
            if ((description.substring(description.length() -
                    keyword.length() - 1).equals(" " + keyword))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Getter for the machine ID.
     * @return the machine ID of this log message
     */
    public String getMachineId() {return machineId;}

    /**
     * Getter for the description.
     * @return The description of this log message
     */
    public String getDescription() {return description;}

    /**
     * @return This log message as one String.
     */
    public String toString() {
        return machineId + ":" + description;
    }
}
