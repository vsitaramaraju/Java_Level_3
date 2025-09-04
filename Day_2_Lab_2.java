package lab;
import java.util.EnumSet;
import java.util.Set;

public class Day_2_Lab_2 {
	 public static void main(String[] args) {
	        Document doc = new Document();

	        doc.transitionTo(DocumentState.REVIEW);    
	        doc.transitionTo(DocumentState.APPROVED);  
	        doc.transitionTo(DocumentState.ARCHIVED);  
	        doc.transitionTo(DocumentState.PUBLISHED); 
	        doc.transitionTo(DocumentState.ARCHIVED);  
	    }
}

enum DocumentState {
	DRAFT,              
	REVIEW,         
	APPROVED,           
	PUBLISHED,       
	ARCHIVED;         

	private Set<DocumentState> allowedTransitions;

	static {
		DRAFT.allowedTransitions = EnumSet.of(REVIEW);
		REVIEW.allowedTransitions = EnumSet.of(APPROVED, DRAFT);
		APPROVED.allowedTransitions = EnumSet.of(PUBLISHED);
		PUBLISHED.allowedTransitions = EnumSet.of(ARCHIVED);
		ARCHIVED.allowedTransitions = EnumSet.noneOf(DocumentState.class);
	}

	public boolean canTransitionTo(DocumentState nextState) {
		return allowedTransitions.contains(nextState);
	}
}

class Document {
    private DocumentState currentState;

    public Document() {
        this.currentState = DocumentState.DRAFT; // default initial state
    }

    public DocumentState getCurrentState() {
        return currentState;
    }

    public void transitionTo(DocumentState nextState) {
        if (currentState.canTransitionTo(nextState)) {
            System.out.println("Transitioning from " + currentState + " → " + nextState);
            this.currentState = nextState;
        } else {
            System.out.println("Invalid transition: " + currentState + " → " + nextState);
        }
    }
}
