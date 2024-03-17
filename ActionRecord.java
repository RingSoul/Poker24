// records an instance of a calculation done by a player, or other actions permitted as a gameplay move
public class ActionRecord {
    private Player actionPerformer;
    private String actionContent; // different string depending on actions performed (created by methods)

    public ActionRecord(Player actionPerformer) {
        this.actionPerformer = actionPerformer;
        actionContent = "";
    }

    // successful calculation to the desired result of 24 (or customized)
    public void recordSuccessfulCalculation(String infix, int pointsScored) {
        /* to be implemented with concatenation */
    }

    // failed calculation means a yielded result other than 24 (or customized)
    public void recordFailedCalculation(String infix, int result) {
        /* to be implemented with concatenation */
    }

    // joker card move: customized/randomized suit and value
    public void recordUsingJokerCard(JokerCard jokerCard, boolean isRandomized) {
        /* to be implemented with getSuit and getValue */
    }

    // draw card = spend 100 points
    public void recordCardDrawn(Card card) {
        /* to be implemented with getSuit and getValue */
    }

    // fill bag = spend 1000 points
    public void recordFillingBag() {
        /* to be implemented with concatenation */
    }

    // give card to another player = spend 150 points
    public void recordCardGivenToAnotherPlayer(Player recipient) {
        /* to be implemented with concatenation */
    }

    // skip turn and force the next player to take action (3 chances max)
    public void recordSkippingTurn() {
        /* to be implemented with concatenation */
    }

    public Player getActionPerformer() {
        return actionPerformer;
    }

    public void setActionPerformer(Player actionPerformer) {
        this.actionPerformer = actionPerformer;
    }

    public String getActionContent() {
        return actionContent;
    }

    public void setActionContent(String actionContent) {
        this.actionContent = actionContent;
    }
}
