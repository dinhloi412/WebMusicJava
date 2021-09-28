package tdmu.music.web.pm03.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tdmu.music.web.pm03.model.Product;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class UploadFile {

//    @PostMapping("/save")
//    public String save(@RequestParam("productImage") MultipartFile file
//    , @RequestParam("imgName")String imgName, ModelMap model)
//    {
//        Product  product= new Product();
//        if(file.isEmpty())
//        {
//            return "404";
//        }
//        Path path= Paths.get("uploads/");
//        try {
//            InputStream inputStream= file.getInputStream();
//            Files.copy(inputStream, path.resolve(file.getOriginalFilename()),
//                    StandardCopyOption.REPLACE_EXISTING);
//
//        }
//        catch (Exception e)
//        {
//
//        }
//    }
    @RequestMapping(value = "/getimages/{productImage}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("productImage") String photo)
    {
        if(!photo.equals("")||photo!=null)
        {
            try {
                Path filename=Paths.get("src/main/resources/static/productImages",photo);
                byte[] buffer= Files.readAllBytes(filename);
                ByteArrayResource byteArrayResource= new ByteArrayResource(buffer);

                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/png"))
                        .body(byteArrayResource);

            }catch (Exception e)
            {

            }

        }
        return ResponseEntity.badRequest().build();
    }
//    @RequestMapping(value = "image/{productImage}",method = RequestMethod.GET)
//    public ResponseEntity<byte[]>getImage(@PathVariable("productImage")String productImage)
//    {
//        byte[] image=
//    }

}
