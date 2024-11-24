package thecode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearListListener implements ActionListener {
    private CruiseInput cruiseInput;

    public ClearListListener(CruiseInput cruiseInput) {
        this.cruiseInput = cruiseInput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cruiseInput.getDestinationListModel().clear();
    }
}
