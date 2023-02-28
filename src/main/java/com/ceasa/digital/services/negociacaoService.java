package com.ceasa.digital.services;

import java.net.URL;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ceasa.digital.Enums.negociacaoResponsesEnum;
import com.ceasa.digital.Enums.vendaNegociacaoStatusEnum;
import com.ceasa.digital.Enums.negociacaoSubStatusEnum;
import com.ceasa.digital.Model.negociacaoModel;
import com.ceasa.digital.Model.ofertaModel;
import com.ceasa.digital.Model.produtoModel;
import com.ceasa.digital.Model.userModel;
import com.ceasa.digital.Model.vendaModel;
import com.ceasa.digital.Repository.negociacaoRepository;
import com.ceasa.digital.Repository.ofertaRepository;
import com.ceasa.digital.Repository.produtoRepository;
import com.ceasa.digital.Repository.vendaRepository;


@Service
public class negociacaoService {
    @Autowired
    negociacaoRepository nRepository;
    @Autowired

    vendaRepository vRepository;

    @Autowired
    userService uService;

    @Autowired
    ofertaRepository oRepository;
    @Autowired
    whatasappService wService;
    @Autowired
    ofertaRepository ofertaRepository;
    @Autowired
    vendaRepository vrepository;
    @Autowired
    produtoRepository pRepository;
    @Value("${phone.prefix}")
    private String phonePrefix;
    @Value("${app.link}")
    private URL appLink;

    public void comecaNegociacao(int idVenda, int Qtd_comprada){
        
        
        negociacaoModel nModel = new  negociacaoModel();

        nModel.setIdVenda(idVenda);
        nModel.setQtd_comprada(Qtd_comprada);
        nModel.setstatusNegociacao(vendaNegociacaoStatusEnum.EM_ANDAMENTO.getValue());
        
        nRepository.save(nModel);

         

    }


    public Optional<negociacaoModel> recuperaNegociacao(int idVenda){
       
       
        return nRepository.findByidVenda(idVenda);

    }



