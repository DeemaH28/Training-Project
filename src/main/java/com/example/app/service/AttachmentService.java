package com.example.app.service;

import com.example.app.exception.AttachmentNotFoundException;
import com.example.app.exception.InvoiceNotFoundException;
import com.example.app.model.Attachment;
import com.example.app.model.Invoice;
import com.example.app.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<Attachment> findAllAttachments(int pageNo, int pageSize){
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Attachment> pagedResult = attachmentRepository.findAll(paging);

        return pagedResult.toList();
    }

    //update
    public Attachment updateAttachment(Attachment attachment){
        Optional<Attachment> attachmentOptional = attachmentRepository.findById(attachment.getId());
        if (attachmentOptional.isEmpty()) {
            return attachmentRepository.save(attachment);

        }
        Attachment attachment1 = attachmentOptional.get();
        attachment1.setId(attachment.getId());
        attachment1.setUrl(attachment.getUrl());
        attachment1.setSize(attachment.getSize());
        attachment1.setAttachType(attachment.getAttachType());

        return attachmentRepository.save(attachment1);
    }

    public void deleteAttachmentById(Integer id)throws AttachmentNotFoundException {

        Optional<Attachment> result = attachmentRepository.findById(id);
        if (result.isPresent()){
            attachmentRepository.deleteById(id);;
        }
        else {
            throw new AttachmentNotFoundException("Could not find any attachment with ID " + id);

        }
    }

    public Attachment findAttachmentById(Integer id) throws AttachmentNotFoundException {
        Optional<Attachment> result = attachmentRepository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new AttachmentNotFoundException ("Could not find any attachment with ID "+ id);
    }
}
