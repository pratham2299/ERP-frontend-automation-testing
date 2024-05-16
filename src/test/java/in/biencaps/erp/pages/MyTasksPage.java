package in.biencaps.erp.pages;

import java.util.*;

import org.openqa.selenium.*;

import com.github.javafaker.Faker;

import in.biencaps.erp.utilities.*;

public class MyTasksPage {
	private Random random = new Random();
	private Faker faker = new Faker();

	protected WebDriver driver;
	protected WebElementActions webElementActions = new WebElementActions();

	// Locators

	public By addTaskButtonAtRightTopSide = By.xpath("//button[normalize-space()='Add Task']");
	public By cancelSidebarIcon = By.xpath("//div[@class='newTask_header_cross_icon']//*[name()='svg']");

	// Locators for add task from sidebar
	public By taskNameTextfieldInSidebar = By.xpath("//input[@placeholder='Enter Tasks Name ']");
	public By newTaskTitleInSidebar = By.xpath("//div[@class='newTaskParagraph']");
	public By departmentLabelInSidebar = By.xpath("//label[normalize-space()='Department']");
	public By endDateLabelInSidebar = By.xpath("//label[normalize-space()='End Date']");
	public By taskScheduleDateValueInSidebar = By.xpath("(//div[@class='taskEdtSelectDateForAdd']//p)[1]");
	public By taskDueDateValueInSidebar = By.xpath("(//div[@class='taskEdtSelectDateForAdd']//p)[2]");
	public By taskAssigneeNameInSidebar = By.cssSelector("div[class='navBarName'] p");
	public By taskAssigneeNameInSidebarAtLevelView = By.cssSelector("div[class='Assignee'] p");
	public By taskDepartmentInSidebar = By.xpath("//div[@class='Tags']//p");
	public By taskPriorityInSidebar = By.xpath("//div[@class='Priority']//p");
	public By taskStatusInSidebar = By.xpath("//div[@class='newTaskAssigneeContent newTasStatusContentEdite']");
	public By statusValuesFromDropdownInSidebar = By.xpath("//div[@class='TagContentDiv']");
	public By valuesFromDropdownInSidebar = By.xpath("//div[@class='TagContentDiv']/p");
	public By taskProjectInSidebar = By.xpath("//div[@class='newAddProjectDiv']//p");
	public By tagDropdownInSidebar = By.xpath("//div[@class='seletedNewTaskAssignee']");
	public By departmentFieldLabelInSidebar = By.xpath("//label[normalize-space()='Department']");
	public By priorityDropdownInSidebar = By.xpath("//div[@class='Priority']//div[@class='newAddTaskAssignee']");
	public By priorityFieldLabelInSidebar = By.xpath("//label[normalize-space()='Priority']");
	public By statusDropdownInSidebar = By.xpath("//div[@class='Status']//div[@class='newAddTaskAssignee']");
	public By statusFieldLabelInSidebar = By.xpath("//label[normalize-space()='Status']");
	public By projectDropdownInSidebar = By.xpath("//div[@class='newAddProjectDiv']");
	public By projectLabelInSidebar = By.xpath("//label[normalize-space()='Project']");
	public By commentTextfieldInSidebar = By.cssSelector("textarea[placeholder='Add Comment here...']");
	public By addTasksButtonInSidebar = By.id("AddTaskBtn");
	public By closeToastMessage = By.xpath("(//*[name()='svg'][@aria-hidden='true'])[1]");
	// Locators in day view
	public By dayButton = By.xpath("//button[normalize-space()='Day']");
	public By todayButton = By.xpath("//button[normalize-space()='Today']");
	public By monthButton = By.xpath("//button[normalize-space()='Month']");
	public By weekButton = By.xpath("//button[normalize-space()='Week']");
	public By addTasksButtonInWeekView = By
			.xpath("//div[@class='rbc-header rbc-today']//div[normalize-space()='Add Tasks']");
	public By calendarInDayView = By.cssSelector("div[class='date-holder-dashboard']");
	public By dateInCurrentDayView = By.cssSelector("div[class='date-holder-dashboard'] p");
	public By previousDayIconInDayView = By.xpath("(//div[@class='dayViewDateSeletor']//*[name()='svg'])[1]");
	public By nextDayIconInDayView = By.xpath("(//div[@class='dayViewDateSeletor']//*[name()='svg'])[2]");
	public By lastRollOverTaskTitle = By.xpath("(//input[@id='due-task-title'])[last()]");

	public By lastTaskTitleInDayView = By.xpath("(//div[@class='taskTileDivContainer']//input[@type='text'])[last()]");
	public By lastTaskStatusInDayView = By.xpath("(//div[@class='OutputWrapperStatus'])[last()]");
	public By lastTaskPriorityInDayView = By.xpath("(//div[@class='OutputWrapperPrority'])[last()]");
	public By lastTaskProjectInDayView = By.xpath("(//div[@class='OutputWrapperProject']//p)[last()]");
	public By lastTaskProjectDivInDayView = By.xpath("//div[@class='selectDiv selectProjectDiv']");
	public By lastTaskScheduleDateInDayView = By.xpath("(//div[@classnam='taskScheduleDiv'])[last()]");
	public By lastTaskDueDateInDayView = By.xpath("(//div[@class='outputDueDateWrapper'])[last()]");
	public By lastTaskDepartmentInDayView = By.xpath("(//div[@class='OutputTag'])[last()]");
	public By lastTaskAssigneeNameInDayView = By.xpath("(//div[@class='AssigneeName']//p)[last()]");
	public By lastTaskVerifiedStatusInDayView = By.xpath("(//div[@class='OutputWrapperVerified'])[last()]");
	public By lastTaskGitlinkIconInDayView = By
			.xpath("(//div[@class='selectDiv selectGitDiv']//*[name()='svg'])[last()]");
	public By lastTaskCommentTextfieldInDayView = By.xpath("(//input[@id='taskTitle-input'])[last()]");
	public By expandButtonsInWeekView = By.xpath("//p[normalize-space()='Expand']");
	public By collapseButtonsInWeekView = By.xpath("//p[normalize-space()='Collaps']");

