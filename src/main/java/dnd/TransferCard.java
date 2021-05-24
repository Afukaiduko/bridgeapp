package dnd;

import constants.Constants;
import view.CardView;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.util.Objects;

public class TransferCard implements Transferable {

    private CardView c;

    public TransferCard(CardView c) {
        this.c = c;
    }

    @Override
    public Object getTransferData(DataFlavor flavour) throws UnsupportedFlavorException {
        if (flavour.equals(Constants.CARD_FLAVOR)) {
            return c;
        } else {
            throw new UnsupportedFlavorException(flavour);
        }
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{Constants.CARD_FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavour) {
        return flavour.equals(Constants.CARD_FLAVOR);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferCard that = (TransferCard) o;
        return Objects.equals(c, that.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(c);
    }
}
