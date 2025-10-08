package br.com.esotk.my_first_rest_with_java_spring.controller.docs;

import br.com.esotk.my_first_rest_with_java_spring.data.dto.UploadFileStorageDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "File", description = "File Controller")
public interface FileControllerDocs {

    UploadFileStorageDTO uploadFile(MultipartFile file);
    List<UploadFileStorageDTO> uploadMultipleFiles(MultipartFile[] file);
    ResponseEntity<Resource> downloadFile(String fileName, HttpServletRequest request);


}