	public By taskTitlesInDayView = By.xpath("//div[@class='taskTileDivContainer']//input[@type='text']");
	public By taskStatusesInDayView = By.xpath("//div[@class='OutputWrapperStatus']");
	public By taskPrioritiesInDayView = By.xpath("//div[@class='OutputWrapperPrority']");
	public By taskProjectsInDayView = By
			.xpath("//div[@class='selectDiv selectProjectDiv']//div[@class='OutputWrapperProject']");
	public By taskScheduleDatesInDayView = By.xpath("//div[@classnam='taskScheduleDiv']");
	public By taskDueDatesInDayView = By.xpath("//div[@class='outputDueDateWrapper']");
	public By taskAssigneeNamesInDayView = By.xpath("//div[@class='AssigneeName']/p");
	public By taskVerifiedStatusesInDayView = By.xpath("//div[@class='OutputWrapperVerified']");
	public By taskGitlinkIconsInDayView = By.xpath("//div[@class='selectDiv selectGitDiv']//*[name()='svg']");
	public By taskCommentsTextfieldInDayView = By.id("taskTitle-input");

	public String taskTitleInDayView = "//div[@class='taskTileDivContainer']//input[@type='text']";
	public String taskStatusInDayView = "//div[@class='OutputWrapperStatus']";
	public String taskPriorityInDayView = "//div[@class='OutputWrapperPrority']";
	public String taskProjectInDayView = "//div[@class='selectDiv selectProjectDiv']//div[@class='OutputWrapperProject']";
	public String taskScheduleDateInDayView = "//div[@classnam='taskScheduleDiv']";
	public String taskDueDateInDayView = "//div[@class='outputDueDateWrapper']";
	public String taskAssigneeNameInDayView = "//div[@class='AssigneeName']/p";
	public String taskVerifiedStatusInDayView = "//div[@class='OutputWrapperVerified']";
	public String taskGitlinkIconInDayView = "//div[@class='selectDiv selectGitDiv']//*[name()='svg']";
	public String taskCommentTextfieldInDayView = "taskTitle-input";

	public By dayViewScroller = By.cssSelector(".DayViewScroller");

	public By gitMessageForNotSelectingProjectOfTaskInDayView = By.xpath("//div[@class='gitmsgforProject']");
	public By refreshButtonInDayView = By.xpath("(//*[name()='svg'][@id='refresh-icon'])[1]");
	public By checkBoxesInDayView = By.xpath("//input[@type='checkbox']");
	public By multipleCheckBoxInDayView = By.id("allSelectChecBox");
	public By deleteButtonFromHeaderInDayView = By.xpath("//p[normalize-space()='Delete']");
	public By deleteButtonForTaskDelete = By.xpath("//button[normalize-space()='Delete']");

	public By addTasksButtonInDayView = By.xpath("//p[normalize-space()='Add Tasks']");
	public By tasksCountInDayView = By.xpath("//div[@class='DayViewActualProgressContentView']/p");
	public By myActivitiesButtonInDayView = By.cssSelector(".getLogs");
	public By employeeNameAfterClickedOnMyActivitiesButton = By.xpath("(//div[@class='logInfo']//span)[1]");
	public By logMessageAfterClickedOnMyActivitiesButton = By.xpath("(//div[@class='log']//p)[1]");
	public By logTimeAfterClickedOnMyActivitiesButton = By.xpath("(//div[@class='logTime'])[1]");
	public By searchBarInDayView = By.cssSelector("input[placeholder='Search task name']");
	public By taskStatusesValuesFromDropdown = By.xpath("//div[@class='listOfStatusWrapper']//div");
	public By taskPrioritiesValuesFromDropdown = By.xpath("//div[@class='listOfProrityWrapper']/div");
	public By taskProjectValuesFromDropdown = By.cssSelector(".ProjectTitleDiv");
	public By previousMonthIconInDayView = By.xpath("//button[@aria-label='Previous Month']//*[name()='svg']");
	public By nextMonthIconInDayView = By.xpath("//button[@aria-label='Next Month']//*[name()='svg']");
	public By taskDepartmentValuesFromDropdown = By.xpath("//div[@class='listOfTagsWrapper']/div");
	public By taskVerifiedStatusValuesFromDropdown = By.xpath("//div[@class='listOfVerifiedWrapper']//div");
	public By taskGitlinkInputTextfieldInDayView = By.id("gitInputBox");
	public By taskGitlinkPushedTextInDayView = By.xpath("//a[normalize-space()='Pushed']");
	public By commentTextfieldForUpdateFromSidebarInDayView = By
			.cssSelector("textarea[placeholder='enter task comment']");
	public By closeIconOfSidebarForTaskUpdateInDayView = By
			.xpath("//div[@class='newTask_header_cross_icon']//*[name()='svg']");
	public By saveButtonForUpdateTaskOfSidebarInDayView = By.id("btnSave");
	public By textAfterRequestSentInDayView = By.xpath("//div[@class='rqtMsgPrt']");

	public By firstDateInWeekView = By.xpath("(//div[@class='day-text-containt'])[1]//span");
	public By lastDateInWeekView = By.xpath("(//div[@class='day-text-containt'])[7]//span");
	public By previousWeekSelectIcon = By.xpath("(//div[@class='weekLabel']//*[name()='svg'])[1]");
	public By nextWeekSelectIcon = By.xpath("(//div[@class='weekLabel']//*[name()='svg'])[2]");
	public By lastStartTimeTextfieldInDayView = By.xpath("(//input[@placeholder='Start time'])[last()]");
	public By timeValuesFromDropdownInDayView = By.xpath("//li[@class='react-datepicker__time-list-item ']");
	public By lastEndTimeTextfieldInDayView = By.xpath("(//input[@placeholder='End time'])[last()]");

	public By imageOfTaskViewedEmployeeInDayView = By.xpath("(//div[@class='taskOwnerImage viewedByDiv'])[last()]");

