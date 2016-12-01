package function;

public class OK implements Operation {

	public void workNew(String path,String name) {
		//如果需要有新的参数输入，应该在第一步弹出一个新的框输让我输入参数，框的类必须有个方法在我调用时传入名字。
		System.out.println("ok"+path+name);

	}

}
