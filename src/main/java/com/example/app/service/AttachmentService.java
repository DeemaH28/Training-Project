package com.example.app.service;

import com.example.app.exception.AttachmentNotFoundException;
import com.example.app.model.Attachment;
import com.example.app.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AttachmentService {
    @Autowired
    private final AttachmentRepository attachmentRepository;

    public AttachmentService(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }


    //add and Save
    public Attachment addAttachment(Attachment attachment){
        return attachmentRepository.save(attachment);
    }

    // find all invoices
    public List<Attachment> findAllAttachments(){
        return (List<Attachment>) attachmentRepository.findAll();
    }
    //update
    public Attachment updateAttachment(Attachment attachment){
        return attachmentRepository.save(attachment);
    }

    public void deleteAttachmentById(Integer id)throws AttachmentNotFoundException {

        Long count = attachmentRepository.countById(id);
        if (count == null || count == 0){
            throw new AttachmentNotFoundException("Could not find any attachment with ID "+ id);
        }
        attachmentRepository.deleteById(id);
    }

    public Attachment findAttachmentById(Integer id) throws AttachmentNotFoundException {
        Optional<Attachment> result = attachmentRepository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new AttachmentNotFoundException ("Could not find any attachment with ID "+ id);
    }
}