	// constructor created for webdriver initialized to this class driver variable
	// Also that webdriver passed as argument to selenium methods class
	// (A class which has common selenium methods used for all classes)
	public MyTasksPage(WebDriver driver2) {
		this.driver = driver2;
		webElementActions = new WebElementActions();
	}

	public void clickOnAddTaskButtonAtRightTopSide() {
		webElementActions.clickOnMethod(addTaskButtonAtRightTopSide);
	}

	public String checkToastMessage(String toastMessageText) {
		By toastMessageLocator = By.xpath("//div[contains(text(),'" + toastMessageText + "')]");
		String toastMessage = webElementActions.getTextMethod(toastMessageLocator);
		return toastMessage;
	}

	public String checkToastMessageInBlueColor(String toastMessageText) {
		By toastMessageLocator = By.xpath("//div[contains(text(),\"" + toastMessageText + "\")]");
		String toastMessage = webElementActions.getTextMethod(toastMessageLocator);
		return toastMessage;
	}

	public void clickOnCloseIconOfToastMessage() {
		webElementActions.clickOnMethod(closeToastMessage);
	}

	public void enterTaskNameWhileAddTaskFromSidebar(String taskTitle) throws InterruptedException {
		webElementActions.sendKeysMethod(taskNameTextfieldInSidebar, taskTitle);
		Thread.sleep(1000);
		webElementActions.sendKeysMethod(taskNameTextfieldInSidebar, Keys.ENTER);
	}

	public String checkTaskNameAfterAddedWhileAddTaskFromSidebar(String taskTitle) {
		return webElementActions.getTextMethod(newTaskTitleInSidebar);
	}

	public void clickOnCancelSidebarIcon() {
		webElementActions.clickOnMethod(cancelSidebarIcon);
	}

	public void clickOnStartDateCalendarFieldWhileAddTaskFromSidebar() {
		webElementActions.clickOnMethod(taskScheduleDateValueInSidebar);
	}

	public void clickOnEndDateCalendarFieldWhileAddTaskFromSidebar() {
		webElementActions.clickOnMethod(taskDueDateValueInSidebar);
	}

	public void selectTaskScheduleDateValueFromCalendarWhileAddTaskFromSidebar(String date) {
		By taskScheduleDateValue = By.xpath("//span[@aria-disabled='false'][normalize-space()='" + date + "']");
		webElementActions.clickOnMethod(taskScheduleDateValue);
	}

	public void selectTaskScheduleDateValueFromCalendarWhileAddTaskFromSidebar(int date) {
		By selectedTaskScheduleDateValue;
		if (date < 1) {
			webElementActions.clickOnMethod(previousMonthIconInDayView);

			By lastDateOfMonth = By.xpath("(//span[@aria-disabled='false'])[last()]");
			String lastDateOfMonthText = webElementActions.getTextMethod(lastDateOfMonth);
			String taskStartDate = String.valueOf(Integer.parseInt(lastDateOfMonthText) + date);
			selectedTaskScheduleDateValue = By
					.xpath("//span[@aria-disabled='false'][normalize-space()='" + taskStartDate + "']");
		} else {
			selectedTaskScheduleDateValue = By
					.xpath("//span[@aria-disabled='false'][normalize-space()='" + date + "']");
		}

		webElementActions.clickOnMethod(selectedTaskScheduleDateValue);
	}

	public void selectTaskDueDateValueFromCalendarWhileAddTaskFromSidebar(int date) {
		By lastDateOfMonth = By.xpath("(//span[@aria-disabled='false'])[last()]");
		String lastDateOfMonthText = webElementActions.getTextMethod(lastDateOfMonth);

		By selectedTaskDueDateValue;
		if (date > Integer.parseInt(lastDateOfMonthText)) {
			webElementActions.clickOnMethod(nextMonthIconInDayView);
			int randomDate = new Faker().number().numberBetween(1, 5);
			selectedTaskDueDateValue = By
					.xpath("//span[@aria-disabled='false'][normalize-space()='" + randomDate + "']");
		} else {
			selectedTaskDueDateValue = By.xpath("//span[@aria-disabled='false'][normalize-space()='" + date + "']");
		}

		webElementActions.clickOnMethod(selectedTaskDueDateValue);
	}

	public void clickOnDepartmentLabelWhileAddTaskFromSidebar() {
		webElementActions.clickOnMethod(departmentLabelInSidebar);
	}

	public String checkSelectedTaskScheduleDateValueFromCalendarWhileAddTaskFromSidebar() {
		return webElementActions.getTextMethod(taskScheduleDateValueInSidebar);
	}

	public String checkSelectedTaskDueDateValueFromCalendarWhileAddTaskFromSidebar() {
		return webElementActions.getTextMethod(taskDueDateValueInSidebar);
	}

	public String checkTaskAssigneeNameWhileAddTaskFromSidebar() {
		return webElementActions.getTextMethod(taskAssigneeNameInSidebar);
	}

	public String checkTaskAssigneeNameWhileAddTaskFromSidebarAtLevelView() {
		return webElementActions.getTextMethod(taskAssigneeNameInSidebarAtLevelView);
	}

	public void clickOnTagFieldLabelWhileAddTaskFromSidebar() {
		webElementActions.clickOnMethod(departmentFieldLabelInSidebar);
	}

	public void clickOnTagDropdownWhileAddTaskFromSidebar() {
		webElementActions.clickOnMethod(tagDropdownInSidebar);
	}

	public void clickOnRandomValueFromDropdownWhileAddTaskFromSidebar() {
		int randomIndex = random.nextInt(webElementActions.sizeOfListOfWebElement(valuesFromDropdownInSidebar));
		webElementActions.clickOnMethod(valuesFromDropdownInSidebar, randomIndex);
	}

	public List<String> checkTaskDepartmentsWhileAddTaskFromSidebar() {
		List<String> taskDepartmentValues = webElementActions.getValuesFromListOfWebElements(taskDepartmentInSidebar);
		return taskDepartmentValues;
	}

	public String checkTaskDepartmentWhileAddTaskFromSidebar() {
		return webElementActions.getTextMethod(taskDepartmentInSidebar);
	}

