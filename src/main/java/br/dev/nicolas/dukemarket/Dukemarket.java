package br.dev.nicolas.dukemarket;

import br.dev.nicolas.dukemarket.bean.Produto;
import br.dev.nicolas.dukemarket.dao.ProdutosDAO;

public class Dukemarket {

    public static void main(String[] args) {
        
        Produto pAux;
        ProdutosDAO pDAO = new ProdutosDAO();

        // =========================================================
        // 5.1 Fase 1: Incluir 3 produtos e exibir
        // =========================================================
        
        /*
        // Teste de inclusao de produto 1
        pAux = new Produto();
        pAux.setCodBarras("11111111");
        pAux.setDescricao("Mouse Logitec Bluetooth");
        pAux.setQtd(50);
        pAux.setValorCompra(80.00);
        pAux.setValorVenda(167.00);

        pDAO.create(pAux);

        // Teste de inclusao de produto 2
        pAux = new Produto();
        pAux.setCodBarras("22222222");
        pAux.setDescricao("Teclado USB Gamer");
        pAux.setQtd(80);
        pAux.setValorCompra(104.00);
        pAux.setValorVenda(220.00);

        pDAO.create(pAux);

        // Teste de inclusao de produto 3
        pAux = new Produto();
        pAux.setCodBarras("33333333");
        pAux.setDescricao("Microcomputador Dell");
        pAux.setQtd(110);
        pAux.setValorCompra(2400.00);
        pAux.setValorVenda(3200.00);

        pDAO.create(pAux);
        */
        
        
//        pDAO.delete(3);


            pAux = pDAO.getResultById(1);
            pAux.setQtd(60);
            pDAO.update(pAux);
            
            
            
        
        
        
        
        
        
        // Lista geral
        pDAO.getResults().forEach(p -> {
            System.out.println(p.toString());
        });


  
    }
}