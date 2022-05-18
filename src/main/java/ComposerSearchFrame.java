import json.OpenOpusServiceFactory;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ComposerSearchFrame extends JFrame
{
    private JLabel intro;
    private JTextField searchBar;
    private JButton searchButton;
    private JLabel info;
    private JPanel searchPanel;
    private final JList<String> composerSearchResults = new JList<>();
    private final JList<String> composerWorks = new JList<>();


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
        addComposerWorks();
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
        searchBar.setText("Bach");
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
        composerWorks.setModel(new DefaultListModel<>());
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

    public void addComposerSearchResults(String[] composerNames)
    {
        composerSearchResults.setListData(composerNames);
        add(composerSearchResults, BorderLayout.WEST);
    }

    private void onComposerClicked(ListSelectionEvent listSelectionEvent)
    {
        if (!listSelectionEvent.getValueIsAdjusting()
                && composerSearchResults.getSelectedIndex() != -1)
        {
            presenter.loadComposerResult(composerSearchResults.getSelectedIndex());
        }
    }

    public void addComposerWorks()
    {
        JScrollPane scrollPane = new JScrollPane(composerWorks);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void setComposerWorks(String[] works)
    {
        composerWorks.setListData(works);
    }

    private void onWorkClicked(ListSelectionEvent listSelectionEvent)
    {
        if (!listSelectionEvent.getValueIsAdjusting()
                && composerSearchResults.getSelectedIndex() != -1)
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
