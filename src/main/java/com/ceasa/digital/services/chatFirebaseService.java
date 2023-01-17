package com.ceasa.digital.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceasa.digital.Forms.chatFirebaseInitLoginAndAddContactForm;
import com.ceasa.digital.Forms.chatFirebaseUpdateuserChatForm;
import com.ceasa.digital.Model.chatFirebaseUserModel;
import com.ceasa.digital.Model.userModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class chatFirebaseService {

    @Autowired
    userService uService;

    public Map<String, Object> initAndValidatedLogin(chatFirebaseInitLoginAndAddContactForm cfbModelLogin)
            throws InterruptedException, ExecutionException {
        Map<String, Object> objUser = new HashMap<String, Object>();

        Firestore dbFirestore = FirestoreClient.getFirestore();

        CollectionReference userReference = dbFirestore.collection("user");

        Query query = userReference.whereEqualTo("id", cfbModelLogin.getId());
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        if (querySnapshot.get().isEmpty()) {

            userModel user = uService.recuperaUsuariobyId(cfbModelLogin.getId()).get();

            chatFirebaseUserModel cfbModel = new chatFirebaseUserModel();

            cfbModel.setId(user.getId());
            cfbModel.setName(user.getNome());
            cfbModel.setStatus("Offiline");
            String docID = "" + cfbModel.getId() + "";

            dbFirestore.collection("user").document(docID).create(cfbModel);

            objUser.put("id", user.getId());
            objUser.put("name", user.getNome());
            objUser.put("status", "offline");

            dbFirestore.collection("user")
                    .document(objUser.get("id").toString()).set(objUser);

            return objUser;
        } else {
            userModel user = uService.recuperaUsuariobyId(cfbModelLogin.getId()).get();

            objUser.put("id", user.getId());
            objUser.put("name", user.getNome());
            objUser.put("status", "Online");
            
            if(querySnapshot.get().getDocuments().get(0).get("photo") != null){
                objUser.put("photo",querySnapshot.get().getDocuments().get(0).get("photo").toString());

            }
            dbFirestore.collection("user")
                    .document(objUser.get("id").toString()).set(objUser);
        

            return querySnapshot.get().getDocuments().get(0).getData();
        }

    }

    public Map<String, Object> getUserByID(int id) throws InterruptedException, ExecutionException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        CollectionReference userReference = dbFirestore.collection("user");

        Query query = userReference.whereEqualTo("id", id);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        return querySnapshot.get().getDocuments().get(0).getData();
    }

    public List<chatFirebaseUserModel> getContactsByID(int id) throws InterruptedException, ExecutionException {

        List<chatFirebaseUserModel> contactList = new ArrayList<chatFirebaseUserModel>();

        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference userReference = dbFirestore.collection("user");
        Query query = userReference.whereEqualTo("id", id);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        QuerySnapshot contactsReference = dbFirestore.collection("user")
                .document(querySnapshot.get().getDocuments().get(0).getId().toString()).collection("contacts").get()
                .get();

        for (QueryDocumentSnapshot dados : contactsReference) {
            chatFirebaseUserModel contact = new chatFirebaseUserModel();
            contact.setId(Integer.parseInt(dados.getData().get("id").toString()));

          
            contact.setName(userReference.whereEqualTo("id", dados.getData().get("id")).get().get().getDocuments()
                    .get(0).getData().get("name").toString());
            

              contact.setStatus(userReference.whereEqualTo("id", dados.getData().get("id")).get().get().getDocuments()
              .get(0).getData().get("status").toString());

            if (userReference.whereEqualTo("id", dados.getData().get("id")).get().get().getDocuments().get(0).getData()
                    .get("photo") != null) {

                contact.setPhoto(userReference.whereEqualTo("id", dados.getData().get("id")).get().get().getDocuments()
                        .get(0).getData().get("photo").toString());
            }

            
            if (dados.getData().get("chatId") != null) {

                contact.setChatId(dados.getData().get("chatId").toString());
            }


            contactList.add(contact);
        }
        return contactList;
    }

    public Map<String, Object> updateUserChatData(chatFirebaseUpdateuserChatForm chatFirebaseUpdateuserChatForm)
            throws InterruptedException, ExecutionException {

        Map<String, Object> response = new HashMap<String, Object>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Map<String, Object> map = new HashMap<String, Object>();

        chatFirebaseInitLoginAndAddContactForm userobj = new chatFirebaseInitLoginAndAddContactForm(
                chatFirebaseUpdateuserChatForm.getId());
        Map<String, Object> oldUserInfos = initAndValidatedLogin(userobj);

        if (chatFirebaseUpdateuserChatForm.getId() == 0) {
            response.put("message", "Id Nulo: ");

            response.put("status", 400);

            return response;

        }

        if (!chatFirebaseUpdateuserChatForm.getStatus().isEmpty()) {

            map.put("status", chatFirebaseUpdateuserChatForm.getStatus());
        }

        if ((!chatFirebaseUpdateuserChatForm.getName().isEmpty())
                && !chatFirebaseUpdateuserChatForm.getPhoto().isEmpty()) {
            map.put("id", oldUserInfos.get("id"));
            map.put("name", chatFirebaseUpdateuserChatForm.getName());
            map.put("photo", chatFirebaseUpdateuserChatForm.getPhoto());

        }

        if ((chatFirebaseUpdateuserChatForm.getName().isEmpty())
                && !chatFirebaseUpdateuserChatForm.getPhoto().isEmpty()) {
            map.put("id", oldUserInfos.get("id"));
            map.put("photo", chatFirebaseUpdateuserChatForm.getPhoto());
        }

        if ((!chatFirebaseUpdateuserChatForm.getName().isEmpty())
                && chatFirebaseUpdateuserChatForm.getPhoto().isEmpty()) {
            map.put("id", oldUserInfos.get("id"));
            map.put("name", chatFirebaseUpdateuserChatForm.getName());

        }

        ApiFuture<WriteResult> collecApiFuture = dbFirestore.collection("user")
                .document(oldUserInfos.get("id").toString()).set(map);

        response.put("message", "Successfully updated: " + collecApiFuture.get().getUpdateTime());
        response.put("status", 200);

        return response;
    }

    public void addContact(int id_comprador, int id_vendedor) throws InterruptedException, ExecutionException {

        chatFirebaseInitLoginAndAddContactForm formVendedor = new chatFirebaseInitLoginAndAddContactForm(id_vendedor);
        chatFirebaseInitLoginAndAddContactForm formComprador = new chatFirebaseInitLoginAndAddContactForm(id_comprador);

        Map<String, Object> objVendedor = new HashMap<String, Object>();
        Map<String, Object> objComprador = new HashMap<String, Object>();


        Map<String, Object> objchat = new HashMap<String, Object>();
        Map<String, Object> messages = new HashMap<String, Object>();

        Map<String, Object> objVendedorID = initAndValidatedLogin(formVendedor);
        Map<String, Object> objCompradorID = initAndValidatedLogin(formComprador);

        objVendedor.put("id", objVendedorID.get("id"));
        objComprador.put("id", objCompradorID.get("id"));

        objchat.put("idVendedor", objVendedorID.get("id"));
        objchat.put("idComprador", objCompradorID.get("id"));

        messages.put("content", "");
        messages.put("stataus", "");
        messages.put("type", "");
        messages.put("timeStamp", "");

        Firestore dbFirestore = FirestoreClient.getFirestore();

        // criando ids para geração da collection
        String idCollectionContactsComprador = objCompradorID.get("id").toString();
        String idCollectionContactsVendedor = objVendedorID.get("id").toString();

        DocumentReference contactReference = dbFirestore.collection("user").document(idCollectionContactsComprador)
                .collection("contacts").document(idCollectionContactsVendedor);

        ApiFuture<DocumentSnapshot> resultContactReference = contactReference.get();
        if (!resultContactReference.get().exists()) {

            //criando chat

            dbFirestore.collection("chats").document(idCollectionContactsVendedor+idCollectionContactsComprador).create(objchat);

            dbFirestore.collection("chats").document(idCollectionContactsVendedor+idCollectionContactsComprador).collection("messages").add(messages);


            objVendedor.put("chatId", idCollectionContactsVendedor+idCollectionContactsComprador);
            objComprador.put("chatId", idCollectionContactsVendedor+idCollectionContactsComprador);


            dbFirestore.collection("user").document(idCollectionContactsComprador).collection("contacts")
                    .document(idCollectionContactsVendedor).create(objVendedor);


            // adicionando a lista de contatos do vendedor
            dbFirestore.collection("user").document(idCollectionContactsVendedor).collection("contacts")
                    .document(idCollectionContactsComprador).create(objComprador);

        }

    }

}
