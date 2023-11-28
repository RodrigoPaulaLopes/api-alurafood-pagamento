package br.com.alurafood.pagamentos.service;

import br.com.alurafood.pagamentos.dtos.PagamentoDto;
import br.com.alurafood.pagamentos.model.Pagamento;
import br.com.alurafood.pagamentos.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;


    public Page<PagamentoDto> findAll(Pageable paginacao){
            return repository.findAll(paginacao).map(PagamentoDto::new);
    }

    public PagamentoDto findById(Long id){
        return new PagamentoDto(repository.getReferenceById(id));
    }

    public PagamentoDto create(PagamentoDto pagamentoDto){
        var pagamento = new Pagamento(pagamentoDto);
        return new PagamentoDto(pagamento);
    }

    public PagamentoDto update(PagamentoDto pagamentoDto){
        var pagamento = new Pagamento(pagamentoDto);
        return new PagamentoDto(pagamento);
    }

    public void excluir(Long id){

        var pagamento  = repository.getReferenceById(id);
        repository.delete(pagamento);
    }

}
