package controllers.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.inject.Singleton;

import controllers.rest.WebserviceInteraction;
import controllers.base.BaseController;
import controllers.requestdto.UsersRequestDto;
import controllers.responsedto.UsersResponseDto;
import models.Person;
import play.mvc.BodyParser;
import play.mvc.Result;
import services.serviceimpl.ServicesFactory;

/**
 * The main set of web services.
 */
@Named
@Singleton
public class Application extends BaseController {

    //private final PersonRepository personRepository;

    // We are using constructor injection to receive a repository to support our desire for immutability.
   /* @Inject
    public Application(final PersonRepository personRepository, final ServicesFactory servicesFactory) {
        this.personRepository = personRepository;
        this.servicesFactory = servicesFactory;
    }*/
    
	@Resource
    private ServicesFactory servicesFactory;

	@Resource
	private WebserviceInteraction webserviceInteraction;
	
	@BodyParser.Of(BodyParser.Json.class)
    public Result index() {

        // For fun we save a new person and then find that one we've just saved. The id is auto generated by
        // the db so we know that we're round-tripping to the db and back in order to demonstrate something
        // interesting. Spring Data takes care of transactional concerns and the following code is all
        // executed on the same thread (a requirement of the JPA entity manager).

        final Person person = new Person();
        person.firstname = "Bruce";
        person.surname = "Smith";

        /*final Person savedPerson = personRepository.save(person);

        final Person retrievedPerson = personRepository.findOne(savedPerson.id);*/
        
        final Person savedPerson = servicesFactory.personService.savePerson(person);

        final Person retrievedPerson = servicesFactory.personService.findOnePerson(savedPerson.id);

        // Deliver the index page with a message showing the id that was generated.
        UsersRequestDto request = new UsersRequestDto();
        request.userType = "USER";
        //try {
        	/*Users user = servicesFactory.usersService.createUser("GUEST");
            Users onotherUser = servicesFactory.usersService.findUserById(user.userId);*/
            
        //} catch(BaseException ex) {
        	
        //}
        return ok(views.html.index.render("Found id: " + retrievedPerson.id + " of person/people Addeed text"));
        
    }
    
    
    public Result persons() {
    	
    	List<UsersResponseDto> response = null;
    	//try {
	    	/*List<Users> users = servicesFactory.usersService.users();
	    	
	    	response = new ArrayList<>();
	    	for(Users user : users) {
	    		UsersResponseDto aUserResponse = new UsersResponseDto(String.valueOf(user.userId), user.userType);
	    		response.add(aUserResponse);
	    	}*/
    		
    		List<Person> persons = servicesFactory.personService.persons();
	    	
	    	response = new ArrayList<>();
	    	for(Person person : persons) {
	    		UsersResponseDto aUserResponse = new UsersResponseDto(String.valueOf(person.firstname), person.surname);
	    		response.add(aUserResponse);
	    	}
	    	//WebserviceInteraction wsIteraction = new WebserviceInteraction();
	    	webserviceInteraction.printResponse(webserviceInteraction.getCityForecastByZip("77001"));
    	/*} catch (BaseException ex) {
    		
    	}*/
    	return convertObjectToJsonResponse(response);
    }
}
