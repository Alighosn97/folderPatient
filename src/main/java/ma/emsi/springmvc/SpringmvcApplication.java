package ma.emsi.springmvc;

import ma.emsi.springmvc.entities.Patient;
import ma.emsi.springmvc.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class SpringmvcApplication implements CommandLineRunner {
    @Autowired
    PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(SpringmvcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //patientRepository.save(new Patient(null,"ali",new Date(),false));
        //patientRepository.save(new Patient(null,"mehdi",new Date(),true));
        //patientRepository.save(new Patient(null,"badr",new Date(),false));
        //patientRepository.save(new Patient(null,"karim",new Date(),true));
        patientRepository.findAll().forEach(p->{
            System.out.println(p.getName());
        });

    }
}
