package edu.rpi.legup.puzzle.shorttruthtable.rules;

import edu.rpi.legup.model.gameboard.Board;
import edu.rpi.legup.model.gameboard.PuzzleElement;
import edu.rpi.legup.model.rules.BasicRule;
import edu.rpi.legup.model.tree.TreeNode;
import edu.rpi.legup.model.tree.TreeTransition;

import edu.rpi.legup.puzzle.shorttruthtable.ShortTruthTableBoard;
import edu.rpi.legup.puzzle.shorttruthtable.ShortTruthTableCell;
import edu.rpi.legup.puzzle.shorttruthtable.ShortTruthTableCellType;
import edu.rpi.legup.model.rules.ContradictionRule;

import java.awt.*;
import java.util.List;

public abstract class BasicRule_Generic extends BasicRule {

    public BasicRule_Generic(String ruleName, String description, String imageName){
        super(ruleName, description, imageName);
    }

    public String checkRuleRawAt(TreeTransition transition, PuzzleElement element){

        //Check that the puzzle element is no unknown
        ShortTruthTableBoard board = (ShortTruthTableBoard) transition.getBoard();
        ShortTruthTableCell cell = (ShortTruthTableCell) board.getPuzzleElement(element);
        if(!cell.isAssigned()){
            return "Only assigned cells are allowed for basic rules";
        }

        //check that it is assigned to the right value
        ShortTruthTableBoard originalBoard = (ShortTruthTableBoard) transition.getParents().get(0).getBoard();

        //Use this board to check what would happen if the cell what the oppisite value
        ShortTruthTableBoard testBoard = originalBoard.copy();
        ((ShortTruthTableCell) testBoard.getPuzzleElement(element)).setType(cell.getType().getNegation());

        //see if there is a contradiction
        ContradictionRule contradictionRule = getContradictionRule();
        String checkContradiction = contradictionRule.checkContradictionAt(testBoard, element);
        if(checkContradiction != null){
            return "Opisite contradiction failed: "+checkContradiction;
        }

        return null;

    }

    abstract ContradictionRule getContradictionRule();


    /**
     * Creates a transition {@link Board} that has this rule applied to it using the {@link TreeNode}.
     *
     * @param node short truth table board used to create default transition board
     * @return default board or null if this rule cannot be applied to this tree node
     */
    @Override
    public Board getDefaultBoard(TreeNode node) {
        return null;
    }
}