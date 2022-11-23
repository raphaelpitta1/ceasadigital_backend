package com.ceasa.digital.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceasa.digital.Enums.negociacaoResponsesEnum;
import com.ceasa.digital.Enums.vendaNegociacaoStatusEnum;
import com.ceasa.digital.Enums.negociacaoSubStatusEnum;
import com.ceasa.digital.Model.negociacaoModel;
import com.ceasa.digital.Model.vendaModel;
import com.ceasa.digital.Repository.negociacaoRepository;
import com.ceasa.digital.Repository.vendaRepository;

@Service
public class negociacaoService {
    @Autowired
    negociacaoRepository nRepository;
    @Autowired

    vendaRepository vRepository;

    @Autowired
    userService uService;



    public void comecaNegociacao(int idVenda, int Qtd_comprada){
        
        
        negociacaoModel nModel = new  negociacaoModel();

        nModel.setIdVenda(idVenda);
        nModel.setQtd_comprada(Qtd_comprada);
        nModel.setstatusNegociacao(vendaNegociacaoStatusEnum.EM_ANDAMENTO.getValue());
        nRepository.save(nModel);
        
    }


    public negociacaoModel recuperaNegociacao(int idVenda){


        return recuperaNegociacao(idVenda);

    }



    public httpResponses processoCancelamento(int idVenda, int idUsuario){
    vendaModel objVenda = new vendaModel();
    negociacaoModel objNegociacao = new negociacaoModel();


        Optional<vendaModel> validaExistenciaVenda = vRepository.findById(idVenda);
        if(!validaExistenciaVenda.isEmpty() && (validaExistenciaVenda.get().getIdComprador() == idUsuario || validaExistenciaVenda.get().getIdVendedor() == idUsuario)){
          
            Optional<negociacaoModel> verificaNegociacao = nRepository.findByidVenda(idVenda);
            objVenda = validaExistenciaVenda.get();
            objNegociacao = verificaNegociacao.get();

            if(!verificaNegociacao.get().getStatusNegociacao().equals(vendaNegociacaoStatusEnum.EM_ANDAMENTO.getValue())){

                if(verificaNegociacao.get().getStatusNegociacao().equals(vendaNegociacaoStatusEnum.CANCELADO.getValue())){

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
                           
                            
                            nRepository.save(objNegociacao);
                            validaExistenciaVenda.get().setVendaStatus(vendaNegociacaoStatusEnum.CANCELADO.getValue());
                            
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
                    
                    nRepository.save(verificaNegociacao.get());
                    vRepository.save(objVenda);
                    
                    return negociacaoResponsesEnum.nDesativado.getResponseObject();

                }


            }

        

        }else{

            if(validaExistenciaVenda.isEmpty()){

                return negociacaoResponsesEnum.n_Nencontrado.getResponseObject();
            }
           
            if(nRepository.findByidVenda(idVenda).get().getStatusNegociacao().equalsIgnoreCase(vendaNegociacaoStatusEnum.CANCELADO.getValue())){
        

                return negociacaoResponsesEnum.nCancelado.getResponseObject();
         }

            if(nRepository.findByidVenda(idVenda).get().getStatusNegociacao().equalsIgnoreCase(vendaNegociacaoStatusEnum.CONCLUIDO.getValue())){

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
        
                    if(!verificaNegociacao.get().getStatusNegociacao().equals(vendaNegociacaoStatusEnum.EM_ANDAMENTO.getValue())){
        
                        if(verificaNegociacao.get().getStatusNegociacao().equals(vendaNegociacaoStatusEnum.CANCELADO.getValue())){
        
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
                                    return negociacaoResponsesEnum.nConcluida.getResponseObject();
        
                                }
        
        
                    }else{
        
                        verificaNegociacao.get().setAprovacao_vendedor(true);
        
                        if(verificaNegociacao.get().isAprovacao_comprador() == false){
        
                            objNegociacao.setSubStatusNegociacao(negociacaoSubStatusEnum.AGUARDANDO_APROVACAO_CONCLUSAO_Comprador.getValue());
                            
                            nRepository.save(verificaNegociacao.get());
                            
                            return negociacaoResponsesEnum.nDesativadoPendenteComprador.getResponseObject();
                        }
        
        
                        if(verificaNegociacao.get().isAprovacao_vendedor() == true){
        
                            objNegociacao.setSubStatusNegociacao(negociacaoSubStatusEnum.CONCLUIDO.getValue());
                            objNegociacao.setstatusNegociacao(vendaNegociacaoStatusEnum.CONCLUIDO.getValue());
                           
                            
                            
                            objVenda.setVendaStatus(vendaNegociacaoStatusEnum.CONCLUIDO.getValue());
                            
                            nRepository.save(verificaNegociacao.get());
                            vRepository.save(objVenda);
                            
                            return negociacaoResponsesEnum.nJaConcluida.getResponseObject();
        
                        }
        
        
                    }
        
                
        
                }else{
        
                    if(validaExistenciaVenda.isEmpty()){
        
                        return negociacaoResponsesEnum.n_Nencontrado.getResponseObject();
                    }
                   
                    if(nRepository.findByidVenda(idVenda).get().getStatusNegociacao().equalsIgnoreCase(vendaNegociacaoStatusEnum.CANCELADO.getValue())){
                
        
                        return negociacaoResponsesEnum.nCancelado.getResponseObject();
                 }
        
                    if(nRepository.findByidVenda(idVenda).get().getStatusNegociacao().equalsIgnoreCase(vendaNegociacaoStatusEnum.CONCLUIDO.getValue())){
        
                       return negociacaoResponsesEnum.nJaConcluida.getResponseObject();
        
                    }
        
                   
                    return negociacaoResponsesEnum.n_Nencontrado.getResponseObject();
        
                
        
                    }
        
        
                return null;
        
                  
        
                }
                
       
       
    }

