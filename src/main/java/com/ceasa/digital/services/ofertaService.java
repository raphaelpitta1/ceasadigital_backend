package com.ceasa.digital.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceasa.digital.Enums.ofertaResponsesEnum;
import com.ceasa.digital.Forms.atualizaOfertaForms;
import com.ceasa.digital.Model.ofertaModel;
import com.ceasa.digital.Repository.ofertaRepository;

@Service
public class ofertaService {

    @Autowired
    private ofertaRepository oRepository;

    public httpResponses cadastrarOferta(int idProduto, int idUnMedida, int idVendedor, int qtdDisponivel,
            float pesoUnMedida,
            float vlUnMedida) {

        try {

            ofertaModel oModel = new ofertaModel();

            oModel.setIdProduto(idProduto);
            oModel.setIdUnMedida(idUnMedida);
            oModel.setIdVendedor(idVendedor);
            oModel.setQtdDisponivel(qtdDisponivel);
            oModel.setVlKG(vlUnMedida, pesoUnMedida);
            oModel.setvlUnMedida(vlUnMedida);
            oModel.setPesoUnMedida(pesoUnMedida);

            List<Optional<ofertaModel>> ofertas = oRepository.findByidVendedor(idVendedor);

            if (!ofertas.isEmpty()) {

                for (int i = 0; i < ofertas.size(); i++) {

                    if (oModel.getIdProduto() == ofertas.get(i).get().getIdProduto()) {

                        return ofertaResponsesEnum.oJacadastrado.getResponseObject();

                    }
                }

                oRepository.save(oModel);
                return ofertaResponsesEnum.oCadastrado.getResponseObject();
            } else {

                oRepository.save(oModel);

                return ofertaResponsesEnum.oCadastrado.getResponseObject();
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ofertaResponsesEnum.oProblem.getResponseObject();

        }

    }

    public List<Optional<ofertaModel>> recuperaOfertasByProduto(int idProduto) {

        List<Optional<ofertaModel>> ofertas = oRepository.findByidProduto(idProduto);

        return ofertas;

    }

    public List<ofertaModel> recuperaOfertas() {

        List<ofertaModel> ofertas = oRepository.findAll();

        return ofertas;

    }

    public List<Optional<ofertaModel>> recuperaOfertasPorVendedor(int idVendedor) {

        List<Optional<ofertaModel>> ofertas = oRepository.findByidVendedor(idVendedor);

        return ofertas;

    }

    public httpResponses encerrarOferta(int id) {

        try {

            Optional<ofertaModel> validaExistenciaOferta = oRepository.findById(id);

            if (validaExistenciaOferta.isEmpty()) {

                return ofertaResponsesEnum.o_Nencontrado.getResponseObject();

            }

            if (validaExistenciaOferta.get().isStatus()) {
                ofertaModel oModel = validaExistenciaOferta.get();
                oModel.setStatus(false);
                oRepository.save(oModel);
                return ofertaResponsesEnum.oDesativado.getResponseObject();

            } else {
                return ofertaResponsesEnum.oJaDesativado.getResponseObject();

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ofertaResponsesEnum.oProblem.getResponseObject();

        }

    }

    public httpResponses ativarOferta(int id) {

        try {

            Optional<ofertaModel> validaExistenciaOferta = oRepository.findById(id);

            if (validaExistenciaOferta.isEmpty()) {

                return ofertaResponsesEnum.o_Nencontrado.getResponseObject();

            }

            if (!validaExistenciaOferta.get().isStatus()) {
                ofertaModel oModel = validaExistenciaOferta.get();
                oModel.setStatus(true);
                oRepository.save(oModel);
                return ofertaResponsesEnum.oAtivada.getResponseObject();

            } else {
                return ofertaResponsesEnum.oJaAtivada.getResponseObject();

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ofertaResponsesEnum.oProblem.getResponseObject();

        }

    }

    public httpResponses atualizarOferta(atualizaOfertaForms atualizaOfertaForms) {

        try {

            Optional<ofertaModel> validaExistenciaOferta = oRepository.findById(atualizaOfertaForms.getId());

            if (validaExistenciaOferta.isEmpty()) {

                return ofertaResponsesEnum.o_Nencontrado.getResponseObject();

            }

            if (validaExistenciaOferta.get().isStatus()) {

                ofertaModel oModel = validaExistenciaOferta.get();
                oModel.setQtdDisponivel(atualizaOfertaForms.getQtd());
                oModel.setvlUnMedida(atualizaOfertaForms.getVlUnMedida());

                oRepository.save(oModel);

                return ofertaResponsesEnum.oUpdate.getResponseObject();
            } else {

                return ofertaResponsesEnum.oDesativadoAcao.getResponseObject();

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ofertaResponsesEnum.oProblem.getResponseObject();

        }

    }

}