	public void clickOnPriorityFieldLabelWhileAddTaskFromSidebar() {
		webElementActions.clickOnMethod(priorityFieldLabelInSidebar);
	}

	public void clickOnPriorityDropdownWhileAddTaskFromSidebar() {
		webElementActions.clickOnMethod(priorityDropdownInSidebar);
	}

	public String checkTaskPriorityWhileAddTaskFromSidebar() {
		return webElementActions.getTextMethod(taskPriorityInSidebar);
	}

	public void clickOnStatusFieldLabelWhileAddTaskFromSidebar() {
		webElementActions.clickOnMethod(statusFieldLabelInSidebar);
	}

	public void clickOnStatusDropdownWhileAddTaskFromSidebar() {
		webElementActions.clickOnMethod(statusDropdownInSidebar);
	}

	public List<String> checkSelectedTaskStatusValueFromDropdownWhileAddTaskFromSidebar() {
		List<String> taskStatusValues = webElementActions
				.getValuesFromListOfWebElements(statusValuesFromDropdownInSidebar);
		return taskStatusValues;
	}

	public void selectRandomStatusWhileAddTaskFromSidebar(String taskStatus) throws InterruptedException {
		By taskStatusValue = By.xpath("//div[@class='TagContentDiv'][normalize-space()='" + taskStatus + "']");
		webElementActions.clickOnMethod(taskStatusValue);
	}

	public String checkTaskStatusWhileAddTaskFromSidebar() {
		return webElementActions.getTextMethod(taskStatusInSidebar);
	}

	public void clickOnProjectFieldLabelWhileAddTaskFromSidebar() {
		webElementActions.clickOnMethod(projectLabelInSidebar);
	}

	public void clickOnProjectDropdownWhileAddTaskFromSidebar() {
		webElementActions.clickOnMethod(projectDropdownInSidebar);
	}

	public String checkTaskProjectWhileAddingTaskFromSidebar() {
		return webElementActions.getTextMethod(taskProjectInSidebar);
	}

	public void enterTaskCommentWhileAddingTaskFromsidebar(String taskComment) {
		webElementActions.sendKeysMethod(commentTextfieldInSidebar, taskComment);
	}

	public String checkTaskCommentWhileAddingTaskFromSidebar() {
		return webElementActions.getTextMethod(commentTextfieldInSidebar);
	}

	public void clickOnAddTasksButtonInSidebar() {
		webElementActions.clickOnMethod(addTasksButtonInSidebar);
	}

	public String checkTaskAddedToastMessage(String date) {
		By taskAddedToastMessage = By.xpath("//div[contains(text(),'Task Added Successfully on: " + date + "')]");
		return webElementActions.getTextMethod(taskAddedToastMessage);
	}

	public void clickOnDayButton() {
		webElementActions.clickOnMethod(dayButton);
	}

	public void clickOnTodayButton() {
		webElementActions.clickOnMethod(todayButton);
	}

	public void clickOnCalendarInDayView() {
		webElementActions.clickOnMethod(calendarInDayView);
	}

	public String checkDateInCurrentDayView() {
		return webElementActions.getTextMethod(dateInCurrentDayView);
	}

	public void clickOnNextDayIconInDayView() {
		webElementActions.clickOnMethod(nextDayIconInDayView);
	}

	public String checkRollOverTaskColor() {
		return webElementActions.getCssValue(lastRollOverTaskTitle, "color");
	}

	public void clickOnParticularDateFromCalendarInDayView(int date) {
		By selectedTaskScheduleDateValue;
		if (date < 1) {
			webElementActions.clickOnMethod(previousMonthIconInDayView);
			By lastDateOfMonth = By.xpath("(//span[@aria-disabled='false'])[last()]");
			String lastDateOfMonthText = webElementActions.getTextMethod(lastDateOfMonth);
			String taskStartDate = String.valueOf(Integer.parseInt(lastDateOfMonthText) + date);
			selectedTaskScheduleDateValue = By
					.xpath("//span[@aria-disabled='false'][normalize-space()='" + taskStartDate + "']");
		} else {
			selectedTaskScheduleDateValue = By
					.xpath("//span[@aria-disabled='false'][normalize-space()='" + date + "']");
		}

		webElementActions.clickOnMethod(selectedTaskScheduleDateValue);
	}

	public void scrollUptoBottomOfTaskDivInDayView() {
		int sizeOfCheckBoxesInDayView = webElementActions.sizeOfListOfWebElement(checkBoxesInDayView);
		webElementActions.scrollUntilElementFound(checkBoxesInDayView, sizeOfCheckBoxesInDayView - 1);
	}

	public void clickOnRefreshButtonInDayView() {
		webElementActions.clickOnMethod(refreshButtonInDayView);
	}

	public String checkRandomTaskTitleInDayView(int index) {
		By taskTitle = By.xpath("//tbody//tr[" + (index + 1) + "] " + taskTitleInDayView + "");
		return webElementActions.getAtrributeMethod(taskTitle, "value");
	}

	public String checkRandomTaskStatusTextInDayView(int index) {
		By taskStatus = By.xpath("//tbody//tr[" + (index + 1) + "] " + taskStatusInDayView + "");
		return webElementActions.getTextMethod(taskStatus);
	}

	public String checkRandomTaskPriorityTextInDayView(int index) {
		By taskPriority = By.xpath("//tbody//tr[" + (index + 1) + "] " + taskPriorityInDayView + "");
		return webElementActions.getTextMethod(taskPriority);
	}

	public String checkRandomTaskProjectTextInDayView(int index) {
		By taskProject = By.xpath("//tbody//tr[" + (index + 1) + "] " + taskProjectInDayView + "");
		return webElementActions.getTextMethod(taskProject);
	}

	public String checkRandomTaskScheduleDateTextInDayView(int index) {
		By taskScheduleDate = By.xpath("//tbody//tr[" + (index + 1) + "] " + taskScheduleDateInDayView + "");
		return webElementActions.getTextMethod(taskScheduleDate);
	}

