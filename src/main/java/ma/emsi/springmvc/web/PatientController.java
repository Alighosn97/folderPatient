package ma.emsi.springmvc.web;

import ma.emsi.springmvc.entities.Patient;
import ma.emsi.springmvc.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PatientController {

    @Autowired
    PatientRepository patientRepository;
    @GetMapping(path = "/index")
    public String index(){
        return "index";
    }
    @GetMapping(path = "/patients")
    public String list(Model model, @RequestParam(name = "page" ,defaultValue = "0") int page ,@RequestParam(name = "size",defaultValue = "5") int size,@RequestParam(name = "keyword",defaultValue = "") String mc){
        Page<Patient> patients = patientRepository.findByNameContains(mc, PageRequest.of(page,size));
        model.addAttribute("patients",patients.getContent());
        model.addAttribute("pages",new int[patients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",mc);
        model.addAttribute("size",size);
        return "patients";
    }
    @GetMapping(path = "/deletePatient")
    public String delete(Long id, String keyword , int page,int size){
        patientRepository.deleteById(id);
        return "redirect:/patients?page="+page+"&size="+size+"&keyword="+keyword;
    }
    @GetMapping(path="/formPatient")
    public String formPatient(Model model ){
        model.addAttribute("patient",new Patient());
        return "formPatient";
    }
    @PostMapping(path = "/savePatient")
    public String savePatient(Model model, @Valid Patient patient, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())return "formPatient";
        patientRepository.save(patient);
        model.addAttribute(patient);
        return "confirmation";
    }

    @GetMapping(path = "/editPatient")
    public String editPatient(Model model,  Long id){
        Patient p = patientRepository.findById(id).get();
        model.addAttribute(p);
        return "formPatient";
    }

}
