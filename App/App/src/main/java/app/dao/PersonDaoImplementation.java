package app.dao;

import app.dao.interfaces.PersonDao;
import app.dao.repository.PersonRepository;
import app.dto.PersonDto;
import app.helper.Helper;
import app.model.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@NoArgsConstructor
public class PersonDaoImplementation implements PersonDao {

    @Autowired
    PersonRepository personRepository;

    @Override
    public boolean existByDocument(PersonDto personDto) throws Exception {
        return personRepository.existsByDocument(personDto.getDocument());
    }

    @Override
    public void createPerson(PersonDto personDto) throws Exception {
        Person person = Helper.parse(personDto);
        personRepository.save(person);
        personDto.setId(person.getId());
    }

    @Override
    public void deletePerson(PersonDto personDto) throws Exception {
        Person person = Helper.parse(personDto);
        personRepository.delete(person);
    }

    @Override
    public PersonDto findByDocument(PersonDto personDto) throws Exception {
        Person person = personRepository.findByDocument(personDto.getDocument());
        if(person==null){
            return null;
        }
        return Helper.parse(person);

    }

}
