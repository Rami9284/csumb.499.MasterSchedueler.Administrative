package com.csumb.Administrative;

import com.csumb.Administrative.entities.Student;
import com.csumb.Administrative.fileEntities.FileStorageService;
import com.csumb.Administrative.fileEntities.UploadFileResponse;
import com.csumb.Administrative.repositotries.IStudentRepository;
import com.opencsv.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private AdministrativeController administrativeController;

    @Autowired
    private IStudentRepository studentRepository;

    @PostMapping("/uploadFile/{type}")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String type) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
//        if(fileName.endsWith(".csv")) {
//            try {
//                BufferedReader br =
//                        new BufferedReader(new FileReader("/Users/illusion/Desktop/csumb.499.MasterSchedueler.Administrative/src/main/resources/uploads/" + fileName));                String line;
//                boolean isFirstLine = true;
//                while ((line = br.readLine()) != null) {
//
//                    String[] values = line.split(",");
//                    List<String> val = Arrays.asList(values);
//                    if(!isFirstLine){
//                        if(type.equals("students")){
//                            List<String> classes = Arrays.asList(val.get(3), val.get(5), val.get(7),val.get(9), val.get(11)
//                            ,val.get(13),val.get(15),val.get(17));
//                            List<boolean> pref =Arrays.asList(val.get(4), val.get(6), val.get(8),val.get(10), val.get(12)
//                                    ,val.get(14),val.get(16),val.get(18));
//                            Student tempStudent = new Student(val.get(0),val.get(1),val.get(2),"None",classes,pref);
////                            Student(String id, String name, int grade,
////                            String academy, List<String> preferred_classes,List<Boolean> preferred){
//                                administrativeController.addStudent(tempStudent);
//                                System.out.println("student upload");
//
//                        } else if(type.equals("class")){
//                            System.out.println("class upload");
//                        } else if(type.equals("teachers")){
//                            System.out.println("teacher upload");
//                        }
//                        System.out.println(Arrays.asList(values));
//                    }
//                    isFirstLine = false;
//                }
//                br.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file,"none"))
                .collect(Collectors.toList());
    }

    //@GetMapping("/downloadFile/{fileName:.+}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
    @GetMapping("/downloadFile/{type}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String type, HttpServletRequest request) {
        // Load file as Resource
        String fileName = "";
        if(type.equals("students")){
            fileName = "student.csv";
            List<Student> students = studentRepository.findAll();
            List<List<String>> data = new ArrayList<>();
            for(Student s: students){
                data.add(s.getData());
            }
            String[] header = {"id","name","grade","academy","period 1", "period 2",
                    "period 3", "period 4", "period 5", "period 6", "prefClass1", "pref1",
                    "prefClass2", "pref2", "prefClass3", "pref3", "prefClass4", "pref4",
                    "prefClass5", "pref5", "prefClass6", "pref6"};
            DataWrite("student.csv",data, header);
        }
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    private void DataWrite(String fileName, List<List<String>> data,String[] header){
        //File file = new File("/Users/illusion/Desktop/csumb.499.MasterSchedueler.Administrative/src/main/resources/uploads/" + fileName);
        File file = new File("/app/src/main/resources/uploads/" + fileName); //heroku

        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);
            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);
            writer.writeNext(header);

            String[] temp = {};
            for(List<String> s: data){
                writer.writeNext(s.toArray(temp));
            }

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}