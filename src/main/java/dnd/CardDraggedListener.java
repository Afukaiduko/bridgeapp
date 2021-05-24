package dnd;

import view.CardView;

import java.awt.*;
import java.awt.dnd.*;
import java.io.Serializable;

public class CardDraggedListener implements DragGestureListener, DragSourceListener, Serializable {
    private CardHolder holder;
    private CardView card;

    @Override
    public void dragGestureRecognized(DragGestureEvent event) {
        Cursor cursor = null;
        if (event.getComponent() instanceof CardView) {
            card = (CardView) event.getComponent();
        } else {
            System.out.println("We don't support drag and drop of this object");
            return;
        }

        if (event.getDragAction() == DnDConstants.ACTION_MOVE) {
            cursor = DragSource.DefaultMoveDrop;
        }

        // Remove Card from parent so we can move.
        holder = (CardHolder) card.getParent();
        holder.removeCard(card);

        System.out.println("Starting drag");
        event.startDrag(cursor, new TransferCard(card));
    }

    @Override
    public void dragEnter(DragSourceDragEvent dsde) {
    }

    @Override
    public void dragOver(DragSourceDragEvent dsde) {
    }

    @Override
    public void dropActionChanged(DragSourceDragEvent dsde) {
    }

    @Override
    public void dragExit(DragSourceEvent dse) {
    }

    @Override
    public void dragDropEnd(DragSourceDropEvent event) {
        System.out.println("Drag ended");
        if (!event.getDropSuccess()) {
            System.out.println("Drop failed so re-adding card to original panel");
            holder.acceptCard(card); // Add the card back if we failed the drop
        }
    }
}
