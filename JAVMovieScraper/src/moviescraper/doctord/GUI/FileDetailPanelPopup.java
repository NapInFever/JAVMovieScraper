package moviescraper.doctord.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class FileDetailPanelPopup extends JPopupMenu {
	
	private static final long serialVersionUID = 8711342488935696278L;
	private AbstractFileDetailPanelEditGUI editor;

	public FileDetailPanelPopup(AbstractFileDetailPanelEditGUI editor) {
		this.editor = editor;
		intialize();
	}

	private void intialize() {
		JMenuItem addItem = new JMenuItem(editor.getMenuItemName());
		addItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editor.showGUI();
			}
		});
		
		
		JMenuItem removeItem = new JMenuItem("Remove Item");
		removeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editor.deleteAction();
			}
		});
		
		this.add(addItem);
		this.add(removeItem);
	}
}
