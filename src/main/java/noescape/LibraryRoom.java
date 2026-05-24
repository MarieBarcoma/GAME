package noescape;

public class LibraryRoom extends BaseRoom {
    public LibraryRoom(String name, boolean isLocked, String puzzleQuestion, String correctAnswer, String clueText, String hintText) {
        super(name, isLocked, puzzleQuestion, correctAnswer, clueText, hintText);
    }

    @Override
    public void showClue() {
        lastMessage = "CLUE: " + clueText;
    }

    @Override
    public void showHint() {
        attemptCount++; 
        lastMessage = "HINT (−1 attempt): " + hintText + "  [Attempt " + attemptCount + " consumed]";
    }

    @Override
    public void checkAnswer(String playerAnswer) {
        if (playerAnswer.trim().equalsIgnoreCase(correctAnswer)) {
            isSolved = true;
            lastMessage = "Correct! You cleared: " + getName();
        } else {
            attemptCount++;
            lastMessage = "Incorrect answer. You've got this.";
        }
    }

    @Override
    public String getRoomType() { 
        return "Library"; 
    }
}