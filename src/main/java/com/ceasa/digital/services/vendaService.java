package com.ceasa.digital.services;

import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ceasa.digital.Enums.vendaResponsesEnum;
import com.ceasa.digital.Enums.negociacaoResponsesEnum;
import com.ceasa.digital.Enums.vendaNegociacaoStatusEnum;

import com.ceasa.digital.Model.ofertaModel;
import com.ceasa.digital.Model.produtoModel;
import com.ceasa.digital.Model.userModel;
import com.ceasa.digital.Model.vendaModel;
import com.ceasa.digital.Repository.ofertaRepository;
import com.ceasa.digital.Repository.produtoRepository;
import com.ceasa.digital.Repository.vendaRepository;

@Service
public class vendaService {

    @Autowired
    private vendaRepository vRepository;
    @Autowired
    private ofertaRepository oRepository;
    @Autowired
    negociacaoService nService;
    @Autowired
    chatFirebaseService chatFirebaseService;
    @Autowired
    userService uService;
    @Autowired
    whatasappService wService;
    @Autowired
    produtoRepository pRepository;
    @Autowired
    ofertaRepository ofertaRepository;
    @Value("${phone.prefix}")
    private String phonePrefix;
    @Value("${app.link}")
    private URL appLink;
   
    
    public httpResponses fazerVenda(int idOferta, int idComprador, int qtd) {

        try {
   
            Optional<ofertaModel> validaExistenciaOferta = oRepository.findById(idOferta);
            
    
            if (!validaExistenciaOferta.isEmpty()) {

                if(validaExistenciaOferta.get().getQtdDisponivel()>= qtd){

                    if(validaExistenciaOferta.get().getIdVendedor() != idComprador){


                        Optional<vendaModel> validaExistenciaNegociacao = vRepository.findByIdCompradorAndIdOfertaAndVendaStatus(idComprador, idOferta, vendaNegociacaoStatusEnum.EM_ANDAMENTO.getValue());
                    
                    if(validaExistenciaNegociacao.isEmpty()){
                        vendaModel vModel = new vendaModel();
                        vModel.setIdComprador(idComprador);
                        vModel.setIdOferta(idOferta);
                        vModel.setQtd_comprada(qtd);
                        vModel.setValor_transacao(validaExistenciaOferta.get().getvlUnMedida(), qtd);
                        vModel.setIdVendedor(validaExistenciaOferta.get().getIdVendedor());
    
                        validaExistenciaOferta.get().setQtdDisponivel(validaExistenciaOferta.get().getQtdDisponivel() - qtd);
                        
                         //Adicionando Contato ao Chat

                         chatFirebaseService.addContact(idComprador, validaExistenciaOferta.get().getIdVendedor());

                         //salvando venda
                        oRepository.save(validaExistenciaOferta.get());
                        vRepository.save(vModel);

                        Optional<vendaModel> vModelNeg = vRepository.findByIdCompradorAndIdOfertaAndVendaStatus(idComprador, idOferta, "PROCESSO");
                        nService.comecaNegociacao(vModelNeg.get().getId(), qtd);
                        produtoModel produto = pRepository.findById(validaExistenciaOferta.get().getIdProduto()).get();
                       
                        userModel Vendedor = uService.recuperaUsuariobyId(validaExistenciaOferta.get().getIdVendedor()).get();
                        userModel Comprador = uService.recuperaUsuariobyId(idComprador).get();
                        String message = "Ola, "+ Vendedor.getNome().toString()+". \r\nO nosso parceiro "+Comprador.getNome().toString()+" demonstrou interesse na sua oferta de: *"+produto.getNome().toString()+"* :)\r\n\r\nAcesse o aplicativo e de continuidade ao processo de venda\r\n\r\n*Link do Aplicativo:* "+appLink+"\r\n\r\nAtenciosamente,\r\n*Equipe Ceasa Digital*"; 
                           
                         System.out.println(message);
                       wService.setNumber(phonePrefix+Vendedor.getTelefone());
                       wService.setMessage(message);
                       wService.sendMessage();


                     

                        //ENVIAR MENSAGEM DE INICIO DE NEGOCIACAO
                        return vendaResponsesEnum.vCadastrado.getResponseObject();                       

                    }else{

                        return negociacaoResponsesEnum.nJaExistente.getResponseObject();

                    }




                    }else{

                        return vendaResponsesEnum.vFraude.getResponseObject();

                    }

                    
                   
                   

                }else{

                    return vendaResponsesEnum.qtd_indisponivel.getResponseObject();

                }

                

            }else{

                return vendaResponsesEnum.v_Nencontrado.getResponseObject();
            }

            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return vendaResponsesEnum.vProblem.getResponseObject();

        }
    

    }

    public List<vendaModel> recuperaVendas() {

        List<vendaModel> vendas= vRepository.findAll();

        return vendas;

    }

    public List<Optional<vendaModel>>  recuperaVendasVendedor(int idVendedor) {
       List<Optional<vendaModel>> validaExistenciaVenda = vRepository.findByidVendedor(idVendedor);
       

        return validaExistenciaVenda;

    }
    public List<Optional<vendaModel>>  recuperaComprasComprador(int idComprador) {
        List<Optional<vendaModel>> validaExistenciaCompra = vRepository.findByidComprador(idComprador);
       

        return validaExistenciaCompra;

    }

    public httpResponses processoCancelamento(int idVenda, int idUsuario) {

        
       
        return nService.processoCancelamento(idVenda, idUsuario);


    }

    public httpResponses processoConclusao(int idVenda, int idUsuario){

       return nService.processoConclusao(idVenda, idUsuario);
    }
   
            
      
}
