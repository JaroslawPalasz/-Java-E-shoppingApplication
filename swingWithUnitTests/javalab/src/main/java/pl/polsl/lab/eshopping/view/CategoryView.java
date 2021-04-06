package pl.polsl.lab.eshopping.view;

import javax.swing.*;

/**
 * Class representing view of categories
 */
public class CategoryView {
    /**
     * Main panel of categoryView gui
     */
    private JPanel panel1;
    /**
     * Category2 button
     */
    private JButton category2;
    /**
     * Category3 button
     */
    private JButton category3;
    /**
     * Logout button
     */
    private JButton logout;
    /**
     * Category1 button
     */
    private JButton category1;
    /**
     * Category4 button
     */
    private JButton category4;
    /**
     * Label representing which client is logged in
     */
    private JLabel loggedin;
    /**
     * Back button
     */
    private JButton back;

    /**
     * Getter of label representing which client is logged in
     * @return loggedin label
     */
    public JLabel getLoggedin() { return loggedin; }

    /**
     * Getter of back button
     * @return back button
     */
    public JButton getBack() { return back; }

    /**
     * Getter of main panel
     * @return panel
     */
    public JPanel getPanel1() { return panel1; }

    /**
     * Getter of Category2 button
     * @return Category2 button
     */
    public JButton getCategory2() { return category2; }

    /**
     * Getter of Category3 button
     * @return category3 button
     */
    public JButton getCategory3() { return category3; }

    /**
     * Getter of logout button
     * @return logout button
     */
    public JButton getLogout() { return logout; }

    /**
     * Getter of Category1 button
     * @return category1 button
     */
    public JButton getCategory1() { return category1; }

    /**
     * Getter of Category4 button
     * @return category4 button
     */
    public JButton getCategory4() { return category4; }
}
