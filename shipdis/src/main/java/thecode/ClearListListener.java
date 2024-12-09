package thecode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearListListener implements ActionListener {
    private final CruiseInput cruiseInput;

    public ClearListListener(CruiseInput cruiseInput) {
        this.cruiseInput = cruiseInput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cruiseInput.getDestinationListModel().clear();
    }
}
