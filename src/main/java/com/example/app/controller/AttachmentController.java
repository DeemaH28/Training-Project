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
@RequestMapping("/attachments")
public class AttachmentController {
    @Autowired
    public final  AttachmentService attachmentService;


    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }
    @GetMapping()
    public ResponseEntity<List<Attachment>> getAllAttachment(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize){
        List<Attachment>attachments=attachmentService.findAllAttachments(pageNo, pageSize);
        return new ResponseEntity<>(attachments, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Attachment> getAttachmentById(@PathVariable("id") Integer id) throws AttachmentNotFoundException {
        Attachment attachment= attachmentService.findAttachmentById(id);
        return new ResponseEntity<>(attachment, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Attachment> addAttachment(@RequestBody Attachment attachment){
        Attachment newAttachment = attachmentService.addAttachment(attachment);
        return new ResponseEntity<>(newAttachment, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Attachment> updateAttachment(@RequestBody Attachment attachment){
        Attachment updateAttachment = attachmentService.updateAttachment(attachment);
        return new ResponseEntity<>(updateAttachment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttachment(@PathVariable("id") Integer id) throws AttachmentNotFoundException{
        attachmentService.deleteAttachmentById(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }


}
