package com.InfluencerMarketplace.clientapp.controllers;

import com.InfluencerMarketplace.clientapp.models.request.ChangeProfilePhotoRequest;
import com.InfluencerMarketplace.clientapp.services.BrandService;
import com.InfluencerMarketplace.clientapp.services.InfluencerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class FileUploadController {
    private InfluencerService influencerService;
    private BrandService brandService;


    @Autowired
    public FileUploadController(InfluencerService influencerService, BrandService brandService) {
        this.influencerService = influencerService;
        this.brandService = brandService;
    }

    @PostMapping("/influencer/upload")
    public ResponseEntity<?> handleFileUpload( @RequestParam("file") MultipartFile file ) {
            String fileName = file.getOriginalFilename();
            String checkFormat = fileName.substring(fileName.length() - 4);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();;
            fileName = authentication.getName() + ".jpg";
            System.out.println(checkFormat);
            if (checkFormat.equals(".jpg") || checkFormat.equals(".png") || checkFormat.equals("jpeg")) {
                Path uploadPath = Paths.get("src\\main\\resources\\static\\images\\profile");
                Path uploadPath2 = Paths.get("target\\classes\\static\\images\\profile");
                String pathFinder = "images\\profile";
                System.out.println(uploadPath.toString());
                try (InputStream inputStream = file.getInputStream(); InputStream inputStream2 = file.getInputStream()) {
                    Path filePath = uploadPath.resolve(fileName);
                    Path filePath2 = uploadPath2.resolve(fileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                    Files.copy(inputStream2, filePath2, StandardCopyOption.REPLACE_EXISTING);
                    ChangeProfilePhotoRequest request = new ChangeProfilePhotoRequest();
                    request.setPath(pathFinder + "\\" + fileName);
                    influencerService.changeProfilePhoto(request);
//            file.transferTo( new File("src\\main\\resources\\static\\images\\profile\\" + fileName));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
                return ResponseEntity.ok("File uploaded successfully.");
            } else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

    }

    @PostMapping("/brand/upload")
    public ResponseEntity<?> handleFileUploadBrand( @RequestParam("file") MultipartFile file ) {

        String fileName = file.getOriginalFilename();
        String checkFormat = fileName.substring(fileName.length() - 4);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();;
        fileName = authentication.getName() + ".jpg";
        System.out.println(checkFormat);
        if (checkFormat.equals(".jpg") || checkFormat.equals(".png") || checkFormat.equals("jpeg")) {
            Path uploadPath = Paths.get("src\\main\\resources\\static\\images\\brandProfile");
            Path uploadPath2 = Paths.get("target\\classes\\static\\images\\brandProfile");
            String pathFinder = "images\\brandProfile";
            System.out.println(uploadPath.toString());
            try (InputStream inputStream = file.getInputStream(); InputStream inputStream2 = file.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Path filePath2 = uploadPath2.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(inputStream2, filePath2, StandardCopyOption.REPLACE_EXISTING);
                ChangeProfilePhotoRequest request = new ChangeProfilePhotoRequest();
                request.setPath(pathFinder + "\\" + fileName);
                brandService.changeProfilePhoto(request);
//            file.transferTo( new File("src\\main\\resources\\static\\images\\profile\\" + fileName));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            return ResponseEntity.ok("File uploaded successfully.");
        } else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