	public String checkRandomTaskDueDateTextInDayView(int index) {
		By taskDueDate = By.xpath("//tbody//tr[" + (index + 1) + "] " + taskDueDateInDayView + "");
		return webElementActions.getTextMethod(taskDueDate);
	}

	public List<String> checkRandomTaskDepartmentTextInDayView(int index) {
		By selfTaskDepartmentsInDayView = By.xpath("//tbody//tr[" + (index + 1) + "]//div[@class='OutputTag']");
		List<String> departmentsInRandomRow = webElementActions
				.getValuesFromListOfWebElements(selfTaskDepartmentsInDayView);
		return departmentsInRandomRow;
	}

	public String checkRandomTaskVerificationStatusTextInDayView(int index) {
		By taskVerifiedStatus = By.xpath("//tbody//tr[" + (index + 1) + "] " + taskVerifiedStatusInDayView + "");
		return webElementActions.getTextMethod(taskVerifiedStatus);
	}

	public String checkRandomTaskCommentTextInUpdateTaskSidebar() {
		return webElementActions.getTextMethod(commentTextfieldForUpdateFromSidebarInDayView);
	}

	public String checkLastTaskTitleInDayView() {
		return webElementActions.getAtrributeMethod(lastTaskTitleInDayView, "value");
	}

	public int checkNewAddedTaskTitleRowIndexInDayView(String taskTitle) {
		List<String> taskTitles = webElementActions.getValuesOfWebelements(taskTitlesInDayView);
		int index = 0;
		for (int i = 0; i < taskTitles.size(); i++) {
			if (taskTitles.get(i).equalsIgnoreCase(taskTitle)) {
				index++;

				if (index == 2) {
					return index;
				}
			}
		}
		return taskTitles.indexOf(taskTitle);
	}

	public String checkLastTaskStatusTextInDayView() {
		return webElementActions.getTextMethod(lastTaskStatusInDayView);
	}

	public String checkLastTaskPriorityTextInDayView() {
		return webElementActions.getTextMethod(lastTaskPriorityInDayView);
	}

	public String checkLastTaskProjectTextInDayView() {
		return webElementActions.getTextMethod(lastTaskProjectInDayView);
	}

	public String checkLastTaskScheduleDateTextInDayView() {
		return webElementActions.getTextMethod(lastTaskScheduleDateInDayView);
	}

	public String checkLastTaskDueDateTextInDayView() {
		return webElementActions.getTextMethod(lastTaskDueDateInDayView);
	}

	public String checkLastTaskDepartmentTextInDayView() {
		return webElementActions.getTextMethod(lastTaskDepartmentInDayView);
	}

	public List<String> checkLastRowTaskDepartmentTextsInDayView() {
		By selfTaskDepartmentsInDayView = By.xpath("(//tbody//tr)[last()]//div[@class='OutputTag']");
		List<String> departmentsInRandomRow = webElementActions
				.getValuesFromListOfWebElements(selfTaskDepartmentsInDayView);
		return departmentsInRandomRow;
	}

	public String checkLastTaskAssigneeNameTextInDayView() {
		return webElementActions.getTextMethod(lastTaskAssigneeNameInDayView);
	}

	public String checkLastTaskVerifiedStatusTextInDayView() {
		return webElementActions.getTextMethod(lastTaskVerifiedStatusInDayView);
	}

	public String checkLastTaskCommentTextInDayView() {
		return webElementActions.getAtrributeMethod(lastTaskCommentTextfieldInDayView, "value");
	}

	public String checkLastTaskCommentTextInUpdateTaskSidebar() {
		return webElementActions.getTextMethod(commentTextfieldForUpdateFromSidebarInDayView);
	}

	public List<String> checkNewAddedTaskDepartmentsTextInDayView() {
		By departmentOfNewAddedTaskInDayView = By.xpath("(//tbody//tr)[last()]//div[@class='OutputTag']");
		List<String> newAddedDepartmentsInLastRow = webElementActions
				.getValuesFromListOfWebElements(departmentOfNewAddedTaskInDayView);
		return newAddedDepartmentsInLastRow;
	}

	public void scrollHorizantally(int xValue) {
		webElementActions.scrollHorizantally(xValue, dayViewScroller);
	}

	public void clickOnLastCheckBoxInDayView() {
		int sizeOfCheckBoxesInDayView = webElementActions.sizeOfListOfWebElement(checkBoxesInDayView);
		webElementActions.clickOnMethod(checkBoxesInDayView, sizeOfCheckBoxesInDayView - 1);
	}

	public void clickOnMultipleCheckBoxesInDayView() {
		webElementActions.clickOnMethod(multipleCheckBoxInDayView);
	}

	public int checkTotalCheckBoxesInDayView() {
		return webElementActions.sizeOfListOfWebElement(checkBoxesInDayView);
	}

	public void clickOnDeleteButtonInDayView() {
		webElementActions.clickOnMethod(deleteButtonFromHeaderInDayView);
	}

	public void clickOnDeleteButtonForTaskDeleteInDayView() {
		webElementActions.clickOnMethod(deleteButtonForTaskDelete);
	}

	public boolean checkTaskNamePresentAfterDeleteInDayView(String taskTitle) {
		List<WebElement> taskTitles = driver.findElements(taskTitlesInDayView);
		boolean isTaskTitlePresentAfterDeleteTaskInDayView = true;
		for (int i = 0; i < taskTitles.size(); i++) {
			if (!taskTitles.get(i).getText().equalsIgnoreCase(taskTitle)) {
				isTaskTitlePresentAfterDeleteTaskInDayView = false;
				break;
			}
		}
		return isTaskTitlePresentAfterDeleteTaskInDayView;
	}

	public void clickOnAddTaskButtonInMonthViewAtParticularDate(String date) {
		By addTaskButtonInMonthViewAtParticularDate = By.xpath("(//p[contains(text(),'Add Task')])[" + date + "]");
		webElementActions.clickOnMethod(addTaskButtonInMonthViewAtParticularDate);
	}

	public void clickOnMonthButton() {
		webElementActions.clickOnMethod(monthButton);
	}

