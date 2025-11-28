package com.cosc75.demo.Controller;
import com.cosc75.demo.Models.ProfessionalSummary;
import com.cosc75.demo.Models.Resume;
import com.cosc75.demo.Services.ResumeService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/generate")  // class-level
public class ResumeController {

    private final ResumeService service;

    public ResumeController(ResumeService service) {
        this.service = service;
    }

    @PostMapping(produces = MediaType.APPLICATION_PDF_VALUE) // no "/generate" in path
    public ResponseEntity<byte[]> generate(@RequestBody Resume resume) throws Exception {

        // Call service
        byte[] pdfBytes = service.generateResumePdf(
                resume.getPersonalInfo(),
                resume.getSummary(),
                resume.getExperience(),
                resume.getEducation(),
                resume.getSkills() // List<String>
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("inline").filename("resume.pdf").build());


        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

}
