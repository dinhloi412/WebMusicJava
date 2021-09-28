package tdmu.music.web.pm03.apicontroller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class UploadFileAPIController {
    @RequestMapping(value = "/api/getimages/{productImage}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("productImage") String photo)
    {
        if(!photo.equals("")||photo!=null)
        {
            try {
                Path filename= Paths.get("src/main/resources/static/productImages",photo);
                byte[] buffer= Files.readAllBytes(filename);
                ByteArrayResource byteArrayResource= new ByteArrayResource(buffer);

                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("audio/mpeg3"))
                        .body(byteArrayResource);

            }catch (Exception e)
            {

            }

        }
        return ResponseEntity.badRequest().build();
    }
}
