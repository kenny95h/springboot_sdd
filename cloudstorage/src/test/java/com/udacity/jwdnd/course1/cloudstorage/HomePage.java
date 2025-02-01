package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(id="logout-button")
    private WebElement logoutButton;

    @FindBy(id="nav-notes-tab")
    private WebElement notebar;

    @FindBy(id="nav-credentials-tab")
    private WebElement credbar;

    @FindBy(id="add-note-button")
    private WebElement addNote;

    @FindBy(id="add-cred-button")
    private WebElement addCred;

    @FindBy(id="note-title")
    private WebElement noteTitleInput;

    @FindBy(id="note-description")
    private WebElement noteDescriptionInput;

    @FindBy(id="note-submit")
    private WebElement noteSubmit;

    @FindBy(id="credential-url")
    private WebElement credUrlInput;

    @FindBy(id="credential-username")
    private WebElement credUsernameInput;

    @FindBy(id="credential-password")
    private WebElement credPasswordInput;

    @FindBy(id="credential-submit")
    private WebElement credSubmit;


    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void logout() {
        this.logoutButton.click();
    }

    public void openNotesTab() {
        this.notebar.click();
    }

    public void openCredsTab() {
        this.credbar.click();
    }

    public void addNoteButton() {
        this.addNote.click();
    }


    public void addCredButton() {
        this.addCred.click();
    }

    public void createNote(String title, String description) {
        this.noteTitleInput.sendKeys(title);
        this.noteDescriptionInput.sendKeys(description);
        this.noteSubmit.click();
    }

    public void updateNote(String title, String description) {
        this.noteTitleInput.clear();
        this.noteTitleInput.sendKeys(title);
        this.noteDescriptionInput.clear();
        this.noteDescriptionInput.sendKeys(description);
        this.noteSubmit.click();
    }

    public void createCred(String url, String username, String password) {
        this.credUrlInput.sendKeys(url);
        this.credUsernameInput.sendKeys(username);
        this.credPasswordInput.sendKeys(password);
        this.credSubmit.click();
    }

    public void updateCredPassword(String password) {
        this.credPasswordInput.clear();
        this.credPasswordInput.sendKeys(password);
        this.credSubmit.click();
    }
}
