package dnd;

import constants.Constants;
import view.CardView;

import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.io.Serializable;

public class CardDroppedListener implements DropTargetListener, Serializable {

    private final CardHolder target;

    public CardDroppedListener(CardHolder target) {
        this.target = target;
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
    }

    @Override
    public void drop(DropTargetDropEvent event) {
        System.out.println("Trying to drop onto panel");
        try {
            Transferable tr = event.getTransferable();
            CardView c = (CardView) tr.getTransferData(Constants.CARD_FLAVOR);

            if (event.isDataFlavorSupported(Constants.CARD_FLAVOR)) {
                event.acceptDrop(DnDConstants.ACTION_MOVE);

                // Tell the drop panel to get the card and remove it from the other panels:

                target.acceptCard(c);
                System.out.println("Drop success");
                event.dropComplete(true);
                return;
            }

            event.rejectDrop();
        } catch (Exception e) {
            e.printStackTrace();
            event.rejectDrop();
        }
        System.out.println("Drop Failed");
        event.dropComplete(false);
    }
}
