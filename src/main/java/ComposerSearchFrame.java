import json.OpenOpusServiceFactory;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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
    private JPanel searchResultPanel;

    private int[] composerIds;

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
        addInfo();
        addSearchResultPanel();
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

    private void addInfo()
    {
        info = new JLabel();
        searchPanel.add(info);
    }

    public void setInfo(String text)
    {
        info.setText(text);
    }

    private void addSearchResultPanel()
    {
        searchResultPanel = new JPanel();
        searchResultPanel.setLayout(new FlowLayout());
        add(searchResultPanel);
    }

    public void addSearchResults(String[] composerNames, int[] composerIds)
    {
        composerSearchResults.setListData(composerNames);
        composerSearchResults.addListSelectionListener(this::onComposerClicked);
        this.composerIds = composerIds;
        searchResultPanel.add(composerSearchResults);
        add(searchResultPanel);
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
        composerWorks.addListSelectionListener(this::onWorkClicked);
        searchResultPanel.add(composerWorks);
        add(searchResultPanel);
    }

    private void onWorkClicked(ListSelectionEvent listSelectionEvent)
    {
        // todo: fix
        Desktop desktop = Desktop.getDesktop();
        try
        {
            String urlString = "https://www.youtube.com/results?search_query=" +
                    URLEncoder.encode(composerWorks.getSelectedValue(), "UTF-8");
            info.setText(urlString);
        }
        catch (UnsupportedEncodingException e)
        {
            info.setText(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new ComposerSearchFrame();
        frame.setVisible(true);
    }

}
