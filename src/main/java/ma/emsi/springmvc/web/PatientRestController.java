package ma.emsi.springmvc.web;

import ma.emsi.springmvc.entities.Patient;
import ma.emsi.springmvc.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientRestController {
    @Autowired
    PatientRepository patientRepository;
    @GetMapping(path="/listPatient")
    public List<Patient> list(){
        return patientRepository.findAll();
    }
    @GetMapping(path="/patients/{id}")
    public Patient getOne(@PathVariable Long id ){
        return patientRepository.findById(id).get();
    }

}