    public httpResponses processoCancelamento(int idVenda, int idUsuario){
    vendaModel objVenda = new vendaModel();
    negociacaoModel objNegociacao = new negociacaoModel();


        Optional<vendaModel> validaExistenciaVenda = vRepository.findById(idVenda);
        if(!validaExistenciaVenda.isEmpty() && (validaExistenciaVenda.get().getIdComprador() == idUsuario || validaExistenciaVenda.get().getIdVendedor() == idUsuario)){
          
            Optional<negociacaoModel> verificaNegociacao = nRepository.findByidVenda(idVenda);
            objVenda = validaExistenciaVenda.get();
            objNegociacao = verificaNegociacao.get();

            if(!verificaNegociacao.get().getstatusNegociacao().equals(vendaNegociacaoStatusEnum.EM_ANDAMENTO.getValue())){

                if(verificaNegociacao.get().getstatusNegociacao().equals(vendaNegociacaoStatusEnum.CANCELADO.getValue())){

                    return negociacaoResponsesEnum.nCancelado.getResponseObject();
                }else{

                    return negociacaoResponsesEnum.nJaConcluida.getResponseObject();
                }

                

            }



            if(validaExistenciaVenda.get().getIdComprador() == idUsuario){
                    verificaNegociacao.get().setAprovacao_comprador(true);

                        if(verificaNegociacao.get().isAprovacao_vendedor() == false){

                            objNegociacao.setSubStatusNegociacao(negociacaoSubStatusEnum.AGUARDANDO_APROVACAO_Vendedor_CANCELAMENTO.getValue());
                            
                            nRepository.save(objNegociacao);
                            
                            return negociacaoResponsesEnum.nDesativadoPendenteComprador.getResponseObject();
                        }


                        if(verificaNegociacao.get().isAprovacao_vendedor() == true){

                            objNegociacao.setSubStatusNegociacao(negociacaoSubStatusEnum.CANCELADO.getValue());
                            objNegociacao.setstatusNegociacao(vendaNegociacaoStatusEnum.CANCELADO.getValue());
                           
                            
                           
                            validaExistenciaVenda.get().setVendaStatus(vendaNegociacaoStatusEnum.CANCELADO.getValue());
                            
                            userModel Vendedor = uService.recuperaUsuariobyId(validaExistenciaVenda.get().getIdVendedor()).get();
                            userModel Comprador = uService.recuperaUsuariobyId(validaExistenciaVenda.get().getIdComprador()).get();
                            produtoModel produto = pRepository.findById(ofertaRepository.findById(validaExistenciaVenda.get().getIdOferta()).get().getIdProduto()).get();
                            String message = "Ola, "+ Vendedor.getNome().toString() +". \r\n seu processo negocial com "+Comprador.getNome().toString()+" relacionado a venda de: *"+produto.getNome().toString()+"* foi cancelado com sucesso.\r\n\r\nAtenciosamente,\r\n*Equipe Ceasa Digital*"; 
                               
                             
                           wService.setNumber(phonePrefix+Vendedor.getTelefone());
                           wService.setMessage(message);
                           wService.sendMessage();
                           
                            message = "Ola, "+ Comprador.getNome().toString() +". \r\n seu processo negocial com "+Vendedor.getNome().toString()+" relacionado a compra de: *"+produto.getNome().toString()+"* foi canceladi com sucesso."+appLink+"\r\n\r\nAtenciosamente,\r\n*Equipe Ceasa Digital*"; 
                               
                             
                           wService.setNumber(phonePrefix+Comprador.getTelefone());
                           wService.setMessage(message);
                           wService.sendMessage();
                           
                           objNegociacao.setAvisa_Cancelamento(true);
                           
                          
                           nRepository.save(objNegociacao);
                           vRepository.save(validaExistenciaVenda.get());


                            return negociacaoResponsesEnum.nDesativado.getResponseObject();

                        }


            }else{

                verificaNegociacao.get().setAprovacao_vendedor(true);

                if(verificaNegociacao.get().isAprovacao_comprador() == false){

                    objNegociacao.setSubStatusNegociacao(negociacaoSubStatusEnum.AGUARDANDO_APROVACAO_Comprador_CANCELAMENTO.getValue());
                    
                    nRepository.save(verificaNegociacao.get());
                    
                    return negociacaoResponsesEnum.nDesativadoPendenteComprador.getResponseObject();
                }


                if(verificaNegociacao.get().isAprovacao_vendedor() == true){

                    objNegociacao.setSubStatusNegociacao(negociacaoSubStatusEnum.CANCELADO.getValue());
                    objNegociacao.setstatusNegociacao(vendaNegociacaoStatusEnum.CANCELADO.getValue());
                   
                    
                    
                    objVenda.setVendaStatus(vendaNegociacaoStatusEnum.CANCELADO.getValue());
                      
                    userModel Vendedor = uService.recuperaUsuariobyId(validaExistenciaVenda.get().getIdVendedor()).get();
                    userModel Comprador = uService.recuperaUsuariobyId(validaExistenciaVenda.get().getIdComprador()).get();
                    produtoModel produto = pRepository.findById(ofertaRepository.findById(validaExistenciaVenda.get().getIdOferta()).get().getIdProduto()).get();
                    String message = "Ola, "+ Vendedor.getNome().toString() +". \r\nseu processo negocial com "+Comprador.getNome().toString()+" relacionado a venda de: *"+produto.getNome().toString()+"*  foi cancelado com sucesso.\r\n\r\nAtenciosamente,\r\n*Equipe Ceasa Digital*"; 
                       
                     
                   wService.setNumber(phonePrefix+Vendedor.getTelefone());
                   wService.setMessage(message);
                   wService.sendMessage();
                   
                    message = "Ola, "+ Comprador.getNome().toString() +". \r\nseu processo negocial com "+Comprador.getNome().toString()+" relacionado a compra de: *"+produto.getNome().toString()+"* foi cancelado com sucesso.\r\n\r\nAtenciosamente,\r\n*Equipe Ceasa Digital*"; 
                       
                     
                   wService.setNumber(phonePrefix+Comprador.getTelefone());
                   wService.setMessage(message);
                   wService.sendMessage();
                   
                   objNegociacao.setAvisa_Cancelamento(true);

                    nRepository.save(verificaNegociacao.get());
                    vRepository.save(objVenda);
                    
                    return negociacaoResponsesEnum.nDesativado.getResponseObject();

                }


            }

        

        }else{

            if(validaExistenciaVenda.isEmpty()){

                return negociacaoResponsesEnum.n_Nencontrado.getResponseObject();
            }
           
            if(nRepository.findByidVenda(idVenda).get().getstatusNegociacao().equalsIgnoreCase(vendaNegociacaoStatusEnum.CANCELADO.getValue())){
        

                return negociacaoResponsesEnum.nCancelado.getResponseObject();
         }

            if(nRepository.findByidVenda(idVenda).get().getstatusNegociacao().equalsIgnoreCase(vendaNegociacaoStatusEnum.CONCLUIDO.getValue())){

               return negociacaoResponsesEnum.nJaConcluida.getResponseObject();

            }

           
            return negociacaoResponsesEnum.n_Nencontrado.getResponseObject();

        

            }


        return null;

          

        }
        
