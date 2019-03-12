package com.jwielens.grocery.controllers;

import com.jwielens.grocery.domain.Boodschapper;
import com.jwielens.grocery.domain.Product;
import com.jwielens.grocery.services.BoodschapperService;
import com.jwielens.grocery.services.ProductService;
import lombok.val;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@RestController
public class EmailController {
    private ProductService productService;
    private BoodschapperService boodschapperService;

    public EmailController(ProductService productService, BoodschapperService boodschapperService) {
        this.productService = productService;
        this.boodschapperService = boodschapperService;
    }

    @RequestMapping("/sendemail/{id}")
    public String sendEmail(@PathVariable Long id) throws MessagingException {
        Boodschapper boodschapper = boodschapperService.getBoodschapperById(id);

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
        mimeMessageHelper.setTo(boodschapper.getEmailadres());
        mimeMessageHelper.setText(maakBoodschappenLijst(), true);
        mimeMessage.setSubject("Boodschappenlijst " + datumVandaag());

        mailSender.send(mimeMessage);
        return "Email sent";
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
