package in.biencaps.erp.utilities;

public interface Constants {
	String environment = "test";
	String browserName = "chrome";

	String websiteUrlOnStagingEnvironemnt = "https://erp.biencaps.in/login";
	String websiteUrlOnTestEnvironemnt = "http://192.168.0.133:3000/login";

	String backendUrl = "https://erp.biencaps.in:10004";

	String databaseUrl = "jdbc:mysql://localhost:3306/mydatabase";
	String databaseUserName = "root";
	String databasePassword = "Pratham@2299";

	String extentReportHostName = "RHEL8";
	String extentReportPassword = "root";
	String extentReportHTMLFileName = "ERP Extent Reports.html";
	String extentReportDocumentTitle = "ERP Automation End To End Testing Report";
	String extentReportName = "ERP Automation Test Exection Results By Prathamesh Dhasade";

	String gmailKey = "jxyn mast qqtg jhkh";

	String adminLevelTesterEmployeeUserId = "INC004";
	String adminLevelTesterEmployeePassword = "Pass@123";

	String teamLeadLevelTesterEmployeeUserId = "INC017";
	String teamLeadLevelTesterEmployeePassword = "Pass@123";

	String leadLevelTesterEmployeeUserId = "INC012";
	String leadLevelTesterEmployeePassword = "Pass@123";

	String employeeUserId = "INC012";
	String employeePassword = "Pass@123";
}
