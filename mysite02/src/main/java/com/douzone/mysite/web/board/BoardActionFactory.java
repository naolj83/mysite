package com.douzone.mysite.web.board;

import com.douzone.web.Action;
import com.douzone.web.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;

		if("view".equals(actionName)) {
			action = new ViewAction();
		} else if("write".equals(actionName)) {
			action = new WriteAction();
		} else if("writeform".equals(actionName)) {
			action = new WriteFormAction();
		} else if("modify".equals(actionName)) {
			action = new ModifyAction();
		} else { // default Action
			action = new ListAction();
		}

		return action;
	}
}
//if("delete".equals(actionName)) {
//	action = new DeleteAction();
//} else if ("deleteform".equals(actionName)) {
//	action = new DeleteFormAction();
//} else if ("add".equals(actionName)) {
//	action = new AddAction();
//} else {
//	action = new ListAction();
//}
//return action;
//}
			
//			if("writeform".equals(actionName)) {
//			
//			} else { //default Action
//				action = new ListAction();
//			}
//			return action;
//		}

