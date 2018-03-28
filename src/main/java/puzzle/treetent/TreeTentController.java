package puzzle.treetent;

import controller.ElementController;
import model.gameboard.ElementData;

import java.awt.event.MouseEvent;

public class TreeTentController extends ElementController
{

    @Override
    public void changeCell(MouseEvent e, ElementData data)
    {
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            if(e.isControlDown())
            {
                this.boardView.getSelectionPopupMenu().show(boardView, this.boardView.getCanvas().getX() + e.getX(), this.boardView.getCanvas().getY() + e.getY());
            }
            else
            {
                if(data.getValueInt() == 0)
                {
                    data.setValueInt(2);
                }
                else if(data.getValueInt() == 2)
                {
                    data.setValueInt(3);
                }
                else
                {
                    data.setValueInt(0);
                }
            }
        }
        else if(e.getButton() == MouseEvent.BUTTON3)
        {
            if(data.getValueInt() == 0)
            {
                data.setValueInt(3);
            }
            else if(data.getValueInt() == 2)
            {
                data.setValueInt(0);
            }
            else
            {
                data.setValueInt(2);
            }
        }
    }
}