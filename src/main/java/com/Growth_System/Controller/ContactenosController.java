package com.Growth_System.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Growth_System.Service.ContactenosService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactenosController {
	
	@Autowired
	private ContactenosService contactenosService;
	
	@GetMapping("/")
	public String index() {
	return "index.html";
	}
	
	@GetMapping("/contactenos")
	public String getContactenos() {
	return "assets/contactenos";
	}
	
	@GetMapping("/proceso_desinfeccion")
	public String getDesinfeccion() {
	return "assets/proceso_desinfeccion";
	}
	
	@GetMapping("/sendMail")
	public String getEnvioMail(){
	return "assets/contactenos";
	}
	
	@PostMapping("/sendMail") //Apuntamos a nuestra url que se definio en nuestro formulario
	public String sendMail(@RequestParam("name") String name,
						   @RequestParam("mail") String mail, @RequestParam("subject") String subject, @RequestParam("body") String body){
	String message = body +"\n\n Datos de contacto: " + "\nNombre: " + name + "\nE-mail: " + mail;
	contactenosService.sendMail("growthsystemsena@gmail.com","growthsystemsena@gmail.com",subject,message);
	return "redirect:/contactenos";
	}
}
