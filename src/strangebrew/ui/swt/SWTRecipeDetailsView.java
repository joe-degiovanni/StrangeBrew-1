package strangebrew.ui.swt;


import strangebrew.ui.core.*;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;

/**
 * @author mike
 *
 *
 */
public class SWTRecipeDetailsView extends RecipeDetailsView {
	Composite myContainer;
	FormLayout myLayout;
	SWTTextOutput myBrewerLabel;
	SWTTextInput myBrewer;
	SWTTextOutput myEfficiencyLabel;
	SWTNumberInput myEfficiency;

	public SWTRecipeDetailsView(Factory aFactory, Composite container) {
		myFactory = aFactory;
		myContainer = container;
	}
	
	public void init() {
		myBrewerLabel = new SWTTextOutput();
		myBrewerLabel.init(myContainer);
		myBrewer = new SWTTextInput(myController);
		myBrewer.init(myContainer);
		myEfficiencyLabel = new SWTTextOutput();
		myEfficiencyLabel.init(myContainer);
		myEfficiency = new SWTNumberInput();
		myEfficiency.init(myContainer);
		
		myLayout = new FormLayout();
		myLayout.marginHeight = 3;
		myLayout.marginWidth = 3;
		myContainer.setLayout(myLayout);
		
		attach(myBrewerLabel.getWidget(), null, 0, 0, 0);
		attach(myBrewer.getWidget(), myBrewerLabel.getWidget(), 
				200, 10, 5);
		attach(myEfficiencyLabel.getWidget(), myBrewer.getWidget(), 
				0, 0, 10);
        attach(myEfficiency.getWidget(), myEfficiencyLabel.getWidget(), 
        		25, 10, 5);		
	}
	
	private void attach(Control control, Control target, 
			int width, int height, int offset) {
		FormData data = new FormData();
		if (width != 0) {
			data.width = width;
		}
		if (height != 0) {
			data.height = height;
		}
		if (target != null) {
			data.left = new FormAttachment(target, offset);
		}
		control.setLayoutData(data);
	}

	public void layout() {
		myContainer.layout();
		myContainer.pack();
	}
	
	public void display() {
		// Not needed in SWT
	}
	
	public void dispose() {
        // @TODO Figure out who should dispose myContainer
	}
	
	public Factory createChildFactory(String aTitle) {
		// This view has no children
		return null;
	}
	
	public TextOutput getBrewerLabel() {
		return myBrewerLabel;
	}
	
	public TextInput getBrewer() {
		return myBrewer;
	}
	
	public TextOutput getEfficiencyLabel() {
		return myEfficiencyLabel;
	}

	public NumberInput getEfficiency() {
		return myEfficiency;
	}

}