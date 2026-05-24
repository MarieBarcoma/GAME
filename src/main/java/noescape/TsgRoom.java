package noescape;

public class TsgRoom extends BaseRoom {
    public TsgRoom(String name, boolean isLocked, String puzzleQuestion, String correctAnswer, String clueText, String hintText) {
        super(name, isLocked, puzzleQuestion, correctAnswer, clueText, hintText);
    }

    @Override
    public void showClue() {
        lastMessage = "CLUE: " + clueText + " [TSG Terminal: answer is case-sensitive!]";
    }

    @Override
    public void showHint() {
        lastMessage = "HINT: " + hintText;
    }

    @Override
    public void checkAnswer(String playerAnswer) {
        if (playerAnswer.equals(correctAnswer)) { 
            isSolved = true;
            lastMessage = "Correct! You cleared: " + getName();
        } else {
            attemptCount++;
            lastMessage = "Incorrect answer. You've got this.  (Case-sensitive — check capitalisation!)";
        }
    }

    @Override
    public String getRoomType() { 
        return "TSG"; 
    }
}