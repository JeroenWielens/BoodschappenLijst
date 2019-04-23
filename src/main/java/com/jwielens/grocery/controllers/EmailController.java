package com.jwielens.grocery.controllers;

import com.jwielens.grocery.domain.Product;
import com.jwielens.grocery.domain.User;
import com.jwielens.grocery.services.ProductService;
import com.jwielens.grocery.services.UserService;
import lombok.val;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
public class EmailController {
    private ProductService productService;
    private UserService userService;

    public EmailController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/sendemail/{id}")
    public String sendEmail(@PathVariable Long id, RedirectAttributes attributes) throws MessagingException {
        Optional<User> boodschapper = userService.findByid(id);
        String emailAdres = "test@test.nl";
        if (boodschapper.isPresent()){
            emailAdres = boodschapper.get().getEmailadres();
        }

        val mailSender = new JavaMailSenderImpl();
        mailSender.setHost("127.0.0.1");
        mailSender.setPort(1025);
        mailSender.setUsername("");
        mailSender.setPassword("");

        val props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");

        val mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("zender@ocs.nl");
        mimeMessageHelper.setTo(emailAdres);
        mimeMessageHelper.setText(maakBoodschappenLijst(), true);
        mimeMessage.setSubject("Boodschappenlijst " + datumVandaag());

        mailSender.send(mimeMessage);

        attributes.addFlashAttribute("message", "Email sent successfully");
        attributes.addFlashAttribute("alertClass", "alert-success");

        return "redirect:/products";
    }

    private String maakBoodschappenLijst() {
        Set<Product> producten = new HashSet<>();
        for (Product product : productService.getProducten()){
            if (product.getBenodigdeHoeveelheid()>1){
                producten.add(product);
            }
        }
        return "Dit is de boodschappenlijst voor " + datumVandaag() + "<br/><br/>" +
                producten.toString()
                        .replace(",", "<br/>")
                        .replace("[", "")
                        .replace("]", "");
    }

    private String datumVandaag() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return localDate.format(formatter);
    }
}
