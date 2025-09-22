package translation;

import javax.swing.*;
import java.awt.event.*;


// TODO Task D: Update the GUI for the program to align with UI shown in the README example.
//            Currently, the program only uses the CanadaTranslator and the user has
//            to manually enter the language code they want to use for the translation.
//            See the examples package for some code snippets that may be useful when updating
//            the GUI.
public class GUI {

    private static String curCountry;
    private static String curLanguage;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JPanel countryPanel = new JPanel();
            JTextField countryField = new JTextField(10);
//            countryField.setText(curCountry);
//            countryField.setEditable(false);
            countryPanel.add(new JLabel("Country:"));
//            countryPanel.add(countryField);

            JComboBox<String> countryComboBox = new JComboBox<>();
            for(String countryCode : new JSONTranslator().getCountryCodes()) {
                countryComboBox.addItem(countryCode);
            }
            countryPanel.add(countryComboBox);

            JPanel translationPanel = new JPanel();
            JLabel resultLabelText = new JLabel("Translation:");
            translationPanel.add(resultLabelText);
            JLabel resultLabel = new JLabel();
            translationPanel.add(resultLabel);

            countryComboBox.addItemListener(new ItemListener() {

                /**
                 * Invoked when an item has been selected or deselected by the user.
                 * The code written for this method performs the operations
                 * that need to occur when an item is selected (or deselected).
                 *
                 * @param e the event to be processed
                 */
                @Override
                public void itemStateChanged(ItemEvent e) {

                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        curCountry = countryComboBox.getSelectedItem().toString();
                        JOptionPane.showMessageDialog(null, "user selected " + curCountry + "!");
                        resultLabel.setText(new JSONTranslator().translate(curCountry, curLanguage));
                    }
                }
            });



            JPanel languagePanel = new JPanel();
            //JTextField languageField = new JTextField(10);
            languagePanel.add(new JLabel("Language:"));
            JComboBox<String> languageComboBox = new JComboBox<>();
            for(String languageCode : new JSONTranslator().getLanguageCodes()) {
                languageComboBox.addItem(languageCode);
            }
            languagePanel.add(languageComboBox);



            // add listener for when an item is selected.
            languageComboBox.addItemListener(new ItemListener() {

                /**
                 * Invoked when an item has been selected or deselected by the user.
                 * The code written for this method performs the operations
                 * that need to occur when an item is selected (or deselected).
                 *
                 * @param e the event to be processed
                 */
                @Override
                public void itemStateChanged(ItemEvent e) {

                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        curLanguage = languageComboBox.getSelectedItem().toString();
                        JOptionPane.showMessageDialog(null, "user selected " + curLanguage + "!");
                        resultLabel.setText(new JSONTranslator().translate(curCountry, curLanguage));
                    }
                }
            });


//            JPanel buttonPanel = new JPanel();
//            JButton submit = new JButton("Submit");
//            buttonPanel.add(submit);


//            // adding listener for when the user clicks the submit button
//            submit.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    String language = languageComboBox.getText();
//                    String country = countryField.getText();
//
//                    // for now, just using our simple translator, but
//                    // we'll need to use the real JSON version later.
//                    Translator translator = new CanadaTranslator();
//
//                    String result = translator.translate(country, language);
//                    if (result == null) {
//                        result = "no translation found!";
//                    }
//                    resultLabel.setText(result);
//
//                }
//
//            });

            curCountry = countryComboBox.getSelectedItem().toString();
            curLanguage = languageComboBox.getSelectedItem().toString();
            resultLabel.setText(new JSONTranslator().translate(curCountry, curLanguage));
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.add(countryPanel);
            mainPanel.add(languagePanel);
            mainPanel.add(translationPanel);

            JFrame frame = new JFrame("Country Name Translator");
            frame.setContentPane(mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);


        });
    }
}