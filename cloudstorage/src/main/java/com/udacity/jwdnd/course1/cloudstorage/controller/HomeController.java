package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@Controller
public class HomeController {

    private UserMapper userMapper;
    private NoteService noteService;
    private FileService fileService;
    private CredentialService credentialService;

    public HomeController(NoteService noteService, UserMapper userMapper, FileService fileService, CredentialService credentialService) {
        this.noteService = noteService;
        this.userMapper = userMapper;
        this.fileService = fileService;
        this.credentialService = credentialService;
    }

    @GetMapping("/home")
    public String getChatPage(Authentication auth, Model model) {
        model.addAttribute("notes", this.noteService.getNotes(getUserId(auth)));
        model.addAttribute("files", this.fileService.getFiles(getUserId(auth)));
        model.addAttribute("credentials", this.credentialService.getCredentials(getUserId(auth)));
        return "home";
    }

    @PostMapping("/note")
    public String postNote(Authentication auth, NoteForm noteForm, Model model, RedirectAttributes redirectAttributes) {
        if (noteForm.getNoteId() != null) {
            this.noteService.updateNote(noteForm);
            redirectAttributes.addFlashAttribute("noteupdated", "Note successfully updated.");
        } else {
            noteForm.setUserid(getUserId(auth));
            this.noteService.addNewNote(noteForm);
            redirectAttributes.addFlashAttribute("noteadded", "New note added successfully.");
        }
        return "redirect:/home";
    }

    @GetMapping("/note/delete/{id}")
    public String deleteNote(@PathVariable Integer id, Authentication auth, Model model, RedirectAttributes redirectAttributes) {
        this.noteService.deleteNote(id);
        redirectAttributes.addFlashAttribute("notedeleted", "Note successfully deleted.");
        return "redirect:/home";
    }

    @PatchMapping("/note")
    public String updateNote(NoteForm noteForm, Authentication auth, Model model, RedirectAttributes redirectAttributes) {
        this.noteService.updateNote(noteForm);
        redirectAttributes.addFlashAttribute("noteupdated", "Note successfully updated.");
        return "redirect:/home";
    }

    @PostMapping("/file")
    public String postFile(Authentication auth, @RequestParam("fileUpload") MultipartFile fileUpload, Model model, RedirectAttributes redirectAttributes) throws IOException {
        Integer userid = getUserId(auth);
        String filename = fileUpload.getOriginalFilename();
        if (this.fileService.isFilenameAvailable(filename, userid)) {
            String contenttype = fileUpload.getContentType();
            Long filesize = fileUpload.getSize();
            InputStream fileStream = fileUpload.getInputStream();
            byte[] barray = new byte[fileStream.available()];
            fileStream.read(barray);
            File newFile = new File(null, filename, contenttype, filesize, userid, barray);
            this.fileService.addFile(newFile);
            redirectAttributes.addFlashAttribute("fileadded", "New file added successfully.");
            return "redirect:/home";
        }
        redirectAttributes.addFlashAttribute("fileexists", "A file with this name already exists.");
        return "redirect:/home";
    }

    @GetMapping("/file/delete/{id}")
    public String deleteFile(@PathVariable Integer id, Authentication auth, Model model, RedirectAttributes redirectAttributes) {
        this.fileService.deleteFile(id);
        redirectAttributes.addFlashAttribute("filedeleted", "File successfully deleted.");
        return "redirect:/home";
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<Resource> viewFile(@PathVariable Integer id, Authentication auth, Model model) {
        File viewFile = this.fileService.getFile(id);
        byte[] fileData = viewFile.getFiledata();
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(fileData));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(viewFile.getContenttype()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + viewFile.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping("/credential")
    public String postNote(@ModelAttribute Credential credential, Authentication auth, Model model, RedirectAttributes redirectAttributes) {
        if (credential.getCredentialId() != null) {
            this.credentialService.updateCredential(credential);
            redirectAttributes.addFlashAttribute("credupdated", "Credential successfully updated.");
        } else {
            System.out.println(credential);
            credential.setUserid(getUserId(auth));
            this.credentialService.createCredential(credential);
            redirectAttributes.addFlashAttribute("credadded", "New credential added successfully.");
        }
        return "redirect:/home";
    }

    @GetMapping("/credential/delete/{id}")
    public String deleteCredential(@PathVariable Integer id, Authentication auth, Model model, RedirectAttributes redirectAttributes) {
        this.credentialService.deleteCredential(id);
        redirectAttributes.addFlashAttribute("creddeleted", "Credential successfully deleted.");
        return "redirect:/home";
    }

    public Integer getUserId(Authentication auth) {
        return userMapper.getUser(auth.getName()).getUserId();
    }
}
