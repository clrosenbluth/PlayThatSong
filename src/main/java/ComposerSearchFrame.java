import json.OpenOpusServiceFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ComposerSearchFrame extends JFrame
{
    private JLabel intro;
    private JPanel introPanel;
    private JTextField searchBar;
    private JButton searchButton;
    private JButton[] searchResults;
    private JPanel searchPanel;

    private final OpenOpusServiceFactory factory = new OpenOpusServiceFactory();
    ComposerSearchPresenter presenter = new ComposerSearchPresenter(this, factory.getInstance());

    public ComposerSearchFrame()
    {
        setTitle("Play That Song");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        addIntroPanel();
        addIntro();
        addSearchPanel();
        addSearchBar();
        addSearchButton();
    }

    private void addIntroPanel()
    {
        introPanel = new JPanel();
        introPanel.setLayout(new FlowLayout());
        add(introPanel);
    }

    private void addIntro()
    {
        intro = new JLabel("Enter a composer's name and press search:");
        introPanel.add(intro);
    }

    private void addSearchPanel()
    {
        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        add(searchPanel);
    }

    private void addSearchBar()
    {
        searchBar = new JTextField(30);
        searchPanel.add(searchBar);
    }

    private void addSearchButton()
    {
        searchButton = new JButton("Search");
        searchButton.addActionListener(this::onSubmitClicked);
        searchPanel.add(searchButton);
    }

    public void onSubmitClicked(ActionEvent event)
    {
        presenter.loadSearchResults(searchBar.getText());
    }

    public static void main(String[] args)
    {
        JFrame frame = new ComposerSearchFrame();
        frame.setVisible(true);
    }

}
