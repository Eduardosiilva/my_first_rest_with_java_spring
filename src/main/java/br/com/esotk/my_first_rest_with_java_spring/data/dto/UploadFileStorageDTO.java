package br.com.esotk.my_first_rest_with_java_spring.data.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;


@Data
public class UploadFileStorageDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private Long size;

    public UploadFileStorageDTO(String fileName,
                                String fileDownloadUri,
                                String contentType,
                                long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = contentType;
        this.size = size;
    }
}