	public String checkScheduleDateFieldValue(String date) {
		By startDateValue = By.xpath("//p[normalize-space()='" + date + "']");
		return webElementActions.getTextMethod(startDateValue);
	}

	public String checkDueDateFieldValue(String date) {
		By startDateValue = By.xpath("//p[normalize-space()='" + date + "']");
		return webElementActions.getTextMethod(startDateValue);
	}

	public void clickOnWeekButton() {
		webElementActions.clickOnMethod(weekButton);
	}

	public void clickOnAddTasksButtonInWeekView() {
		webElementActions.clickOnMethod(addTasksButtonInWeekView);
	}

	public void clickOnAllExpandButtonsInWeekView() {
		for (int i = 0; i < webElementActions.sizeOfListOfWebElement(expandButtonsInWeekView); i++) {
			webElementActions.clickOnMethod(expandButtonsInWeekView);
		}
	}

	public void scrollUntilAllCollapseButtonsInWeekView() {
		for (int i = 0; i < webElementActions.sizeOfListOfWebElement(collapseButtonsInWeekView); i++) {
			webElementActions.clickOnMethod(collapseButtonsInWeekView);
		}
	}

	public String checkLastDateInWeekView() {
		return webElementActions.getTextMethod(lastDateInWeekView);
	}

	public String checkFirstDateInWeekView() {
		return webElementActions.getTextMethod(firstDateInWeekView);
	}

	public void clickOnPreviousWeekSelectIcon() {
		webElementActions.clickOnMethod(previousWeekSelectIcon);
	}

	public void clickOnNextWeekSelectIcon() {
		webElementActions.clickOnMethod(nextWeekSelectIcon);
	}

	public String checkNewAddedTaskTitleInWeekView(String taskTitle) {
		By lastTaskTitleInWeekView = By.xpath("//p[@title='" + taskTitle + "']");
		return webElementActions.getTextMethod(lastTaskTitleInWeekView);
	}

	public void clickOnAddTasksButtonInDayView() {
		webElementActions.clickOnMethod(addTasksButtonInDayView);
	}

	public void clickOnLastTaskTitleTextfieldInDayView() {
		webElementActions.clickOnMethod(lastTaskTitleInDayView);
	}

	public void clearLastTaskTitleTextfieldInDayView() {
		webElementActions.clearMethod(lastTaskTitleInDayView);
	}

	public void enterTaskTitleInLastTaskTitleTextfieldDayView(String taskTitle) throws InterruptedException {
		webElementActions.sendKeysMethod(lastTaskTitleInDayView, taskTitle);
		webElementActions.sendKeysMethod(lastTaskTitleInDayView, Keys.ENTER);
	}

	public String checkCompletedTaskCountOutOfTotalTasksCountInDayView() {
		return webElementActions.getTextMethod(tasksCountInDayView, 1);
	}

	public int checkSizeOfCompletedTaskInDayView() {
		return webElementActions.sizeOfListOfWebElement(By.xpath("//div[contains(text(),'Completed')]"));
	}

	public int checkPercentageOfTaskCompletedInDayView() {
		String CompletedTaskPercenatage = webElementActions
				.getTextMethod(By.xpath("(//div[@class='DayViewProgressHeader']//p)[position()=1]"));
		int CompletedTaskPercenatageInteger = Integer.parseInt(CompletedTaskPercenatage.replaceAll("[^0-9]", ""));
		return CompletedTaskPercenatageInteger;
	}

	public void clickOnGetMyActivitiesButtonInDayView() {
		webElementActions.clickOnMethod(myActivitiesButtonInDayView);
	}

	public String checkEmployeeNameAtMyActivitiesInDayView(String employeeName) {
		return webElementActions.getTextMethod(employeeNameAfterClickedOnMyActivitiesButton);
	}

	public String checkActionAtMyActivitiesInDayView() {
		return webElementActions.getTextMethod(logMessageAfterClickedOnMyActivitiesButton);
	}

	public String checkLogTimeAtMyActivitiesInDayView() {
		return webElementActions.getTextMethod(logTimeAfterClickedOnMyActivitiesButton);
	}

	public String checkSearchedTaskInDayView(String searchKey) throws InterruptedException {
		webElementActions.clickOnMethod(searchBarInDayView);
		webElementActions.sendKeysMethod(searchBarInDayView, searchKey);
		webElementActions.sendKeysMethod(searchBarInDayView, Keys.ENTER);
		return webElementActions.getAtrributeMethod(searchBarInDayView, "value");
	}

	public void clickOnRandomTaskTitleInDayView(int index) {
		webElementActions.clickOnMethod(taskTitlesInDayView, index);
	}

	public void clearRandomTaskTitleTextfieldInDayView(int index) {
		webElementActions.clearMethod(taskTitlesInDayView, index);
	}

	public void enterTaskTitleInRandomTaskTitleTextfieldInDayView(int index, String newTaskTitle) {
		webElementActions.sendKeysMethod(taskTitlesInDayView, index, newTaskTitle);
		webElementActions.sendKeysMethod(taskTitlesInDayView, index, Keys.ENTER);
	}

	public void clickOnRandomTaskStatusInDayView(int index) {
		webElementActions.clickOnMethod(taskStatusesInDayView, index);
	}

	public void clickOnLastTaskStatusInDayView() {
		webElementActions.clickOnMethod(lastTaskStatusInDayView);
	}

	public List<String> listOfTaskStatusValuesFromDropdownInDayView() throws InterruptedException {
		List<String> taskStatusValues = new ArrayList<String>();

		for (int i = 0; i < webElementActions.sizeOfListOfWebElement(taskStatusesValuesFromDropdown) - 1; i++) {
			taskStatusValues.add(webElementActions.getTextMethod(taskStatusesValuesFromDropdown, i));
		}

		return taskStatusValues;
	}

	public void clickOnSelectedTaskStatusValueFromDropdownInDayView(String taskStatus) {
		By taskStatusValue = By
				.xpath("//div[@class='listOfStatusWrapper']//div[contains(text(),'" + taskStatus + "')]");
		webElementActions.clickOnMethod(taskStatusValue);
	}