        public httpResponses processoConclusao(int idVenda, int idUsuario){
            vendaModel objVenda = new vendaModel();
            negociacaoModel objNegociacao = new negociacaoModel();
        
        
                Optional<vendaModel> validaExistenciaVenda = vRepository.findById(idVenda);
                if(!validaExistenciaVenda.isEmpty() && (validaExistenciaVenda.get().getIdComprador() == idUsuario || validaExistenciaVenda.get().getIdVendedor() == idUsuario)){
                  
                    Optional<negociacaoModel> verificaNegociacao = nRepository.findByidVenda(idVenda);
                    objVenda = validaExistenciaVenda.get();
                    objNegociacao = verificaNegociacao.get();
        
                    if(!verificaNegociacao.get().getstatusNegociacao().equals(vendaNegociacaoStatusEnum.EM_ANDAMENTO.getValue())){
        
                        if(verificaNegociacao.get().getstatusNegociacao().equals(vendaNegociacaoStatusEnum.CANCELADO.getValue())){
        
                            return negociacaoResponsesEnum.nCancelado.getResponseObject();
                        }else{
        
                            return negociacaoResponsesEnum.nJaConcluida.getResponseObject();
                        }
        
                        
        
                    }
        
        
        
                    if(validaExistenciaVenda.get().getIdComprador() == idUsuario){
                            verificaNegociacao.get().setAprovacao_comprador(true);
        
                                if(verificaNegociacao.get().isAprovacao_vendedor() == false){
        
                                    objNegociacao.setSubStatusNegociacao(negociacaoSubStatusEnum.AGUARDANDO_APROVACAO_CONCLUSAO_Vendedor.getValue());
                                    
                                    nRepository.save(objNegociacao);
                                    
                                    return negociacaoResponsesEnum.nConcluidaPendenteVendedor.getResponseObject();
                                }
        
        
                                if(verificaNegociacao.get().isAprovacao_vendedor() == true){
        
                                    objNegociacao.setSubStatusNegociacao(negociacaoSubStatusEnum.CONCLUIDO.getValue());
                                    objNegociacao.setstatusNegociacao(vendaNegociacaoStatusEnum.CONCLUIDO.getValue());
                                   
                                    
                                    nRepository.save(objNegociacao);
                                    objVenda.setVendaStatus(vendaNegociacaoStatusEnum.CONCLUIDO.getValue());
                                    
                                    vRepository.save(objVenda);

                                    userModel Vendedor = uService.recuperaUsuariobyId(validaExistenciaVenda.get().getIdVendedor()).get();
                                    userModel Comprador = uService.recuperaUsuariobyId(validaExistenciaVenda.get().getIdComprador()).get();
                                    produtoModel produto = pRepository.findById(ofertaRepository.findById(validaExistenciaVenda.get().getIdOferta()).get().getIdProduto()).get();
                                    String message = "Ola, "+ Vendedor.getNome().toString()+". \r\nseu processo negocial com "+Comprador.getNome().toString()+" relacionado a venda de: *"+produto.getNome().toString()+"* foi concluido com sucesso.\r\n\r\nAtenciosamente,\r\n*Equipe Ceasa Digital*"; 
                                       
                                    System.out.println(message);
                                     
                                   wService.setNumber(phonePrefix+Vendedor.getTelefone());
                                   wService.setMessage(message);
                                   wService.sendMessage();
                                   
                                  message = "Ola, "+ Vendedor.getNome().toString()+".\r\nseu processo negocial com "+Vendedor.getNome().toString()+" relacionado a compra de: *"+produto.getNome().toString()+"* foi concluido com sucesso.\r\n\r\nAtenciosamente,\r\n*Equipe Ceasa Digital*"; 
                                       
                                     
                                   wService.setNumber(phonePrefix+Comprador.getTelefone());
                                   wService.setMessage(message);
                                   wService.sendMessage();
                                   
                                   objNegociacao.setAvisa_ConclusaoVenda(true);
                                    
                                    return negociacaoResponsesEnum.nConcluida.getResponseObject();
        
                                }
        
        
                    }else{
        
                        verificaNegociacao.get().setAprovacao_vendedor(true);
        
                        if(verificaNegociacao.get().isAprovacao_comprador() == false){
        
                            objNegociacao.setSubStatusNegociacao(negociacaoSubStatusEnum.AGUARDANDO_APROVACAO_CONCLUSAO_Comprador.getValue());
                            
                            nRepository.save(verificaNegociacao.get());
                            
                            return negociacaoResponsesEnum.nConcluidaPendenteComprador.getResponseObject();
                        }
        
        
                        if(verificaNegociacao.get().isAprovacao_vendedor() == true){
        
                            objNegociacao.setSubStatusNegociacao(negociacaoSubStatusEnum.CONCLUIDO.getValue());
                            objNegociacao.setstatusNegociacao(vendaNegociacaoStatusEnum.CONCLUIDO.getValue());
                           
                            
                            
                            objVenda.setVendaStatus(vendaNegociacaoStatusEnum.CONCLUIDO.getValue());
                            
                            nRepository.save(verificaNegociacao.get());
                            vRepository.save(objVenda);

                            objNegociacao.setSubStatusNegociacao(negociacaoSubStatusEnum.CONCLUIDO.getValue());
                                    objNegociacao.setstatusNegociacao(vendaNegociacaoStatusEnum.CONCLUIDO.getValue());
                                   
                                    
                                    nRepository.save(objNegociacao);
                                    objVenda.setVendaStatus(vendaNegociacaoStatusEnum.CONCLUIDO.getValue());
                                    
                                    vRepository.save(objVenda);

                                    userModel Vendedor = uService.recuperaUsuariobyId(validaExistenciaVenda.get().getIdVendedor()).get();
                                    userModel Comprador = uService.recuperaUsuariobyId(validaExistenciaVenda.get().getIdComprador()).get();
                                    produtoModel produto = pRepository.findById(ofertaRepository.findById(validaExistenciaVenda.get().getIdOferta()).get().getIdProduto()).get();
                                    String message = "Ola, "+ Vendedor.getNome().toString() +". \r\nseu processo negocial com "+Comprador.getNome().toString()+" relacionado a venda de: *"+produto.getNome().toString()+"*  foi concluido com sucesso.\r\n\r\nAtenciosamente,\r\n*Equipe Ceasa Digital*"; 
                                       
                                    System.out.println(message);
                                   wService.setNumber(phonePrefix+Vendedor.getTelefone());
                                   wService.setMessage(message);
                                   wService.sendMessage();
                                   
                                    message = "Ola, "+ Comprador.getNome().toString() +". \r\nseu processo negocial com "+Vendedor.getNome().toString()+" relacionado a compra de: *"+produto.getNome().toString()+"* foi concluido com sucesso.\r\n\r\nAtenciosamente,\r\n*Equipe Ceasa Digital*"; 
                                       
                                    System.out.println(message);
                                   wService.setNumber(phonePrefix+Comprador.getTelefone());
                                   wService.setMessage(message);
                                   wService.sendMessage();
                                   
                                   objNegociacao.setAvisa_ConclusaoVenda(true);
                            
                            return negociacaoResponsesEnum.nJaConcluida.getResponseObject();
        
                        }
        
        
                    }
        
                
        
                }else{
        
                    if(validaExistenciaVenda.isEmpty()){
        
                        return negociacaoResponsesEnum.n_Nencontrado.getResponseObject();
                    }
                   
                    if(nRepository.findByidVenda(idVenda).get().getstatusNegociacao().equalsIgnoreCase(vendaNegociacaoStatusEnum.CANCELADO.getValue())){
                
        
                        return negociacaoResponsesEnum.nCancelado.getResponseObject();
                 }
        
                    if(nRepository.findByidVenda(idVenda).get().getstatusNegociacao().equalsIgnoreCase(vendaNegociacaoStatusEnum.CONCLUIDO.getValue())){
        
                       return negociacaoResponsesEnum.nJaConcluida.getResponseObject();
        
                    }
        
                   
                    return negociacaoResponsesEnum.n_Nencontrado.getResponseObject();
        
                
        
                    }
        
        
                return null;
        
                  
        
                }
                
       
       
    }

