package com.portfolio.portfoliogg.Controller;

import com.portfolio.portfoliogg.Entity.Educacion;
import com.portfolio.portfoliogg.Service.ServiceEducacion;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educacion")
@CrossOrigin(origins = "http://localhost:4200")
public class ControllerEducacion {
    
    @Autowired
    ServiceEducacion servEdu;
    
    
    @GetMapping("/listaeducacion")
    public ResponseEntity<List<Educacion>> listaEducacion() {
        List<Educacion> listaEducacion = servEdu.listaEducacion();
        return new ResponseEntity(listaEducacion, HttpStatus.OK);
    }
    
    
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/creareducacion")
    public ResponseEntity<?> crearEducacion(@RequestBody Educacion educacion) {
        Educacion educ = new Educacion(educacion.getNombreEducacion(), educacion.getFechaEducacion(), educacion.getDescripcionEducacion());
        servEdu.guardar(educ);

        return new ResponseEntity(new Mensaje("Educacion añadida"), HttpStatus.OK);
    }
    

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizareducacion/{id}")
     public ResponseEntity<?> editarEducacion(@PathVariable("id") int id, @RequestBody Educacion educacion) {

     Educacion educ = servEdu.getOne(id).get();

      educ.setNombreEducacion(educacion.getNombreEducacion());
      educ.setFechaEducacion(educacion.getFechaEducacion());
      educ.setDescripcionEducacion(educacion.getDescripcionEducacion());

      servEdu.guardar(educ);
      return new ResponseEntity(new Mensaje("Educacion actualizada"), HttpStatus.OK);
    }
     
     
     
     
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/borrareducacion/{id}")
    public ResponseEntity<?> borrarEducacion(@PathVariable("id") int id) {
       servEdu.borrar(id);
        return new ResponseEntity(new Mensaje("Educacion eliminada"), HttpStatus.OK);
    }
    
    
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/actualizarporid/{id}")
    public ResponseEntity<Educacion>actualizarPorId(@PathVariable("id")  int id){
    
    Educacion educacion = servEdu.getOne(id).get();
    return new ResponseEntity(educacion, HttpStatus.OK);
    }
    
}
