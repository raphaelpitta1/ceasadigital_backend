package com.ceasa.digital.Controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceasa.digital.Forms.chatFirebaseInitLoginAndAddContactForm;
import com.ceasa.digital.Forms.chatFirebaseUpdateuserChatForm;
import com.ceasa.digital.Model.chatFirebaseUserModel;
import com.ceasa.digital.services.chatFirebaseService;
import com.google.rpc.context.AttributeContext.Response;


@RestController
@RequestMapping("api/chat")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class chatFirebaseController {

    @Autowired
    chatFirebaseService cfbService;

    @PostMapping("/initLogin")

    public ResponseEntity<Map<String, Object>> initLogin(@RequestBody chatFirebaseInitLoginAndAddContactForm cfbModel) throws InterruptedException, ExecutionException{

        return ResponseEntity.ok().body(cfbService.initAndValidatedLogin(cfbModel));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Map<String, Object>> getUserByID(@PathVariable int id) throws InterruptedException, ExecutionException{

        return ResponseEntity.ok().body(cfbService.getUserByID(id));
    }

    @GetMapping("/user/contacts/{id}")
    public ResponseEntity<List<chatFirebaseUserModel>> getContactsByID(@PathVariable int id) throws InterruptedException, ExecutionException{

        return ResponseEntity.ok().body(cfbService.getContactsByID(id));
    }


    @PatchMapping("/user")
    public ResponseEntity<Map<String, Object>> updateUserChatData(@RequestBody chatFirebaseUpdateuserChatForm cFirebaseUpdateuserChatForm) throws InterruptedException, ExecutionException{
        
        Map<String, Object> response = cfbService.updateUserChatData(cFirebaseUpdateuserChatForm);
        int status = (int) response.get("status");
        return ResponseEntity.status(status).body(response);
     


    }

}
