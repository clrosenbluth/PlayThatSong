import json.OpenOpusServiceFactory;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ComposerSearchFrame extends JFrame
{
    private JLabel intro;
    private JPanel introPanel;
    private JTextField searchBar;
    private JButton searchButton;
    private JLabel info;
    private JPanel searchPanel;
    private JList<String> composerSearchResults = new JList<>();
    private JList<String> composerWorks = new JList<>();

    private int[] composerIds;

    private final OpenOpusServiceFactory factory = new OpenOpusServiceFactory();
    ComposerSearchPresenter presenter = new ComposerSearchPresenter(this, factory.getInstance());

    public ComposerSearchFrame()
    {
        composerSearchResults.addListSelectionListener(this::onComposerClicked);
        composerWorks.addListSelectionListener(this::onWorkClicked);

        setTitle("Play That Song");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        addSearchPanel();
        addIntro();
        addSearchBar();
        addSearchButton();
        addInfo();
    }

    private void addSearchPanel()
    {
        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        add(searchPanel, BorderLayout.NORTH);
    }

    private void addIntro()
    {
        intro = new JLabel("Enter a composer's name and press search:");
        searchPanel.add(intro);
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

    private void addInfo()
    {
        info = new JLabel();
        searchPanel.add(info);
    }

    public void setInfo(String text)
    {
        info.setText(text);
    }

    public void addComposerSearchResults(String[] composerNames, int[] composerIds)
    {
        composerSearchResults.setListData(composerNames);
        this.composerIds = composerIds;
        add(composerSearchResults, BorderLayout.WEST);
    }

    private void onComposerClicked(ListSelectionEvent listSelectionEvent)
    {
        int index = composerSearchResults.getSelectedIndex();
        presenter.loadComposerResult(composerIds[index]);
    }

    public void addComposerWorks(String[] works)
    {
        // todo: make scrollable, make sure both lists are shown (or that there's a back button)
        composerWorks.setListData(works);
        add(composerWorks, BorderLayout.CENTER);
    }

    private void onWorkClicked(ListSelectionEvent listSelectionEvent)
    {
        // todo: fix
        if (!listSelectionEvent.getValueIsAdjusting())
        {
            presenter.openWorkInBrowser(composerWorks.getSelectedValue());
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new ComposerSearchFrame();
        frame.setVisible(true);
    }

}
