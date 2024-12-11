package language_Guide;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a matching question where the user is asked to match a word with its translation or definition.
 * This class extends the abstract Question class and implements specific behaviors for matching questions.
 */
public abstract class Matching extends Question {
    private String language;
    private ArrayList<Word> choices;
    private ArrayList<Word> shuffledChoices;
    private Word userAnswer;

    /**
     * Constructs a Matching question with a specific language and a list of possible answer choices.
     *
     * @param language The language for which the question is being created.
     * @param answerChoices A list of Word objects that are the possible choices for the question.
     */
    public Matching(String language, ArrayList<Word> answerChoices) {
        super(language);
        this.language = language;
        this.choices = answerChoices;
        this.shuffledChoices = shuffle(answerChoices);
    }

    /**
     * Shuffles the list of possible answer choices.
     *
     * @param answerChoices The original list of choices to shuffle.
     * @return A shuffled list of choices.
     */
    private ArrayList<Word> shuffle(ArrayList<Word> answerChoices) {
        ArrayList<Word> shuffledList = new ArrayList<>(answerChoices);
        Collections.shuffle(shuffledList);
        return shuffledList;
    }

    /**
     * Sets the user's answer.
     *
     * @param userAnswer The word selected by the user as their answer.
     */
    public void setUserAnswer(Word userAnswer) {
        this.userAnswer = userAnswer;
    }

    /**
     * Checks if the user's answer is correct.
     *
     * @param correctWord The correct word to match against.
     * @param user The user object to update progress.
     * @return True if the user's answer matches the correct word; otherwise, false.
     */
    public boolean isMatchCorrect(Word correctWord, User user) {
        boolean isCorrect = correctWord.equals(userAnswer);
        user.getUserDictionary().getWordByUUID(correctWord.getWordUUID()).wordPresented(isCorrect);
        return isCorrect;
    }

    /**
     * Provides a formatted string representing the question and its choices.
     *
     * @return A string that lists the word to match along with all possible choices.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Match the following word to one of the choices:\n");

        for (int i = 0; i < shuffledChoices.size(); i++) {
            result.append(i + 1).append(". ").append(shuffledChoices.get(i).getWordinLanguage()).append("\n");
        }

        return result.toString();
    }
}
