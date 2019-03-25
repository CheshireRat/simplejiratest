package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.awt.event.KeyEvent;

public class IssuePage {
    WebDriver driver;

    IssuePage(WebDriver driver) {
        this.driver = driver;
    }

    private By issueTypeField = By.id("issuetype-field");
    private By summaryField = By.id("summary");
    private By descriptionField = By.id("description");
    private By submitIssueButton = By.id("create-issue-submit");
    private By descriptionEdit = By.id("description-val");
    private By labelsEditButton = By.xpath("//*[@id='wrap-labels']/div/span");

    By removeLabelsButton(String labelName) {
        return By.xpath(String.format("//*[@class='value-text' and contains(text(),'%s')]/../../../em", labelName));
    }

    By labelByName(String labelName) {
        return By.xpath(String.format("//span[contains(text(),'%s')]", labelName));
    }

    private By description = By.id("description");
    private By emptyDescription = By.xpath("//em[contains(text(),'Click to add description')]");
    private By labelsTextInput = By.id("labels-textarea");
    private By labelsDropdown = By.xpath("//*[@id='labels-multi-select']/span");
    private By workflowDropdown = By.id("opsbar-transitions_more");
    private By workflowDropdownInProgress = By.xpath("//span[contains(text(),'In Progress')]");
    private By issueStatusInProgress = By.xpath("//*[(@id='status-val')]  [span[contains(text(), 'In Progress')]]");


    By issueTypeField() {
        return (issueTypeField);
    }

    By summaryField() {
        return (summaryField);
    }

    By descriptionField() {
        return (descriptionField);
    }

    By submitIssueButton() {
        return (submitIssueButton);
    }

    By descriptionEdit() {
        return descriptionEdit;
    }

    By labelsEditButton() {
        return (labelsEditButton);
    }

    By description() {
        return (description);
    }

    By emptyDescription() {
        return (emptyDescription);
    }

    By labelsTextInput() {
        return (labelsTextInput);
    }

    By workflowDropdown() {
        return (workflowDropdown);
    }

    By workflowDropdownInProgress() {
        return (workflowDropdownInProgress);
    }

    By issueStatusInProgress() {
        return (issueStatusInProgress);
    }


    void issueTypeFieldInput(String issueType) {
        driver.findElement(issueTypeField()).sendKeys(issueType);
        driver.findElement(issueTypeField()).sendKeys(Keys.ENTER);
    }

    void summaryFieldInput(String issueSummary) {
        driver.findElement(summaryField()).click();
        driver.findElement(summaryField()).sendKeys(issueSummary);
    }

    void descriptionFieldInput(String issueDescription) {
        driver.findElement(summaryField()).click();
        driver.findElement(summaryField()).sendKeys(issueDescription);
    }

    void submitIssueButtonClick() {
        driver.findElement(submitIssueButton()).click();
    }

    void labelsEditButtonClick() {
        driver.findElement(labelsEditButton()).click();
    }

    void removeLabelsButtonClick(String labelName) {
        driver.findElement(removeLabelsButton(labelName)).click();
    }

    void enterButtonPress() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch(AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    void labelsTextInput(String labelName) {
        driver.findElement(labelsTextInput()).sendKeys(labelName);
        driver.findElement(labelsTextInput()).submit();
    }

    void workflowDropdownClick() {
        driver.findElement(workflowDropdown()).click();
    }

    void workflowDropdownInProgressClick() {
        driver.findElement(workflowDropdownInProgress()).click();
    }

    void descriptionEditClick() {
        driver.findElement(descriptionEdit()).click();
    }

    void descriptionClear() {
        driver.findElement(description()).clear();
    }

    int emptyDescriptionSize() {
        return driver.findElements(emptyDescription()).size();
    }


}
