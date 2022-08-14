package com.example.app.controller;

import com.example.app.exception.AttachmentNotFoundException;
import com.example.app.model.Attachment;
import com.example.app.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attach")
public class AttachmentController {
    @Autowired
   private final AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Attachment>> getAllAttachments(){
        List<Attachment> attachments  = attachmentService.findAllAttachments();
        return new ResponseEntity<>(attachments, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Attachment> getAllAttachmentById(@PathVariable("id") Integer id) throws AttachmentNotFoundException {
        Attachment attachment= attachmentService.findAttachmentById(id);
        return new ResponseEntity<>(attachment, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Attachment> addAttachment(@RequestBody Attachment attachment){
        Attachment newAttachment = attachmentService.addAttachment(attachment);
        return new ResponseEntity<>(newAttachment, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Attachment> updateAttachment(@RequestBody Attachment attachment){
        Attachment updateAttachment = attachmentService.updateAttachment(attachment);
        return new ResponseEntity<>(updateAttachment, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAttachment(@PathVariable("id") Integer id) throws AttachmentNotFoundException{
        attachmentService.deleteAttachmentById(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
