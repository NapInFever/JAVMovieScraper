package moviescraper.doctord.GUI;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import moviescraper.doctord.Movie;
import moviescraper.doctord.SiteParsingProfile.SiteParsingProfileItem;
import moviescraper.doctord.SiteParsingProfile.SpecificProfileFactory;
import moviescraper.doctord.controller.BrowseDirectoryAction;
import moviescraper.doctord.controller.BrowseUriAction;
import moviescraper.doctord.controller.MoveToNewFolderAction;
import moviescraper.doctord.controller.OpenFileAction;
import moviescraper.doctord.controller.RefreshDirectoryAction;
import moviescraper.doctord.controller.ScrapeMovieAction;
import moviescraper.doctord.controller.ScrapeMovieActionAutomatic;
import moviescraper.doctord.controller.ScrapeMovieActionData18Movie;
import moviescraper.doctord.controller.ScrapeMovieActionData18WebContent;
import moviescraper.doctord.controller.ScrapeSpecificAction;
import moviescraper.doctord.controller.WriteFileDataAction;
import moviescraper.doctord.preferences.MoviescraperPreferences;

public class GUIMainMenuBar extends JMenuBar{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MoviescraperPreferences preferences;
	private GUIMain guiMain;
	

	
	public GUIMainMenuBar(GUIMain guiMain)
	{
		this.preferences = guiMain.getPreferences();
		this.guiMain = guiMain;
		initializeMenus();
	}
	
