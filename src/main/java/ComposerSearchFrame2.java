import json.OpenOpusServiceFactory;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ComposerSearchFrame2 extends JFrame
{
    private JLabel intro;
    private JTextField searchBar;
    private JButton searchButton;
    private JLabel info;
    private final JList<String> composerSearchResults = new JList<>();
    private final JList<String> composerWorks = new JList<>();

    private int[] composerIds;

    private final OpenOpusServiceFactory factory = new OpenOpusServiceFactory();
    ComposerSearchPresenter2 presenter = new ComposerSearchPresenter2(this, factory.getInstance());

    private JPanel contentPane;

    public ComposerSearchFrame2()
    {
        setTitle("Play That Song Two");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container framePane = getContentPane();
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

        addIntro();
        addSearchBar();
        addSearchButton();
        addInfo();

        framePane.add(contentPane, BorderLayout.CENTER);
    }

    private void addIntro()
    {
        intro = new JLabel("Enter a composer's name and press search:");
        contentPane.add(intro);
    }

    private void addSearchBar()
    {
        searchBar = new JTextField(30);
        contentPane.add(searchBar);
    }

    private void addSearchButton()
    {
        searchButton = new JButton("Search");
        searchButton.addActionListener(this::onSubmitClicked);
        contentPane.add(searchButton);
    }

    public void onSubmitClicked(ActionEvent event)
    {
        presenter.loadSearchResults(searchBar.getText());
    }

    private void addInfo()
    {
        info = new JLabel();
        contentPane.add(info);
    }

    public void setInfo(String text)
    {
        info.setText(text);
    }

    public void addComposerSearchResults(String[] composerNames, int[] composerIds)
    {
        composerSearchResults.setListData(composerNames);
        composerSearchResults.addListSelectionListener(this::onComposerClicked);
        this.composerIds = composerIds;
        contentPane.add(composerSearchResults);
    }

    private void onComposerClicked(ListSelectionEvent listSelectionEvent)
    {
        int index = composerSearchResults.getSelectedIndex();
        presenter.loadComposerResult(composerIds[index]);
    }

    public void addComposerWorks(String[] works)
    {
        // todo: make scrollable, make sure both lists are shown (or that there's a back button)
//        JPanel panel = new JPanel(new BorderLayout());
        composerWorks.setListData(works);
//        composerWorks.addListSelectionListener(this::onWorkClicked);
        composerWorks.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent e)
            {
                presenter.openWorkInBrowser(composerWorks.getSelectedValue());
            }
        });
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(composerWorks);
        composerWorks.setLayoutOrientation(JList.VERTICAL);
        contentPane.add(scrollPane);
//        add(panel);
    }

    private void onWorkClicked(ListSelectionEvent listSelectionEvent)
    {
        // todo: fix
        presenter.openWorkInBrowser(composerWorks.getSelectedValue());
    }

    public static void main(String[] args)
    {
        JFrame frame = new ComposerSearchFrame2();
        frame.setVisible(true);
    }
}
