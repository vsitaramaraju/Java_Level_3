package day_3_lab_1;

public class InvalidEmployeeRecordException extends Exception{
	private final int lineNumber;
	private final String reason;
	
	public InvalidEmployeeRecordException(int lineNumber, String reason) {
		   super("Line " + lineNumber + ": " + reason);
		   this.lineNumber = lineNumber;
	       this.reason = reason;	
	}
	
	public int getLineNumber() {
        return lineNumber;
    }

    public String getReason() {
        return reason;
    }
}
