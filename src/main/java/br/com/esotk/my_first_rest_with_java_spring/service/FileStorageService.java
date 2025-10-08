package br.com.esotk.my_first_rest_with_java_spring.service;

import br.com.esotk.my_first_rest_with_java_spring.config.FileStorageConfig;
import br.com.esotk.my_first_rest_with_java_spring.controller.FileController;
import br.com.esotk.my_first_rest_with_java_spring.exception.FileStorageException;
import br.com.esotk.my_first_rest_with_java_spring.exception.FileStorageNotFoundException;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Data
@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);

    public FileStorageService(FileStorageConfig fileStorageConfig){
        Path path = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath()
                .toAbsolutePath().normalize();

        this.fileStorageLocation = path;

        try {
            logger.info("Criando Diretorio onde os arquivos enviados serão armazenados.");
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e){
            logger.error("Não foi possível criar o diretório onde os arquivos enviados serão armazenados.", e);
            throw new FileStorageException("Não foi possível criar o diretório onde os arquivos enviados serão armazenados.", e);

        }

    }

    public String storeFile(MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")){
                logger.error("Nome de arquivo inválido " + fileName);
                throw new FileStorageException("Nome de arquivo inválido " + fileName);
            }
            logger.info("Armazenando arquivo " + fileName + " no diretorio.");
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (Exception e) {
            logger.error("Não foi possível armazenar o arquivo " + fileName + ". Por favor tente novamente.");
            throw new FileStorageException("Não foi possível armazenar o arquivo " + fileName + ". Por favor tente novamente");
        }

    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                logger.error("Arquivo não encontrado " + fileName);
                throw new FileStorageNotFoundException("Arquivo não encontrado" + fileName);
            }
        } catch (Exception e) {
            logger.error("Arquivo não encontrado " + fileName);
            throw new FileStorageNotFoundException("Arquivo não encontrado" + fileName);
        }

    }

}
