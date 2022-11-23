package com.ceasa.digital.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.ceasa.digital.Enums.produtoResponsesEnum;
import com.ceasa.digital.Model.produtoModel;

import com.ceasa.digital.Repository.produtoRepository;

@Service
public class produtoService {

    @Autowired
    private produtoRepository pRepository;

    public httpResponses cadastrarProduto(String nome) {

        try {

            produtoModel pModel = new produtoModel();

            pModel.setNome(nome);

            Optional<produtoModel> validaExistenciaProduto = pRepository.findByNome(pModel.getNome().toString());

            if (!validaExistenciaProduto.isEmpty()) {

                return produtoResponsesEnum.pJacadastrado.getResponseObject();

            }

            pRepository.save(pModel);

            return produtoResponsesEnum.pCadastrado.getResponseObject();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return produtoResponsesEnum.pProblem.getResponseObject();

        }

    }

    public List<produtoModel> recuperaProdutos() {

        List<produtoModel> produtos = pRepository.findAll();

        return produtos;

    }

    public httpResponses deletarProduto(String nome) {

        try {

            produtoModel pModel = new produtoModel();

            pModel.setNome(nome);

            Optional<produtoModel> validaExistenciaProduto = pRepository.findByNome(pModel.getNome().toString());

            if (validaExistenciaProduto.isEmpty()) {

                return produtoResponsesEnum.p_Nencontrado.getResponseObject();

            }

            pRepository.delete(validaExistenciaProduto.get());

            return produtoResponsesEnum.pDesativado.getResponseObject();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return produtoResponsesEnum.pProblem.getResponseObject();

        }

    }

    public httpResponses atualizarProduto(String nome) {

        try {

            produtoModel pModel = new produtoModel();

            pModel.setNome(nome);

            Optional<produtoModel> validaExistenciaProduto = pRepository.findByNome(pModel.getNome().toString());

            if (validaExistenciaProduto.isEmpty()) {

                return produtoResponsesEnum.p_Nencontrado.getResponseObject();

            }

            pRepository.save(validaExistenciaProduto.get());

            return produtoResponsesEnum.pUpdate.getResponseObject();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return produtoResponsesEnum.pProblem.getResponseObject();

        }

    }

}
