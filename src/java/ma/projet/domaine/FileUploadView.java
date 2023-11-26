/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Asmaa
 */
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.faces.bean.ManagedBean;

@Named
@ManagedBean(name = "yourBean")
public class FileUploadView implements Serializable {

    private UploadedFile uploadedFile;

    private String photo; // Assuming this is the field in your database to store the photo path

    public void handleFileUpload(FileUploadEvent event) {
        uploadedFile = event.getFile();

        // Save the file to a directory on your server
        String uploadDirectory = "";
        String fileName = uploadedFile.getFileName();

        // Specify the complete path, including the directory
        Path path = Paths.get(uploadDirectory, fileName);

        FacesMessage message = new FacesMessage("Successful", fileName + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);

        try (InputStream input = uploadedFile.getInputstream()) {
            Files.copy(input, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }

        // Set the photo path in your managed bean (you may want to save this path in your database)
        photo = "" + fileName;
    }

    // Getter and setter for photoPath
    public String getPhotoPath() {
        return photo;
    }

    public void setPhotoPath(String photoPath) {
        this.photo = photoPath;
    }

    // Getter and setter for uploadedFile
    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
}
