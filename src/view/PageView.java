/***********************************************************************
 * Module:  PageView.java
 * Author:  Ognjen
 * Purpose: Defines the Class ProjectView
 ***********************************************************************/

package view;

import gerudok_observer.GObserver;
import gerudok_observer.GObserverNotification;
import model.Model;
import model.Page;
import java.awt.Color;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import constants.Constants;
import controller.PageController;

@SuppressWarnings("serial")
public class PageView extends JPanel implements GObserver {
	private PageController pageController;
	private Page page;
	private Model model;
	private JPanel content;

	public PageView(Model model, Page page) {
		this.model = model;
		this.model.addObserver(this);
		this.setPage(page);
		this.page.addObserver(this);

		setMaximumSize(Constants.DOCUMENT_SIZE);
		setPreferredSize(Constants.DOCUMENT_SIZE);
		setMinimumSize(Constants.DOCUMENT_SIZE);
		setBackground(Color.LIGHT_GRAY);
		setAlignmentY(CENTER_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// Border i content
		setBorder(new EmptyBorder(0, 0, Constants.DOCUMENT_BOTTOM_MARGIN, 0));
		content = new JPanel();
		content.setBackground(new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat()));
		add(content);
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	// PAGE SVA ONA SRANJA ?!
	public void updateSelection(Object[] path, int i) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GObserverNotification notification, Object obj) {
	}
}