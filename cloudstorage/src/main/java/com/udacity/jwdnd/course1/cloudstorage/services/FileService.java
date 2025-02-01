package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class FileService {
    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) { this.fileMapper = fileMapper; }

    public void addFile(File file) {
        fileMapper.addFile(file);
    };

    public void deleteFile(Integer fileId) {
        fileMapper.deleteFile(fileId);
    }

    public boolean isFilenameAvailable(String filename, Integer userid) {
        return fileMapper.getFileByFilename(filename, userid) == null;
    }

    public File getFile(Integer fileId) {
        return fileMapper.getFileById(fileId);
    }

    public List<File> getFiles(Integer userid) {
        return fileMapper.getFilesByUserId(userid);
    }
}