	public void clickOnLastTaskPriorityInDayView() {
		webElementActions.clickOnMethod(lastTaskPriorityInDayView);
	}

	public void clickOnRandomTaskPriorityInDayView(int index) {
		webElementActions.clickOnMethod(taskPrioritiesInDayView, index);
	}

	public String listOfTaskPriorityValuesFromDropdownInDayView() throws InterruptedException {
		int sizeOfPriorityValues = webElementActions.sizeOfListOfWebElement(taskPrioritiesValuesFromDropdown);
		int indexForTaskPrioritySelection = faker.number().numberBetween(1, sizeOfPriorityValues - 1);
		return webElementActions.getTextMethod(taskPrioritiesValuesFromDropdown, indexForTaskPrioritySelection);
	}

	public String checkSelectedTaskPriorityValueFromDropdownInDayView() throws InterruptedException {
		int sizeOfPriorityValues = webElementActions.sizeOfListOfWebElement(taskPrioritiesValuesFromDropdown);
		int indexForTaskPrioritySelection = faker.number().numberBetween(1, sizeOfPriorityValues - 1);
		return webElementActions.getTextMethod(taskPrioritiesValuesFromDropdown, indexForTaskPrioritySelection);
	}

	public void clickOnSelectedTaskPriorityValueFromDropdownInDayView(String taskPriority) {
		By taskPriorityValue = By
				.xpath("//div[@class='listOfProrityWrapper']//div[contains(text(),'" + taskPriority + "')]");
		webElementActions.clickOnMethod(taskPriorityValue);
	}

	public void clickOnLastTaskProjectInDayView() {
		webElementActions.clickOnMethod(lastTaskProjectDivInDayView,
				webElementActions.sizeOfListOfWebElement(lastTaskProjectDivInDayView) - 2);
	}

	public void clickOnRandomTaskProjectInDayView(int index) {
		webElementActions.clickOnMethod(taskProjectsInDayView, index);
	}

	public String checkSelectedTaskProjectValueFromDropdownInDayView() {
		int sizeOfProjectValues = webElementActions.sizeOfListOfWebElement(taskProjectValuesFromDropdown);
		int indexForTaskProjectSelection = faker.number().numberBetween(0, sizeOfProjectValues - 1);
		return webElementActions.getTextMethod(taskProjectValuesFromDropdown, indexForTaskProjectSelection);
	}

	public void clickOnSelectedTaskProjectValueFromDropdownInDayView(String randomProject) {
		By projectValue = By
				.xpath("//div[@class='listOfProjectWrapper']//div[contains(text(),'" + randomProject + "')]");
		webElementActions.clickOnMethod(projectValue);
	}

	public void clickOnLastRowStartTimeTextfield() {
		webElementActions.clickOnMethod(lastStartTimeTextfieldInDayView);
	}

	public String checkSelectedTimeOfLastRowTaskInDayView() {
		int randomIndex = random.nextInt(webElementActions.sizeOfListOfWebElement(timeValuesFromDropdownInDayView));
		return webElementActions.getTextMethod(timeValuesFromDropdownInDayView, randomIndex);
	}

	public void clickOnSelectedTaskTimeValueFromDropdownInDayView(String randomTime) {
		By timeValueFromDropdown = By.xpath("//li[normalize-space()='" + randomTime + "']");
		webElementActions.clickOnMethod(timeValueFromDropdown);
	}

	public void clickOnLastRowEndTimeTextfield() {
		webElementActions.clickOnMethod(lastEndTimeTextfieldInDayView);
	}

	public void clickOnLastTaskDueDateInDayView() {
		webElementActions.clickOnMethod(lastTaskDueDateInDayView);
	}

	public void clickOnRandomTaskDueDateInDayView(int index) {
		webElementActions.clickOnMethod(taskDueDatesInDayView, index);
	}

	public String checkSelectedTaskDueDateValueFromCalendarInDayView(int date) {
		By lastDateOfMonth = By.xpath("(//span[@aria-disabled='false'])[last()]");
		String lastDateOfMonthText = webElementActions.getTextMethod(lastDateOfMonth);

		By selectedTaskDueDateValue;
		if (date > Integer.parseInt(lastDateOfMonthText)) {
			webElementActions.clickOnMethod(nextMonthIconInDayView);
			int randomDate = new Faker().number().numberBetween(1, 5);
			selectedTaskDueDateValue = By
					.xpath("//span[@aria-disabled='false'][normalize-space()='" + randomDate + "']");
		} else {
			selectedTaskDueDateValue = By.xpath("//span[@aria-disabled='false'][normalize-space()='" + date + "']");
		}

		return webElementActions.getTextMethod(selectedTaskDueDateValue);
	}

	public void clickOnSelectedTaskDueDateValueFromCalendarInDayView(String date) {
		By taskDueDateValue = By.xpath("//span[@aria-disabled='false'][normalize-space()='" + date + "']");
		webElementActions.clickOnMethod(taskDueDateValue);
	}

	public void clickOnRandomTaskDepartmentInDayView(int index) {
		By randomTaskDepartmentRow = By.xpath("//tbody//tr[" + (index + 1) + "]//div[@class='OutputTag']");
		int sizeOfDepartmentsInRandomRow = webElementActions.sizeOfListOfWebElement(randomTaskDepartmentRow);
		webElementActions.clickOnMethod(randomTaskDepartmentRow, sizeOfDepartmentsInRandomRow - 1);
	}

	public void clickOnLastTaskDepartmentInDayView() {
		webElementActions.clickOnMethod(lastTaskDepartmentInDayView);
	}

	public String checkSelectedTaskDepartmentValueFromDropdownInDayView() {
		int sizeOfDepartmentValues = webElementActions.sizeOfListOfWebElement(taskDepartmentValuesFromDropdown);
		int indexForTaskDepartmentSelection = faker.number().numberBetween(1, sizeOfDepartmentValues - 1);
		return webElementActions.getTextMethod(taskDepartmentValuesFromDropdown, indexForTaskDepartmentSelection);
	}

