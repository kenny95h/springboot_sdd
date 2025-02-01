package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public List<Credential> getCredentials(Integer userid) {
        return credentialMapper.getCredentialsByUserId(userid);
    }

    public int createCredential(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String encryptedpassword = encryptionService.encryptValue(credential.getEncryptedpassword(), encodedSalt);
        return credentialMapper.insertCredential(new Credential(null, credential.getUrl(), credential.getUsername(), encryptedpassword, credential.getUserid(), encodedSalt));
    }

    public void deleteCredential(Integer credentialId) {
        credentialMapper.deleteCredential(credentialId);
    }

    public void updateCredential(Credential credential) {
        String encryptedpassword = encryptionService.encryptValue(credential.getEncryptedpassword(), credentialMapper.getCredentialById(credential.getCredentialId()).getSalt());
        credential.setEncryptedpassword(encryptedpassword);
        credentialMapper.updateCredential(credential);
    }
}
