/*
 * Created on Oct 15, 2004
 */
package strangebrew.ui.core;

import strangebrew.ImportXml;
import strangebrew.Recipe;

/**
 * @author mike
 *
 */
public class MenuController extends Controller {

	MenuView myContents;
	MainController myParent;
	
	public MenuController(MenuView aView, MainController parent) {
		super(aView);
		myContents = aView;
	}

	public void init() {
		myView.init();

		myContents.getFileMenu().set("File");
		myContents.getOpenItem().set("Open");
		myContents.getQuitItem().set("Quit");
		myView.layout();
	}

	public void dispose() {
		myView.dispose();
	}
	
	public void execute() {
      if (myContents.getQuitItem().isSelected()) {
      	myContents.quit();
      }
      if (myContents.getOpenItem().isSelected()) {
		ImportXml imp = new ImportXml(myContents.getOpenFilename());
		myParent.setRecipe(imp.handler.getRecipe());	
      }
	}
	
	public void cleanUp() {
		// Nothing to do yet
	}
	
	public void setRecipe(Recipe aRecipe) {
		myRecipe = aRecipe;
	}
}