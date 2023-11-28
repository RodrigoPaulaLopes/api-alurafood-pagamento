package br.com.alurafood.pagamentos.model;

import br.com.alurafood.pagamentos.dtos.PagamentoDto;
import br.com.alurafood.pagamentos.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "pagamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotBlank
    @Size(max=100)
    private String nome;

    @NotBlank
    @Size(max=19)
    private String numero;

    @NotBlank
    @Size(max=7)
    private String expiracao;

    @NotBlank
    @Size(max = 3, min = 3)
    private String codigo;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private Long pedidoId;

    @NotNull
    private Long formaDePagamentoId;

    public Pagamento(PagamentoDto pagamentoDto) {
        this.setNome(pagamentoDto.nome());
        this.setValor(pagamentoDto.valor());
        this.setCodigo(pagamentoDto.codigo());
        this.setExpiracao(pagamentoDto.expiracao());
        this.setNumero(pagamentoDto.numero());
        this.setStatus(Status.CRIADO);
        this.setFormaDePagamentoId(pagamentoDto.formaDePagamentoId());
        this.setPedidoId(pagamentoDto.pedidoId());

    }
}