	public void clickOnSelectedTaskDepartmentValueFromDropdownInDayView(String taskDepartment) {
		By taskDepartmentValue = By
				.xpath("//div[@class='listOfTagsWrapper']//div[contains(text(),'" + taskDepartment + "')]");
		webElementActions.clickOnMethod(taskDepartmentValue);
	}

	public String checkNewUpdatedLastTaskDepartmentInDayView(String taskDepartment) {
		By latestTaskDepartment = By
				.xpath("//tbody//tr[last()]//div[@class='OutputTag' and normalize-space()='" + taskDepartment + "']");
		return webElementActions.getTextMethod(latestTaskDepartment);
	}

	public String checkNewUpdatedRandomTaskDepartmentInDayView(int index) {
		By randomTaskDepartmentRow = By.xpath("//tbody//tr[" + (index + 1) + "]//div[@class='OutputTag']");
		int sizeOfDepartmentsInRandomRow = webElementActions.sizeOfListOfWebElement(randomTaskDepartmentRow);
		return webElementActions.getTextMethod(randomTaskDepartmentRow, sizeOfDepartmentsInRandomRow - 1);
	}

	public void clickOnLastTaskVerifiedStatusInDayView() {
		webElementActions.clickOnMethod(lastTaskVerifiedStatusInDayView);
	}

	public void clickOnRandomTaskVerifiedStatusInDayView(int index) {
		webElementActions.clickOnMethod(taskVerifiedStatusesInDayView, index);
	}

	public String checkSelectedTaskVerifiedStatusValueFromDropdownInDayView() {
		int sizeOfVerifiedStatusValues = webElementActions.sizeOfListOfWebElement(taskVerifiedStatusValuesFromDropdown);
		int indexForTaskVerifiedStatusSelection = faker.number().numberBetween(0, sizeOfVerifiedStatusValues - 1);
		System.out.println("verified index from dropdown is: " + indexForTaskVerifiedStatusSelection);
		return webElementActions.getTextMethod(taskVerifiedStatusValuesFromDropdown,
				indexForTaskVerifiedStatusSelection);
	}

	public void clickOnSelectedTaskVerifiedStatusValueFromDropdownInDayView(String taskVerifiedStatus) {
		By verifiedStatusValue = By
				.xpath("//div[@class='listOfVerifiedWrapper']//div[normalize-space()='" + taskVerifiedStatus + "']");
		webElementActions.clickOnMethod(verifiedStatusValue);
	}

	public String checkNewUpdatedLastTaskVerifiedStatusInDayView() {
		return webElementActions.getTextMethod(lastTaskVerifiedStatusInDayView);
	}

	public void clickOnLastGitlinkIconInDayView() {
		webElementActions.clickOnMethod(lastTaskGitlinkIconInDayView);
	}

	public void clickOnRandomGitlinkIconInDayView(int index) {
		webElementActions.clickOnMethod(taskGitlinkIconsInDayView, index);
	}

	public String checkTextForNotSelectingProjectBeforeGitlinkAddInDayView() {
		return webElementActions.getTextMethod(gitMessageForNotSelectingProjectOfTaskInDayView);
	}

	public void enterGitlinkValueInDayView(String taskGitLink) {
		webElementActions.clickOnMethod(taskGitlinkInputTextfieldInDayView);
		webElementActions.sendKeysMethod(taskGitlinkInputTextfieldInDayView, taskGitLink);
		webElementActions.sendKeysMethod(taskGitlinkInputTextfieldInDayView, Keys.ENTER);
	}

	public String checkGitlinkPushedtextInDayView() {
		return webElementActions.getTextMethod(taskGitlinkPushedTextInDayView);
	}

	public String checkNewUpdatedGitlinkInDayView() {
		return webElementActions.getAtrributeMethod(taskGitlinkInputTextfieldInDayView, "value");
	}

	public void clickOnLastTaskCommentTextfieldInDayView() {
		webElementActions.clickOnMethod(lastTaskCommentTextfieldInDayView);
	}

	public void clickOnRandomTaskCommentTextfieldInDayView(int index) {
		webElementActions.clickOnMethod(taskCommentsTextfieldInDayView, index);
	}

	public void scrollUntilCommentTextfieldInUpdateTaskSidebar() {
		webElementActions.scrollUntilElementFound(commentTextfieldForUpdateFromSidebarInDayView);
	}

	public void clearTaskCommentTextfieldInSidebarForUpdateTaskInDayView() {
		webElementActions.clearMethod(commentTextfieldForUpdateFromSidebarInDayView);
	}

	public void enterTaskCommentValueInSidebarForUpdateTaskInDayView(String taskComment) {
		webElementActions.sendKeysMethod(commentTextfieldForUpdateFromSidebarInDayView, taskComment);
	}

	public void clickOnSaveButtonInSidebarForUpdateTaskInDayView() {
		webElementActions.clickOnMethod(saveButtonForUpdateTaskOfSidebarInDayView);
	}

	public void clickOnCloseIconOfSidebarForUpdateTaskInDayView() {
		webElementActions.clickOnMethod(closeIconOfSidebarForTaskUpdateInDayView);
	}

	public String checkRandomTaskCommentTextInDayView(int index) {
		return webElementActions.getAtrributeMethod(taskCommentsTextfieldInDayView, index, "value");
	}

	public String checkTextAfterTaskRequestSentInDayView() {
		webElementActions.mouseHoverOnElement(lastTaskStatusInDayView);

		return webElementActions.getTextMethod(textAfterRequestSentInDayView);
	}

	public String checkViwedHigherAuthorityNameInDayView(String taskViwedEmployee) throws InterruptedException {
		webElementActions.mouseHoverOnElement(imageOfTaskViewedEmployeeInDayView);
		Thread.sleep(1000);
		By taskViwedEmployeeNameAfterHover = By.xpath("//div[normalize-space()='" + taskViwedEmployee + "']");

		return webElementActions.getTextMethod(taskViwedEmployeeNameAfterHover);
	}
}