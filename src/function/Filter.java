package function;

import core.CleanBreak;
import core.WorkComplete;

public class Filter implements Operation {

	public void workNew(String path,String fileName) {
		//如果需要有新的参数输入，应该在第一步弹出一个新的框输让我输入参数，框的类必须有个方法在我调用时传入名字。
		CleanBreak cleanBreak = new CleanBreak();
		cleanBreak.cleanLineBreak(path, fileName);

		new WorkComplete();
	}

}