	private void initializePreferencesMenu(){
		

		//Set up the preferences menu
		JMenu preferenceMenu = new JMenu("Preferences");
		preferenceMenu.setMnemonic(KeyEvent.VK_P);
		preferenceMenu.getAccessibleContext().setAccessibleDescription(
				"Preferences for JAVMovieScraper");


		//Checkbox for writing fanart and poster
		JCheckBoxMenuItem writeFanartAndPosters = new JCheckBoxMenuItem("Write fanart and poster files");
		writeFanartAndPosters.setState(getPreferences().getWriteFanartAndPostersPreference());
		writeFanartAndPosters.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				//save the menu choice off to the preference object (and the disk based settings file)
				if(e.getStateChange() == ItemEvent.SELECTED)
					getPreferences().setWriteFanartAndPostersPreference(true);
				else if(e.getStateChange() == ItemEvent.DESELECTED)
					getPreferences().setWriteFanartAndPostersPreference(false);

			}
		});
		preferenceMenu.add(writeFanartAndPosters);

		//Checkbox for overwriting fanart and poster
		JCheckBoxMenuItem overwriteFanartAndPosters = new JCheckBoxMenuItem("Overwrite fanart and poster files");
		overwriteFanartAndPosters.setState(getPreferences().getOverWriteFanartAndPostersPreference());
		overwriteFanartAndPosters.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				//save the menu choice off to the preference object (and the disk based settings file)
				if(e.getStateChange() == ItemEvent.SELECTED)
					getPreferences().setOverWriteFanartAndPostersPreference(true);
				else if(e.getStateChange() == ItemEvent.DESELECTED)
					getPreferences().setOverWriteFanartAndPostersPreference(false);

			}
		});
		preferenceMenu.add(overwriteFanartAndPosters);

		//Checkbox for overwriting writing actors to .actor folder
		JCheckBoxMenuItem writeActorImages = new JCheckBoxMenuItem("Write Actor Images");
		writeActorImages.setState(getPreferences().getDownloadActorImagesToActorFolderPreference());
		writeActorImages.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				//save the menu choice off to the preference object (and the disk based settings file)
				if(e.getStateChange() == ItemEvent.SELECTED)
					getPreferences().setDownloadActorImagesToActorFolderPreference(true);
				else if(e.getStateChange() == ItemEvent.DESELECTED)
					getPreferences().setDownloadActorImagesToActorFolderPreference(false);

			}
		});
		preferenceMenu.add(writeActorImages);

		//Checkbox for scraping extrafanart		
		JCheckBoxMenuItem scrapeExtraFanart = new JCheckBoxMenuItem("Write Extrafanart When Writing Data to a Directory or Moving File to a Directory");
		scrapeExtraFanart.setState(getPreferences().getExtraFanartScrapingEnabledPreference());
		scrapeExtraFanart.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				//save the menu choice off to the preference object (and the disk based settings file)
				if(e.getStateChange() == ItemEvent.SELECTED)
					getPreferences().setExtraFanartScrapingEnabledPreference(true);
				else if(e.getStateChange() == ItemEvent.DESELECTED)
					getPreferences().setExtraFanartScrapingEnabledPreference(false);

			}
		});
		preferenceMenu.add(scrapeExtraFanart);

		//Checkbox for also creating folder.jpg	in addition to the poster file jpg
		JCheckBoxMenuItem createFolderJpg = new JCheckBoxMenuItem("Create folder.jpg for each folder");
		createFolderJpg.setState(getPreferences().getCreateFolderJpgEnabledPreference());
		createFolderJpg.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				//save the menu choice off to the preference object (and the disk based settings file)
				if(e.getStateChange() == ItemEvent.SELECTED)
					getPreferences().setCreateFolderJpgEnabledPreference(true);
				else if(e.getStateChange() == ItemEvent.DESELECTED)
					getPreferences().setCreateFolderJpgEnabledPreference(false);

			}
		});
		preferenceMenu.add(createFolderJpg);

		//Checkbox for using fanart.jpg and poster.jpg, not moviename-fanart.jpg and moviename-poster.jpg
		JCheckBoxMenuItem noMovieNameInImageFiles = new JCheckBoxMenuItem("Save poster and fanart as fanart.jpg and poster.jpg instead of moviename-fanart.jpg and moviename-poster.jpg");
		noMovieNameInImageFiles.setState(getPreferences().getNoMovieNameInImageFiles());
		noMovieNameInImageFiles.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				//save the menu choice off to the preference object (and the disk based settings file)
				if(e.getStateChange() == ItemEvent.SELECTED)
					getPreferences().setNoMovieNameInImageFiles(true);
				else if(e.getStateChange() == ItemEvent.DESELECTED)
					getPreferences().setNoMovieNameInImageFiles(false);

			}
		});
		preferenceMenu.add(noMovieNameInImageFiles);

		//Checkbox for writing the trailer to file
		JCheckBoxMenuItem writeTrailerToFile = new JCheckBoxMenuItem("Write Trailer To File");
		writeTrailerToFile.setState(getPreferences().getWriteTrailerToFile());
		writeTrailerToFile.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				//save the menu choice off to the preference object (and the disk based settings file)
				if(e.getStateChange() == ItemEvent.SELECTED)
					getPreferences().setWriteTrailerToFile(true);
				else if(e.getStateChange() == ItemEvent.DESELECTED)
					getPreferences().setWriteTrailerToFile(false);

			}
		});
		preferenceMenu.add(writeTrailerToFile);

		//Checkbox for naming .nfo file movie.nfo instead of using movie name in file
		JCheckBoxMenuItem nfoNamedMovieDotNfo = new JCheckBoxMenuItem(".nfo file named movie.nfo instead of using movie name");
		nfoNamedMovieDotNfo.setState(getPreferences().getNfoNamedMovieDotNfo());
		nfoNamedMovieDotNfo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				//save the menu choice off to the preference object (and the disk based settings file)
				if(e.getStateChange() == ItemEvent.SELECTED)
					getPreferences().setNfoNamedMovieDotNfo(true);
				else if(e.getStateChange() == ItemEvent.DESELECTED)
					getPreferences().setNfoNamedMovieDotNfo(false);

			}
		});
		preferenceMenu.add(nfoNamedMovieDotNfo);

		//Checkbox for using IAFD Actors instead of Data18
		JCheckBoxMenuItem useIAFDForActors = new JCheckBoxMenuItem("Using IAFD Actors instead of Data18");
		useIAFDForActors.setState(getPreferences().getUseIAFDForActors());
		useIAFDForActors.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				//save the menu choice off to the preference object (and the disk based settings file)
				if(e.getStateChange() == ItemEvent.SELECTED)
					getPreferences().setUseIAFDForActors(true);
				else if(e.getStateChange() == ItemEvent.DESELECTED)
					getPreferences().setUseIAFDForActors(false);

			}
		});
		preferenceMenu.add(useIAFDForActors);

		//Checkbox for renaming Movie file
		JCheckBoxMenuItem renameMovieFile = new JCheckBoxMenuItem("Rename Movie File");
		
		renameMovieFile.setState(getPreferences().getRenameMovieFile());
		renameMovieFile.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				//save the menu choice off to the preference object (and the disk based settings file)
				if(e.getStateChange() == ItemEvent.SELECTED)
					getPreferences().setRenameMovieFile(true);
				else if(e.getStateChange() == ItemEvent.DESELECTED)
					getPreferences().setRenameMovieFile(false);

			}
		});
		preferenceMenu.add(renameMovieFile);

		//Checkbox for scraping JAV files in japanese instead of english when clicking scrape jav
		JCheckBoxMenuItem scrapeInJapanese = new JCheckBoxMenuItem("Scrape JAV Movies in Japanese Instead of English");
		scrapeInJapanese.setState(getPreferences().getScrapeInJapanese());
		scrapeInJapanese.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				//save the menu choice off to the preference object (and the disk based settings file)
				if(e.getStateChange() == ItemEvent.SELECTED)
					getPreferences().setScrapeInJapanese(true);
				else if(e.getStateChange() == ItemEvent.DESELECTED)
					getPreferences().setScrapeInJapanese(false);

			}
		});
		preferenceMenu.add(scrapeInJapanese);
		
		//Checkbox for scraping dialog box allowing the user to override the URL used when scraping
		JCheckBoxMenuItem promptForUserProvidedURL = new JCheckBoxMenuItem("Provide the URL yourself when scraping");
		promptForUserProvidedURL.setState(getPreferences().getPromptForUserProvidedURLWhenScraping());
		promptForUserProvidedURL.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				//save the menu choice off to the preference object (and the disk based settings file)
				if(e.getStateChange() == ItemEvent.SELECTED)
					getPreferences().setPromptForUserProvidedURLWhenScraping(true);
				else if(e.getStateChange() == ItemEvent.DESELECTED)
					getPreferences().setPromptForUserProvidedURLWhenScraping(false);

			}
		});
		preferenceMenu.add(promptForUserProvidedURL);
		
		
		add(preferenceMenu);

	}
	
	private void initializeSettingsMenu() {

		JMenu renameMenu = new JMenu("Settings");
		renameMenu.setMnemonic(KeyEvent.VK_S);
		JMenuItem renameSettings = new JMenuItem("Rename Settings...");
		renameSettings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Movie currentSelectedMovie = null;
				if(guiMain.getMovieToWriteToDiskList().size() > 0)
					currentSelectedMovie = guiMain.getMovieToWriteToDiskList().get(0);
				new RenamerGUI(getPreferences(), currentSelectedMovie);
			}
		});
		renameMenu.add(renameSettings);
		
		add(renameMenu);
	}
	
	private void initializeFileMenu() {
		// File menu

		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.getAccessibleContext().setAccessibleDescription(
				"File actions for JAVMovieScraper");

		//Browse directory file menu
		JMenuItem browseDirectory = new JMenuItem("Browse directory...");
		browseDirectory.setMnemonic(KeyEvent.VK_B);
		browseDirectory.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,
				Event.CTRL_MASK));
		browseDirectory.addActionListener(new BrowseDirectoryAction(guiMain));
		fileMenu.add(browseDirectory);
		
		//Refresh file menu
		JMenuItem refreshDirectory = new JMenuItem("Refresh");
		refreshDirectory.setMnemonic(KeyEvent.VK_R);
		refreshDirectory.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				Event.CTRL_MASK));
		refreshDirectory.addActionListener(new RefreshDirectoryAction(guiMain));
		fileMenu.add(refreshDirectory);
		
		fileMenu.addSeparator();
		
		//Open file menu
		JMenuItem openFile = new JMenuItem("Open File");
		openFile.setMnemonic(KeyEvent.VK_O);
		openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,
				Event.CTRL_MASK));
		openFile.addActionListener(new OpenFileAction(guiMain));
		fileMenu.add(openFile);

		JMenuItem writeFile = new JMenuItem("Write File Data");
		writeFile.setMnemonic(KeyEvent.VK_W);
		writeFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
				Event.CTRL_MASK));
		writeFile.addActionListener(new WriteFileDataAction(guiMain));
		fileMenu.add(writeFile);
		
		JMenuItem moveFile = new JMenuItem("Move File to New Folder");
		moveFile.setMnemonic(KeyEvent.VK_M);
		moveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,
				Event.CTRL_MASK));
		moveFile.addActionListener(new MoveToNewFolderAction(guiMain));
		fileMenu.add(moveFile);
		
		JMenuItem cleanFile = new JMenuItem("Clean up File Name");
		cleanFile.setMnemonic(KeyEvent.VK_C);
		cleanFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				Event.CTRL_MASK));
		cleanFile.addActionListener(new MoveToNewFolderAction(guiMain));
		fileMenu.add(cleanFile);
		
		
		fileMenu.addSeparator();
		
		//Exit file menu
		JMenuItem exit = new JMenuItem("Exit");
		exit.setMnemonic(KeyEvent.VK_E);
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				Event.CTRL_MASK));
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		fileMenu.add(exit);
		
		add(fileMenu);
	}
	
	private void initializeViewMenu() {
		
		JMenu viewMenu = new JMenu("View");
		viewMenu.setMnemonic(KeyEvent.VK_V);
		
		JMenuItem consoleInSeperateWindowMenuItem = new JMenuItem("View Output In New Window");
		consoleInSeperateWindowMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MessageConsoleGUI.showWindow();
			}
		});
		
		
		JCheckBoxMenuItem consolePanelMenuItem = new JCheckBoxMenuItem("Show Output Panel");
		consolePanelMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0));
		consolePanelMenuItem.setState(guiMain.getGuiSettings().getShowOutputPanel());
		consolePanelMenuItem.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
					guiMain.showMessageConsolePanel();
				else if(e.getStateChange() == ItemEvent.DESELECTED)
					guiMain.hideMessageConsolePanel();	
			}
		});
		
		JCheckBoxMenuItem buttonPanelMenuItem = new JCheckBoxMenuItem("Show Tool Bar");
		buttonPanelMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));
		buttonPanelMenuItem.setState(guiMain.getGuiSettings().getShowToolbar());
		buttonPanelMenuItem.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
					guiMain.showButtonPanel();
				else if(e.getStateChange() == ItemEvent.DESELECTED)
					guiMain.hideButtonPanel();	
				
			}
		});
		
		viewMenu.add(buttonPanelMenuItem);
		viewMenu.add(consolePanelMenuItem);
		viewMenu.add(consoleInSeperateWindowMenuItem);
		
		add(viewMenu);
	}
	
	private void initializeScrapeMenu() {
		JMenu scrapeMenu = new JMenu("Scrape");
		scrapeMenu.setMnemonic(KeyEvent.VK_S);
		
		JMenuItem scrapeJav = new JMenuItem(new ScrapeMovieAction(guiMain));
		scrapeJav.setText(scrapeJav.getText() + "...");
		scrapeJav.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK | Event.SHIFT_MASK));
		
		JMenuItem scrapeJavAuto = new JMenuItem(new ScrapeMovieActionAutomatic(guiMain));
		scrapeJavAuto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		
		JMenuItem scrapeData18Movie = new JMenuItem(new ScrapeMovieActionData18Movie(guiMain));
		scrapeData18Movie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, Event.CTRL_MASK | Event.SHIFT_MASK));
		
		JMenuItem scrapeData18WebContent = new JMenuItem(new ScrapeMovieActionData18WebContent(guiMain));
		scrapeData18WebContent.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, Event.CTRL_MASK));
		
		scrapeMenu.add(scrapeJav);
		scrapeMenu.add(scrapeJavAuto);
		scrapeMenu.add(scrapeData18Movie);
		scrapeMenu.add(scrapeData18WebContent);
		
		
		JMenu specificMenu = new JMenu("Specific Scrape");
		scrapeMenu.add(specificMenu);
		
		int i = 0;
		
		for(SiteParsingProfileItem item: SpecificProfileFactory.getAll()){
			JMenuItem menuItem = new JMenuItem(item.toString());
			
			if (++i < 10)
				menuItem.setAccelerator(KeyStroke.getKeyStroke(Character.forDigit(i,  10), Event.CTRL_MASK | Event.SHIFT_MASK));
			
			menuItem.addActionListener(new ScrapeSpecificAction(guiMain, item.getParser()));
			specificMenu.add(menuItem);
		}
		
		add(scrapeMenu);
	}
	
	private void initializeHelpMenu() {
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		
		JMenuItem website = new JMenuItem("Visit website");
		website.addActionListener(new BrowseUriAction(BrowseUriAction.MainWebsiteUri));
		
		JMenuItem reportBug = new JMenuItem("Report bug");
		reportBug.addActionListener(new BrowseUriAction(BrowseUriAction.ReportBugUri));
		
		JMenuItem about = new JMenuItem("About...");
		about.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AboutDialog(guiMain.getFrmMoviescraper()).setVisible(true);
			}
		});
		
		helpMenu.add(website);
		helpMenu.add(reportBug);
		helpMenu.addSeparator();
		helpMenu.add(about);
		
		add(helpMenu);
	}
	
	private void initializeMenus() {
		//add the various menus together
		initializeFileMenu();
		initializeScrapeMenu();
		initializePreferencesMenu();
		initializeSettingsMenu();
		initializeViewMenu();
		initializeHelpMenu();
	}
	
	private MoviescraperPreferences getPreferences(){
		return preferences;
	}

}
