package Core;
import java.io.File;

public class Controller {
	private Controller(){}
	private static Controller instance = new Controller();
	public static Controller getController(){
		return instance;
	}
	
	private String filePath;
	private String fileName;
	private String testName=null;
	
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getFilePath(){
		return this.filePath;
	}
	public void setFilePath(String filePath){
		if(filePath.contains("\\")){
			this.filePath = filePath.substring(0, filePath.lastIndexOf("\\")+1);
			System.out.println(this.filePath);
		}else if(filePath.contains("/")){
			this.filePath = filePath.substring(0, filePath.lastIndexOf("/")+1);
		}else{
			this.filePath="";
		}
		
	}
	public String getFileName(){
		return this.fileName;
	}
	public void setFileName(String fileName){
		this.fileName = fileName;
	}


}
