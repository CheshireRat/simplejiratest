package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IssuePage {

    WebDriver driver;

    IssuePage(WebDriver driver) {
        this.driver = driver;
    }

    private By issueTypeField = By.id ("issuetype-field");
    private By summaryField = By.id ("summary");
    private By descriptionField = By.id ("description");
    private By submitIssueButton = By.id ("create-issue-submit");


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


}
